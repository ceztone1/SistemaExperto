package expertSystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class File_Index extends File {
    File oFILE=new File();
    public Tree oTREE = new Tree();
    TDA_Index oTDA_I=null;

    public void write(TDA_Index tda_index,String index,long position) throws IOException {
        RandomAccessFile file=oFILE.openFile(index,"rw");
        /*if(oFILE.openFile(index,"rw"))
        {*/
            oFILE.file.seek((position==0)?oFILE.file.length():position); //final position of file Index
            oFILE.file.writeInt(tda_index.key); //write INT index
            oFILE.file.writeInt(tda_index.position); //write INT position
            oFILE.closeFile(); //close file
        //}
    }
    public void readSequentially(String index) throws IOException {
        long ap_actual,ap_final;
        RandomAccessFile file=oFILE.openFile(index,"r");
        /*if(oFILE.openFile(index,"r"))
        {*/
            while ((ap_actual=oFILE.file.getFilePointer())!=(ap_final=oFILE.file.length()))
            {
                oTDA_I=new TDA_Index(oFILE.file.readInt(),oFILE.file.readInt());
                //System.out.println("lei   key  "+ oTDA_I.getKey()+"    position   "+oTDA_I.getPosition());
                oTREE.insert(oTDA_I);
            }
            oFILE.closeFile();
        //}
    }
    public void update(){

    }
    public void delete(int key,String index) throws IOException {
        long ap_actual,ap_final, position;
        boolean b=true;
        RandomAccessFile file=oFILE.openFile(index,"r");
        /*if(oFILE.openFile(index,"r"))
        {*/
            while (((ap_actual=oFILE.file.getFilePointer())!=(ap_final=oFILE.file.length()))&& b)
            {
                oTDA_I=new TDA_Index(oFILE.file.readInt(),oFILE.file.readInt());
                //System.out.println("key  "+oTDA_I.getKey()+"   positio   "+ oTDA_I.getPosition());
                if(oTDA_I.getKey()==key) {
                    b=false;
                }
            }
            if(!b)
            {
                System.out.println("sali  ");
                position=(oFILE.file.getFilePointer() - 8);
                oFILE.closeFile();
//                    System.out.println("position   a"+position+"position   a"+oFILE.file.getFilePointer());
                oTDA_I=new TDA_Index(0,0);
                write(oTDA_I,index,position);
            }
        //}

    }
}
