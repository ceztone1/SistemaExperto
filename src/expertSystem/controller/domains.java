package expertSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import expertSystem.File_domains;
import expertSystem.TDA_Domains;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class domains implements Initializable {
    @FXML
    JFXListView<String> listDomains,listElements;
    @FXML
    JFXButton btnDELETE,btnADD;
    @FXML
    JFXTextField txtElement;
    File_domains oFILE_D=new File_domains();
    ObservableList<TDA_Domains>rows= FXCollections.observableArrayList();
    ObservableList<String>rowName= FXCollections.observableArrayList();
    ObservableList<String >rowValues= FXCollections.observableArrayList();
    int pos;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnDELETE.setOnAction(handler);
        btnADD.setOnAction(handler);
        try {

            rows=oFILE_D.read();
            for (int i = 0; i <rows.size() ; i++) {
                rowName.add(rows.get(i).getName());
            }
            listDomains.setItems(rowName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        listDomains.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent arg0) {
                rowValues.clear();
                //String values=rows.get(i-1).getValues();
                pos=search(listDomains.getSelectionModel().getSelectedItem());
                String v[]=rows.get(pos).getValues().split(",");
                for (int j = 0; j <v.length ; j++) {
                    rowValues.add(v[j]);
                }
                listElements.setItems(rowValues);
            }

        });
       /* listElements.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent arg0) {
                System.out.println("Elemente  "+listElements.getSelectionModel().getSelectedItem());
            }

        });*/

    }
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
    public int searchListElements(String selected){
        int i=0;
        boolean ban=true;
        while(i<rowValues.size() && ban){
            if(rowValues.get(i).equals(selected) )
                ban=false;
            i++;
        }
        return --i;
    }
    EventHandler<ActionEvent> handler= new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if(event.getSource()==btnDELETE){
                try {
                    delete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(event.getSource()==btnADD){
               // txtElement.setVisible(true);
                try {
                    System.out.println("si");
                    update();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    public void update() throws IOException {
        int position;
        if (listDomains.getSelectionModel().getSelectedIndex() >= 0) {
            //System.out.println("Selectteddd "+listDomains.getSelectionModel().getSelectedIndex());
            position=search(listDomains.getSelectionModel().getSelectedItem());
            TDA_Domains tda_domains=rows.get(position);
            tda_domains.setValues((tda_domains.getValues()!=null)?tda_domains.getValues()+(rowValues.size()==1?"":",")+txtElement.getText():","+txtElement.getText());
            //System.out.println("Queda   "+tda_domains.getValues());
            rows.get(position).setName(tda_domains.getName());
            rows.get(position).setValues(tda_domains.getValues());
            oFILE_D.update(rows);
        }
    }
    public void delete() throws IOException {
        if (listElements.getSelectionModel().getSelectedIndex() >= 0) {
            int position;
            position=searchListElements(listElements.getSelectionModel().getSelectedItem());
            System.out.println("Selectec   "+listElements.getSelectionModel().getSelectedItem()+"  "+position +" "+rowValues.size()+"    gfh   "+(position==(rowValues.size()-1)?",":"")+listElements.getSelectionModel().getSelectedItem()+(position==(rowValues.size()-1)?"":","));
            TDA_Domains tda_domains=rows.get(pos);
            String val[]=tda_domains.getValues().split((position==(rowValues.size()-1)?(position==0)?"":",":"")+listElements.getSelectionModel().getSelectedItem()+(position==(rowValues.size()-1)?"":","));
            String v="";
            for (int i = 0; i <val.length ; i++) {
                v+=val[i];
            }
            //System.out.println("Queda  "+v);
            tda_domains.setValues(v);
            rows.get(pos).setValues(tda_domains.getValues());
            oFILE_D.update(rows);
        }
    }
}
