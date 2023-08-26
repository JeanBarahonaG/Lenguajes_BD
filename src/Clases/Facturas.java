/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Jeanca Barahona
 */
public class Facturas {
    
    int id;
    String fecha;
    String cantidad;
    int total;
    String metodo_Pago;

    public Facturas(int id, String fecha, String cantidad, int total, String metodo_Pago) {
        this.id = id;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.total = total;
        this.metodo_Pago = metodo_Pago;
    }

    public Facturas() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getMetodo_Pago() {
        return metodo_Pago;
    }

    public void setMetodo_Pago(String metodo_Pago) {
        this.metodo_Pago = metodo_Pago;
    }
    
        
}
