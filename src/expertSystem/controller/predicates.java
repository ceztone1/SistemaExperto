package expertSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import expertSystem.File_Predicates;
import expertSystem.TDA_Domains;
import expertSystem.TDA_Predicates;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/*
* Controlador de la vista de predicados, se puede cambiar la descripcion del predicado interactua con la clase File_predicates
* */
public class predicates implements Initializable {
    @FXML
    JFXListView<String> listPredicate;
    @FXML
    JFXTextArea txaDescription;
    @FXML
    JFXButton btnADD,btnSE;
    ObservableList<TDA_Predicates> rows= FXCollections.observableArrayList();
    ObservableList<String>rowName= FXCollections.observableArrayList();
    File_Predicates oFILE_P=new File_Predicates();
    int pos;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnADD.setOnAction(handler);
        btnSE.setOnAction(handler);
        refresh();
        listPredicate.setOnKeyPressed(
                event -> {
                    switch (event.getCode())
                    {
                        case F5:
                           // rows.clear();
                          //  listPredicate.getSelectionModel().clearSelection();
                            refresh();
                            break;
                    }
                }
        );
        listPredicate.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent arg0) {
                txaDescription.clear();
                if (rows!=null){
                    pos=search(listPredicate.getSelectionModel().getSelectedItem());
                    txaDescription.setText(rows.get(pos).getDescription());
                }
            }
        });
        listPredicate.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (e.isPrimaryButtonDown() && e.getClickCount() == 2) {
                    String selectedItem=listPredicate.getSelectionModel().getSelectedItem();
                    btnSE.setVisible(true);
                    btnADD.setVisible(false);
                }
            }
        });

    }
    EventHandler<ActionEvent> handler= new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Alert alert;
            if (btnADD==event.getSource())
            {
                try {
                    update();
                    alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("successful add!");
                    alert.setTitle("Successfully");
                    alert.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (btnSE==event.getSource()){
                try {
                    update();
                    alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("successful edit!");
                    alert.setTitle("Successfully");
                    alert.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                btnSE.setVisible(false);
                btnADD.setVisible(true);
            }
        }
    };
    public int search(String selected){
        int i=0;
        boolean ban=true;
        while(i<rows.size() && ban){
            if(rows.get(i).getName().equals(selected) )
                ban=false;
            i++;
        }
        return --i;
    }

    public void refresh(){
        try {
            listPredicate.getItems().clear();
            System.out.println("ssisisis predicates");
            rows=oFILE_P.read();
            if(rows!=null)
            {
                for (int i = 0; i <rows.size() ; i++) {
                    rowName.add(rows.get(i).getName());
                }
                listPredicate.setItems(rowName);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update () throws IOException {
        int position;
        if (listPredicate.getSelectionModel().getSelectedIndex() >= 0) {
            position=search(listPredicate.getSelectionModel().getSelectedItem());
            TDA_Predicates tda_predicates=rows.get(position);
            tda_predicates.setDescription(txaDescription.getText());
            rows.get(position).setName(tda_predicates.getName());
            rows.get(position).setDescription(tda_predicates.getDescription());
            oFILE_P.update(rows);
        }
    }
}
