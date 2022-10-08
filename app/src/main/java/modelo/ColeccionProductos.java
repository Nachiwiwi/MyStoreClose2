package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ColeccionProductos implements Serializable {
    ArrayList<Producto> listadoProductos;
    HashMap<Integer, Producto> productosMapa;


    public ColeccionProductos() {
        this.listadoProductos = new ArrayList<>();
        this.productosMapa = new HashMap<>();
    }

    public void agregarProducto(Producto producto){
        this.productosMapa.put(producto.getId(),producto);
        this.listadoProductos.add(producto);
    }

    public void eliminarProducto(int idProducto){
        this.productosMapa.remove(idProducto);
        for(int i= 0; i< this.listadoProductos.size();i++){
            if(this.listadoProductos.get(i).getNombre().equals(idProducto)){
                this.listadoProductos.remove(i);
                break;
            }
        }
    }

    public Producto obtenerProducto(int idProducto){
        return this.productosMapa.get(idProducto);
    }

    public int dimensionColeccion(){
        return this.listadoProductos.size();
    }

    public Producto obtenerProductoPorIndice(int index){
        return this.listadoProductos.get(index);
    }

    public Object copiarColeccion() throws CloneNotSupportedException{
        return super.clone();
    }
}