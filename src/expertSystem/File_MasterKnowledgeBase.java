package expertSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.io.IOException;
/*INFORMATION OF THE REGISTER

4 bytes FOR int
6 Strings (antecedent) of 40 characters
1 string (consequent) de 40 characters
1 boolean (gui) normally is  false
row size = 565 bytes

* */
public class File_MasterKnowledgeBase extends File {
    TDA_Index oTDA_I;
    public File_Index oFILE_I =new File_Index();
    File oFILE=new File();
    ObservableList<TDA_KnowledgeBase> registros = FXCollections.observableArrayList();

    public void write (int key,String []c,boolean gui) throws IOException {

        StringBuffer buffer=null;
            if(oFILE.openFile("MasterKnowledgeBase.bin","rw"))
            {
                long size=oFILE.file.length();
                oFILE.file.seek(size);
                oFILE.file.writeInt(key);
               // String c[]=clause.split("V");
                int cou=c.length;
                long position=(size/565)+1;
                if(cou<=7 && cou>1) {
                    for (int i = 0; i < 6; i++) {
                        //System.out.println("si qui  "+c[i]);
                        buffer=new StringBuffer((i<(cou-1)?(c[i]==null?" ":c[i]):" "));
                        buffer.setLength(40);
                        oFILE.file.writeChars(buffer.toString());
                    }
                    //System.out.println("si aqui   d  "+c[cou-1]);
                    buffer=new StringBuffer(c[cou-1]==null?" ":c[cou-1]);
                    buffer.setLength(40);
                    oFILE.file.writeChars(buffer.toString());
                }
               /* else
                {
                    for (int i = 0; i <7 ; i++) {
                        buffer=new StringBuffer(i==0?c[0]:" ");
                        buffer.setLength(40);
                        oFILE.file.writeChars(buffer.toString());
                    }
                }*/
                oFILE.file.writeBoolean(gui);
                //System.out.println("Escribi gui   "+gui);
                oTDA_I=new TDA_Index(key,(int)(position));
                oFILE_I.write(oTDA_I,"indexMaster.bin",0);
                oFILE.closeFile();
            }
    }
    long ap_actual,ap_final;

    public ObservableList<TDA_KnowledgeBase> readSequentially() throws IOException {
        TDA_KnowledgeBase tda_kb;
        if(oFILE.openFile("MasterKnowledgeBase.bin","r"))
        {
            while ((ap_actual=oFILE.file.getFilePointer())!=(ap_final=oFILE.file.length()))
            {
                tda_kb=new TDA_KnowledgeBase(oFILE.file.readInt(),readChars(),readChars(),readChars(),readChars(),readChars(),readChars(),readChars(),oFILE.file.readBoolean());
                if(tda_kb.getKey()!=0)
                    registros.add(tda_kb);
            }
            oFILE.closeFile();
        }
        return registros;
    }

    public String readChars() throws IOException {
        char ante [] = new char[40],temp;
        for (int c = 0; c < ante.length; c++) {
            temp = oFILE.file.readChar();
            ante[c] = temp;        }
        new String(ante).replace('\0',' ');
        return String.valueOf(ante);
    }

    public TDA_KnowledgeBase readSecRandom(int position) throws IOException {
        long lreg,desplaza;
        TDA_KnowledgeBase tda_kb=null;
        if(oFILE.openFile("MasterKnowledgeBase.bin","r"))
        {
            tda_kb=new TDA_KnowledgeBase(oFILE.file.readInt(),readChars(),readChars(),readChars(),readChars(),readChars(),readChars(),readChars(),oFILE.file.readBoolean());
            lreg=oFILE.file.getFilePointer();
            desplaza=(position-1)*lreg;
            oFILE.file.seek(desplaza);
            tda_kb=new TDA_KnowledgeBase(oFILE.file.readInt(),readChars(),readChars(),readChars(),readChars(),readChars(),readChars(),readChars(),oFILE.file.readBoolean());
            //System.out.println("EL valor buscado en la posicion "+position+" es: "+tda_kb.getKey()+"  a  "+tda_kb.getAnt1()+"  c "+tda_kb.getCons());
            oFILE.closeFile();
        }
        return tda_kb;
    }
    public void delete(int position) throws IOException {
        long lreg,desplaza;
        TDA_KnowledgeBase tda_kb;
        StringBuffer buffer;
        if(oFILE.openFile("MasterKnowledgeBase.bin","r"))
        {
            tda_kb=new TDA_KnowledgeBase(oFILE.file.readInt(),readChars(),readChars(),readChars(),readChars(),readChars(),readChars(),readChars(),oFILE.file.readBoolean());
            lreg=oFILE.file.getFilePointer();
            desplaza=(position-1)*lreg;
            oFILE.closeFile();
            if (oFILE.openFile("MasterKnowledgeBase.bin","rw"))
            {
                oFILE.file.seek(desplaza);
                oFILE.file.writeInt(0);
                for (int i = 0; i < 7; i++) {
                    buffer=new StringBuffer("                                        ");
                    buffer.setLength(40);
                    oFILE.file.writeChars(buffer.toString());
                }
                oFILE.closeFile();
            }
        }
    }
    public Boolean update(Node node,String []c,boolean gui) throws IOException {
        long lreg,desplaza;
        TDA_KnowledgeBase tda_kb;
        StringBuffer buffer;
        if(oFILE.openFile("MasterKnowledgeBase.bin","r"))
        {
            tda_kb=new TDA_KnowledgeBase(oFILE.file.readInt(),readChars(),readChars(),readChars(),readChars(),readChars(),readChars(),readChars(),oFILE.file.readBoolean());
            lreg=oFILE.file.getFilePointer();
            desplaza=(node.info.getPosition()-1)*lreg;
            oFILE.closeFile();
            if (oFILE.openFile("MasterKnowledgeBase.bin","rw"))
            {
                oFILE.file.seek(desplaza);
                oFILE.file.writeInt(node.info.getKey());
                for (int i = 0; i < 7; i++) {
                    buffer=new StringBuffer("                                        ");
                    buffer.setLength(40);
                    oFILE.file.writeChars(buffer.toString());
                }
                oFILE.file.seek(desplaza+4);
                //String c[]=clause.split("∧");
                int cou=c.length;
                if(cou<=7 && cou>1) {
                    for (int i = 0; i < 6; i++) {
                        buffer=new StringBuffer((i<(cou-1)?(c[i]==null?" ":c[i]):" "));
                        //buffer=new StringBuffer((i<(cou-1)?c[i]:" "));
                        buffer.setLength(40);
                        oFILE.file.writeChars(buffer.toString());
                    }
                    buffer=new StringBuffer(c[cou-1]==null?" ":c[cou-1]);
                    buffer.setLength(40);
                    oFILE.file.writeChars(buffer.toString());
                }
               /* else
                {
                    for (int i = 0; i <7 ; i++) {
                        buffer=new StringBuffer(i==0?c:" ");
                        buffer.setLength(40);
                        oFILE.file.writeChars(buffer.toString());
                    }
                }*/
                oFILE.file.writeBoolean(gui);
                oFILE.closeFile();
                return true;
            }
        }
        return false;
    }
}
