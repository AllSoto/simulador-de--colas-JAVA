/**
 * Implementación de Pila (Stack) con nodos y punteros
 */
public class Pila {
    private Nodo<Cliente> tope;

    public Pila() {
        this.tope = null;
    }

    /**
     * Push - Agrega al tope
     */
    public void push(Cliente cliente) {
        Nodo<Cliente> nuevo = new Nodo<>(cliente);
        nuevo.setSiguiente(tope);
        tope = nuevo;
    }

    /**
     * Pop - Elimina y retorna el tope
     */
    public Cliente pop() {
        if (estaVacia()) {
            return null;
        }
        Cliente cliente = tope.getDato();
        tope = tope.getSiguiente();
        return cliente;
    }

    /**
     * Peek - Consulta el tope sin eliminarlo
     */
    public Cliente peek() {
        if (estaVacia()) {
            return null;
        }
        return tope.getDato();
    }

    /**
     * Muestra todo el historial (de más reciente a más antiguo)
     */
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("El historial está vacío.");
            return;
        }
        Nodo<Cliente> actual = tope;
        System.out.println("=== Historial de Atenciones (Reciente → Antiguo) ===");
        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
        }
    }

    public boolean estaVacia() {
        return tope == null;
    }
}