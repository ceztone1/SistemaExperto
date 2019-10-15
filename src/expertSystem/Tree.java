package expertSystem;

public class Tree {
    Node root;
    String men="";
    public Tree()
    {
        root=null;
    }
    public boolean emptyTree()
    {
        return root==null;
    }
    public void insert(TDA_Index dato)
    {
        Node ant,rec;
        if(emptyTree())
            root=new Node(dato);
        else
        {
            rec=root;
            ant=rec;
            while(rec!=null)
            {
                ant=rec;
                if(dato.key<rec.info.key)
                    rec=rec.izq;
                else
                    rec=rec.der;
            }
            if(dato.key<ant.info.key)
                ant.izq=new Node(dato);
            else
                ant.der=new Node(dato);
        }
    }
    public boolean search(int dato)
    {
        Node rec, ant;

        if(!emptyTree())
        {
            rec=root;
            ant=rec;
            while(rec!=null)
            {
                while(rec!=null)
                {
                    ant=rec;
                    if(dato<rec.info.key)
                        rec=rec.izq;
                    else
                        rec=rec.der;
                }
                if(dato==ant.info.key)
                    return true;
                else
                    return false;
            }
        }
        return false;
    }
    public Node seaNode(int dato)
    {
        Node rec, ant;

        if(!emptyTree())
        {
            rec=root;
            ant=rec;
            while(rec!=null)
            {
                while(rec!=null)
                {
                    ant=rec;
                    if(dato<rec.info.key)
                        rec=rec.izq;
                    else
                        rec=rec.der;
                }
                if(dato==ant.info.key)
                    return ant;
                else
                    return null;
            }
        }
        return null;
    }
    public String in_orden(Node nodo)
    {
        if(nodo!=null)
        {
            in_orden(nodo.izq);
            men=men+nodo.info.key+ " ";
            in_orden(nodo.der);
        }
        return men;
    }
    public String pre_orden(Node nodo)
    {
        if(nodo!=null)
        {
            men=men+nodo.info.key+ " ";
            pre_orden(nodo.izq);
            pre_orden(nodo.der);
        }
        return men;
    }
    public String pos_orden(Node nodo)
    {
        if(nodo!=null)
        {
            pos_orden(nodo.izq);
            pos_orden(nodo.der);
            men=men+nodo.info.key+ " ";
        }
        return men;
    }
    public int nivel(int dato)
    {
        Node rec, ant;
        int con=-1;
        if(search(dato))
        {
            if(!emptyTree())
            {
                if(root.info.key==dato)
                    return 0;
                else
                {
                    rec=root;
                    ant=rec;
                    while(rec!=null)
                    {
                        ant=rec;
                        if(dato<rec.info.key)
                            rec=rec.izq;
                        else
                            rec=rec.der;
                        con=con+1;
                    }
                }
            }
        }

        return con;
    }
   /* public String sucesores(int dato)
    {
        men="";
        Node rec, ant;
        if(!arbol_vacio())
        {
            rec=root;
            ant=rec;
            while(rec!=null)
            {
                ant=rec;
                //   men=men+rec.info+" ";
                if(dato<rec.info.key)
                    rec=rec.izq;
                else
                    rec=rec.der;
            }
            if(ant.info.key==dato)
            {
                return in_orden(ant);
            }
        }
        return men="No hay";
    }*/
   /* public String ancestros(int dato)
    {
        men="";
        Node rec, ant;
        {
            rec=root;
            ant=rec;
            while(rec.info.key!=dato && rec!=null)
            {
                men=men+rec.info+ ",";
                ant=rec;
                if(dato<rec.info.key)
                    rec=rec.izq;
                else
                    rec=rec.der;
            }
            if(rec.info.key==dato)
                return men;
            else
                return "No existe el dato";
        }

     /*   if(nodo!=raiz)
        {

            in_orden(nodo.izq);
            men=men+nodo.info+ " ";
            in_orden(nodo.der);

        }*---
        //   return men;
    }*/
    /*  public void eliminar (Nodo nodo)
      {
          Nodo rec, ant;
          if(!arbol_vacio())
          {
              rec=raiz;
              ant=rec;
              while(rec!=nodo && rec!=null)
              {
                  ant=rec;
                  if(nodo.info<rec.info)
                      rec=rec.izq;
                  else
                      rec=rec.der;
              }
              if(ant.info==nodo.info)

          }
      }*/
    public boolean delete(int dato)
    {
        Node rec, ant;
        rec=root;
        ant=rec;
        boolean izq=true;
        while(rec.info.key!=dato)
        {
            ant=rec;
            if(dato<rec.info.key)
            {
                izq=true;
                rec=rec.izq;
            }
            else
            {
                izq=false;
                rec=rec.der;
            }
            if(rec==null)
                return false;
        }
        if(rec.izq==null && rec.der==null)
        {
            if(rec==root)
                root=null;
            else
            {
                if(izq)
                    ant.izq=null;
                else
                    ant.der=null;
            }
        }
        else
        {
            if(rec.der==null)
            {
                if(rec==root)
                    root=rec.izq;
                else
                {
                    if(izq)
                        ant.izq=rec.izq;
                    else
                        ant.der=rec.izq;
                }
            }
            else
            {
                if(rec.izq==null)
                {
                    if(rec==root)
                        root=rec.der;
                    else
                    {
                        if(izq)
                            ant.izq=rec.der;
                        else
                            ant.der=rec.der;
                    }
                }
                else
                {
                    Node rem=NodoR(rec);
                    if(rec==root)
                        root=rem;
                    else
                    if(izq)
                        ant.izq=rem;
                    else
                        ant.der=rem;
                    rem.der=rec.der;
                }
            }
        }
        return true;
    }
    public Node NodoR(Node nodo)
    {
        Node Rant, rem,rec;
        Rant=nodo;
        rem=Rant;
        rec=nodo.izq;
        while(rec!=null)
        {
            Rant=rem;
            rem=rec;
            rec=rec.der;
        }
        if(rem!=nodo.izq)
        {
            Rant.der=rem.izq;
            rem.izq=nodo.izq;
        }
        return rem;
    }
   /* public void eliminar3(int dato)
    {
        Node rec, ant, antant;
        if (!arbol_vacio())
        {
            rec=root;
            ant=rec;

            while(rec!=null)
            {
                ant=rec;
                antant=ant;
                if(dato<rec.info.key)
                {
                    antant=ant.izq;
                    rec=rec.izq;
                }
                else
                {
                    antant=ant.der;
                    rec=rec.der;
                }

            }
            if(dato==ant.info.key && ant.izq==null && ant.der==null)
                antant=null;
        }
    }*/
}
