package expertSystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainView implements Initializable {
     @FXML
    JFXButton btnDO,btnKB,btnAB;
     @FXML
    MenuItem mn1_1,mn4_1,mn4_2;
     @FXML
    VBox vbKB,vbDO,vbAB;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mn1_1.setOnAction(handler);

        mn4_1.setOnAction(handler);
        mn4_2.setOnAction(handler);
       // mn2.setOnAction(handler);
        vbAB.setVisible(true);
        btnDO.setOnAction(handler);
        btnKB.setOnAction(handler);
        btnAB.setOnAction(handler);
    }
    EventHandler<ActionEvent> handler= new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
        if(btnDO==event.getSource())
        {
            vbKB.setVisible(false);
            vbDO.setVisible(true);
            vbAB.setVisible(false);
        }
        else
             if (btnKB==event.getSource())
            {
                vbKB.setVisible(true);
                vbDO.setVisible(false);
                vbAB.setVisible(false);
            }
             else
                 if(event.getSource()==btnAB)
                 {
                     vbKB.setVisible(false);
                     vbDO.setVisible(false);
                     vbAB.setVisible(true);
                 }
        }
    };

}
