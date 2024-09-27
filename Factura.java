package proyectoProgramacion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Factura {
	// Atributos
    private String numeroFactura;
    private String nombreCliente;
    private String direccionCliente;
    private String nitCliente;
    private Scanner entrada;
    private static final String FILE_PATH = "pedido.json";

    public Factura() {
        this.numeroFactura = generarNumeroFactura();
    }

    private String generarNumeroFactura() {
        Random random = new Random();
        int numero = random.nextInt(1000000); // Genera un número aleatorio de 6 dígitos
        return String.format("%06d", numero);
    }

    // Datos para factura
    public void solicitarDatosCliente() {
        entrada = new Scanner(System.in);
        System.out.print("Ingresa el nombre del cliente: ");
        nombreCliente = entrada.nextLine();
        while (!nombreCliente.matches("[a-zA-Z\\s]+")) {
            System.out.println("Nombre no válido. Debe contener solo letras.");
            System.out.print("Ingresa el nombre del cliente: ");
            nombreCliente = entrada.nextLine();
        }

        System.out.print("Ingresa la dirección del cliente: ");
        direccionCliente = entrada.nextLine();
        while (!direccionCliente.matches("[a-zA-Z0-9\\s,.-]+")) {
            System.out.println("Dirección no válida. Puede contener letras, números y símbolos (,.-).");
            System.out.print("Ingresa la dirección del cliente: ");
            direccionCliente = entrada.nextLine();
        }

        System.out.print("Ingresa el NIT del cliente: ");
        nitCliente = entrada.nextLine();
        while (!nitCliente.matches("\\d+")) {
            System.out.println("NIT no válido. Debe contener solo números.");
            System.out.print("Ingresa el NIT del cliente: ");
            nitCliente = entrada.nextLine();
        }
    }

    private List<Producto> leerPedido() {
        Gson gson = new Gson();
        List<Producto> productos = new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Producto[] productosArray = gson.fromJson(reader, Producto[].class);
            if (productosArray != null) {
                for (Producto producto : productosArray) {
                    productos.add(producto);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productos;
    }

    public void generarFactura() {
        List<Producto> productos = leerPedido();
        if (productos == null || productos.isEmpty()) {
            System.out.println("No hay productos en el pedido.");
            return;
        }

        BigDecimal total = BigDecimal.ZERO;
        System.out.println("\nFACTURA");
        System.out.println("Número de Factura: " + numeroFactura);
        System.out.println("Cliente: " + nombreCliente);
        System.out.println("Dirección: " + direccionCliente);
        System.out.println("NIT: " + nitCliente);
        System.out.println("\nProductos:");

        for (Producto producto : productos) {
            if (producto != null) {
                System.out.println(producto.getNombre() + " - Cantidad: " + producto.getCantidad() + " - Precio: $" + producto.getPrecio());
                total = total.add(producto.getPrecio().multiply(BigDecimal.valueOf(producto.getCantidad())));
            } else {
                System.out.println("Producto es null");
            }
        }

        System.out.println("\nTotal: $" + total);

        // Guardar factura en archivo JSON
        guardarFacturaEnArchivo();
    }

    private void guardarFacturaEnArchivo() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("factura_" + numeroFactura + ".json")) {
            gson.toJson(this, writer);
            System.out.println("Factura guardada en archivo JSON.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Facturar
    public void generarFactura(String archivoFactura) {
        try (FileWriter writer = new FileWriter(archivoFactura)) {
            writer.write("No. Factura: " + numeroFactura + "\n");
            writer.write("Nombre del Cliente: " + nombreCliente + "\n");
            writer.write("Dirección del Cliente: " + direccionCliente + "\n");
            writer.write("NIT del Cliente: " + nitCliente + "\n");
            writer.write("============================================\n");

            List<Producto> productos = leerPedido();
            for (Producto producto : productos) {
                writer.write("Nombre: " + producto.getNombre() + "\n");
                writer.write("Id: " + producto.getId() + "\n");
                writer.write("Cantidad: " + producto.getCantidad() + "\n");
                writer.write("Origen: " + producto.getOrigen() + "\n");
                writer.write("Modificada: " + producto.isModificada() + "\n");
                writer.write("Precio: " + producto.getPrecio() + "\n");
                writer.write("Total: " + producto.getPrecio().multiply(BigDecimal.valueOf(producto.getCantidad())) + "\n");
                writer.write("--------------------------------------------\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Mostrar en pantalla factura
    public void mostrarFactura(String archivoFactura) {
        try (FileReader reader = new FileReader(archivoFactura);
             Scanner scanner = new Scanner(reader)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
	
	