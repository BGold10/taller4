package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
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
        T nodo = raiz.valor;
        boolean flag = true;
        while (flag){
            if (nodo != elem){
                if(nodo < elem){
                    nodo = nodo.derecha;
                }
            } else {
                flag = false;
            }
        }
    }

    public boolean pertenece(T elem){
        throw new UnsupportedOperationException("No implementada aun");
    }

    public void eliminar(T elem){
        throw new UnsupportedOperationException("No implementada aun");
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
