package pol.una.py.model.base;

public class Pila {
	class Nodo {
	    char simbolo;
	    Nodo sig;
	}
	
    private Nodo raiz;
    
    public Pila () {
        raiz=null;
    }
    
    public void insertar(char x) {
    	Nodo nuevo;
        nuevo = new Nodo();
        nuevo.simbolo = x;
        if (raiz==null)
        {
            nuevo.sig = null;
            raiz = nuevo;
        }
        else
        {
            nuevo.sig = raiz;
            raiz = nuevo;
        }
    }
    
    public char extraer ()
    {
        if (raiz!=null)
        {
            char informacion = raiz.simbolo;
            raiz = raiz.sig;
            return informacion;
        }
        else
        {
            return Character.MAX_VALUE;
        }
    }  
    
    public boolean vacia() {
        if (raiz==null) {
            return true;
        } else {
            return false;
        }
    }
}
