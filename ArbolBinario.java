import java.util.Iterator;

public abstract class ArbolBinario<T> implements Coleccion<T> {

    protected class Vertice {

        public T elemento;

        public Vertice izquierdo;

        public Vertice derecho;

        public Vertice padre;

        public Vertice(T elemento) {
            this.elemento = elemento;
            this.izquierdo = null;
            this.derecho = null;
            this.padre = null;
        }

        public boolean esHijoIzquierdo() {
            return padre != null && padre.izquierdo == this;
        }

        public boolean esHijoDerecho() {
            return padre != null && padre.derecho == this;
        }

        public void agregaIzquierdo(Vertice hijo) {
            izquierdo = hijo;
            if (hijo != null){ 
                hijo.padre = this;
            }
        }

        public void agregaDerecho(Vertice hijo) {
            derecho = hijo;
            if (hijo != null) hijo.padre = this;
        }
    }

    protected Vertice raiz;

    protected int tamanio;

    public ArbolBinario() {
        this.raiz = null;
        this.tamanio = 0;
    }

    @Override
    public abstract boolean buscar(T elemento);

    public T acceder(int indice) {
        ListaDoblementeLigada<T> recorrido = devolverRecorrido();
        return recorrido.acceder(indice);
    }

    public int devolverTamanio() {
        return tamanio;
    }

    public boolean estaVacio() {
        return raiz == null && tamanio == 0;
    }

    @Override
    public abstract void insertar(T elemento);

    @Override
    public abstract void eliminar(T elemento);

    protected abstract ListaDoblementeLigada<T> devolverRecorrido();

    @Override
    public Iterator<T> iterator() {
        return devolverRecorrido().iterator();
    }
}
