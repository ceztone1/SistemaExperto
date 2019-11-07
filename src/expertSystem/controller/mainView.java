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
/*
* Controlador de la vista general, interactua con los controladores de las demas vistas
* */
public class mainView implements Initializable {
     @FXML
    JFXButton btnDO,btnKB,btnAB,btnPR;
     @FXML
    MenuItem mn1_1,mn4_1,mn4_2;
     @FXML
    VBox vbKB,vbDO,vbAB,vbPR;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mn1_1.setOnAction(handler);
        mn4_1.setOnAction(handler);
        mn4_2.setOnAction(handler);
        vbAB.setVisible(true);
        btnDO.setOnAction(handler);
        btnKB.setOnAction(handler);
        btnAB.setOnAction(handler);
        btnPR.setOnAction(handler);
    }
    EventHandler<ActionEvent> handler= new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
        if(btnDO==event.getSource())
        {
            vbDO.setVisible(true);
            vbKB.setVisible(false);
            vbAB.setVisible(false);
            vbPR.setVisible(false);
        }
        if (btnKB==event.getSource())
        {
            vbKB.setVisible(true);
            vbDO.setVisible(false);
            vbAB.setVisible(false);
            vbPR.setVisible(false);
        }
        if(event.getSource()==btnAB)
        {
            vbAB.setVisible(true);
            vbKB.setVisible(false);
            vbDO.setVisible(false);
            vbPR.setVisible(false);
        }
        if (event.getSource()==btnPR)
        {
            vbPR.setVisible(true);
            vbKB.setVisible(false);
            vbDO.setVisible(false);
            vbAB.setVisible(false);
        }
        }
    };
}
