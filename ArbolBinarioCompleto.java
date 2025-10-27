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
        return vertice.padre == null && raiz.equals(vertice);
    }

    /**
     * Inserta un nuevo elemento en el árbol manteniendo la propiedad de 
     * ser completo.
     * 
     * @param elemento El elemento a insertar en el árbol en el último nivel,
     * lo más a lo izquierda posible.
     * @throws IllegalArgumetnException Si el elemento es nulo.
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
            if (ultimoAgregado.esHijoIzquierdo()) {
                ultimoAgregado.padre.derecho = n;
                ultimoAgregado = n;
            }else{
                Vertice pua = ultimoAgregado.padre;
                if (!esRaiz(pua)) {
                    pua = pua.padre.derecho;
                }
                agregaEnPrimerEspacioIzquierdo(pua, n);
            }
        }
        tamanio++;
    }

    /**
     * Método que elimina un elemento del árbol manteniendo la propiedad
     * de ser completo.
     * 
     * @param elemento El elemento a eliminar del árbol.
     * @param IllegalArgumentException Si el elemento a querer eliminar
     * es nulo.
     * @param NoSuchElementException Si el árbol está vacío.
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
            Vertice actual = cola.sacar()
            ultimoVertice = actual;
            if (actual.elemento.equals(elemento)) {
                eliminar = actual;
            }
            if (actual.izquierdo != null) {
                cola.meter(actual.izquierdo);
            }
            if (actual.derecho != null) {
                cola.meter(actual.izquierdo);
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


    @Override
    public boolean buscar(T elemento) {
        /*Aqui va tu codigo*/
    }

    private boolean buscar(T elemento, Vertice v){
        /*Aqui va tu codigo*/    
    }

    @Override
    public ListaDoblementeLigada<T> devolverRecorrido() {
        /*Aqui va tu codigo*/
    }

    private void agregaEnPrimerEspacioIzquierdo(Vertice desde, Vertice nuevo) {
        /*Aqui va tu codigo*/
    }

}
