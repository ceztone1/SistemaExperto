package expertSystem.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class actsBase implements Initializable {
    @FXML
    VBox vbox;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    javafx.event.EventHandler<ActionEvent> handler= new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

        }
    };

}
