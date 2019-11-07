package expertSystem;



//import java.io.File;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;

public class File {
    RandomAccessFile file;
    BufferedWriter bw=null;
    BufferedReader br=null;
    java.io.File fileTXT;


    public RandomAccessFile openFile(String name,String action) throws FileNotFoundException {
     try {
         file=new RandomAccessFile(name,action);
         System.out.println("si se abrio "+file);

         return file;
     }
     catch (Exception e)
     {
         System.out.println(e);
     }
        return null;
    }
    public BufferedReader openFileBR(String name){
        try {
            fileTXT=new java.io.File(name);
            System.out.println("file r es  "+fileTXT.getName());
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

    public BufferedWriter openFileBW(String name) {
        try {
            fileTXT=new java.io.File(name);
            System.out.println("file w es  "+fileTXT.getName());
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

    /*public boolean openFileTxt(String name,int option) throws IOException {
       try {
           fileTXT=new java.io.File(name);
           if(!fileTXT.exists()){
               fileTXT.createNewFile();
           }
           if (option==0) {
               bw=new BufferedWriter(new FileWriter(fileTXT,true));
               //bw = new BufferedWriter(new FileWriter(fileTXT,true));
               System.out.println("Si entre con 0  "+fileTXT.getName());
               return true;
           }
           if (option==1)
           {
               System.out.println("Si entre con 1 " +fileTXT.getName());
               br = new BufferedReader(new FileReader(fileTXT));
               return true;
           }

        }
       catch (IOException ex) {
           System.out.println("Error to create file txt");
       return false;
    }
       return false;
    }*/
    public boolean closeFile() throws IOException {
        file.close();
        return true;
    }
}
