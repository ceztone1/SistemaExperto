package expertSystem;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
/**
 * clase para generar archivo de predicados y con este archivo poder generar base de hechos
 * Hereda de File
 * Metodos para escribir, buscar, actualizar y leer predicados
 */

public class File_Predicates extends File {

    File oFILE=new File();

    public void write(TDA_Predicates tda_predicates) throws IOException  //Método que escribir un nuevo predicado en archivo
    {
        bw=new BufferedWriter(new FileWriter("predicates.txt",true));
        bw.write(tda_predicates.getName());
        bw.newLine();
        bw.write("");
        bw.newLine();
        bw.close();
    }
    public ObservableList<TDA_Predicates> read() throws IOException //Lee todos los registros para mostrar en lista
    {
        ObservableList<TDA_Predicates> rows=null;
        br=oFILE.openFileBR("predicates.txt");
            String line=br.readLine(),desc;
            if (line!=null) {
                rows= FXCollections.observableArrayList();
                do {
                    desc=br.readLine();
                    rows.add(new TDA_Predicates(line,((desc).equals("")?null:desc)));
                    line = br.readLine();
                } while (line != null);
            }
            br.close();
        return rows;
    }
    public TDA_Predicates search(String name) throws IOException //Metodo para buscar un predicado en el archivo
    {
        TDA_Predicates tda_predicates=null;
        boolean ban=true;
        br=oFILE.openFileBR("predicates.txt");
            String line=br.readLine();
            if(line!=null){
                do {
                    if(line.equals(name)) {
                        tda_predicates = new TDA_Predicates(line, br.readLine());
                        ban=false;
                    }
                    line=br.readLine();
                }while (line!=null && ban);
            }
            br.close();
        return tda_predicates;
    }
    public void update(ObservableList<TDA_Predicates> rows) throws IOException //Metodo para actualizar una predicado (Se debe generar un archivo temporal nuevo, eliminar el anterior y renombrar el archivo temporal)
    {
        bw=oFILE.openFileBW("predicatesTemp.txt");
            for (int i = 0; i <rows.size() ; i++) {
                bw.write(rows.get(i).getName());
                bw.newLine();
                bw.write((rows.get(i).getDescription()==null?(""):(rows.get(i).getDescription())));
                bw.newLine();
            }
        bw.close();
        java.io.File temp=new java.io.File("predicates.txt");
        temp.delete();
        oFILE.fileTXT.renameTo(new java.io.File(temp.getName()));
    }
}
