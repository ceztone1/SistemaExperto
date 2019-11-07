package expertSystem;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
/*
* Clase file, controla abrir y cerrar un archivo, ya sea de texto(txt) o binario (RandomAccessFile). Disponible para escribir y leer
* */
public class File {
    RandomAccessFile file;
    BufferedWriter bw=null;
    BufferedReader br=null;
    java.io.File fileTXT;
    public RandomAccessFile openFile(String name,String action) //Metodo para abrir archivo del tipo binario (RandomAccessFile)
    {
     try {
         file=new RandomAccessFile(name,action);
         System.out.println("open file "+file);
         return file;
     }
     catch (Exception e)
     {
         System.out.println(e);
     }
        return null;
    }
    public BufferedReader openFileBR(String name) //Metodo para abrir archivo txt(Lectura)
    {
        try {
            fileTXT=new java.io.File(name);
            System.out.println("file is  "+fileTXT.getName());
            if(!fileTXT.exists()){
                fileTXT.createNewFile();
            }
            br = new BufferedReader(new FileReader(fileTXT));
            return br;
        }
        catch (IOException e)
        {
            System.out.println("Error to read");
            return null;
        }
    }
    public BufferedWriter openFileBW(String name) //Metodo para escribir en archivo txt (Escritura)
    {
        try {
            fileTXT=new java.io.File(name);
            System.out.println("file is  "+fileTXT.getName());
            if(!fileTXT.exists()){
                fileTXT.createNewFile();
            }
            bw=new BufferedWriter(new FileWriter(fileTXT,true));
            return bw;
        }
        catch (IOException e)
        {
            System.out.println("Error to write ");
            return null;
        }
    }

    public boolean closeFile() throws IOException //Metodo para cerrar archivo (RandomAccessFile)
    {
        file.close();
        return true;
    }
}
