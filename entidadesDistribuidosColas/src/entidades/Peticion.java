
package entidades;

import java.io.Serializable;
import java.util.Date;

public class Peticion implements Serializable {
    private int id;
    private TipoPeticion tipoPeticion;
    private Date fecha;
    private Prioridad prioridad;
    private Cliente cliente;

    public Peticion(int id, TipoPeticion tipoPeticion, Date fecha, Prioridad prioridad, Cliente cliente) {
        this.id = id;
        this.tipoPeticion = tipoPeticion;
        this.fecha = fecha;
        this.prioridad = prioridad;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoPeticion getTipoPeticion() {
        return tipoPeticion;
    }

    public void setTipoPeticion(TipoPeticion tipoPeticion) {
        this.tipoPeticion = tipoPeticion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Petición{" +
                "id=" + id +
                ", tipoPeticion=" + tipoPeticion +
                ", fecha=" + fecha +
                ", prioridad=" + prioridad +
                ", cliente=" + cliente +
                '}';
    }
}
