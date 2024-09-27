package proyectoProgramacion;

import java.math.BigDecimal;


class Sombrilla extends Producto {

	 // Constructor
    public Sombrilla(int cantidad) {
        super("Sombrilla", new BigDecimal("149.0"), "002", cantidad); // Valores predeterminados
    }

    // Implementación del método abstracto mostrarInformacion
    @Override
    public void mostrarInformacion() {
        System.out.println("Sombrilla " + getNombre() + ":");
        System.out.println("Precio: $" + getPrecio());
        System.out.println("Id: " + getId());
        System.out.println("Cantidad: " + getCantidad());
        System.out.println("Origen: " + getOrigen());
        System.out.println("Modificada: " + isModificada());
    }
}