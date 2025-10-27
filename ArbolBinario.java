import java.util.Iterator;

/**
 * Ckase abstracta que modela un Arbol Binario de un tipo
 * genérico. La clase implementa la clase Coleccion lo que le
 * permite hacer acciones como inserción o eliminación de elementos.
 * 
 * @param <T> el tipo de elementos almacenados en el árbol.
 */
public abstract class ArbolBinario<T> implements Coleccion<T> {

    /**
     * Clase interna protegida que simula un nodo del árbol (Vértice).
     */
    protected class Vertice {

        /*El elemento del vértice. */
        public T elemento;

        /*El hijo izquierdo del vértice */
        public Vertice izquierdo;

        /*El hijo derecho del vértice */
        public Vertice derecho;

        /*El padre del vértice. */
        public Vertice padre;

        /**
         * Constructor que inicializa el vértice con un elemento.
         * El vértice no se inicializa con un padre, hijo izquierdo
         * o hijo derecho referenciados.
         * 
         * @param elemento El elemento del vértice.
         */
        public Vertice(T elemento) {
            this.elemento = elemento;
            this.izquierdo = null;
            this.derecho = null;
            this.padre = null;
        }

        /**
         * Método que determina si este vértice es hijo izquierdo de su padre.
         * 
         * @return {@code true} si  tiene padre y es su hijo izquierdo;
         * {@code false} en otro caso.
         */
        public boolean esHijoIzquierdo() {
            return padre != null && padre.izquierdo == this;
        }

        /**
         * Método que determina si este vértice es hijo derecho de su padre.
         * 
         * @return {@code true} si tiene padre y es su hijo derecho;
         * {@code false} en otro caso.
         */
        public boolean esHijoDerecho() {
            return padre != null && padre.derecho == this;
        }

        /**
         * Asigna un vértice como hijo izquierdo del vértice actual.
         * 
         * @param hijo El vértice que se asignará como hijo izquierdo
         */
        public void agregaIzquierdo(Vertice hijo) {
            izquierdo = hijo;
            if (hijo != null){ 
                hijo.padre = this;
            }
        }

        /**
         * Asigna un vértice como hijo derecho del vértice actual.
         * 
         * @param hijo El vértice que se asignará como hijo derecho.
         */
        public void agregaDerecho(Vertice hijo) {
            derecho = hijo;
            if (hijo != null) hijo.padre = this;
        }

        /**
         * Compara este vértice actual con otro para determinar 
         * si son iguales.
         * 
         * @param o el Vértice con el que se desea comparar.
         * @return {@code true} si el vértice a comparar es igual al actual;
         * {@code false} en otro caso.
         */
        @Override
        public boolean equals(Object o) {
            if(this == o){
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            @SuppressWarnings("unchecked")
            Vertice vertice = (Vertice) o;
            if(this.elemento == null){
                return vertice.elemento == null;
            }
            return this.elemento.equals(vertice.elemento);
        }

    }

    /*Raíz del árbol. */
    protected Vertice raiz;

    /*Número total de vértices en el árbol. */
    protected int tamanio;

    /**
     * Construcor que inicializa un árbol vacío.
     */
    public ArbolBinario() {
        this.raiz = null;
        this.tamanio = 0;
    }

    /**
     * Método abstracto para buscar un elemento en el árbol.
     * 
     * @param elemento El elemento a buscar en el árbol.
     * @param return {@code true} si el elemento a buscar está
     * en el árbol; {@code false} en otro caso.
     */
    @Override
    public abstract boolean buscar(T elemento);

    /**
     * Método que devuelve el elemento que está en la posición
     * {@code indice}, dado un recorrido del árbol.
     * 
     * @param indice La posición del elemento dado un recorrido del árbol.
     * @return El elemento correspondiente en el {@code indice}.
     */
    public T acceder(int indice) {
        ListaDoblementeLigada<T> recorrido = devolverRecorrido();
        return recorrido.acceder(indice);
    }

    /**
     * Método que devuelve el número de vértices en el árbol.
     * @return El número de vértices en el árbol.
     */
    public int devolverTamanio() {
        return tamanio;
    }

    /**
     * Método que nos devuelve un valor booleano que nos dice
     * si el árbol está vacío o no.
     * 
     * @return {@code true} Si el árbol está vacío. {@code false}
     * en otro caso.
     */
    public boolean estaVacio() {
        return raiz == null && tamanio == 0;
    }

    /**
     * Método abstracto que inserta un nuevo elemento
     * en el árbol.
     * 
     * 
     * @param elemento El elemento a insertar en el árbol.
     */
    @Override
    public abstract void insertar(T elemento);

    /**
     * Método abstracto que elimina un elemento contenido
     * en el árbol.
     * 
     * @param elemento El elemento a eliminar del árbol.
     */
    @Override
    public abstract void eliminar(T elemento);

    /**
     * Método que devuelve una lista con los elementos del árbol
     * siguiendo un recorrido especifico.
     * 
     * @return Una lista doblemente ligada con los vértices siguiendo
     * el recorrido.
     */
    protected abstract ListaDoblementeLigada<T> devolverRecorrido();

    /**
     * Método que permite recorrer el árbol con un iterador.
     * 
     * @return Un iterador que recorre el árbol según su recorrido.
     */
    @Override
    public Iterator<T> iterator() {
        return devolverRecorrido().iterator();
    }
}
