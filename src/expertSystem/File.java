package expertSystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class File {
    RandomAccessFile file;
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
    public boolean closeFile() throws IOException {
        file.close();
        return true;
    }
}
