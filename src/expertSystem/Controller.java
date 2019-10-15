package expertSystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import expertSystem.File_MasterKnowledgeBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

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

        } catch (IOException e) {
            e.printStackTrace();
        }
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

            }
            if(event.getSource()==btnDELETE)
            {

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

        }
    };

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
