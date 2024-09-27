package proyectoProgramacion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Pedido implements IPedido {

	private List<Producto> productos;

    public Pedido() {
        productos = new ArrayList<>();
    }

    @Override
    public void crearPedido() {
        try (Scanner entrada = new Scanner(System.in)) {
            int opcion;

            do {
                mostrarMenu();
                opcion = obtenerOpcion(entrada);

                switch (opcion) {
                    case 1:
                        Playera playera = IngresoProductos.ingresarDatosPlayera(entrada);
                        productos.add(playera);
                        break;
                    case 2:
                        Sombrilla sombrilla = IngresoProductos.ingresarDatosSombrilla(entrada);
                        productos.add(sombrilla);
                        break;
                    case 3:
                        Gorra gorra = IngresoProductos.ingresarDatosGorra(entrada);
                        productos.add(gorra);
                        break;
                    case 4:
                        Pachon pachon = IngresoProductos.ingresarDatosPachon(entrada);
                        productos.add(pachon);
                        break;
                    case 5:
                        Chumpa chumpa = IngresoProductos.ingresarDatosChumpa(entrada);
                        productos.add(chumpa);
                        break;
                    case 6:
                        finalizarPedido();
                        break;
                    default:
                        System.out.println("Opción no válida. Inténtalo de nuevo.");
                }

            } while (opcion != 6);
        }
    }

    private void mostrarMenu() {
        System.out.println();
        System.out.println("\tMENÚ DE PRODUCTOS:");
        System.out.println();
        System.out.println("1. Playera");
        System.out.println("2. Sombrilla");
        System.out.println("3. Gorra");
        System.out.println("4. Pachon");
        System.out.println("5. Chumpa");
        System.out.println("6. Finalizar Pedido");
        System.out.print("Elige un producto (1-5, 6 para finalizar): ");
    }

    private int obtenerOpcion(Scanner entrada) {
        while (!entrada.hasNextInt()) {
            System.out.println("Error: Ingresa un número válido.");
            entrada.next();
        }
        return entrada.nextInt();
    }

    private void finalizarPedido() {
        System.out.println("Finalizar. ¡Imprimir Pedido!");
        System.out.println();
        BigDecimal totalCombinado = IngresoProductos.calcularTotalCombinado();
        GuardarProductos.guardarTotalCombinado(totalCombinado);
        ImprimirPedido.imprimir();
        Main.main(null);
    }

    @Override
    public void verExistencias() {
        // Implementa la lógica para ver las existencias de productos.
    }

    @Override
    public void crearOrdenCompra() {
        // Implementa la lógica para crear una orden de compra.
    }

    @Override
    public void facturar() {
        // Implementa la lógica para facturar.
    }
}