package expertSystem.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/*
* Controlador de vista de base de hechos, solo utiliza los elementos de la interfaz e interactua con MOTOR DE INFERENCIA
* */
public class actsBase implements Initializable
{
    @FXML
    VBox vbox;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    EventHandler<ActionEvent> handler= new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {}
    };

}
