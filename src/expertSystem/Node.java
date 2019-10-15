package expertSystem;

public class Node {
    TDA_Index info;
    Node izq;
    Node der;
    public Node(TDA_Index I)
    {
        info=I;
        izq=null;
        der=null;
    }
}
