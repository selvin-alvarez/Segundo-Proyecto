package proyectoProgramacion;

import java.math.BigDecimal;

class Chumpa extends Producto {

	// Constructor
    public Chumpa(int cantidad) {
        super("Chumpa", new BigDecimal("399.0"), "004", cantidad); // Valores predeterminados
    }

    // Implementación del método abstracto mostrarInformacion
    @Override
    public void mostrarInformacion() {
        System.out.println("Chumpa " + getNombre() + ":");
        System.out.println("Precio: $" + getPrecio());
        System.out.println("Id: " + getId());
        System.out.println("Cantidad: " + getCantidad());
        System.out.println("Origen: " + getOrigen());
        System.out.println("Modificada: " + isModificada());
    }
}
