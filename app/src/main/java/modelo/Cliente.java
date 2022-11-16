package modelo;

import java.io.Serializable;

public class Cliente extends Usuario implements Serializable {

    private String idUsuario;
    private String nombreRealUsuario;
    private String usuario;
    private String correoUsuario;
    private String clave;
    private Direccion posicionActual = null;
    private ColeccionPedidos coleccionPedidos;

    //Constructores
    public Cliente(String id, String nombreRealUsuario, String usuario, String correo, String clave) {
        super(clave, correo);
        this.idUsuario = id;
        this.nombreRealUsuario = nombreRealUsuario;
        this.usuario = usuario;
        this.correoUsuario = correo;
        this.clave = clave;

    }

    public Cliente(String clave, String correo,String nombre, String idCliente) {
        super(clave, correo);
        this.nombreRealUsuario = nombre;
        this.idUsuario = idCliente;
    }


    public Cliente(String clave, String correo) {
        super(clave, correo);
    }

    //Setters y getters
    public String getId() {
        return idUsuario;
    }

    public void setId(String id) {
        this.idUsuario = id;
    }

    public String getNombre() {
        return nombreRealUsuario;
    }

    public void setNombre(String nombre) {
        this.nombreRealUsuario = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }



    public Direccion getPosicionActual(){
        return posicionActual;
    }
    public void agregarPedido(Pedido pedido){
        this.coleccionPedidos.agregarPedido(pedido);
    }

    public void eliminarPedido(int idPedido){
        this.coleccionPedidos.eliminarPedido(idPedido);
    }

    public Pedido obtenerPedido(int idPedido){
        return this.coleccionPedidos.obtenerPedido(idPedido);
    }

    public void setUbicacion(double latitud, double longitud){
        if(this.posicionActual == null) {
            this.posicionActual = new Direccion(latitud, longitud);

        }else{
            this.posicionActual.setLatitud(latitud);
            this.posicionActual.setLongitud(longitud);
        }
    }
}

