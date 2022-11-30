package adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mystoreclose.R;

import modelo.ColeccionPedidos;
import modelo.ColeccionProductos;
import modelo.EmpresaMinimarket;
import modelo.Pedido;

public class AdaptadorEncargosNuevosMinimarket extends RecyclerView.Adapter<AdaptadorEncargosNuevosMinimarket.ViewHolder>{

    public interface ItemClickListener{
        void rechazarPedido(int indexPedido);
        void aceptarPedido(int indexPedido);
    }

    //private ColeccionPedidos coleccionPedidos;

    private EmpresaMinimarket minimarket;

    private ItemClickListener myListener;
    public void setItemListener(ItemClickListener itemListener){
        this.myListener = itemListener;
    }

    public AdaptadorEncargosNuevosMinimarket(EmpresaMinimarket minimarket){
        this.minimarket = minimarket;
    }

    @NonNull
    // Se llama esta funcion cuando el recyclerView precise de una nueva vista para mostrar por pantalla
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.formato_de_salida_encargos_nuevos, parent,false);
        //return new ViewHolder(v,this.myListener, this.productosMostrados);
        return new ViewHolder(v, this.myListener);
    }

    // Para actualizar datos de una vista
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.bind(this.NombresProductos.get(position));
        Pedido p = this.minimarket.obtenerPedidoNuevoPorIndice(position);
        if (p != null)
            holder.getTextView().setText("Nombre Producto: "+ p.getProductoSolicitado().getNombre() +"\n" +
                "Numero de unidades: "+ p.getCantidadProductosSolicitados()
            +"\nNombre Cliente: " + p.getCliente().getNombre()+ "\nDescripción : " +p.getProductoSolicitado().getDescripcion());
        /*holder.getTextView().setText("Nombre: "+this.productosMostrados.obtenerProductoPorIndice(position).getNombre()
                + "\nPrecio: "+ this.productosMostrados.obtenerProductoPorIndice(position).getPrecio());*/
    }

    // Numero de elementos que tiene una lista
    @Override
    public int getItemCount() {
        return this.minimarket.dimensionColeccionPedidosNuevos();
    }

    // clase ViewHolder

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView texto;
        //private ImageView botonVer;

        private ImageView botonRechazar;
        private ImageView botonAceptar;

        public ViewHolder(View v, ItemClickListener listener) {
//        public ViewHolder(View v, ItemClickListener listener, ColeccionProductos coleccionProductos) {
            super(v);
            this.texto = v.findViewById(R.id.productoEncargoTexto);
            this.botonRechazar = v.findViewById(R.id.declineImage);
            this.botonAceptar = v.findViewById(R.id.checkImage);

            this.botonAceptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posicion = getBindingAdapterPosition();
                    listener.aceptarPedido(posicion);
                    System.out.println("Aceptar");
                }
            });

            this.botonRechazar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posicion = getBindingAdapterPosition();
                    listener.rechazarPedido(posicion);
                    System.out.println("Rechazar");
                }
            });

            // Realiza una accion cuando se seleccione un elemento
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.d("Adapter Productos", getBindingAdapterPosition() + " Element " + coleccionProductos.dimensionColeccion() +" clicked.");
                }
            });

            // botonVerProducto
            /*this.botonVer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int posicion = getBindingAdapterPosition();
                        System.out.println("La posicion es: "+posicion+ " El producto es: "+
                                coleccionProductos.obtenerProductoPorIndice(posicion).getNombre()+ " y el tamaño es: " + coleccionProductos.dimensionColeccion());
                        listener.verProducto(coleccionProductos.obtenerProductoPorIndice(posicion).getId());
                    }
                }
            });*/

        }

        public TextView getTextView(){
            return this.texto;
        }
    }

    /*public void filtrado(String textoEq) throws CloneNotSupportedException {
        if(textoEq.length() == 0){
            this.productosMostrados.clonarColeccion(this.minimarket.obtenerColeccion());
        }else{
            this.productosMostrados.vaciarContenido();

            for (int i = 0; i< minimarket.obtenerCantidadDeProductos();i++){
                if(minimarket.obtenerProductoIndice(i).getNombre().toLowerCase().contains(textoEq.toLowerCase())){
                    this.productosMostrados.agregarProducto(this.minimarket.obtenerProductoIndice(i));
                }
            }
        }
        notifyDataSetChanged();
    }*/

}
