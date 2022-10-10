package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ColeccionProductos implements Serializable {
    private ArrayList<Producto> listadoProductos;
    private HashMap<Integer, Producto> productosMapa;


    public ColeccionProductos() {
        this.listadoProductos = new ArrayList<>();
        this.productosMapa = new HashMap<>();
    }

    public void agregarProducto(Producto producto){
        if(!this.productosMapa.containsKey(producto.getId())) {
            this.productosMapa.put(producto.getId(), producto);
            this.listadoProductos.add(producto);
        }
        else
        {
            System.out.println("El producto ya existe");
        }
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

    public ColeccionProductos duplicarColeccion(){
        ColeccionProductos coleccionNueva = new ColeccionProductos();

        coleccionNueva.productosMapa = (new HashMap<>(this.productosMapa));
        coleccionNueva.listadoProductos = new ArrayList<>(this.listadoProductos);
        return coleccionNueva;
    }

    public void clonarColeccion(ColeccionProductos c){
        this.productosMapa = new HashMap<>(c.productosMapa);
        this.listadoProductos = new ArrayList<>(c.listadoProductos);
    }



    public int obtenerIndiceProducto(int id){
        for (int i = 0; i < this.listadoProductos.size(); i++){
            if(this.listadoProductos.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }

    public void vaciarContenido(){
        this.listadoProductos.clear();
        this.productosMapa.clear();
    }

}