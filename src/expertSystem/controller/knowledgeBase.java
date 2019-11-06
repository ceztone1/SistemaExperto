package expertSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import expertSystem.File_MasterKnowledgeBase;
import expertSystem.Node;
import expertSystem.TDA_KnowledgeBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.WHITE;

public class knowledgeBase implements Initializable {
    @FXML
    JFXButton btnADD,btnDELETE,btnEDIT,btnSEARCH,btnOR,btnNEG;
    @FXML
    JFXTextField txtSEARCH;
    @FXML
    JFXTextArea txtADD;
    @FXML
    JFXCheckBox cbGUI;
    @FXML
    TableView<TDA_KnowledgeBase> tblcb;
    File_MasterKnowledgeBase oFILE_KB=new File_MasterKnowledgeBase();
    Alert alert;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnADD.setOnAction(handler);
        btnDELETE.setOnAction(handler);
        btnEDIT.setOnAction(handler);
        btnSEARCH.setOnAction(handler);
        btnOR.setOnAction(handler);
        btnNEG.setOnAction(handler);

        tblcb.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (e.isPrimaryButtonDown() && e.getClickCount() == 2) //MEHTOD FOR EDIT
                {
                    TDA_KnowledgeBase tda_knowledgeBase=tblcb.getSelectionModel().getSelectedItem();
                    System.out.println(tda_knowledgeBase.getAnt2()==null?"soy null":"no soy null");
                    System.out.println("tiene ->"+tda_knowledgeBase.getAnt2()+"<-");
                    txtADD.setText(tda_knowledgeBase.getAnt1()+"∧"+tda_knowledgeBase.getAnt2()+"∧"+tda_knowledgeBase.getAnt3()+"∧"+tda_knowledgeBase.getAnt4()+"∧"+tda_knowledgeBase.getAnt5()+"∧"+tda_knowledgeBase.getAnt6()+"→"+tda_knowledgeBase.getCons());
                    cbGUI.setSelected(tda_knowledgeBase.isGui());
                    btnEDIT.setVisible(true);
                    btnADD.setVisible(false);
                    System.out.println("Edit "+tda_knowledgeBase.getKey());
                }
            }
        });
        try {
            refreshTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    EventHandler<ActionEvent> handler= new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if(event.getSource()==btnADD)
            {
                insert();
            }
            if(event.getSource()==btnEDIT)
            {
                update();
            }
            if(event.getSource()==btnDELETE)
            {
               delete();
            }
            if(event.getSource()==btnSEARCH)
            {
                search();
            }
            if(event.getSource()==btnOR) {
                txtADD.setText(txtADD.getText() + "∧");
                txtADD.requestFocus();
            }
            if(event.getSource()==btnNEG)
            {
                txtADD.setText(txtADD.getText()+"→");
                txtADD.requestFocus();
            }
            try {
                refreshTable();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
    public void search(){
        Node node;
        if (!txtSEARCH.getText().isEmpty()) {
            try {
                node=oFILE_KB.oFILE_I.oTREE.seaNode(Integer.parseInt(txtSEARCH.getText()));
                System.out.println("n   "+ Integer.parseInt(txtSEARCH.getText()));
                if (node!=null) {
                    TDA_KnowledgeBase tda_kb = oFILE_KB.readSecRandom(node.info.getPosition());
                    //System.out.println(tda_kb.getKey()+"    key  ");
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Result");
                    alert.setContentText("Row found is  Key: " + tda_kb.getKey() +
                            "\n antecedent 1 " + tda_kb.getAnt1() + "\n antecedent 2 " + tda_kb.getAnt2() +
                            "\n antecedent 3 " + tda_kb.getAnt3() + "\n antecedent 4 " + tda_kb.getAnt4() +
                            "\n antecedent 5 " + tda_kb.getAnt5() + "\n antecedent 6 " + tda_kb.getAnt6() +
                            "\n consequent   " + tda_kb.getCons());
                    alert.show();
                }
                else {
                    alert=new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("No exists this key ");
                    alert.setTitle("Advertisement");
                    alert.show();
                }
            }
            catch (Exception e)
            {
                txtSEARCH.clear();
                txtSEARCH.requestFocus();
                System.out.println(e);
            }

        }else{
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Position is Empty");
            alert.setTitle("Empty");
            alert.show();
        }
        txtSEARCH.clear();
        txtSEARCH.requestFocus();
    }
    public void delete()
    {
        Node node;
        if(tblcb.getSelectionModel().getSelectedIndex()>=0) {
            TDA_KnowledgeBase tda_knowledgeBase = (tblcb.getSelectionModel().getSelectedItem());

            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Advertisement");
            alert.setHeaderText("Delete confirm");
            alert.setContentText("Are you sure delete row " + tda_knowledgeBase.getKey() + "?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                node=oFILE_KB.oFILE_I.oTREE.seaNode(tda_knowledgeBase.getKey());

                try {
                    oFILE_KB.oFILE_I.oTREE.delete(node.info.getKey());
                    oFILE_KB.oFILE_I.delete(node.info.getKey(),"indexMaster.bin");
                    oFILE_KB.delete(node.info.getPosition());
                    oFILE_KB.oFILE_I.oTREE.men="";
                    System.out.println(" f  "+oFILE_KB.oFILE_I.oTREE.pre_orden(oFILE_KB.oFILE_I.oTREE.root));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //System.out.println(oFILE_KB.oFILE_I.oTREE.pre_orden(oFILE_KB.oFILE_I.oTREE.root));

                System.out.println("el nodo es  "+node.info.getKey()+ "  "+node.info.getPosition());
            } else if (option.get() == ButtonType.CANCEL) {
            }
        }
        else {
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Select a item ");
            alert.setTitle("Warning");
            alert.show();
        }
    }
    //ada
    //fdf^s>>s
    //fd^ss
    //dsdas^d^a^f^ff^q>>f
    public boolean validForm(String clause){
        int c=0;
        for (int i = 0; i < clause.length(); i++) {
            if(clause.charAt(i)=='(')
              c++;
            if (clause.charAt(i)==')')
                c--;
        }
        return (c==0)?true:false;

    }
    public String [] valid(String clause){
        String []a=null,c=null,l=new String[7];
        boolean ban=true;
        int i,j;
        i=j=0;
        a=clause.split("∧");
        if(a.length==1) //no encontró el simbolo
        {
          c=clause.split("→");
          if(c.length==1)
          {
              l[0]=clause; //solo tiene un antecedente y ninguno más ni consecuente
              return (validForm(l[0])?l:null);
          }
          else {
              if (c.length==2){
                  l[0]=c[0];
                  l[6]=c[1];
                  return (validForm(l[0])&&validForm(l[1])?l:null);
              }
          }
        }
        else
        {
           if(a.length>=2){
               int k=0;
               while(k<(a.length-1) && ban){
                   if(a[k].split("→").length>1)
                       ban=false;
                   k++;
               }
               if(ban)
               {
                   c=a[k].split("→");
                   if(c.length==1)
                   {
                       k=0;
                       while(k<a.length && ban){
                           l[k]=a[k];
                           ban=validForm(l[k])?true:false;
                           k++;
                       }
                       return ban?l:null;
                   }
                   else
                   {
                       if(c.length==2)
                       {
                           k=0;
                           while(k<(a.length-1) && ban){
                               l[k]=a[k];
                               ban=validForm(l[k])?true:false;
                               k++;
                           }
                           if(ban)
                           {
                                l[k]=c[0];
                                l[6]=c[1];
                                return (validForm(l[k]) && validForm(l[0])?l:null);
                           }
                           return null;
                       }
                       else
                           return null;
                   }
               }
               else
                       return null;
           }
        }
        return null;
    }
    public void insert()
    {
        if(!txtADD.getText().isEmpty())
        {
            String clause=txtADD.getText();
            //System.out.println("aqui si   "+clause);
            String []c=valid(clause);
            if(c!=null) {
                for (int i = 0; i <7 ; i++) {
                    System.out.println("values     \n"+c[i]);

                }
                try {
                    int key = Integer.parseInt(Alert());
                    if (!oFILE_KB.oFILE_I.oTREE.search(key)) {
                        oFILE_KB.write(key, c, (cbGUI.isSelected()) ? true : false);
                        txtADD.clear();
                    } else {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText("Already exists this key :(");
                        alert.show();
                    }
                } catch (Exception e) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Unfortunately something failed! :( ");
                    alert.show();
                }
            }
            else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Unfortunately something failed! :( ");
                alert.show();
            }

        }else{
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Clause is Empty");
            alert.setTitle("Empty");
            alert.show();
        }
    }
    public void update(){
        try {
            if(tblcb.getSelectionModel().getSelectedIndex()>=0) {
                TDA_KnowledgeBase tda_knowledgeBase = (tblcb.getSelectionModel().getSelectedItem());
                Node node = oFILE_KB.oFILE_I.oTREE.seaNode(tda_knowledgeBase.getKey());
                if (node != null) {
                    String []c=valid(txtADD.getText());
                    if(c!=null) {
                        if (oFILE_KB.update(node,c,cbGUI.isSelected()?true:false )) {
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("Key (" + node.info.getKey() + ") update with success!");
                            alert.setTitle("Success");
                            alert.show();
                            txtADD.clear();
                        }
                    } else {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("No exists this key ");
                        alert.setTitle("Advertisement");
                        alert.show();
                    }
                    }

            }
        }catch (Exception e)
        {
            System.out.println("dd "+e);
        }

        //if edit has succefull then put visible the button ADD and no visible EDIT
        btnADD.setVisible(true);
        btnEDIT.setVisible(false);
        cbGUI.setSelected(false);
    }
    public void refreshTable() throws IOException {
        try {
            tblcb.getItems().clear();
            tblcb.setItems(oFILE_KB.readSequentially());
            //oFILE_KB.readSecRandom(1);
            oFILE_KB.oFILE_I.readSequentially("indexMaster.bin");
            System.out.println(oFILE_KB.oFILE_I.oTREE.pre_orden(oFILE_KB.oFILE_I.oTREE.root));
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*tblcb.getItems().clear();
        tblcb.setItems(oFILE_KB.readSequentially());*/
    }
    public String Alert(){
        TextInputDialog dialog = new TextInputDialog("1");
        dialog.setTitle("Clause key");
        dialog.setHeaderText("A key");
        dialog.setContentText("Please enter a key:");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            result.get();
        }
        return result.get();
    }

  /*  private void closeStage( )
    {
        Stage stage = (Stage) VBC.getScene().getWindow();
        stage.close();
    }*/

}
