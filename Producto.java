package proyectoProgramacion;

import java.math.BigDecimal;

abstract class Producto {
	
	// Atributos
    private String nombre;
    private BigDecimal precio;
    private String id;
    private int cantidad;
    private String origen; // local o importada
    private boolean modificada; // true si debe ser modificada, false si no

    // Constructor
    public Producto(String nombre, BigDecimal precio, String id, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.id = id;
        this.cantidad = cantidad;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public boolean isModificada() {
        return modificada;
    }

    public void setModificada(boolean modificada) {
        this.modificada = modificada;
    }

    // Método abstracto
    public abstract void mostrarInformacion();
}
