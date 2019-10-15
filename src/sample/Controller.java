package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    JFXButton btnADD,btnDELETE,btnEDIT,btnSEARCH;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnADD.setOnAction(handler);
        btnDELETE.setOnAction(handler);
        btnEDIT.setOnAction(handler);
        btnSEARCH.setOnAction(handler);
    }
    EventHandler<ActionEvent> handler= new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if(event.getSource()==btnADD)
            {

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
        }
    };
  /*  private void closeStage( )
    {
        Stage stage = (Stage) VBC.getScene().getWindow();
        stage.close();
    }*/

}
