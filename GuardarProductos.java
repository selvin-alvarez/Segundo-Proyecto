package proyectoProgramacion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class GuardarProductos {
	
	private static final String FILE_PATH = "pedido.json";
    private static List<Producto> productos = new ArrayList<>();

    // Método para guardar producto
    private static void guardarEnArchivo(Producto producto) {
        productos.add(producto);
        guardarEnJson();
    }

    // Método para guardar el total combinado
    public static void guardarTotalCombinado(BigDecimal totalCombinado) {
        // Crear un producto ficticio para almacenar el total combinado
        Producto totalProducto = new Producto("Total Combinado", totalCombinado, "000", 1) {
            @Override
            public void mostrarInformacion() {
                System.out.println("Total Combinado: $" + getPrecio());
            }
        };
        productos.add(totalProducto);
        guardarEnJson();
    }

    // Método para guardar la lista de productos en un archivo JSON
    private static void guardarEnJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(productos, writer);
            System.out.println("Datos guardados en el archivo JSON.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Guardar Playera
    public static void guardarEnArchivo(Playera playera) {
        guardarEnArchivo((Producto) playera);
    }

    // Guardar Sombrilla
    public static void guardarEnArchivo(Sombrilla sombrilla) {
        guardarEnArchivo((Producto) sombrilla);
    }

    // Guardar Gorra
    public static void guardarEnArchivo(Gorra gorra) {
        guardarEnArchivo((Producto) gorra);
    }

    // Guardar Pachón
    public static void guardarEnArchivo(Pachon pachon) {
        guardarEnArchivo((Producto) pachon);
    }

    // Guardar Chumpa
    public static void guardarEnArchivo(Chumpa chumpa) {
        guardarEnArchivo((Producto) chumpa);
    }
}

