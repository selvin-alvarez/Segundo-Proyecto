package proyectoProgramacion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Random;
import java.util.Scanner;

class OrdenCompra {
	
	// Atributos
	private String numeroOrden;
    private String nombreProveedor;
    private String direccionProveedor;
    private String nitProveedor;
    private Scanner entrada;
    
    public OrdenCompra() {
        this.numeroOrden = generarNumeroOrden();
    }

    private String generarNumeroOrden() {
        Random random = new Random();
        int numero = random.nextInt(1000000); // Genera un número aleatorio de 6 dígitos
        return String.format("%06d", numero);
    }

    public void solicitarDatosCliente() {
        entrada = new Scanner(System.in);
        nombreProveedor = solicitarNombreProveedor();
        direccionProveedor = solicitarDireccionProveedor();
        nitProveedor = solicitarNitProveedor();
    }

    private String solicitarNombreProveedor() {
        System.out.print("Ingresa el nombre del Proveedor: ");
        String nombre = entrada.nextLine();
        while (!nombre.matches("[a-zA-Z\\s]+")) {
            System.out.println("Nombre no válido. Debe contener solo letras.");
            System.out.print("Ingresa el nombre del proveedor: ");
            nombre = entrada.nextLine();
        }
        return nombre;
    }

    private String solicitarDireccionProveedor() {
        System.out.print("Ingresa la dirección del proveedor: ");
        String direccion = entrada.nextLine();
        while (!direccion.matches("[a-zA-Z0-9\\s,.-]+")) {
            System.out.println("Dirección no válida. Puede contener letras, números y símbolos (,.-).");
            System.out.print("Ingresa la dirección del Proveedor: ");
            direccion = entrada.nextLine();
        }
        return direccion;
    }

    private String solicitarNitProveedor() {
        System.out.print("Ingresa el NIT del proveedor: ");
        String nit = entrada.nextLine();
        while (!nit.matches("\\d+")) {
            System.out.println("NIT no válido. Debe contener solo números.");
            System.out.print("Ingresa el NIT del Proveedor: ");
            nit = entrada.nextLine();
        }
        return nit;
    }

    // Datos de Producto
    public static Producto ingresarDatosProducto(Scanner entrada, String nombreProducto) {
        System.out.println("DATOS DE " + nombreProducto.toUpperCase() + ":");
        System.out.println();

        int cantidad = obtenerCantidad(entrada);
        String origen = obtenerOrigen(entrada);
        boolean modificada = obtenerModificacion(entrada);

        Producto producto = new Producto(nombreProducto, new BigDecimal("0.0"), "", cantidad) {
            @Override
            public void mostrarInformacion() {
                System.out.println("Producto: " + getNombre());
                System.out.println("Cantidad: " + getCantidad());
                System.out.println("Origen: " + getOrigen());
                System.out.println("Modificada: " + (isModificada() ? "Sí" : "No"));
            }
        };
        producto.setOrigen(origen);
        producto.setModificada(modificada);

        return producto;
    }

    private static int obtenerCantidad(Scanner entrada) {
        System.out.print("Ingresa la cantidad: ");
        while (!entrada.hasNextInt()) {
            System.out.println("Cantidad no válida. Debe ser un número entero.");
            entrada.next();
            System.out.print("Ingresa la cantidad: ");
        }
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

    // Guardar datos en archivo JSON
    public void guardarDatosEnArchivo(Producto... productos) {
        try (FileWriter writer = new FileWriter("OrdenCompra.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(this, writer);

            // Mostrar los datos en pantalla
            System.out.println("Número de Orden: " + numeroOrden);
            System.out.println("Nombre del Proveedor: " + nombreProveedor);
            System.out.println("Dirección del Proveedor: " + direccionProveedor);
            System.out.println("NIT del Proveedor: " + nitProveedor);

            for (Producto producto : productos) {
                System.out.println("\nDATOS DE " + producto.getNombre().toUpperCase() + ":");
                System.out.println("Cantidad: " + producto.getCantidad());
                System.out.println("Origen: " + producto.getOrigen());
                System.out.println("Modificada: " + (producto.isModificada() ? "Sí" : "No"));
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar los datos en el archivo.");
            e.printStackTrace();
        }
    }
}

