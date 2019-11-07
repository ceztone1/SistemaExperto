package expertSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.RandomAccessFile;
/*
* Clase File_Master programada para agregar, mostrar, actualizar o eliminar un registro del archivo maestro
* Hereda de File
* Utiliza la clase:
 *  TDA_Index
 *  File_domains
 *  File_predicates
* Metodos escribir, mostrar, leer (Secuencia y aleatorio), eliminar
* */
/*INFORMATION OF THE REGISTER

4 bytes FOR int
6 Strings (antecedent) of 40 characters
1 string (consequent) de 40 characters
1 boolean (gui) normally is  false
row size = 565 bytes

* */


public class File_MasterKnowledgeBase extends File {
    TDA_Index oTDA_I;
    long ap_actual,ap_final;
    public File_Index oFILE_I =new File_Index();
    File oFILE=new File();
    File_Predicates oFILE_P=new File_Predicates();
    File_domains oFILE_D=new File_domains();
    ObservableList<TDA_KnowledgeBase> rows = FXCollections.observableArrayList();

    public void write (int key,String []c,boolean gui) throws IOException //Metodo para escribir en el archivo maestro (Recibe llave, antedecentes (6) y consecuentes(1) y un valor booleano) Al finalizar la insercion dentro del archivo Master, se inserta en el archivo index con la posicion logica que le toca (Haciendo el conteo de cada registro) e Index se encargar de agregarlo al arbol binario
    {
        StringBuffer buffer=null;
        RandomAccessFile file=oFILE.openFile("MasterKnowledgeBase.bin","rw");
                long size=file.length();
                oFILE.file.seek(size);
                oFILE.file.writeInt(key);
                int cou=c.length;
                long position=(size/565)+1;
                if(cou<=7 && cou>1) {
                    for (int i = 0; i < 6; i++) {
                        System.out.println("el valor es "+c[i]);
                            addDomains(c[i]);  //agregar los dominios existentes de los predicados
                            addPredicates(c[i]); //agregar los predicados al archivo predicates
                        buffer=new StringBuffer((i<(cou-1)?(c[i]==null?" ":c[i]):" "));
                        buffer.setLength(40);
                        oFILE.file.writeChars(buffer.toString());
                    }
                    addDomains(c[cou-1]);
                    buffer=new StringBuffer(c[cou-1]==null?" ":c[cou-1]);
                    buffer.setLength(40);
                    oFILE.file.writeChars(buffer.toString());
                }
                oFILE.file.writeBoolean(gui);
                oTDA_I=new TDA_Index(key,(int)(position));
                oFILE_I.write(oTDA_I,"indexMaster.bin",0);
                oFILE.closeFile();
    }
    public void addPredicates(String predicate) throws IOException //agrega los predicados que comienzan la red de inferencia, es decir, todos aquellos son antecedentes y que no se pueden inferir
    {
        if (predicate!=null)
        {
            if(rows!=null)
            {
                int i=0;
                boolean ban=true;
                while(i<rows.size() && ban)
                {
                    if(rows.get(i).getCons().contains(predicate))
                        ban=false;
                    i++;
                }
                if (rows.size()==0 || ban)
                {
                    TDA_Predicates tda_pAUX;
                    tda_pAUX=oFILE_P.search(predicate);
                    if (tda_pAUX==null)
                        oFILE_P.write(new TDA_Predicates(predicate,null));
                }
            }
        }
    }
    public void addDomains(String predicate) //agrega los dominios encontrados de cada premisa al archivo de dominios (si ya existen NO los agrega)
            /*Ejemplo:
            *  tiene(p,e) Primero separa por ( nos queda -->  "p,e)" despues separa por ')' -->  p,e y finalmente por ',' quedando solo los dominios
            *  */
    {
        String []c;
        if(predicate!=null){
            c=predicate.split("\\(");
            try {
                c=c[1].split("\\)");
                c=c[0].split(",");
                for (int i = 0; i <c.length ; i++) {
                    TDA_Domains tda_domains=oFILE_D.search(c[i]);
                    if (tda_domains==null) //no existe hat que agregarlo
                    {
                        oFILE_D.write(new TDA_Domains(c[i],null));
                    }
                }
            }
            catch (Exception e) {
                System.out.println("error to separate  "+e);
            }
        }
    }
    public ObservableList<TDA_KnowledgeBase> readSequentially() throws IOException //Metodo para leer secuencial (de inicio a fin) -> usado para mostrar registros en tabla
    {
        TDA_KnowledgeBase tda_kb;
        RandomAccessFile file=oFILE.openFile("MasterKnowledgeBase.bin","r");
            while ((ap_actual=oFILE.file.getFilePointer())!=(ap_final=oFILE.file.length()))
            {
                tda_kb=new TDA_KnowledgeBase(oFILE.file.readInt(),readChars(),readChars(),readChars(),readChars(),readChars(),readChars(),readChars(),oFILE.file.readBoolean());
                if(tda_kb.getKey()!=0)
                    rows.add(tda_kb);
            }
            oFILE.closeFile();
        return rows;
    }
    public String readChars() throws IOException //Metodo generico para leer 40 chars del archivo
    {
        char ante [] = new char[40],temp;
        for (int c = 0; c < ante.length; c++) {
            temp = oFILE.file.readChar();
            ante[c] = temp;        }
        new String(ante).replace('\0',' ');
        return String.valueOf(ante);
    }
    public TDA_KnowledgeBase readSecRandom(int position) throws IOException //Metodo para leer aleatoriamente un registro del archivo maestro (Recibe la posicion encontrada el index pero que se encuentra cargada en RAM en un arbol binario)
    {
        long lreg,desplaza;
        TDA_KnowledgeBase tda_kb=null;
        RandomAccessFile file=oFILE.openFile("MasterKnowledgeBase.bin","r");
            tda_kb=new TDA_KnowledgeBase(oFILE.file.readInt(),readChars(),readChars(),readChars(),readChars(),readChars(),readChars(),readChars(),oFILE.file.readBoolean());
            lreg=oFILE.file.getFilePointer();
            desplaza=(position-1)*lreg;
            oFILE.file.seek(desplaza);
            tda_kb=new TDA_KnowledgeBase(oFILE.file.readInt(),readChars(),readChars(),readChars(),readChars(),readChars(),readChars(),readChars(),oFILE.file.readBoolean());
            oFILE.closeFile();
        return tda_kb;
    }
    public void delete(int position) throws IOException //Metodo eliminar, quita el registro deseado del archivo maestro (Recibe posicion, encontrada en el archivo indice que genera un arbol binario en RAM)
    {
        long lreg,desplaza;
        TDA_KnowledgeBase tda_kb;
        StringBuffer buffer;
        file=oFILE.openFile("MasterKnowledgeBase.bin","r");
            tda_kb=new TDA_KnowledgeBase(oFILE.file.readInt(),readChars(),readChars(),readChars(),readChars(),readChars(),readChars(),readChars(),oFILE.file.readBoolean());
            lreg=oFILE.file.getFilePointer();
            desplaza=(position-1)*lreg;
            oFILE.closeFile();
            file=oFILE.openFile("MasterKnowledgeBase.bin","rw");
                oFILE.file.seek(desplaza);
                oFILE.file.writeInt(0);
                for (int i = 0; i < 7; i++) {
                    buffer=new StringBuffer("                                        ");
                    buffer.setLength(40);
                    oFILE.file.writeChars(buffer.toString());
                }
                oFILE.closeFile();
    }
    public Boolean update(Node node,String []c,boolean gui) throws IOException //Metodo que actualizar la informacion de un registro
    {
        long lreg,desplaza;
        TDA_KnowledgeBase tda_kb;
        StringBuffer buffer;
        file=oFILE.openFile("MasterKnowledgeBase.bin","r");
            tda_kb=new TDA_KnowledgeBase(oFILE.file.readInt(),readChars(),readChars(),readChars(),readChars(),readChars(),readChars(),readChars(),oFILE.file.readBoolean());
            lreg=oFILE.file.getFilePointer();
            desplaza=(node.info.getPosition()-1)*lreg;
            oFILE.closeFile();
            file=oFILE.openFile("MasterKnowledgeBase.bin","rw");
                oFILE.file.seek(desplaza);
                oFILE.file.writeInt(node.info.getKey());
                for (int i = 0; i < 7; i++) {
                    buffer=new StringBuffer("                                        ");
                    buffer.setLength(40);
                    oFILE.file.writeChars(buffer.toString());
                }
                oFILE.file.seek(desplaza+4);
                int cou=c.length;
                if(cou<=7 && cou>1) {
                    for (int i = 0; i < 6; i++) {
                        addDomains(c[i]);  //agregar los dominios existentes de los predicados
                        addPredicates(c[i]); //agregar los predicados al archivo predicates
                        buffer=new StringBuffer((i<(cou-1)?(c[i]==null?" ":c[i]):" "));
                        buffer.setLength(40);
                        oFILE.file.writeChars(buffer.toString());
                    }
                    addDomains(c[cou-1]);
                    buffer=new StringBuffer(c[cou-1]==null?" ":c[cou-1]);
                    buffer.setLength(40);
                    oFILE.file.writeChars(buffer.toString());
                }
                oFILE.file.writeBoolean(gui);
                oFILE.closeFile();
                return true;
    }
}
