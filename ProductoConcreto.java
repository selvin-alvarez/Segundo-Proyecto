package proyectoProgramacion;

import java.math.BigDecimal;

public class ProductoConcreto extends Producto {

	// Constructor sin argumentos
    public ProductoConcreto() {
        super("", BigDecimal.ZERO, "", 0);
    }

    // Constructor con argumentos
    public ProductoConcreto(String nombre, BigDecimal precio, String id, int cantidad) {
        super(nombre, precio, id, cantidad);
    }

    // Implementación del método abstracto
    @Override
    public void mostrarInformacion() {
        System.out.println("Nombre: " + getNombre());
        System.out.println("Precio: " + getPrecio());
        System.out.println("ID: " + getId());
        System.out.println("Cantidad: " + getCantidad());
        System.out.println("Origen: " + getOrigen());
        System.out.println("Modificada: " + isModificada());
    }
}
