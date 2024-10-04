package proyectoProgramacion;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;

class ImprimirPedido {
	
	private static final String FILE_PATH = "pedido.json";

	public static void imprimir() {
	    Gson gson = new Gson();
	    try (FileReader reader = new FileReader(FILE_PATH)) {
	        Type productoListType = new TypeToken<List<ProductoConcreto>>() {}.getType();
	        List<ProductoConcreto> productos = gson.fromJson(reader, productoListType);

	        if (productos != null) {
	            for (ProductoConcreto producto : productos) {
	                if (producto != null) {
	                    System.out.println("Nombre: " + producto.getNombre());
	                    System.out.println("Id: " + producto.getId());
	                    System.out.println("Cantidad: " + producto.getCantidad());
	                    System.out.println("Origen: " + producto.getOrigen());
	                    System.out.println("Modificada: " + producto.isModificada());
	                    System.out.println("Precio: $" + producto.getPrecio());
	                    BigDecimal total = producto.getPrecio().multiply(BigDecimal.valueOf(producto.getCantidad()));
	                    System.out.println("Total: $" + total);
	                    System.out.println("--------------------------------------------");
	                } else {
	                    System.out.println("Producto es null");
	                }
	            }
	        } else {
	            System.out.println("La lista de productos es null");
	        }
	    } catch (IOException e) {
	        System.err.println("Error: Archivo no encontrado o error al leer el archivo.");
	    }
	}
}
