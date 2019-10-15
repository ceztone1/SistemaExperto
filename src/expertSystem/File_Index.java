package expertSystem;

import java.io.FileNotFoundException;
import java.io.IOException;

public class File_Index extends File {
    File oFILE=new File();
    TDA_Index oTDA_I = new TDA_Index();
    Tree oTREE = new Tree();
    public void write(TDA_Index tda_index,String index) throws IOException {
        if(oFILE.openFile(index,"rw"))
        {
            oFILE.file.seek(oFILE.file.length()); //final position of file Index

            oFILE.file.writeInt(tda_index.key); //write INT index
            oFILE.file.writeInt(tda_index.position); //write INT position

            oFILE.closeFile(); //close file
        }

    }
    public void readSequentially(String index) throws IOException {
        long ap_actual,ap_final;
        TDA_Index oTDA_I;
        int rows=0;
        if(oFILE.openFile(index,"r"))
        {
            while ((ap_actual=oFILE.file.getFilePointer())!=(ap_final=oFILE.file.length()))
            {
                oTDA_I=new TDA_Index(oFILE.file.readInt(),oFILE.file.readInt());
                oTREE.insert(oTDA_I);
                rows++;
            }
            oFILE.closeFile();
        }
    }
    public void update(){

    }
    public void delete(){

    }
}
