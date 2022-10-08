package modelo;

public class ProductoElegido extends Producto{
    int cantidad;

    public ProductoElegido(String nombre, String precio, String oferta) {
        super(nombre, precio, oferta);
    }

    public ProductoElegido(String nombre, String precio) {
        super(nombre, precio);
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


}
