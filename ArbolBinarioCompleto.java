import java.util.NoSuchElementException;

public class ArbolBinarioCompleto<T> extends ArbolBinario<T> {
    
    private Vertice ultimoAgregado;

    public ArbolBinarioCompleto() {
        super();
        ultimoAgregado = null;
    }

    private boolean esRaiz(Vertice vertice){
        return vertice.padre == null && raiz.equals(vertice);
    }

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

    @Override
    public void eliminar(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento es nulo.");
        }
        if (estaVacio()) {
            throw new NoSuchElementException("El árbol está vacío.");
        }
        Vertice eliminar;
        Vertice ultimoVertice;
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

    private void actualizarUltimoAgregado() {
        /*Aqui va tu codigo*/
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
