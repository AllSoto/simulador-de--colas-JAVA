/**
 * Clase Cliente
 */
public class Cliente {
    private String id;
    private String nombre;
    private String servicio;

    public Cliente(String id, String nombre, String servicio) {
        this.id = id;
        this.nombre = nombre;
        this.servicio = servicio;
    }

    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getServicio() { return servicio; }

    @Override
    public String toString() {
        return String.format("ID: %s | %s | Servicio: %s", id, nombre, servicio);
    }
}