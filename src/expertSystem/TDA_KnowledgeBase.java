package expertSystem;

public class TDA_KnowledgeBase {
    int key;
    public TDA_KnowledgeBase(){}
    public TDA_KnowledgeBase(int key, String ant1, String ant2, String ant3, String ant4, String ant5, String ant6, String ant7, String ant8, String ant9, String ant10, String cons) {
        this.key = key;
        this.ant1 = ant1;
        this.ant2 = ant2;
        this.ant3 = ant3;
        this.ant4 = ant4;
        this.ant5 = ant5;
        this.ant6 = ant6;
        this.ant7 = ant7;
        this.ant8 = ant8;
        this.ant9 = ant9;
        this.ant10 = ant10;
        this.cons = cons;
    }

    String ant1,ant2,ant3,ant4,ant5,ant6,ant7,ant8,ant9,ant10;
    String cons;
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

    public String getAnt7() {
        return ant7;
    }

    public void setAnt7(String ant7) {
        this.ant7 = ant7;
    }

    public String getAnt8() {
        return ant8;
    }

    public void setAnt8(String ant8) {
        this.ant8 = ant8;
    }

    public String getAnt9() {
        return ant9;
    }

    public void setAnt9(String ant9) {
        this.ant9 = ant9;
    }

    public String getAnt10() {
        return ant10;
    }

    public void setAnt10(String ant10) {
        this.ant10 = ant10;
    }

    public String getCons() {
        return cons;
    }

    public void setCons(String cons) {
        this.cons = cons;
    }


}
