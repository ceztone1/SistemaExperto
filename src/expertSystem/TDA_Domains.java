package expertSystem;
/*
* Clase de TDA de dominios
 */
public class TDA_Domains {
    String name;
    String values;

    public TDA_Domains(String name, String values) {
        this.name = name;
        this.values = values;
    }
    public TDA_Domains(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }


}
