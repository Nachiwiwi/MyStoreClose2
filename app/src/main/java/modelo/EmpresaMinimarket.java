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
    private ColeccionPedidos coleccionPedidosAceptados;
    private ColeccionPedidos coleccionPedidosNuevos;
    private ColeccionPedidos coleccionPedidosRechazados;
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
        this.coleccionPedidosNuevos = new ColeccionPedidos();
        this.coleccionPedidosAceptados = new ColeccionPedidos();
        this.coleccionPedidosRechazados = new ColeccionPedidos();
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

    public String getNombreMinimarket() {
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

    public ColeccionProductos obtenerColeccion(){
        return this.coleccionProductos.duplicarColeccion();
    }

    public ColeccionProductos generarColeccion() throws CloneNotSupportedException{
        /*ColeccionProductos coleccionProductos = new ColeccionProductos();
        for(int i = 0 ; i < )*/
        return this.coleccionProductos;
    }

    public void limpiarDatos(){
        this.coleccionProductos.vaciarContenido();
        this.coleccionPedidosAceptados.vaciarContenido();
        this.coleccionPedidosNuevos.vaciarContenido();
        this.coleccionPedidosRechazados.vaciarContenido();
    }

    public Direccion getPosicion(){
        return this.posicion;
    }

    // para Pedidos

    public void agregarPedido(Pedido pedido){
        if (pedido.getEstado() == 1){
            this.coleccionPedidosNuevos.agregarPedido(pedido);
        }
        if(pedido.getEstado() == 2 || pedido.getEstado() == 3 || pedido.getEstado() == 4){
            this.coleccionPedidosAceptados.agregarPedido(pedido);
        }
        if (pedido.getEstado() == 0){
            this.coleccionPedidosRechazados.agregarPedido(pedido);
        }
    }

    public void eliminarPedido(int idP){
        if(this.coleccionPedidosNuevos.contienePedido(idP)){
            this.coleccionPedidosNuevos.eliminarPedido(idP);
            return;
        }
        else{
            this.coleccionPedidosAceptados.eliminarPedido(idP);
        }

        this.coleccionPedidosRechazados.eliminarPedido(idP);
    }

    public Pedido obtenerPedidoId(int idP){
        if(this.coleccionPedidosNuevos.contienePedido(idP)){
            return this.coleccionPedidosNuevos.obtenerPedido(idP);
        }
        return this.coleccionPedidosAceptados.obtenerPedido(idP);
    }

    public Pedido obtenerPedidoNuevoPorIndice(int index){
        return this.coleccionPedidosNuevos.obtenerPedidoIndice(index);
    }

    public Pedido obtenerPedidoAceptadoPorIndice(int index){
        return this.coleccionPedidosAceptados.obtenerPedidoIndice(index);
    }

    public int obtenerIndiceProducto(int id){
        return this.coleccionProductos.obtenerIndiceProducto(id);
    }

    public int dimensionColeccionPedidosAceptados(){
        return this.coleccionPedidosAceptados.dimensionPedidos();
    }

    public int dimensionColeccionPedidosNuevos(){
        return this.coleccionPedidosNuevos.dimensionPedidos();
    }
}
