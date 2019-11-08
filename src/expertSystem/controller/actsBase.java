package expertSystem.controller;

import com.jfoenix.controls.*;
import expertSystem.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.SnapshotResult;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Stack;

/*
 * Controlador de vista de base de hechos, solo utiliza los elementos de la interfaz e interactua con MOTOR DE INFERENCIA
 * */
public class actsBase implements Initializable {
    @FXML
    VBox vbox;
    @FXML
    JFXTextField txtName;
    @FXML
    JFXButton btnNa,btnIn;
    @FXML
    JFXListView<String>fBase;
    File_domains oFILE_D = new File_domains();
    File_Predicates oFILE_P = new File_Predicates();
    int i = 0;
    int ant = 0, antN = 0, dom = 0;
    float prom, var = 0;
    boolean ban = true;
    int sizes = 0;
    VBox vertical = new VBox(6);
    JFXRadioButton rb[][] = new JFXRadioButton[100][5]; //array de radio buttons;
    VBox vPredicate[];
    JFXComboBox cb[] = new JFXComboBox[100]; //array de combo box
    ToggleGroup tg;
    ArrayList<String> array = new ArrayList<>();
    ArrayList<String> arrayN = new ArrayList<>();
    ObservableList<TDA_Predicates> rows_P = FXCollections.observableArrayList();
    ObservableList<TDA_Domains> rows_D = FXCollections.observableArrayList();
    ObservableList<String> factsBase = FXCollections.observableArrayList();
    ObservableList<String> fcBm = FXCollections.observableArrayList();
    ObservableList<String> empty = FXCollections.observableArrayList();
    File_MasterKnowledgeBase oFILE_KB = new File_MasterKnowledgeBase();
    JFXButton btn[];

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnNa.setOnAction(handler);
        btnIn.setOnAction(handler);
        try {
            rows_D = oFILE_D.read();
            rows_P = oFILE_P.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == btnNa) {
                if (!txtName.getText().isEmpty()) {
                    try {
                        for (int j = 0; j < rows_P.size(); j++) {
                            if (rows_D.get(j).getName().equalsIgnoreCase("p")) {
                                rows_D.set(j, new TDA_Domains(rows_D.get(j).getName(), txtName.getText()));
                                oFILE_D.update(rows_D);
                                rows_D = oFILE_D.read();
                                btnNa.setDisable(true);
                                txtName.setDisable(true);
                                btnIn.setVisible(true);
                                vbox.getChildren().add(vertical);
                            }
                        }
                        GUI();
                        vbox.setStyle("-fx-background-color: #43B0F7");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            if (btnIn==event.getSource())
            {
                Stack<String> bh = new Stack<>();

                ObservableList<TDA_KnowledgeBase> rows = FXCollections.observableArrayList();
                encadenamiento e = new encadenamiento();
                try {
                    rows=oFILE_KB.readSequentially();
                } catch (IOException s) {
                    s.printStackTrace();
                }
                System.out.println("infer YAAAA");
                for (int j = 0; j <fcBm.size() ; j++) {
                    bh.push(fcBm.get(j));
                    System.out.println(fcBm.get(j).toString());

                }
                ArrayList<String> justificacion ;
               justificacion= e.adelante(rows,bh);
                Node nod
                        ;
               String mensaje="Reglas utilizadas\n";
                for (int j = 0; j < justificacion.size(); j++) {
                    System.out.println("Justificacion   "+justificacion.get(j).);
                    int llave=Integer.parseInt(justificacion.get(j));
                    nod=oFILE_KB.oFILE_I.oTREE.seaNode(2);
                    if (nod==null)
                        System.out.println("diferente"
                        );
                    System.out.println(nod+"    nooooooooooooodo");
                    try {
                        TDA_KnowledgeBase t = new TDA_KnowledgeBase();
                    t=oFILE_KB.readSecRandom(nod.info.getPosition());
                    mensaje+=t.getAnt1()+" "+t.getAnt2()+" "+t.getAnt3()+" "+t.getAnt4()+" "+t.getAnt5()+" "+t.getAnt6()+t.getCons()+"\n";

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }



                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(mensaje);
                alert.setTitle("Justificacion");
                alert.show();


            }
        }
    };

    public void initialice() {
        for (int j = 0; j < cb.length; j++) {
            cb[j] = new JFXComboBox();
        }
    }
    String predi="";
    public void GUI() {

        ObservableList<String> rowElements;
        vertical.setAlignment(Pos.CENTER);
        initialice();
        try {
            String n[];
            btn = new JFXButton[rows_P.size()];
            vPredicate=new VBox[rows_P.size()];
            for (i = 0; i < rows_P.size(); i++) {
                VBox vbAUX = new VBox(5);
                vbAUX.setAlignment(Pos.CENTER);
                TDA_Predicates tda_predicates = rows_P.get(i);
                n = tda_predicates.getName().split("\\(");
                if (n.length >= 2) {
                    if (n[0].equalsIgnoreCase("sintomas") || n[0].equalsIgnoreCase("compulsion")) //
                    {
                        n = n[1].split("\\)");
                        if (n.length == 1) {
                            String g = i + "," + ant;
                            TDA_Domains tda_domains = oFILE_D.search(n[0]);
                            if (tda_domains != null) {
                                String v[] = tda_domains.getValues().split(",");
                                rowElements = FXCollections.observableArrayList();
                                for (int j = 0; j < v.length; j++) {
                                    rowElements.add(v[j]);
                                }
                                vbAUX = symtop(rowElements, tda_predicates);
                                g += "," + (ant+rowElements.size());
                                ant += rowElements.size();
                                array.add(g);
                            }
                        }
                    } else //Tieene la forma -> tiene(p,e) <-
                    {
                        System.out.println("entreee   " + antN);
                        HBox hBox = new HBox(5);
                        hBox.setAlignment(Pos.CENTER);
                        String predicate = n[0];
                        n = n[1].split("\\)");
                        if (n.length == 1) {
                            String domains[] = n[0].split(",");
                            dom = domains.length;
                            String g = i + "," + antN + "," + (antN + dom - 1);
                            System.out.println(" G tieneeeeeeeeeeee   " + g);
                            Label lbl = new Label((tda_predicates.getName() + "  " + tda_predicates.getDescription()).toUpperCase());
                            lbl.setStyle("-fx-background-color: #80F49C");
                            lbl.setPrefWidth(1000);
                            lbl.setAlignment(Pos.CENTER);
                            Label lblT = new Label(predicate);
                            lblT.setAlignment(Pos.CENTER);
                            vbAUX.getChildren().addAll(lbl);
                            for (int j = 0; j < domains.length; j++) {
                                TDA_Domains tda_domains = oFILE_D.search(domains[j]);
                                if (tda_domains != null) {
                                    if (tda_domains.getValues() != null) {
                                        String elements[] = tda_domains.getValues().split(",");
                                        for (int k = 0; k < elements.length; k++) {
                                            if (j == 0)
                                                cb[antN].getItems().add(elements[k]);
                                            if (j == 1)
                                                cb[antN + 1].getItems().add(elements[k]);
                                        }
                                    }
                                }
                            }
                            HBox hbv = new HBox(5);
                            hbv.setAlignment(Pos.CENTER);
                            hbv.getChildren().addAll(cb[antN], lblT, cb[antN + 1]);
                            vbAUX.getChildren().addAll(hBox, hbv);
                            if (dom == 2)
                                antN += dom;
                            else
                                antN++;
                            System.out.println("edddddddd   " + antN);
                            arrayN.add(g);
                        }
                    }
                }
                btn[i] = new JFXButton("Agregar hecho");
                btn[i].setOnAction(event -> {
                    for (i = 0; i < rows_P.size(); i++) {
                        if (event.getSource() == btn[i]) {
                            for (int j = 0; j < array.size(); j++) {
                                String ele[] = array.get(j).split(",");
                                int p = Integer.parseInt(ele[0]);
                                if (i == p) {
                                    ban = false;
                                    int start, fina;
                                    start = Integer.parseInt(ele[1]);
                                    fina = Integer.parseInt(ele[2]);
                                    System.out.println("inicio     "+start+ "   final   "+fina);
                                    for (int r = start; r < fina; r++) {
                                        for (int c = 0; c < 5; c++) {
                                            System.out.println("Rangos  "+r+"   c  "+c);
                                            if (rb[r][c].isSelected()) {
                                                var += c;
                                                System.out.println("Si entre    con c en    "+c+ "    el valor eahora es   "+var);
                                            }
                                        }
                                    }
                                    prom = (var / (fina - start));

                                    System.out.println("var    sum  " + var + "    promedio  de  row  " + i + "  es   " + prom + "   predicate   " + rows_P.get(i) + "   resta  " + (fina - start));
                                    predi=addValueRules(rows_P.get(i).getName(), prom + "", null);
                                    factsBase.add(predi);
                                    fBase.setItems(empty);
                                    fBase.setItems(factsBase);
                                    if (prom >= 2.5) {
                                        //System.out.println("es mayor  en  posciion  " + i);
                                        fcBm.add(rows_P.get(i).getName());
                                        System.out.println("predicate  with value  "+predi);
                                    }
                                    var = 0;
                                    //agregar como hecho
                                }
                            }
                            if (ban)  //parte tiene(p,e)
                            {
                                for (int j = 0; j < arrayN.size(); j++) {
                                    String ele[] = arrayN.get(j).split(",");
                                    if (ele.length == 3) {
                                        int pos = Integer.parseInt(ele[0]);
                                        if (i == pos) {
                                            int one = 0, two = 0;
                                            one = Integer.parseInt(ele[1]);
                                            two = Integer.parseInt(ele[2]);
                                            String do1, do2;
                                            try {
                                                do1 = cb[one].getValue().toString();
                                                do2 = cb[two].getValue().toString();
                                                if (one != two) {
                                                    predi=addValueRules(rows_P.get(i).getName(),do1,do2);
                                                    System.out.println("Dom   1  " + do1 + "   predicado   " + i + "     dom   2  " + do2);
                                                }
                                                else
                                                {
                                                    predi=addValueRules(rows_P.get(i).getName(),do1,null);
                                                    System.out.println("Dom   1  " + do1 + "   predicado   " + i);
                                                }
                                                factsBase.add(predi);
                                                fBase.setItems(empty);
                                                fBase.setItems(factsBase);
                                                fcBm.add(rows_P.get(i).getName());
                                            } catch (Exception e) {
                                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                                alert.setTitle("Notice");
                                                alert.setContentText("Select an option, the combo cannot be empty!");
                                                alert.show();
                                            }

                                            //agregar como hecho
                                        }
                                    }
                                }
                            }
                            ban=true;
                            System.out.println("si clic   " + i);
                        }
                      //  vertical.getChildren().add(new Label(predi));
                    }
                });

                //vPredicate[i].getChildren().add(new Label(predi));
                btn[i].setStyle("-fx-background-color: #80F49C");
                btn[i].setPrefHeight(30);
                btn[i].setPrefWidth(150);
                btn[i].setAlignment(Pos.CENTER);
                vertical.getChildren().addAll(vbAUX, btn[i]);
            }
        } catch (Exception e) {
            System.out.println("Error aaaaa  " + e);
        }
    }

    public String addValueRules(String predicate, String a, String b) {
        String end = null;
        try {
            String pa[];
            pa = predicate.split("\\(");
            end = pa[0] + "(";
            pa = pa[1].split("\\)");
            pa = pa[0].split(",");
            if (pa.length == 2)
                end += a + "," + b + ")";
            else
                end += a + ")";
        } catch (Exception e) {
            System.out.println("Error to add elements " + e);
        }
        return end;
    }

    public JFXRadioButton[][] resizeArray(int resize, JFXRadioButton[][] a) {
        JFXRadioButton[][] b = new JFXRadioButton[resize][5];
        /* 1ºArg: Array origen,
         * 2ºArg: Por donde comienza a copiar en el origen
         * 3ºArg: Array destino
         * 4ºArg: Por donde comienza a copiar en el destino
         * 5ºArg: Numero de elementos que copiara del origen
         * */
        System.arraycopy(a, 0, b, 0, a.length);
        return b;
    }

    public JFXComboBox[] resizeArray(int resize, JFXComboBox[] a) {
        JFXComboBox[] b = new JFXComboBox[resize];
        System.arraycopy(a, 0, b, 0, a.length);
        return b;
    }

    public VBox symtop(ObservableList<String> rowElements, TDA_Predicates tda_predicates) {
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        HBox hBox;
        sizes += rowElements.size();
        if (sizes >= rb.length) {
            rb = resizeArray((rb.length + rowElements.size() + 1), rb);
        }
        Label lbl = new Label((tda_predicates.getName() + "  " + tda_predicates.getDescription()).toUpperCase());
        lbl.setStyle("-fx-background-color: #80F49C");
        lbl.setPrefWidth(1000);
        lbl.setAlignment(Pos.CENTER);
        JFXTextArea txtElements;
        vBox.getChildren().add(lbl);
        int elements = 0;
        for (int i = ant; i < (ant + rowElements.size()); i++) {

            tg = new ToggleGroup();
            hBox = new HBox(15);
            hBox.setAlignment(Pos.CENTER);
            txtElements = new JFXTextArea(rowElements.get(elements));
            txtElements.setPrefWidth(500);
            txtElements.setPrefHeight(50);
            txtElements.setEditable(false);
            elements++;
            hBox.getChildren().add(txtElements); ////addddddddddddddd
            for (int j = 0; j < 5; j++) {
                System.out.println("Inicio   ->  "+i+    "  end- --->    "+j );
                rb[i][j] = new JFXRadioButton(j + "");
                rb[i][j].setToggleGroup(tg);
                hBox.getChildren().addAll(rb[i][j]); ////addddddddddddddd
            }
            vBox.getChildren().addAll(hBox);
        }
        return vBox;
    }
}
