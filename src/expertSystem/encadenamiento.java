package expertSystem;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

public class encadenamiento {

   public  ArrayList<String> adelante(ObservableList<TDA_KnowledgeBase> BC,Stack<String> BH) {
        ArrayList<String> reglasUsadas = new ArrayList<>();
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
       alert.setHeaderText("Alert");
       alert.setTitle("Successsful");
        Boolean bandera = true;
        String reglaUtilizada = "";
        String nuevosHechos;
        String ultimaRegla = "";
        ArrayList<String> conjuntoConflicto = new ArrayList<>();
        conjuntoConflicto.add(" ");
        while (!conjuntoConflicto.isEmpty() && bandera) {
            conjuntoConflicto = equiparar(BH, BC);
            if (!conjuntoConflicto.isEmpty()) {
                reglaUtilizada = resolucion(conjuntoConflicto);
                String finalReglaUtilizada = reglaUtilizada;
                conjuntoConflicto.removeIf(x -> (x == finalReglaUtilizada));
                nuevosHechos = aplicar(reglaUtilizada, BH, BC);
                reglasUsadas.add(reglaUtilizada);
                BC.set(Integer.parseInt(reglaUtilizada) - 1, new TDA_KnowledgeBase(Integer.parseInt(reglaUtilizada) - 1, "", "", "", "", "", "", "",true));
                actualizar(BH, nuevosHechos);
                if (nuevosHechos.equalsIgnoreCase("noToc(p)") || nuevosHechos.equalsIgnoreCase("TOC(p)")) {
                    bandera = false;
                }
                conjuntoConflicto = equiparar(BH, BC);
            }
        }
       System.out.println("ultima regla "+ultimaRegla);
        ultimaRegla = BH.pop();
        if(ultimaRegla.equalsIgnoreCase("noToc(p)")){
            alert.setContentText("no tienes toc");


        }else{
            if(ultimaRegla.equalsIgnoreCase("toc(p)")){
                alert.setContentText("tienes toc");
            }else{
                alert.setContentText(ultimaRegla);

            }

        }
        alert.show();
        return reglasUsadas;
    }

    ArrayList<String> equiparar(Stack<String> BaseH, ObservableList<TDA_KnowledgeBase> BaseC) {
        boolean existeBH = false;
        String[] arrayaux;
        int cont = 0;
        ArrayList<TDA_KnowledgeBase> equiparar = new ArrayList<>();
        ArrayList<String> aux = new ArrayList<>();

        for (int j = 0; j < BaseC.size(); j++) {
            cont = 0;
            if (BaseH.contains(BaseC.get(j).getAnt1()) && numAntecedentes(BaseC, BaseC.get(j).getKey() - 1) == 1) {
                existeBH = true;
            } else {
                if (BaseH.contains(BaseC.get(j).getAnt1())) {
                    cont++;
                    if (cont == numAntecedentes(BaseC, BaseC.get(j).getKey() - 1)) {
                        equiparar.add(new TDA_KnowledgeBase(BaseC.get(j).getKey(), numAntecedentes(BaseC, BaseC.get(j).getKey() - 1)));
                    }
                } else {
                    existeBH = false;
                }
                if (BaseH.contains(BaseC.get(j).getAnt2())) {
                    cont++;
                    if (cont == numAntecedentes(BaseC, BaseC.get(j).getKey() - 1)) {
                        equiparar.add(new TDA_KnowledgeBase(BaseC.get(j).getKey(), numAntecedentes(BaseC, BaseC.get(j).getKey() - 1)));
                    }

                } else {
                    existeBH = false;
                }
                if (BaseH.contains(BaseC.get(j).getAnt3())) {
                    cont++;
                    if (cont == numAntecedentes(BaseC, BaseC.get(j).getKey() - 1)) {
                        equiparar.add(new TDA_KnowledgeBase(BaseC.get(j).getKey(), numAntecedentes(BaseC, BaseC.get(j).getKey() - 1)));
                    }
                } else {
                    existeBH = false;
                }
                if (BaseH.contains(BaseC.get(j).getAnt4())) {
                    if (cont == numAntecedentes(BaseC, BaseC.get(j).getKey() - 1)) {
                        equiparar.add(new TDA_KnowledgeBase(BaseC.get(j).getKey(), numAntecedentes(BaseC, BaseC.get(j).getKey() - 1)));
                    }
                    cont++;
                } else {
                    existeBH = false;
                }
                if (BaseH.contains(BaseC.get(j).getAnt5())) {
                    if (cont == numAntecedentes(BaseC, BaseC.get(j).getKey() - 1)) {
                        equiparar.add(new TDA_KnowledgeBase(BaseC.get(j).getKey(), numAntecedentes(BaseC, BaseC.get(j).getKey() - 1)));
                    }
                    cont++;
                } else {
                    existeBH = false;
                }
                if (BaseH.contains(BaseC.get(j).getAnt6())) {
                    if (cont == numAntecedentes(BaseC, BaseC.get(j).getKey() - 1)) {
                        equiparar.add(new TDA_KnowledgeBase(BaseC.get(j).getKey(), numAntecedentes(BaseC, BaseC.get(j).getKey() - 1)));
                    }
                    cont++;
                } else {
                    existeBH = false;
                }
            }
            if (existeBH) {
                equiparar.add(new TDA_KnowledgeBase(BaseC.get(j).getKey(), numAntecedentes(BaseC, BaseC.get(j).getKey() - 1)));
            }
        }


        Collections.sort(equiparar, new Comparator<TDA_KnowledgeBase>() {
            @Override
            public int compare(TDA_KnowledgeBase p1, TDA_KnowledgeBase p2) {
                return new Integer(p1.getKey()).compareTo(new Integer(p2.getKey()));
            }
        });

        //problema con 2 digitos

        for (int i = 0; i < equiparar.size(); i++) {

            aux.add(equiparar.get(i).getKey() + "");
        }


        return aux;

    }

    String resolucion(ArrayList<String> conjuntoConflicto) {
        return conjuntoConflicto.get(0);
    }


    String aplicar(String reglaUtilizada, Stack<String> BaseH, ObservableList<TDA_KnowledgeBase> BaseC) {
        return BaseC.get(Integer.parseInt(reglaUtilizada) - 1).getCons();
    }

    void actualizar(Stack<String> BaseH, String nuevoHecho) {
        BaseH.push(nuevoHecho);
    }

    int numAntecedentes(ObservableList<TDA_KnowledgeBase> BaseC, int key) {
        int cont = 0;
        if (!BaseC.get(key).getAnt1().replace("\u0000", "").equalsIgnoreCase(" ")) {
            cont++;
        }
        if (!BaseC.get(key).getAnt2().replace("\u0000", "").equalsIgnoreCase(" ")) {
            cont++;
        }
        if (!BaseC.get(key).getAnt3().replace("\u0000", "").equalsIgnoreCase(" ")) {
            cont++;
        }
        if (!BaseC.get(key).getAnt4().replace("\u0000", "").equalsIgnoreCase(" ")) {
            cont++;
        }
        if (!BaseC.get(key).getAnt5().replace("\u0000", "").equalsIgnoreCase(" ")) {
            cont++;
        }
        if (!BaseC.get(key).getAnt6().replace("\u0000", "").equalsIgnoreCase(" ")) {
            cont++;
        }
        return cont;

    }
/*
    boolean esAntecedente(String regla, ObservableList<TDA_KnowledgeBase> BaseC) {
        for (int j = 0; j < BaseC.size(); j++) {
            if (BaseC.get(j).getAnt1().equalsIgnoreCase(regla)) {
                return true;
            }
            if (BaseC.get(j).getAnt2().equalsIgnoreCase(regla)) {
                return true;
            }
            if (BaseC.get(j).getAnt3().equalsIgnoreCase(regla)) {
                return true;
            }
            if (BaseC.get(j).getAnt4().equalsIgnoreCase(regla)) {
                return true;
            }
            if (BaseC.get(j).getAnt5().equalsIgnoreCase(regla)) {
                return true;
            }
            if (BaseC.get(j).getAnt6().equalsIgnoreCase(regla)) {
                return true;
            }
        }
        return false;
    }
*/

}
