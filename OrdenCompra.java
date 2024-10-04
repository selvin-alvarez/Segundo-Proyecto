package proyectoProgramacion;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrdenCompra {
    private String proveedor;

    public OrdenCompra() {
        new ArrayList<>();
    }

    public void solicitarDatosProveedor() {
        try (Scanner entrada = new Scanner(System.in)) {
			System.out.print("Ingresa el nombre del proveedor: ");
			this.setProveedor(entrada.nextLine());
		}
    }

    public static Producto ingresarDatosProducto(Scanner entrada, String nombreProducto) {
        System.out.print("Ingresa el precio del producto " + nombreProducto + ": ");
        while (!entrada.hasNextBigDecimal()) {
            System.out.println("Precio no válido. Debe ser un número decimal.");
            entrada.next();
            System.out.print("Ingresa el precio del producto " + nombreProducto + ": ");
        }
        entrada.nextLine(); // Limpiar el buffer

        System.out.print("Ingresa la cantidad del producto " + nombreProducto + ": ");
        while (!entrada.hasNextInt()) {
            System.out.println("Cantidad no válida. Debe ser un número entero.");
            entrada.next();
            System.out.print("Ingresa la cantidad del producto " + nombreProducto + ": ");
        }
        entrada.nextLine(); // Limpiar el buffer

        System.out.print("¿Es local o importada? (local/importada): ");
        String origen = entrada.nextLine().toLowerCase();
        while (!origen.matches("local|importada")) {
            System.out.println("Opción no válida. Debe ser 'local' o 'importada'.");
            System.out.print("¿Es local o importada? (local/importada): ");
            origen = entrada.nextLine().toLowerCase();
        }

        System.out.print("¿Debe ser modificada? (si/no): ");
        String respuestaModificada = entrada.nextLine().toLowerCase();
        while (!respuestaModificada.matches("si|no")) {
            System.out.println("Respuesta no válida. Debe ser 'si' o 'no'.");
            System.out.print("¿Debe ser modificada? (si/no): ");
            respuestaModificada = entrada.nextLine().toLowerCase();
        }
        return new ProductoConcreto();
    }

    public void guardarDatosEnArchivo(Producto... productos) throws IOException {
        try (FileWriter writer = new FileWriter("OrdenCompra.json")) {
            for (Producto producto : productos) {
                writer.write(producto.toString() + "\n");
            }
        }
    }

    public void mostrarDatosDesdeArchivo() {
        // Implementación para leer y mostrar datos desde el archivo
    }

    public static void crearOrdenCompra(OrdenCompra ordenCompra, Scanner entrada) {
        System.out.println("Has elegido Crear Orden de Compra.");
        ordenCompra.solicitarDatosProveedor();

        // Lista de nombres de productos
        String[] productosNombres = {"Playera", "Sombrilla", "Gorra", "Pachón", "Chumpa"};
        List<Producto> productos = new ArrayList<>();

        // Agregar productos
        for (String nombre : productosNombres) {
            Producto producto = ingresarDatosProducto(entrada, nombre);
            productos.add(producto);
        }

        // Guardar y mostrar datos de la orden
        try {
            ordenCompra.guardarDatosEnArchivo(productos.toArray(new Producto[0]));
            ordenCompra.mostrarDatosDesdeArchivo();
            System.out.println("Datos de la orden de compra guardados en OrdenCompra.json");
        } catch (IOException e) {
            System.err.println("Error al guardar los datos de la orden de compra: " + e.getMessage());
        }
    }

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
}

