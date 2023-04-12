/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Proveedor implements Serializable{
    private int id;
    private String nombre;
    private String direccion;
    private List<Producto> productos;

//Constructor
    public Proveedor(int id,String nombre, String direccion) {
        this.id=id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.productos = new ArrayList<Producto>();
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public void agregarProducto(Producto producto) {
        this.productos.add(producto);
    }

    public void eliminarProducto(Producto producto) {
        this.productos.remove(producto);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
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
        final Proveedor other = (Proveedor) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", productos=" + productos + '}';
    }
}
