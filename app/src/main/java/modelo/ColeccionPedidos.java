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

    public Pedido obtenerPedido(int id){
        return this.coleccionPedidosMap.get(id);
    }

    public ProductoElegido obtenerPedidoIndice(int idPedido,int indexP){
        return (ProductoElegido) this.coleccionPedidosArray.get(idPedido).obtenerProducto(indexP);
    }


}
