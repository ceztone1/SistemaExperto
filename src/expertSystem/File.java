package expertSystem;

import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File.*;

public class File {
    RandomAccessFile file;
    BufferedWriter bw;
    BufferedReader br;
    java.io.File fileTXT;


    public boolean openFile(String name,String action) throws FileNotFoundException {
     try {
         file=new RandomAccessFile(name,action);
         return true;
     }
     catch (Exception e)
     {
         System.out.println(e);
     }
        return false;
    }

    public boolean openFileTxt(String name,int option) throws IOException {
       try {
           fileTXT=new java.io.File(name);
           if(!fileTXT.exists()){
               fileTXT.createNewFile();
           }
           if (option==0) {
               bw=new BufferedWriter(new FileWriter(fileTXT));
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
    }
    public boolean closeFile() throws IOException {
        file.close();
        return true;
    }
}
