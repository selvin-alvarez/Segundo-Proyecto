package proyectoProgramacion;

import java.math.BigDecimal;
import java.util.Scanner;

class IngresoProductos {
	
	// Inicializar variables
    private static BigDecimal totalPlayeras = BigDecimal.ZERO;
    private static BigDecimal totalSombrillas = BigDecimal.ZERO;
    private static BigDecimal totalGorras = BigDecimal.ZERO;
    private static BigDecimal totalPachones = BigDecimal.ZERO;
    private static BigDecimal totalChumpas = BigDecimal.ZERO;

    // Datos de Playera
    public static Playera ingresarDatosPlayera(Scanner entrada) {
        System.out.println("DATOS DE PLAYERA:");
        System.out.println();

        Playera playera = new Playera(obtenerCantidad(entrada));
        completarDatosProducto(entrada, playera);
        totalPlayeras = totalPlayeras.add(playera.getPrecio().multiply(BigDecimal.valueOf(playera.getCantidad())));

        GuardarProductos.guardarEnArchivo(playera);

        return playera;
    }

    // Datos de Sombrilla
    public static Sombrilla ingresarDatosSombrilla(Scanner entrada) {
        System.out.println("DATOS DE SOMBRILLA");
        System.out.println();

        Sombrilla sombrilla = new Sombrilla(obtenerCantidad(entrada));
        completarDatosProducto(entrada, sombrilla);
        totalSombrillas = totalSombrillas.add(sombrilla.getPrecio().multiply(BigDecimal.valueOf(sombrilla.getCantidad())));

        GuardarProductos.guardarEnArchivo(sombrilla);

        return sombrilla;
    }

    // Datos de Gorra
    public static Gorra ingresarDatosGorra(Scanner entrada) {
        System.out.println("DATOS DE GORRA");
        System.out.println();

        Gorra gorra = new Gorra(obtenerCantidad(entrada));
        completarDatosProducto(entrada, gorra);
        totalGorras = totalGorras.add(gorra.getPrecio().multiply(BigDecimal.valueOf(gorra.getCantidad())));

        GuardarProductos.guardarEnArchivo(gorra);

        return gorra;
    }

    // Datos de Pachón
    public static Pachon ingresarDatosPachon(Scanner entrada) {
        System.out.println("DATOS DE PACHÓN");
        System.out.println();

        Pachon pachon = new Pachon(obtenerCantidad(entrada));
        completarDatosProducto(entrada, pachon);
        totalPachones = totalPachones.add(pachon.getPrecio().multiply(BigDecimal.valueOf(pachon.getCantidad())));

        GuardarProductos.guardarEnArchivo(pachon);

        return pachon;
    }

    // Datos de Chumpa
    public static Chumpa ingresarDatosChumpa(Scanner entrada) {
        System.out.println("DATOS DE CHUMPA");
        System.out.println();

        Chumpa chumpa = new Chumpa(obtenerCantidad(entrada));
        completarDatosProducto(entrada, chumpa);
        totalChumpas = totalChumpas.add(chumpa.getPrecio().multiply(BigDecimal.valueOf(chumpa.getCantidad())));

        GuardarProductos.guardarEnArchivo(chumpa);

        return chumpa;
    }

    private static void completarDatosProducto(Scanner entrada, Producto producto) {
        producto.setOrigen(obtenerOrigen(entrada));
        producto.setModificada(obtenerModificacion(entrada));
    }

    private static int obtenerCantidad(Scanner entrada) {
        System.out.print("Ingresa la cantidad: ");
        int cantidad = entrada.nextInt();

        while (cantidad <= 0) {
            System.out.println("Cantidad no válida. Debe ser mayor a 0.");
            System.out.print("Ingresa la cantidad: ");
            cantidad = entrada.nextInt();
        }
        return cantidad;
    }

    private static String obtenerOrigen(Scanner entrada) {
        System.out.print("¿Es local o importada? (local/importada): ");
        String origen = entrada.next().toLowerCase();

        while (!origen.matches("local|importada")) {
            System.out.println("Opción no válida. Debe ser 'local' o 'importada'.");
            System.out.print("¿Es local o importada? (local/importada): ");
            origen = entrada.next().toLowerCase();
        }
        return origen;
    }

    private static boolean obtenerModificacion(Scanner entrada) {
        System.out.print("¿Debe ser modificada? (si/no): ");
        String respuestaModificada = entrada.next().toLowerCase();

        while (!respuestaModificada.matches("si|no")) {
            System.out.println("Respuesta no válida. Debe ser 'si' o 'no'.");
            System.out.print("¿Debe ser modificada? (si/no): ");
            respuestaModificada = entrada.next().toLowerCase();
        }
        return respuestaModificada.equalsIgnoreCase("si");
    }

    public static BigDecimal calcularTotalCombinado() {
        return totalPlayeras.add(totalSombrillas).add(totalGorras).add(totalPachones).add(totalChumpas);
    }
}


