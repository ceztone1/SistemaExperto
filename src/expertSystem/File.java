package expertSystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class File {
    RandomAccessFile file;
    public boolean openFile(String name,String action) throws FileNotFoundException {
        file=new RandomAccessFile(name,action);
        return true;
    }
    public boolean closeFile() throws IOException {
        file.close();
        return true;
    }
}
