package proyectoProgramacion;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner entrada = new Scanner(System.in);
        Factura factura = new Factura();

        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            if (entrada.hasNextInt()) {
                int opcion = entrada.nextInt();
                entrada.nextLine(); // Consumir el salto de línea
                System.out.println("--------------------------------------------");

                switch (opcion) {
                    case 1:
                        crearPedido(new Pedido());
                        break;
                    case 2:
                        crearOrdenCompra(new OrdenCompra(), entrada);
                        break;
                    case 3:
                        cargarExistencias(new Inventario());
                        break;
                    case 4:
                        facturar(factura);
                        break;
                    case 5:
                        descargarExistencias(new CalculoExistencia(), factura);
                        break;
                    case 6:
                        verExistencias(new Existencias());
                        break;
                    case 7:
                        new EstadoProducto().mostrarMenu();
                        break;
                    case 8:
                        System.out.println("Saliendo del programa...");
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, elige una opción entre 1 y 8.");
                }
            } else {
                System.out.println("Entrada no válida. Por favor, ingresa un número.");
                entrada.nextLine(); // Limpiar el buffer
            }
        }

        entrada.close();
        factura.cerrarScanner();
        System.out.println("Gracias por usar el sistema de facturación.");
    }

    private static void mostrarMenu() {
        System.out.println("\tMENÚ:");
        System.out.println();
        System.out.println("1. Crear Pedido");
        System.out.println("2. Crear Orden de Compra");
        System.out.println("3. Cargar Existencias");
        System.out.println("4. Factura");
        System.out.println("5. Descargar Existencias");
        System.out.println("6. Ver Existencias");
        System.out.println("7. Estado de Productos");
        System.out.println("8. Salir");
        System.out.print("Elige una opción (1-8): ");
    }

    private static void crearPedido(Pedido pedido) {
        System.out.println("Has elegido Crear Pedido.");
        pedido.crearPedido();
    }

    private static void crearOrdenCompra(OrdenCompra ordenCompra, Scanner entrada) {
        System.out.println("Has elegido Crear Orden de Compra.");
        ordenCompra.solicitarDatosProveedor();
    }

    private static void cargarExistencias(Inventario inventario) {
        System.out.println("Has elegido Cargar Existencias.");
        try {
            inventario.cargarDatosDesdeOrdenCompra("OrdenCompra.json");
            System.out.println("Existencias cargadas correctamente.");
        } catch (Exception e) {
            System.out.println("Error al cargar existencias: " + e.getMessage());
        }
    }

    private static void facturar(Factura factura) {
        System.out.println("Has elegido Facturar.");
        factura.solicitarDatosCliente();
        factura.generarFactura();
        factura.mostrarFactura("factura_" + factura.getNumeroFactura() + ".json");
    }

    private static void descargarExistencias(CalculoExistencia calculoExistencia, Factura factura) {
        System.out.println("Has elegido Descargar Existencias.");
        try {
            calculoExistencia.actualizarInventario("factura_" + factura.getNumeroFactura() + ".json", "Inventario.json");
            System.out.println("Existencias actualizadas correctamente.");
        } catch (IOException e) {
            System.out.println("Error al actualizar existencias: " + e.getMessage());
        }
    }

    private static void verExistencias(Existencias existencias) {
        System.out.println("Has elegido Ver Existencias.");
        existencias.leerInventario("Inventario.json");
    }
}
	
		
		