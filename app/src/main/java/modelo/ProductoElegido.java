package modelo;

public class ProductoElegido extends Producto{
    int cantidad;

    public ProductoElegido(String nombre, String precio, int id, String descripcion, int idRelacion) {
        super(nombre, precio, id, descripcion, idRelacion);
    }


    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


}
