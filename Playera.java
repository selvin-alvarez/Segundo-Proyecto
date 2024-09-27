package proyectoProgramacion;

import java.math.BigDecimal;

class Playera extends Producto {

	// Constructor
    public Playera(int cantidad) {
        super("Playera", new BigDecimal("299.0"), "001", cantidad); // Valores predeterminados
    }

    // Implementación del método abstracto mostrarInformacion
    @Override
    public void mostrarInformacion() {
        System.out.println("Playera " + getNombre() + ":");
        System.out.println("Precio: $" + getPrecio());
        System.out.println("Id: " + getId());
        System.out.println("Cantidad: " + getCantidad());
        System.out.println("Origen: " + getOrigen());
        System.out.println("Modificada: " + isModificada());
    }
}

