package proyectoProgramacion;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

class Existencias {
	
	public void leerInventario(String archivoInventario) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(archivoInventario)) {
            Type productoListType = new TypeToken<List<Producto>>() {}.getType();
            List<Producto> productos = gson.fromJson(reader, productoListType);
            for (Producto producto : productos) {
                System.out.println("Nombre: " + producto.getNombre());
                System.out.println("Precio: " + producto.getPrecio());
                System.out.println("Id: " + producto.getId());
                System.out.println("Cantidad: " + producto.getCantidad());
                System.out.println("Origen: " + producto.getOrigen());
                System.out.println("Modificada: " + producto.isModificada());
                System.out.println("--------------------------------------------");
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo de inventario: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
