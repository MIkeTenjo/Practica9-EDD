import java.util.NoSuchElementException;

/**
 * Clase que simula un Árbol Binario Completo.
 * 
 * Un Árbol Binario Completo tiene la caracteristica en el que 
 * todos sus niveles están llenos excpeto posiblemente el último, además
 * todos sus vértices están acomodados de izquierda a derecha, de modo
 * que si el último nivel tiene un "hoyo", este hoyo siempre estará
 * del lado derecho.
 * 
 * @param <T> El tipo de los elementos almacenados en el árbol.
 */
public class ArbolBinarioCompleto<T> extends ArbolBinario<T> {
    
    /**El último vértice agregado del árbol. */
    private Vertice ultimoAgregado;

    /**
     * Constructor que inicializa un árbol binario completo vacío.
     */
    public ArbolBinarioCompleto() {
        super();
        ultimoAgregado = null;
    }

    /**
     * Método auxiliar que determina si un vértice es la ráiz del árbol.
     * 
     * @param vertice El vértice a verificar si es la raíz del árbol.
     * @return {@code true} si {@code vertice} es la ráiz del árbol. {@code false}
     * en otro caso.
     */
    private boolean esRaiz(Vertice vertice){
        return raiz != null && vertice != null && vertice.padre == null && raiz.equals(vertice);
    }

    /**
     * Inserta un nuevo elemento en el árbol manteniendo la propiedad de 
     * ser completo.
     * 
     * @param elemento El elemento a insertar en el árbol en el último nivel,
     * lo más a lo izquierda posible.
     * @throws IllegalArgumentException Si el elemento es nulo.
     */
    @Override
    public void insertar(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento a agregar es nulo.");
        }
        Vertice n = new Vertice(elemento);
        if (estaVacio()) {
            raiz = n;
            ultimoAgregado = n;
        }else{
            Vertice vi = ultimoAgregado;
            if (vi.esHijoIzquierdo()) {
                vi.padre.derecho = n;
                ultimoAgregado = n;
                tamanio++;
                return;
            }
            while (vi.esHijoDerecho()) {
                vi = vi.padre;
            }
            if (!esRaiz(vi)) {
                vi = vi.padre.derecho;
            }
            agregaEnPrimerEspacioIzquierdo(vi, n);
        }
        tamanio++;
    }

    /**
     * Método que elimina un elemento del árbol manteniendo la propiedad
     * de ser completo.
     * 
     * @param elemento El elemento a eliminar del árbol.
     * @throws IllegalArgumentException Si el elemento a querer eliminar
     * es nulo.
     * @throws NoSuchElementException Si el árbol está vacío.
     */
    @Override
    public void eliminar(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento es nulo.");
        }
        if (estaVacio()) {
            throw new NoSuchElementException("El árbol está vacío.");
        }
        Vertice eliminar = null;
        Vertice ultimoVertice = null;
        Cola<Vertice> cola = new Cola<Vertice>();
        cola.meter(raiz);
        while (!cola.estaVacia()) {
            Vertice actual = cola.sacar();
            ultimoVertice = actual;
            if (actual.elemento.equals(elemento)) {
                eliminar = actual;
            }
            if (actual.izquierdo != null) {
                cola.meter(actual.izquierdo);
            }
            if (actual.derecho != null) {
                cola.meter(actual.derecho);
            }
        }
        if (eliminar == null) {
            return;
        }
        eliminar.elemento = ultimoVertice.elemento;
        if (esRaiz(ultimoVertice)) {
            raiz = null;
        }else{
            Vertice p = ultimoVertice.padre;
            if (p.izquierdo.equals(ultimoVertice)) {
                p.izquierdo = null;
            }else{
                p.derecho = null;
            }
        }
        tamanio--;
        actualizarUltimoAgregado();
    }

    /**
     * Actualiza el valor del último vértice agregado en el árbol.
     */
    private void actualizarUltimoAgregado() {
        if (estaVacio()) {
            ultimoAgregado = null;
            return;
        }

        Cola<Vertice> cola = new Cola<>();
        cola.meter(raiz);
        Vertice ultimo = null;

        while (!cola.estaVacia()) {
            ultimo = cola.sacar();

            if (ultimo.izquierdo != null) {
                cola.meter(ultimo.izquierdo);
            }
            if (ultimo.derecho != null) {
                cola.meter(ultimo.derecho);
            }
        }

        ultimoAgregado = ultimo;
    }

    /**
     * Busca si un elemento pertenece al árbol.
     * @param elemento el elemento a buscar en el arbol.
     * @return true en caso de que el elemento se encuentre en el arbol, false en caso contrario.
     */
    @Override
    public boolean buscar(T elemento) {
        return buscar(elemento, raiz);
    }

    /**
     * Busca un elemento en el arbol de forma recursiva, buscando vertice por vertice.
     * @param elemento el elemento a buscar en el arbol.
     * @param v el vertice a partir del cual se hara la busqueda.
     * @return true en caso de que el elemento se encuentre en el arbol, false en caso contrario.
     */
    private boolean buscar(T elemento, Vertice v){
        if (v == null) {
            return false;
        }
        if (v.elemento.equals(elemento)) {
            return true;
        }
        return buscar(elemento, v.izquierdo) || buscar(elemento, v.derecho);    
    }

    /**
     * Devuelve el arbol en forma de lista utilizando el recorrido BFS.
     * @return Una lista doblemente ligada que contiene los elementos del arbol siguiendo el orden de un recorrido BFS.
     */
    @Override
    public ListaDoblementeLigada<T> devolverRecorrido() {
        ListaDoblementeLigada<T> recorrido = new ListaDoblementeLigada<>();

        if (estaVacio()) {
            return recorrido;
        }

        Cola<Vertice> cola = new Cola<>();
        cola.meter(raiz);

        while (!cola.estaVacia()) {
            Vertice actual = cola.sacar();
            recorrido.insertarFinal(actual.elemento);

            if (actual.izquierdo != null) {
                cola.meter(actual.izquierdo);
            }
            if (actual.derecho != null) {
                cola.meter(actual.derecho);
            }
        }

        return recorrido;
    }

    /**
     * Inserta un nuevo vertice en el primer espacio izquierdo disponible dentro del subarbol binario completo inducido por un vertice V .
     * @param desde El vertice desde el cual sera el subarbol inducido
     * @param nuevo El vertice a agregar
     */
    private void agregaEnPrimerEspacioIzquierdo(Vertice desde, Vertice nuevo) {
        while (desde.izquierdo != null) {
            desde = desde.izquierdo;
        }
        desde.izquierdo = nuevo;
        nuevo.padre = desde;
        ultimoAgregado = nuevo;
    }

}
