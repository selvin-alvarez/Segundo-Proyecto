package proyectoProgramacion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class EstadoProducto {
	
	private static final String FILE_PATH = "estadoProductos.json";
    private Map<String, String> estados;

    public EstadoProducto() {
        estados = new HashMap<>();
        cargarEstados();
    }

    // Método para mostrar el menú
    public void mostrarMenu() {
        Scanner entrada = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\tMENÚ DE ESTADO DE PRODUCTOS:");
            System.out.println("1. Agregar Estado");
            System.out.println("2. Modificar Estado");
            System.out.println("3. Eliminar Estado");
            System.out.println("4. Ver Estado");
            System.out.println("5. Salir");
            System.out.print("Elige una opción (1-5): ");

            if (entrada.hasNextInt()) {
                opcion = entrada.nextInt();
                entrada.nextLine(); // Limpiar el buffer
                System.out.println("--------------------------------------------");

                switch (opcion) {
                    case 1:
                        agregarEstado(entrada);
                        break;
                    case 2:
                        modificarEstado(entrada);
                        break;
                    case 3:
                        eliminarEstado(entrada);
                        break;
                    case 4:
                        verEstado(entrada);
                        break;
                    case 5:
                        System.out.println("Saliendo del menú de estado de productos...");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, elige una opción entre 1 y 5.");
                }
            } else {
                System.out.println("Entrada no válida. Por favor, ingresa un número.");
                entrada.nextLine(); // Limpiar el buffer
            }
        } while (opcion != 5);
    }

    // Método para agregar un estado
    private void agregarEstado(Scanner entrada) {
        System.out.print("Ingresa el nombre del producto: ");
        String nombreProducto = entrada.nextLine();
        System.out.print("Ingresa el estado del producto: ");
        String estadoProducto = entrada.nextLine();

        estados.put(nombreProducto, estadoProducto);
        guardarEstados();
        System.out.println("Estado agregado correctamente.");
    }

    // Método para modificar un estado
    private void modificarEstado(Scanner entrada) {
        System.out.print("Ingresa el nombre del producto a modificar: ");
        String nombreProducto = entrada.nextLine();

        if (estados.containsKey(nombreProducto)) {
            System.out.print("Ingresa el nuevo estado del producto: ");
            String nuevoEstado = entrada.nextLine();
            estados.put(nombreProducto, nuevoEstado);
            guardarEstados();
            System.out.println("Estado modificado correctamente.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    // Método para eliminar un estado
    private void eliminarEstado(Scanner entrada) {
        System.out.print("Ingresa el nombre del producto a eliminar: ");
        String nombreProducto = entrada.nextLine();

        if (estados.containsKey(nombreProducto)) {
            estados.remove(nombreProducto);
            guardarEstados();
            System.out.println("Estado eliminado correctamente.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    // Método para ver el estado de un producto
    private void verEstado(Scanner entrada) {
        System.out.print("Ingresa el nombre del producto: ");
        String nombreProducto = entrada.nextLine();

        if (estados.containsKey(nombreProducto)) {
            System.out.println("Estado del producto " + nombreProducto + ": " + estados.get(nombreProducto));
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    // Método para cargar los estados desde el archivo JSON
    private void cargarEstados() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type tipoMapa = new TypeToken<Map<String, String>>() {}.getType();
            estados = gson.fromJson(reader, tipoMapa);
        } catch (IOException e) {
            System.out.println("No se pudo cargar el archivo de estados. Se creará uno nuevo.");
        }
    }

    // Método para guardar los estados en el archivo JSON
    private void guardarEstados() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(estados, writer);
            System.out.println("Estados guardados en el archivo JSON.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar los estados en el archivo.");
            e.printStackTrace();
        }
    }
}

