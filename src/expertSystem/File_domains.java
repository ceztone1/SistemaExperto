package expertSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;



public class File_domains extends File{
  File oFILE=new File();
  //TDA_Domains tda_domains;

  public void write(TDA_Domains tda_domains) throws IOException {
      if(openFileTxt("domains.txt",0))
      {
          bw.write(tda_domains.getName());
          bw.newLine();
          bw.write(tda_domains.getValues());
          bw.newLine();
          bw.close();
      }
  }
  public ObservableList<TDA_Domains> read() throws IOException {
      ObservableList<TDA_Domains> rows=null;
      TDA_Domains tda_domains;
      //String value,name;
      if(openFileTxt("domains.txt",1)){
          rows= FXCollections.observableArrayList();
          String line=br.readLine();          //
          do {
                    tda_domains=new TDA_Domains();
                  tda_domains.setName(line);
                  tda_domains.setValues(br.readLine());
             // System.out.println("Name  "+tda_domains.getName()+"   value  "+tda_domains.getValues());
                  rows.add(tda_domains);
                  line=br.readLine();
              //System.out.println(line+"   contiene");
          }while (line!=null);
          br.close();
      }
      return rows;
  }
  public TDA_Domains search(String name) throws IOException {
      TDA_Domains tda_domains=null;
      boolean ban=true;
      if(openFileTxt("domains.txt",1)){
          String line=br.readLine();
          do {
              if(line.equals(name)) {
                  tda_domains = new TDA_Domains(line, br.readLine());
                  ban=false;
                //  java.io.File fichero = new java.io.File("Dominio");
              }
          }while (line!=null && ban);
         br.close();
      }
      return tda_domains;
  }

  public void update(ObservableList<TDA_Domains> rows) throws IOException {

      //boolean ban=true;
      if(oFILE.openFileTxt("domainsTemp.txt",0)){
          bw=new BufferedWriter(new FileWriter(fileTXT));
          for (int i = 0; i <rows.size() ; i++) {
                bw.write(rows.get(i).getName());
                bw.newLine();
                bw.write(rows.get(i).getValues());
                bw.newLine();
          }
          fileTXT.renameTo(new java.io.File("domains.txt"));
          java.io.File temp=new java.io.File("domainsTemp.txt");
          temp.delete();

          bw.close();
      }
  }

}
