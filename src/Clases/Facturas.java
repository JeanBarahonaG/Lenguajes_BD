package Clases;

public class Facturas {
    
    int id;
    String fecha;
    int cantidad;
    int total;
    String metodo_Pago;

    public Facturas(int id, String fecha, int cantidad, int total, String metodo_Pago) {
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
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
