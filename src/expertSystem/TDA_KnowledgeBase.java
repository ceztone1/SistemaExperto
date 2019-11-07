package expertSystem;
/*
 * Clase de TDA de base de conocimiento
 */
public class TDA_KnowledgeBase {
    int key;

    public TDA_KnowledgeBase(int key, String ant1, String ant2, String ant3, String ant4, String ant5, String ant6, String cons,boolean gui) {
        this.key = key;
        this.ant1 = ant1;
        this.ant2 = ant2;
        this.ant3 = ant3;
        this.ant4 = ant4;
        this.ant5 = ant5;
        this.ant6 = ant6;

        this.cons = cons;
        this.gui=gui;
    }

    String ant1,ant2,ant3,ant4,ant5,ant6;
    String cons;

    public boolean isGui() {
        return gui;
    }

    public void setGui(boolean gui) {
        this.gui = gui;
    }

    boolean gui;
    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getAnt1() {
        return ant1;
    }

    public void setAnt1(String ant1) {
        this.ant1 = ant1;
    }

    public String getAnt2() {
        return ant2;
    }

    public void setAnt2(String ant2) {
        this.ant2 = ant2;
    }

    public String getAnt3() {
        return ant3;
    }

    public void setAnt3(String ant3) {
        this.ant3 = ant3;
    }

    public String getAnt4() {
        return ant4;
    }

    public void setAnt4(String ant4) {
        this.ant4 = ant4;
    }

    public String getAnt5() {
        return ant5;
    }

    public void setAnt5(String ant5) {
        this.ant5 = ant5;
    }

    public String getAnt6() {
        return ant6;
    }

    public void setAnt6(String ant6) {
        this.ant6 = ant6;
    }


    public String getCons() {
        return cons;
    }

    public void setCons(String cons) {
        this.cons = cons;
    }


}
