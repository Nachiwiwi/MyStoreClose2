package modelo;

import java.util.ArrayList;
import java.util.HashMap;

public class ColeccionProductos {
    ArrayList<Producto> listadoProductos;
    HashMap<String, Producto> productosMapa;


    public ColeccionProductos() {
        this.listadoProductos = new ArrayList<>();
        this.productosMapa = new HashMap<>();
    }

    public void agregarProducto(Producto producto){
        this.productosMapa.put(producto.getId(),producto);
        this.listadoProductos.add(producto);
    }

    public void eliminarProducto(String idProducto){
        this.productosMapa.remove(idProducto);
        for(int i= 0; i< this.listadoProductos.size();i++){
            if(this.listadoProductos.get(i).getNombre().equals(idProducto)){
                this.listadoProductos.remove(i);
                break;
            }
        }
    }

    public Producto obtenerProducto(String idProducto){
        return this.productosMapa.get(idProducto);
    }
}
