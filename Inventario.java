package proyectoProgramacion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Inventario {
	
	private List<Producto> productos;
    private static final String FILE_PATH = "inventario.json";

    public Inventario() {
        productos = new ArrayList<>();
    }

    // Verificar si existe el archivo inventario.json
    private boolean existeArchivoInventario() {
        File archivo = new File(FILE_PATH);
        return archivo.exists();
    }

    // Cargar datos desde OrdenCompra.json
    public void cargarDatosDesdeOrdenCompra(String archivoOrdenCompra) {
        Map<String, Integer> cantidadesOrdenCompra = new HashMap<>();
        try (FileReader reader = new FileReader(archivoOrdenCompra)) {
            Gson gson = new Gson();
            Type tipoMapa = new TypeToken<Map<String, Integer>>() {}.getType();
            cantidadesOrdenCompra = gson.fromJson(reader, tipoMapa);
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo de orden de compra.");
            e.printStackTrace();
        }

        // Verificar y actualizar inventario.json
        if (existeArchivoInventario()) {
            actualizarInventario(cantidadesOrdenCompra);
        } else {
            guardarDatosEnInventario();
        }
    }

    // Actualizar inventario.json con las cantidades de OrdenCompra.json
    private void actualizarInventario(Map<String, Integer> cantidadesOrdenCompra) {
        Map<String, Producto> inventarioActual = leerInventario();

        // Sumar cantidades de OrdenCompra.json a inventario.json
        for (Map.Entry<String, Integer> entry : cantidadesOrdenCompra.entrySet()) {
            String nombreProducto = entry.getKey();
            int cantidadOrdenCompra = entry.getValue();
            if (inventarioActual.containsKey(nombreProducto)) {
                Producto producto = inventarioActual.get(nombreProducto);
                producto.setCantidad(producto.getCantidad() + cantidadOrdenCompra);
            } else {
                Producto nuevoProducto = new Producto(nombreProducto, new BigDecimal("0.0"), "", cantidadOrdenCompra) {
                    @Override
                    public void mostrarInformacion() {
                        System.out.println("Producto: " + getNombre());
                        System.out.println("Cantidad: " + getCantidad());
                        System.out.println("Origen: " + getOrigen());
                        System.out.println("Modificada: " + (isModificada() ? "Sí" : "No"));
                    }
                };
                inventarioActual.put(nombreProducto, nuevoProducto);
            }
        }

        // Guardar el inventario actualizado en inventario.json
        guardarInventario(inventarioActual);
        mostrarInventario(inventarioActual);
    }

    // Leer inventario.json
    private Map<String, Producto> leerInventario() {
        Gson gson = new Gson();
        Map<String, Producto> inventarioActual = new HashMap<>();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type tipoMapa = new TypeToken<Map<String, Producto>>() {}.getType();
            inventarioActual = gson.fromJson(reader, tipoMapa);
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo de inventario.");
            e.printStackTrace();
        }
        return inventarioActual;
    }

    // Guardar inventario.json
    private void guardarInventario(Map<String, Producto> inventarioActual) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(inventarioActual, writer);
            System.out.println("Datos guardados en el archivo JSON de inventario.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar los datos en el archivo de inventario.");
            e.printStackTrace();
        }
    }

    // Mostrar inventario actualizado
    private void mostrarInventario(Map<String, Producto> inventarioActual) {
        System.out.println("INVENTARIO ACTUALIZADO");
        for (Producto producto : inventarioActual.values()) {
            System.out.println("\nDATOS DE " + producto.getNombre().toUpperCase() + ":");
            System.out.println("Cantidad: " + producto.getCantidad());
            System.out.println("Origen: " + producto.getOrigen());
            System.out.println("Modificada: " + (producto.isModificada() ? "Sí" : "No"));
        }
    }

    // Guardar datos en inventario.json
    public void guardarDatosEnInventario() {
        Map<String, Producto> inventarioActual = new HashMap<>();
        for (Producto producto : productos) {
            inventarioActual.put(producto.getNombre().toUpperCase(), producto);
        }
        guardarInventario(inventarioActual);
    }
}
