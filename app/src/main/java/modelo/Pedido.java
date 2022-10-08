package modelo;

import java.io.Serializable;

public class Pedido implements Serializable {
    private ColeccionProductos listaProductosDeseados; // guarda objetos de clase ProductoElegido
    private int id;
    private String fechaLimite;
    private String fechaInicio;

    public Pedido(){
        this.listaProductosDeseados = new ColeccionProductos();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public ProductoElegido obtenerProducto(int id){
        return (ProductoElegido) this.listaProductosDeseados.obtenerProducto(id);
    }

    public void agregarProducto(Producto producto){
        this.listaProductosDeseados.agregarProducto(producto);
    }

    public void eliminarProducto(int id){
        this.listaProductosDeseados.eliminarProducto(id);
    }
}
