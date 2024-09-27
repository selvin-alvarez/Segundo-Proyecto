package proyectoProgramacion;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner entrada = new Scanner(System.in);

        // Instancias
        Pedido pedido = new Pedido();
        OrdenCompra ordenCompra = new OrdenCompra();
        CalculoExistencia calculoExistencia = new CalculoExistencia();
        Factura factura = new Factura();
        Inventario inventario = new Inventario();
        Existencias existencias = new Existencias();
        EstadoProducto estadoProducto = new EstadoProducto();

        int opcion = 0;

        do {
            mostrarMenu();
            if (entrada.hasNextInt()) {
                opcion = entrada.nextInt();
                entrada.nextLine();
                System.out.println("--------------------------------------------");

                switch (opcion) {
                    case 1:
                        crearPedido(pedido);
                        break;
                    case 2:
                        crearOrdenCompra(ordenCompra, entrada);
                        break;
                    case 3:
                        cargarExistencias(inventario);
                        break;
                    case 4:
                        facturar(factura);
                        break;
                    case 5:
                        descargarExistencias(calculoExistencia);
                        break;
                    case 6:
                        verExistencias(existencias);
                        break;
                    case 7:
                        estadoProducto.mostrarMenu();
                        break;
                    case 8:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, elige una opción entre 1 y 8.");
                }
            } else {
                System.out.println("Entrada no válida. Por favor, ingresa un número.");
                entrada.nextLine(); // Limpiar el buffer
            }
        } while (opcion != 8);

        entrada.close();
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
        ordenCompra.solicitarDatosCliente();
        Producto playera = OrdenCompra.ingresarDatosProducto(entrada, "Playera");
        Producto sombrilla = OrdenCompra.ingresarDatosProducto(entrada, "Sombrilla");
        Producto gorra = OrdenCompra.ingresarDatosProducto(entrada, "Gorra");
        Producto pachon = OrdenCompra.ingresarDatosProducto(entrada, "Pachón");
        Producto chumpa = OrdenCompra.ingresarDatosProducto(entrada, "Chumpa");
        ordenCompra.guardarDatosEnArchivo(playera, sombrilla, gorra, pachon, chumpa);
        System.out.println("Datos de la orden de compra guardados en OrdenCompra.json");
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
        factura.generarFactura("factura.json");
        factura.mostrarFactura("factura.json");
    }

    private static void descargarExistencias(CalculoExistencia calculoExistencia) {
        System.out.println("Has elegido Descargar Existencias.");
        try {
            calculoExistencia.actualizarInventario("factura.json", "Inventario.json");
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
