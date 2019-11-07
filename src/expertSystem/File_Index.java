package expertSystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
/**
 * Clase File_index encargada de generar el archivo indice conforme se agregan registros al archivo maestro
 * (Clase generica puede usarse para generar cualquier indice de cualquier archivo maestro).
 * Hereda de File
 * Tiene relación con la clase TDA_Index ademas de generar un Arbol en RAM para la busqueda de informacion (llave de un arhcivo maestro y saber su posicion logica)
 * Métodos escribir, leer (Secuencial) y eliminar
 */

public class File_Index extends File {
    File oFILE=new File();
    public Tree oTREE = new Tree();
    TDA_Index oTDA_I=null;

    public void write(TDA_Index tda_index,String index,long position) throws IOException //Escribir un nuevo registro en el archivo index (Se recibe el TDA_Index, nombre del archivo indice y la posicion logica)
    {
        RandomAccessFile file=oFILE.openFile(index,"rw");
            oFILE.file.seek((position==0)?oFILE.file.length():position); //final position of file Index
            oFILE.file.writeInt(tda_index.key); //write INT index
            oFILE.file.writeInt(tda_index.position); //write INT position
            oFILE.closeFile(); //close file
    }
    public void readSequentially(String index) throws IOException //Metodo que lee archivo indice de manera secuencial (Recibe el nombre del archivo a leer)
    {
        long ap_actual,ap_final;
        RandomAccessFile file=oFILE.openFile(index,"r");
            while ((ap_actual=oFILE.file.getFilePointer())!=(ap_final=oFILE.file.length()))
            {
                oTDA_I=new TDA_Index(oFILE.file.readInt(),oFILE.file.readInt());
                oTREE.insert(oTDA_I);
            }
            oFILE.closeFile();
    }
    public void delete(int key,String index) throws IOException //Metodo para eliminar un registro del archivo indice
    {
        long ap_actual,ap_final, position;
        boolean b=true;
        RandomAccessFile file=oFILE.openFile(index,"r");
            while (((ap_actual=oFILE.file.getFilePointer())!=(ap_final=oFILE.file.length()))&& b)
            {
                oTDA_I=new TDA_Index(oFILE.file.readInt(),oFILE.file.readInt());
                if(oTDA_I.getKey()==key) {
                    b=false;
                }
            }
            if(!b)
            {
                position=(oFILE.file.getFilePointer() - 8);
                oFILE.closeFile();
                oTDA_I=new TDA_Index(0,0);
                write(oTDA_I,index,position);
            }
    }
}
