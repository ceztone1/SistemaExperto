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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnADD.setOnAction(handler);
        btnDELETE.setOnAction(handler);
        btnEDIT.setOnAction(handler);
        btnSEARCH.setOnAction(handler);

        btnOR.setOnAction(handler);

        btnNEG.setOnAction(handler);
       try {
            tblcb.setItems(oFILE_KB.readSequentially());
            oFILE_KB.oFILE_I.readSequentially("indexMaster.bin");
            System.out.println(oFILE_KB.oFILE_I.oTREE.pre_orden(oFILE_KB.oFILE_I.oTREE.root));
        } catch (IOException e) {
            e.printStackTrace();
        }
        tblcb.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (e.isPrimaryButtonDown() && e.getClickCount() == 2) //MEHTOD FOR EDIT
                {
                    TDA_KnowledgeBase tda_knowledgeBase=tblcb.getSelectionModel().getSelectedItem();
                    btnEDIT.setVisible(true);
                    btnADD.setVisible(false);
                    System.out.println("Edit "+tda_knowledgeBase.getKey());
                }
            }
        });

    }
    EventHandler<ActionEvent> handler= new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if(event.getSource()==btnADD)
            {
                String clause=txtADD.getText();
                    try {
                        int key =Integer.parseInt(Alert());
                        oFILE_KB.write(key,clause);
                        txtADD.clear();
                    }
                    catch (Exception e){
                        Alert alert=new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText("Already exists the key");
                        alert.show();
                    }
            }
            if(event.getSource()==btnEDIT)
            {

                //if edit has succefull then put visible the button ADD and no visible EDIT
                btnADD.setVisible(true);
                btnEDIT.setVisible(false);
            }
            if(event.getSource()==btnDELETE)
            {
                if(tblcb.getSelectionModel().getSelectedIndex()>=0) {
                    TDA_KnowledgeBase tda_knowledgeBase = (tblcb.getSelectionModel().getSelectedItem());
                    Alert alert;
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Confirmar eliminación");
                    alert.setContentText("¿Esta seguro de eliminar al usuario " + tda_knowledgeBase.getKey() + "?");
                    Optional<ButtonType> option = alert.showAndWait();
                    if (option.get() == ButtonType.OK) {

                    } else if (option.get() == ButtonType.CANCEL) {
                    }
                }
            }
            if(event.getSource()==btnSEARCH)
            {

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
    public void refreshTable() throws IOException {
        tblcb.getItems().clear();
        tblcb.setItems(oFILE_KB.readSequentially());
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
