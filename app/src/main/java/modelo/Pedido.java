package modelo;

import java.io.Serializable;

public class Pedido implements Serializable {
    // proximamente... o tal vez no
    //private ColeccionProductos listaProductosDeseados; // guarda objetos de clase ProductoElegido
    //private ProductoElegido productoSolicitado;
    private Producto productoSolicitado;
    private int cantidadProductosSolicitados;
    private int id;
    private int idDetalle;
    private int estado;
    private String fechaLimite;
    private String fechaInicio;
    private Cliente cliente;

    public Pedido(Producto productoElegido ){
        this.productoSolicitado = productoElegido;
        this.estado = 1;
    }

    public Pedido(Producto productoElegido, int v ){
        this.productoSolicitado = productoElegido;
        this.estado = v;
    }

    public Pedido(Producto productoElegido, int v, int id,int idDetalle ,String fechai, String fechaf, int cant, Cliente cliente){
        this.productoSolicitado = productoElegido;
        this.estado = v;
        this.id = id;
        this.fechaInicio = fechai;
        this.fechaLimite = fechaf;
        this.idDetalle = idDetalle;
        this.cantidadProductosSolicitados = cant;
        this.cliente = cliente;
    }

    public int getEstado(){
        return this.estado;
    }
    public void setEstado(int v){
        this.estado = v;
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

    /*public ProductoElegido obtenerProducto(int id){
        return (ProductoElegido) this.listaProductosDeseados.obtenerProducto(id);
    }*/
    public Producto obtenerProducto(){
        return this.productoSolicitado;
    }

    public Producto getProductoSolicitado() {
        return productoSolicitado;
    }

    public void setProductoSolicitado(Producto productoSolicitado) {
        this.productoSolicitado = productoSolicitado;
    }

    public int getCantidadProductosSolicitados() {
        return cantidadProductosSolicitados;
    }

    public void setCantidadProductosSolicitados(int cantidadProductosSolicitados) {
        this.cantidadProductosSolicitados = cantidadProductosSolicitados;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Cliente getCliente(){
        return this.cliente;
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    /*public void agregarProducto(Producto producto){
        this.listaProductosDeseados.agregarProducto(producto);
    }

    public void eliminarProducto(int id){
        this.listaProductosDeseados.eliminarProducto(id);
    }*/
}
