/**
 * Implementación de Cola (Queue) con nodos y punteros
 */
public class Cola {
    private Nodo<Cliente> frente;
    private Nodo<Cliente> finalCola;

    public Cola() {
        this.frente = null;
        this.finalCola = null;
    }

    /**
     * Encola un cliente al final
     */
    public void encolar(Cliente cliente) {
        Nodo<Cliente> nuevo = new Nodo<>(cliente);
        if (estaVacia()) {
            frente = nuevo;
            finalCola = nuevo;
        } else {
            finalCola.setSiguiente(nuevo);
            finalCola = nuevo;
        }
    }

    /**
     * Desencola el cliente del frente
     */
    public Cliente desencolar() {
        if (estaVacia()) {
            return null;
        }
        Cliente cliente = frente.getDato();
        frente = frente.getSiguiente();
        if (frente == null) {
            finalCola = null;
        }
        return cliente;
    }

    /**
     * Muestra todos los clientes en la cola (orden de llegada)
     */
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("La cola está vacía.");
            return;
        }
        Nodo<Cliente> actual = frente;
        System.out.println("=== Cola de Espera ===");
        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
        }
    }

    public boolean estaVacia() {
        return frente == null;
    }
}