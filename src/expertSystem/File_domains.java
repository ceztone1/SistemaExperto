package expertSystem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
/**
 * Clase File_domains para realizar la escritura, lectura y busqueda de dominios en el archivo domains.txt
 * hereda de File
 *
 */

public class File_domains extends File{
  File oFILE=new File(); //Objeto de File
  public void write(TDA_Domains tda_domains) throws IOException  //Metodo para escribir en archivo
  {
          bw=new BufferedWriter(new FileWriter("domains.txt",true));
          bw.write(tda_domains.getName());
          bw.newLine();
          bw.write("");
          bw.newLine();
          bw.close();
  }
  public ObservableList<TDA_Domains> read() throws IOException //Metodo para leer del archivo (Lectura secuencial)
  {
      ObservableList<TDA_Domains> rows=null;
      TDA_Domains tda_domains;
      br=oFILE.openFileBR("domains.txt");
          String line=br.readLine();
          if (line!=null) {
              rows= FXCollections.observableArrayList();
              do {
                  tda_domains = new TDA_Domains();
                  tda_domains.setName(line);
                  line = br.readLine();
                  tda_domains.setValues((line.equals("")) ? null : line);
                  rows.add(tda_domains);
                  line = br.readLine();
              } while (line != null);
          }
          br.close();
      return rows;
  }
  public TDA_Domains search(String name) throws IOException //Metodo para buscar un dominio
  {
      TDA_Domains tda_domains=null;
      boolean ban=true;
      br=oFILE.openFileBR("domains.txt");
          String line=br.readLine();
          if(line!=null){
              do {
                  if(line.equals(name)) {
                      tda_domains = new TDA_Domains(line, br.readLine());
                      ban=false;
                  }
                  line=br.readLine();
              }while (line!=null && ban);
          }
         br.close();
      return tda_domains;
  }
  public void update(ObservableList<TDA_Domains> rows) throws IOException //Metodo para actualizar el archivo (Se debe crear un archivo temporal, eliminar el anterior y renombrar el archivo temporal)
  {
      bw=oFILE.openFileBW("domainsTemp.txt");
          for (int i = 0; i <rows.size() ; i++) {
                bw.write(rows.get(i).getName());
                bw.newLine();
                bw.write((rows.get(i).getValues()==null?(""):(rows.get(i).getValues())));
                bw.newLine();
          }
          bw.close();
          java.io.File temp=new java.io.File("domains.txt");
          temp.delete();
          oFILE.fileTXT.renameTo(new java.io.File(temp.getName()));
  }
}
