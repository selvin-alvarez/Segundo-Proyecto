package proyectoProgramacion;

import java.math.BigDecimal;

class Pachon extends Producto {

	// Constructor
    public Pachon(int cantidad) {
        super("Pachon", new BigDecimal("119.0"), "004", cantidad); // Valores predeterminados
    }

    // Implementación del método abstracto mostrarInformacion
    @Override
    public void mostrarInformacion() {
        System.out.println("Pachon " + getNombre() + ":");
        System.out.println("Precio: $" + getPrecio());
        System.out.println("Id: " + getId());
        System.out.println("Cantidad: " + getCantidad());
        System.out.println("Origen: " + getOrigen());
        System.out.println("Modificada: " + isModificada());
    }
}
