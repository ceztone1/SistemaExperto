package expertSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
/*INFORMATION OF THE REGISTER

4 bytes FOR int
6 Strings (antecedent) of 40 characters
1 string (consequent) de 40 characters
row size = 284

* */
public class File_MasterKnowledgeBase extends File {
    TDA_KnowledgeBase okb=new TDA_KnowledgeBase();
    TDA_Index oI;
    File_Index ofI =new File_Index();
    File ofile=new File();
    ObservableList<TDA_KnowledgeBase> registros = FXCollections.observableArrayList();
    public void write (int key,String clause) throws IOException {
        System.out.println("key "+ key+"  clause   "+clause);
        StringBuffer buffer=null;
            if(ofile.openFile("MasterKnowledgeBase.bin","rw"))
            {
                ofile.file.seek(ofile.file.length());
                System.out.println("length   "+ofile.file.length());
                ofile.file.writeInt(key);

                String c[]=clause.split("V");
                int cou=c.length;

                if(cou<=7 && cou>1) {
                    System.out.println("si");

                    for (int i = 0; i < 6; i++) {
                        System.out.println("ggg  "+i+"  have " +(i<(cou-1)?c[i]:"ya no")  +"  ");
                        buffer=new StringBuffer((i<(cou-1)?c[i]:""));
                        buffer.setLength(40);

                        ofile.file.writeChars(buffer.toString());
                        System.out.println("Si lo hice  "+i);
                    }
                    System.out.println("El ultimo re    " +c[cou-1]);
                    buffer=new StringBuffer(c[cou-1]);
                    buffer.setLength(40);
                    ofile.file.writeChars(buffer.toString());
                }
                else
                {

                    for (int i = 0; i <7 ; i++) {
                        System.out.println("entre  "+i);
                        buffer=new StringBuffer(i==0?clause:" ");
                        buffer.setLength(40);
                        ofile.file.writeChars(buffer.toString());
                    }
                }
                ofile.closeFile();
            }
    }
    long ap_actual,ap_final;
    public ObservableList<TDA_KnowledgeBase> readSequentially() throws IOException {
        TDA_KnowledgeBase tda_kb;
        if(ofile.openFile("MasterKnowledgeBase.bin","r"))
        {
            System.out.println("length  read "+ofile.file.length());
            while ((ap_actual=ofile.file.getFilePointer())!=(ap_final=ofile.file.length()))
            {
                int d=ofile.file.readInt();
                tda_kb=new TDA_KnowledgeBase(d,readChars(),readChars(),readChars(),readChars(),readChars(),readChars(),readChars());
                System.out.println("Si entre  key   "+tda_kb.getKey()+ "    amt 1  "+ tda_kb.getAnt1());
                registros.add(tda_kb);
            }
            ofile.closeFile();
        }
        return registros;
    }
    public String readChars() throws IOException {
        char ante [] = new char[40],temp;
        for (int c = 0; c < ante.length; c++) {
            temp = ofile.file.readChar();
            System.out.println("Tempo titne   "+c +"   ree -> " +temp);
            ante[c] = temp;
        }
        new String(ante).replace('\0',' ');
        System.out.println("Esto tiene "+ante.toString());
        return String.valueOf(ante);
    }
    public void readSecRandom(){

    }
    public void delete(){

    }
    public void update(){

    }
}
