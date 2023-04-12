package entidades;

import java.io.Serializable;
import java.util.Objects;

public class Producto implements Serializable{

    private String nombre;
    private double precio;
    private String marca;
    private int cantidad;
    private String codigoBarras;

//Constructor
    public Producto(String nombre, double precio, String marca, int cantidad, String codigoBarras) {
        this.nombre = nombre;
        this.precio = precio;
        this.marca = marca;
        this.cantidad = cantidad;
        this.codigoBarras = codigoBarras;
    }

//Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.codigoBarras);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Producto other = (Producto) obj;
        return Objects.equals(this.codigoBarras, other.codigoBarras);
    }

    @Override
    public String toString() {
        return "Producto{" + "nombre=" + nombre + ", precio=" + precio + ", marca=" + marca + ", cantidad=" + cantidad + ", codigoBarras=" + codigoBarras + '}';
    }
    
}
