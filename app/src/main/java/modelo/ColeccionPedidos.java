package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ColeccionPedidos implements Serializable {

    private ArrayList<Pedido> coleccionPedidosArray;
    private HashMap<Integer, Pedido> coleccionPedidosMap;

    public ColeccionPedidos(){
        this.coleccionPedidosArray = new ArrayList<>();
        this.coleccionPedidosMap = new HashMap<Integer,Pedido>();
    }

    public void agregarPedido(Pedido pedido){
        this.coleccionPedidosArray.add(pedido);
        this.coleccionPedidosMap.put(pedido.getId(),pedido);
    }

    public void eliminarPedido(int id){
        if(this.coleccionPedidosMap.containsKey(id)){
            for (int i = 0; i < this.coleccionPedidosArray.size(); i++){
                if(this.coleccionPedidosArray.get(i).getId() == id ){
                    this.coleccionPedidosArray.remove(i);
                    break;
                }
            }
            this.coleccionPedidosMap.remove(id);
        }
    }

    public boolean contienePedido(int idP){
        return this.coleccionPedidosMap.containsKey(idP);
    }

    public Pedido obtenerPedido(int id){
        return this.coleccionPedidosMap.get(id);
    }

    public Pedido obtenerPedidoIndice(int index){

        return this.coleccionPedidosArray.get(index);
    }


    /*public ProductoElegido obtenerPedidoIndice(int idPedido,int indexP){
        return (ProductoElegido) this.coleccionPedidosArray.get(idPedido).obtenerProducto();
    }*/

    public Producto obtenerProductoElegidoPorPedidoIndice(int index){
        return this.coleccionPedidosArray.get(index).obtenerProducto();
    }

    public int dimensionPedidos(){
        return this.coleccionPedidosArray.size();
    }

    public void vaciarContenido(){
        this.coleccionPedidosArray.clear();
        this.coleccionPedidosMap.clear();
    }

}