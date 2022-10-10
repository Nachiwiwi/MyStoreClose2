package modelo;

import java.io.Serializable;

// Al implementar la interfaz serializable le permite al objeto poder ser pasado a traves de parametro a otra ventana
public class EmpresaMinimarket implements Serializable {
    private String nombreEmpresa;
    private String nombreMinimarket;
    private Direccion direccion;
    private String rutEmpresa;
    private ColeccionPedidos coleccionPedidos;
    private ColeccionProductos coleccionProductos;

    public EmpresaMinimarket(String nombreEmpresa, String nombreMinimarket, String id, Direccion direccion, String rutEmpresa, String contraseña, String corrreo) {
        this.nombreEmpresa = nombreEmpresa;
        this.nombreMinimarket = nombreMinimarket;
        this.direccion = direccion;
        this.rutEmpresa = rutEmpresa;
        this.coleccionPedidos = new ColeccionPedidos();
        this.coleccionProductos = new ColeccionProductos();
    }

    public EmpresaMinimarket(String nombreEmpresa, String nombreMinimarket,String rutEmpresa,String direccion,String correo){
        this.nombreEmpresa = nombreEmpresa;
        this.nombreMinimarket = nombreMinimarket;
        this.rutEmpresa = rutEmpresa;
    }
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getNombreMinimarket() {
        return nombreMinimarket;
    }

    public void setNombreMinimarket(String nombreMinimarket) {
        this.nombreMinimarket = nombreMinimarket;
    }

    public String getRutEmpresa() {
        return rutEmpresa;
    }

    public void setRutEmpresa(String rutEmpresa) {
        this.rutEmpresa = rutEmpresa;
    }


    public String getLongitud() {
        return this.direccion.getLongitud();
    }

    public void setLongitud(String longitud) {
        this.direccion.setLongitud(longitud);
    }

    public String getLatitud() {
        return this.direccion.getLatitud();
    }

    public void setLatitud(String latitud) {
        this.direccion.setLatitud(latitud);
    }

    // metodos de la reglas del negocio

    // Para productos
    public void agregarProducto(Producto producto){
        this.coleccionProductos.agregarProducto(producto);
    }

    public Producto obtenerProducto(int idProducto){
        return this.coleccionProductos.obtenerProducto(idProducto);
    }

    public void eliminarProducto(int idProducto){
        this.coleccionProductos.eliminarProducto(idProducto);
    }

    public Producto obtenerProductoIndice(int index){
        return this.coleccionProductos.obtenerProductoPorIndice(index);
    }

    public int obtenerCantidadDeProductos(){
        return this.coleccionProductos.dimensionColeccion();
    }

    // Para pedidos

    public void agregarPedido(Pedido pedido){
        this.coleccionPedidos.agregarPedido(pedido);
    }

    public void eliminarPedido(int idP){
        this.coleccionPedidos.eliminarPedido(idP);
    }

    public Pedido obtenerPedidoId(int idP){
        return this.coleccionPedidos.obtenerPedido(idP);
    }

    public ProductoElegido obtenerProductoPedidoIndice(int idPedido,int indexProducto){
        return (ProductoElegido) this.coleccionPedidos.obtenerPedidoIndice(idPedido,indexProducto);
    }

    public int obtenerIndiceProducto(int id){
        return this.coleccionProductos.obtenerIndiceProducto(id);
    }

    public ColeccionProductos obtenerColeccion() throws CloneNotSupportedException {
        return this.coleccionProductos.duplicarColeccion();
    }

    public void limpiarDatos(){
        this.coleccionProductos.vaciarContenido();
    }


}
