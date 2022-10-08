package modelo;

public class Cliente extends Usuario{

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String nombre;
    private String usuario;
    private ColeccionPedidos coleccionPedidos;
    private String id;

    public Cliente(String clave, String correo) {
        super(clave, correo);
    }

    public Cliente(String clave, String correo, String id, String nombre, String usuario) {
        super(clave, correo);
        this.nombre = nombre;
        this.usuario = usuario;
        this.id = id;
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
}

