package expertSystem;
/*
* Clase Nodo (Utilizada por el arbol se genera un nuevo nodo cada que existe un nuevo registro en index)
* */
public class Node {
    public TDA_Index info;
    Node izq;
    Node der;
    public Node(TDA_Index I)
    {
        info=I;
        izq=null;
        der=null;
    }
}
