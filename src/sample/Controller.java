package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    JFXButton btnADD,btnDELETE,btnEDIT,btnSEARCH,btnAND,btnCOND,btnOR,btnNEG;
    @FXML
    JFXTextField txtADD;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnADD.setOnAction(handler);
        btnDELETE.setOnAction(handler);
        btnEDIT.setOnAction(handler);
        btnSEARCH.setOnAction(handler);
        btnAND.setOnAction(handler);
        btnOR.setOnAction(handler);
        btnCOND.setOnAction(handler);
        btnNEG.setOnAction(handler);
    }
    EventHandler<ActionEvent> handler= new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if(event.getSource()==btnADD)
            {
                System.out.println("");
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
            if(event.getSource()==btnOR)
            {
                txtADD.setText(txtADD.getText()+"V");

            }
            if(event.getSource()==btnAND)
            {
                txtADD.setText(txtADD.getText()+"^");
            }
            if(event.getSource()==btnCOND)
            {
                txtADD.setText(txtADD.getText()+"->");
            }
            if(event.getSource()==btnNEG)
            {
                txtADD.setText(txtADD.getText()+"¬");
            }
        }
    };
  /*  private void closeStage( )
    {
        Stage stage = (Stage) VBC.getScene().getWindow();
        stage.close();
    }*/

}
