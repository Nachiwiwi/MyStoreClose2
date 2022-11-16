package modelo;

import java.io.Serializable;

// Al implementar la interfaz serializable le permite al objeto poder ser pasado a traves de parametro a otra ventana
public class EmpresaMinimarket implements Serializable {

    private int idMinimarket;
    private String nombreEmpresa;
    private String nombreMinimarket;
    private String direccion;
    private String rutEmpresa;
    private String claveAdministradorMinimarket;
    private String mailAdministradorMinimarket;
    private Direccion posicion;
    private ColeccionPedidos coleccionPedidos;
    private ColeccionProductos coleccionProductos;
    private int distanciaRespectoUsuario;


    public EmpresaMinimarket(int idMinimarket, String nombreEmpresa, String nombreMinimarket, String direccion, String rutEmpresa, String clave, String corrreo, double latitud, double longitud) {
        this.idMinimarket = idMinimarket;
        this.nombreEmpresa = nombreEmpresa;
        this.nombreMinimarket = nombreMinimarket;
        this.direccion = direccion;
        this.rutEmpresa = rutEmpresa;
        this.claveAdministradorMinimarket = clave;
        this.mailAdministradorMinimarket = corrreo;
        this.posicion = new Direccion(latitud, longitud);
        this.coleccionPedidos = new ColeccionPedidos();
        this.coleccionProductos = new ColeccionProductos();
    }

    public String getDireccion() {return this.direccion;}

    public void setDireccion(String direccion) {this.direccion = direccion;}

    public int getIdMinimarket() {return idMinimarket;}

    public void setIdMinimarket(int idMinimarket) {
        this.idMinimarket = idMinimarket;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getNombreMinimarket(String nombre_local) {
        return nombreMinimarket;
    }

    public void setNombreMinimarket(String nombreMinimarket) {this.nombreMinimarket = nombreMinimarket;}

    public String getRutEmpresa() {
        return rutEmpresa;
    }

    public void setRutEmpresa(String rutEmpresa) {
        this.rutEmpresa = rutEmpresa;
    }




    // metodos de la reglas del negocio

    //distancia
    public String getDistancia(){
        return this.posicion.getDistancia();
    }

    public int getDistanciaRespectoUsuario() {
        return distanciaRespectoUsuario;
    }

    public void setDistanciaRespectoUsuario(int distanciaRespectoUsuario) {
        this.distanciaRespectoUsuario = distanciaRespectoUsuario;
    }

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

    public Producto obtenerProductoPedidoIndice(int index){
        return this.coleccionPedidos.obtenerProductoElegidoPorPedidoIndice(index);
    }

    public Pedido obtenerPedidoIndice(int index){
        return this.coleccionPedidos.obtenerPedidoIndice(index);
    }

    public int obtenerIndiceProducto(int id){
        return this.coleccionProductos.obtenerIndiceProducto(id);
    }

    public ColeccionProductos obtenerColeccion(){
        return this.coleccionProductos.duplicarColeccion();
    }

    public void limpiarDatos(){
        this.coleccionProductos.vaciarContenido();
    }

    public Direccion getPosicion(){
        return this.posicion;
    }

    // para Pedidos

    public int dimensionColeccionPedidos(){
        return this.coleccionPedidos.dimensionPedidos();
    }
}
