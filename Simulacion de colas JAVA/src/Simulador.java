import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Simulador {
    private static Cola cola = new Cola();
    private static Pila historial = new Pila();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Simulador de Cola de Atención al Cliente ===");
        cargarClientesDesdeArchivo(); // Carga automática al iniciar

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> cargarClientesDesdeArchivo();
                case 2 -> agregarClienteManual();
                case 3 -> atenderCliente();
                case 4 -> verCola();
                case 5 -> verHistorial();
                case 6 -> consultarUltimoAtendido();
                case 7 -> System.out.println("¡Gracias por usar el simulador!");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 7);
    }

    private static void mostrarMenu() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Cargar clientes desde archivo");
        System.out.println("2. Agregar cliente manualmente");
        System.out.println("3. Atender siguiente cliente");
        System.out.println("4. Ver cola de espera");
        System.out.println("5. Ver historial de atenciones");
        System.out.println("6. Consultar último atendido");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void cargarClientesDesdeArchivo() {
        String ruta = "data/clientes.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            int count = 0;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty() || linea.startsWith("ID")) continue;
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    Cliente c = new Cliente(partes[0].trim(), partes[1].trim(), partes[2].trim());
                    cola.encolar(c);
                    count++;
                }
            }
            System.out.println("✓ Cargados " + count + " clientes desde archivo.");
        } catch (IOException e) {
            System.out.println("⚠ No se pudo cargar el archivo: " + e.getMessage());
        }
    }

    private static void agregarClienteManual() {
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Servicio: ");
        String servicio = scanner.nextLine();

        Cliente c = new Cliente(id, nombre, servicio);
        cola.encolar(c);
        System.out.println("✓ Cliente agregado a la cola.");
    }

    private static void atenderCliente() {
        if (cola.estaVacia()) {
            System.out.println("⚠ La cola está vacía.");
            return;
        }
        Cliente atendido = cola.desencolar();
        historial.push(atendido);
        System.out.println("✓ Atendido: " + atendido);
    }

    private static void verCola() {
        cola.mostrar();
    }

    private static void verHistorial() {
        historial.mostrar();
    }

    private static void consultarUltimoAtendido() {
        Cliente ultimo = historial.peek();
        if (ultimo != null) {
            System.out.println("Último cliente atendido: " + ultimo);
        } else {
            System.out.println("Aún no hay clientes atendidos.");
        }
    }

    private static int leerEntero() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}