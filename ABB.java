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
        } else {
            Nodo nodo_izq = nodo.izquierda;
            Nodo nodo_der = nodo.derecha;
            while (flag){
                if (nodo.valor != elem){ // si el nodo.value en el que estoy no es elem
                    if(nodo.valor.compareTo(elem) > 0){ // me fijo si es mayor que elem 
                        if (nodo.izquierda != null){               
                            if (nodo.izquierda.valor.compareTo(elem) < 0){ //me fijo si el proximo no es elem para  (me di cuenta que está mal porque estoy reemplazando a un elemento en caso de que no esté)
                                nodo_izq = nodo.izquierda;
                                nodo.izquierda = nodo_elem;
                                nodo.izquierda.izquierda = nodo_izq;
                                flag = false;
                            } else { //sino me voy a la izquierda
                                nodo = nodo.izquierda;
                          }
                        } else {
                            nodo.izquierda = nodo_elem;
                            flag = false;
                        }
                    } else {
                        if (nodo.derecha != null){ 
                            if (nodo.derecha.valor.compareTo(elem) > 0){ //lo mismo
                                nodo_der = nodo.derecha;
                                nodo.derecha = nodo_elem;
                                nodo.derecha.derecha = nodo_der;
                                flag = false;
                            } else {
                            nodo = nodo.derecha;
                            } 
                        }else {
                            nodo.derecha = nodo_elem;
                            flag = false;
                        }
                    }
                    if (flag == false){
                        cardinal += 1;
                    }
                } else {
                    flag = false;
                }
            }
            }
        }
    public boolean pertenece(T elem){
        boolean res = false;
        Nodo nodo_elem = new Nodo(elem);
        Nodo nodo = raiz;
        boolean flag = true;
        if (nodo == null){
            return res;
        } else{
            while (flag){
                if (nodo.valor != nodo_elem.valor){
                    if (nodo.valor.compareTo(elem) > 0){
                        if (nodo.izquierda != null){
                            nodo = nodo.izquierda;
                        } else {
                            return false;
                        }
                    } else {
                        if (nodo.derecha != null){
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
        Nodo nodo_elem = new Nodo(elem);
        boolean flag = true;
        if (pertenece(elem) == false){
            return;
        } else {
            Nodo nodo_izq = nodo.izquierda;
            Nodo nodo_der = nodo.derecha;
            flag = true;
            while(flag){
                if (nodo.valor != elem){
                    if (nodo.valor.compareTo(elem) > 0){
                        nodo.padre = nodo;
                        nodo = nodo.izquierda;
                    } else {
                        nodo.padre = nodo;
                        nodo = nodo.derecha;
                    }
                } else {
                    if (nodo.izquierda == null){
                        nodo = null;
                    } else if (nodo.derecha == null){
                        nodo = null;
                    } else if (nodo.derecha.derecha == null){

                    } 
                    nodo_izq = nodo.izquierda.izquierda;
                    nodo_der = nodo.derecha.derecha;
                    nodo = nodo.padre; //RECORDAR CAMBIARLO SIEMPRE QUE ME MUEVO    
                    nodo.izquierda = nodo_izq;
                    nodo.derecha = nodo_der;
                    cardinal -= 1; 
                    flag = false;
                }
            }
        }
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
