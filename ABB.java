package aed;
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    
    private Nodo raiz;
    private int cardinal;
    private int altura;

    private class Nodo {
        T valor;
        Nodo izquierda;
        Nodo derecha;
        Nodo padre; 
        Nodo(T v){
            valor = v;
            izquierda = null;
            derecha = null;
            padre = null;
        }    
    }

    public ABB() {
        Nodo raiz = null;
        int cardinal = 0;
        int altura = 0;
    }

    public int cardinal() {
        int res = cardinal;
        return res;
    }

    public T minimo(){
        Nodo res = raiz;
        boolean flag = true;
        while(flag){
            if (res.izquierda != null){
                res = res.izquierda;
            } else {
                flag = false;
            }
            
        }
        return res.valor;
    }

    public T maximo(){
        Nodo res = raiz;
        boolean flag = true;
        while(flag){
            if (res.derecha != null){
                res = res.derecha;
            } else {
                flag = false;
            }
            
        }
        return res.valor;
    }

    public void insertar(T elem){ 
        Nodo nodo = raiz;
        Nodo nodo_elem = new Nodo(elem);
        boolean flag = true;
        if (nodo == null){
            raiz = nodo_elem;
            cardinal = cardinal +1;
            return; 
        }
        while (flag){
            if (nodo.valor.compareTo(elem) != 0){ // si el nodo.value en el que estoy no es elem
                if(nodo.valor.compareTo(elem) > 0){ // me fijo si es mayor que elem 
                    if (nodo.izquierda != null){               
                        nodo = nodo.izquierda;
                        
                    } else {
                        nodo.izquierda = nodo_elem;
                        nodo.izquierda.padre = nodo;
                        flag = false;
                    }
                } else {
                    if (nodo.derecha != null){ 
                        nodo = nodo.derecha;
                            
                    }else {
                        nodo.derecha = nodo_elem;
                        nodo.derecha.padre = nodo;
                        flag = false;
                    }
                }
                if (flag == false){
                    cardinal += 1;
                }
            }  else {
                flag = false;
            }
        }
    }
    public boolean pertenece(T elem){
        boolean res = false;
        Nodo nodo = raiz;
        Nodo nodo_padre = null;
        boolean flag = true;
        if (nodo == null){
            return res;
        } else{
            while (flag){
                if (nodo.valor.compareTo(elem) != 0){ // LLegué al elem?
                    if (nodo.valor.compareTo(elem) > 0){
                        if (nodo.izquierda != null){
                            nodo_padre = nodo;
                            nodo = nodo.izquierda;
                        } else {
                            return false;
                        }
                    } else {
                        if (nodo.derecha != null){
                            nodo_padre = nodo;
                            nodo = nodo.derecha;
                        } else {
                            return false;
                        }
                    }
                } else {
                    flag = false;
                    res = true;
                }
           }            
        }
        return res;
        }

    public void eliminar(T elem){
        Nodo nodo = raiz;
        boolean flag = true;
        if (pertenece(elem) == false){
            return;
        } else {
            //Nodo nodo_izq = nodo.izquierda;
            //Nodo nodo_der = nodo.derecha;
            
            flag = true;
            while(flag){
                Nodo nodo_padre = nodo.padre;
                if (nodo.valor.compareTo(elem) != 0){ //ME MUEVO
                    if (nodo.valor.compareTo(elem) > 0){ //me muevo a la izquierda
                        nodo_padre = nodo;
                        nodo = nodo.izquierda;
                    } else { //me muevo a la derecha
                        nodo_padre = nodo;
                        nodo = nodo.derecha;
                    }
                } else { //Llegué al nodo!
                    if (nodo == raiz && hijos(nodo) == 0){
                        nodo = null;
                        raiz = null;
                    }else if (hijos(nodo) == 0){                    
                        if (nodo.padre.valor.compareTo(elem) > 0){
                            nodo.padre.izquierda = null;
                        } else {
                            nodo.padre.derecha = null;
                        }
                    }else if(nodo == raiz && hijos(nodo) == 1){
                        raiz = raiz.izquierda;
                    } else if (nodo == raiz && hijos(nodo) == 2){
                        raiz = raiz.derecha;
                    } else if(hijos(nodo) == 1){
                        if (nodo_padre.izquierda == nodo) {
                            nodo_padre.izquierda = nodo.izquierda;
                        } else {
                            nodo_padre.derecha = nodo.izquierda;
                        }
                        //nodo = nodo.izquierda;
                        nodo.padre = nodo_padre;
                        
                    } else if (hijos(nodo) == 2){
                        if (nodo_padre.izquierda == nodo) {
                            nodo_padre.izquierda = nodo.derecha;
                        } else {
                            nodo_padre.derecha = nodo.derecha;
                        }
                        //nodo = nodo.derecha;
                        nodo.padre = nodo_padre;
                    } else if(nodo != raiz && hijos(nodo) == 3){
                        //nodo_padre = nodo.padre;
                        T min = minimo_desde_nodo(nodo.derecha);
                        eliminar(minimo_desde_nodo(nodo.derecha));
                        nodo.valor = min;
                        //nodo.padre = nodo_padre;
                        cardinal += 1;
                    } else {
                        //nodo_padre = nodo.padre;
                        T min = minimo_desde_nodo(nodo.derecha);
                        eliminar(minimo_desde_nodo(nodo.derecha));
                        nodo.valor = min;
                        //nodo.padre = nodo_padre;
                        cardinal += 1;
                        raiz.valor = min;    
                    }
                
                    flag = false;
                }
            } 
            cardinal -= 1;
        }
    }
    public T minimo_desde_nodo(Nodo nodo){
        Nodo res = nodo;
        boolean flag = true;
        while(flag){
            if (res.izquierda != null){
                res = res.izquierda;
            } else {
                flag = false;
            }
            
        }
        return res.valor;

    }
    public int hijos(Nodo nodo){
        int res = 0;
        if (nodo.izquierda != null){
            res = 1;
            if (nodo.derecha != null){
                res = 3;
            } 
        } else if(nodo.derecha != null) {
            res = 2;
        }
    return res;
    }


    public String toString(){
        throw new UnsupportedOperationException("No implementada aun");
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        public boolean haySiguiente() {            
            throw new UnsupportedOperationException("No implementada aun");
        }
    
        public T siguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
