package proyectoProgramacion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
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
    private transient Scanner entrada; // Transient para excluir de la serialización
    private static final String FILE_PATH_PEDIDO = "pedido.json";
    private static final String FILE_PATH_FACTURA = "factura.json";

    public Factura() {
        this.numeroFactura = generarNumeroFactura();
        this.entrada = new Scanner(System.in);
    }

    private String generarNumeroFactura() {
        Random random = new Random();
        int numero = random.nextInt(1000000); // Genera un número aleatorio de 6 dígitos
        return String.format("%06d", numero);
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    // Solicitar datos del cliente con validación
    public void solicitarDatosCliente() {
        if (nombreCliente == null || direccionCliente == null || nitCliente == null) {
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
    }

    // Leer pedido desde archivo JSON
    private List<Producto> leerPedido() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Producto.class, new InstanceCreator<Producto>() {
            @Override
            public Producto createInstance(Type type) {
                return new ProductoConcreto(); // Devuelve una instancia de una clase concreta que extiende Producto
            }
        });
        Gson gson = gsonBuilder.create();
        List<Producto> productos = new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_PATH_PEDIDO)) {
            Producto[] productosArray = gson.fromJson(reader, Producto[].class);
            if (productosArray != null) {
                for (Producto producto : productosArray) {
                    productos.add(producto);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return productos;
    }

    // Generar factura y calcular total
    public void generarFactura() {
        solicitarDatosCliente();
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
                System.out.println(producto.getNombre() + " - Cantidad: " + producto.getCantidad() + " - Precio: Q" + producto.getPrecio());
                total = total.add(producto.getPrecio().multiply(BigDecimal.valueOf(producto.getCantidad())));
            } else {
                System.out.println("Producto es null");
            }
        }

        System.out.println("\nTotal: Q" + total);

        // Guardar factura en archivo JSON
        guardarFacturaEnArchivo();
    }

    // Guardar factura en archivo JSON
    private void guardarFacturaEnArchivo() {
        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .addSerializationExclusionStrategy(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes f) {
                    return f.getDeclaredClass() == Scanner.class;
                }

                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    return false;
                }
            })
            .create();
        try (FileWriter writer = new FileWriter(FILE_PATH_FACTURA)) {
            gson.toJson(this, writer);
            System.out.println("Factura guardada en archivo JSON.");
        } catch (IOException e) {
            System.err.println("Error al guardar la factura en archivo JSON: " + e.getMessage());
        }
    }

    // Mostrar factura desde archivo
    public void mostrarFactura(String archivoFactura) {
        try (FileReader reader = new FileReader(archivoFactura);
             Scanner scanner = new Scanner(reader)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
        }
    }

    // Cerrar el Scanner
    public void cerrarScanner() {
        if (entrada != null) {
            entrada.close();
        }
    }
}

	
	