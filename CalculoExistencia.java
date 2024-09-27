package proyectoProgramacion;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
class CalculoExistencia {
	
	// Método para actualizar el inventario
    public void actualizarInventario(String archivoFactura, String archivoInventario) throws IOException {
        // Leer datos de la factura
        Map<String, Integer> cantidadesFactura = leerFactura(archivoFactura);

        // Leer inventario actual
        Map<String, Producto> inventarioActual = leerInventario(archivoInventario);

        // Actualizar inventario con las cantidades de la factura
        for (Map.Entry<String, Integer> entry : cantidadesFactura.entrySet()) {
            String nombreProducto = entry.getKey();
            int cantidadFactura = entry.getValue();
            if (inventarioActual.containsKey(nombreProducto)) {
                Producto producto = inventarioActual.get(nombreProducto);
                producto.setCantidad(producto.getCantidad() - cantidadFactura);
            } else {
                System.out.println("Producto no encontrado en el inventario: " + nombreProducto);
            }
        }

        // Guardar el inventario actualizado
        guardarInventario(inventarioActual, archivoInventario);
    }

    // Método para leer la factura
    private Map<String, Integer> leerFactura(String archivoFactura) throws IOException {
        Gson gson = new Gson();
        Map<String, Integer> cantidadesFactura = new HashMap<>();
        try (FileReader reader = new FileReader(archivoFactura)) {
            Type tipoMapa = new TypeToken<Map<String, Integer>>() {}.getType();
            cantidadesFactura = gson.fromJson(reader, tipoMapa);
        }
        return cantidadesFactura;
    }

    // Método para leer el inventario
    private Map<String, Producto> leerInventario(String archivoInventario) throws IOException {
        Gson gson = new Gson();
        Map<String, Producto> inventarioActual = new HashMap<>();
        try (FileReader reader = new FileReader(archivoInventario)) {
            Type tipoMapa = new TypeToken<Map<String, Producto>>() {}.getType();
            inventarioActual = gson.fromJson(reader, tipoMapa);
        }
        return inventarioActual;
    }

    // Método para guardar el inventario actualizado
    private void guardarInventario(Map<String, Producto> inventarioActual, String archivoInventario) throws IOException {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(archivoInventario)) {
            gson.toJson(inventarioActual, writer);
        }
    }
}


