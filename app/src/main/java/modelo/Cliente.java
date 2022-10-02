package modelo;

public class Cliente extends Usuario{

    private String nombre;
    private String usuario;
    //private ColecionPedidos listadoPedidosPersonales;
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
}
