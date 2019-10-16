package expertSystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import expertSystem.File_MasterKnowledgeBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.WHITE;

public class Controller implements Initializable {
    @FXML
    JFXButton btnADD,btnDELETE,btnEDIT,btnSEARCH,btnOR,btnNEG;
    @FXML
    JFXTextField txtADD,txtSEARCH;
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
                    txtADD.setText(tda_knowledgeBase.getAnt1()+"V"+tda_knowledgeBase.getAnt2()+"V"+tda_knowledgeBase.getAnt3()+"V"+tda_knowledgeBase.getAnt4()+"V"+tda_knowledgeBase.getAnt5()+"V"+tda_knowledgeBase.getAnt6()+"V"+tda_knowledgeBase.getCons());
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
                txtADD.setText(txtADD.getText() + "V");

            }
            if(event.getSource()==btnNEG)
            {
                txtADD.setText(txtADD.getText()+"¬");
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
    }
    public void insert()
    {
        if(!txtADD.getText().isEmpty())
        {
            String clause=txtADD.getText();
            try {
                int key =Integer.parseInt(Alert());
                if(!oFILE_KB.oFILE_I.oTREE.search(key))
                {
                    oFILE_KB.write(key,clause);
                    txtADD.clear();
                }
                else {
                    alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Already exists this key :(");
                    alert.show();
                }
            }
            catch (Exception e){
                alert=new Alert(Alert.AlertType.ERROR);
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
                    if (oFILE_KB.update(node, txtADD.getText())) {
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
        }catch (Exception e)
        {
            System.out.println("dd "+e);
        }

        //if edit has succefull then put visible the button ADD and no visible EDIT
        btnADD.setVisible(true);
        btnEDIT.setVisible(false);
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
