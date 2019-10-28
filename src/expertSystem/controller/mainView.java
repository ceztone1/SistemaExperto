package expertSystem.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainView implements Initializable {
     @FXML
    Menu mn2,mn3;
     @FXML
    MenuItem mn1_1,mn2_1,mn2_2,mn2_3,mn2_4,mn3_1,mn3_2,mn3_3,mn3_4,mn4_1,mn4_2;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mn1_1.setOnAction(handler);
        mn2_1.setOnAction(handler);
        mn2_2.setOnAction(handler);
        mn2_3.setOnAction(handler);
        mn2_4.setOnAction(handler);
        mn3_1.setOnAction(handler);
        mn3_2.setOnAction(handler);
        mn3_3.setOnAction(handler);
        mn3_4.setOnAction(handler);
        mn4_1.setOnAction(handler);
        mn4_2.setOnAction(handler);
        mn2.setOnAction(handler);
        mn3.setOnAction(handler);
    }
    EventHandler<ActionEvent> handler= new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource()==mn2_1){
                System.out.println("Sisissi");
            }
        }
    };

}
