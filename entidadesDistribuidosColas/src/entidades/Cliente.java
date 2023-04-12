
package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Serializable{
    private int id;
    private String nombre;
    private int edad;
    private String direccion;
    private List<Producto> productosComprados;
//Constructor

    public Cliente(int id,String nombre, int edad, String direccion) {
       this.id=id;
        this.nombre = nombre;
        this.edad = edad;
        this.direccion = direccion;
        this.productosComprados = new ArrayList<Producto>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Producto> getProductosComprados() {
        return productosComprados;
    }

    public void setProductosComprados(ArrayList<Producto> productosComprados) {
        this.productosComprados = productosComprados;
    }

    public void comprarProducto(Producto producto) {
        this.productosComprados.add(producto);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.id;
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
        final Cliente other = (Cliente) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", direccion=" + direccion + ", productosComprados=" + productosComprados + '}';
    }
}
