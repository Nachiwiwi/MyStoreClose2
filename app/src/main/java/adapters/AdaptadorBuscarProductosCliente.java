package adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mystoreclose.R;

import java.util.ArrayList;

import modelo.Producto;

public class AdaptadorBuscarProductosCliente extends RecyclerView.Adapter<AdaptadorBuscarProductosCliente.ViewHolder>{
    private ArrayList<Producto> listadoProductos ;
    private ArrayList<Producto> listadoProductosFiltrados ;
    private ItemClickListener clickListener;

    public interface ItemClickListener{
        void verProducto(int posicion);
    }

    public void setItemListener(ItemClickListener itemListener){
        this.clickListener = itemListener;
    }

    public AdaptadorBuscarProductosCliente(ArrayList<Producto> NombresProductos){
        this.listadoProductos = NombresProductos ;
        this.listadoProductosFiltrados = new ArrayList<>(NombresProductos) ;

    }

    @NonNull
    // Se llama esta funcion cuando el recyclerView precise de una nueva vista para mostrar por pantalla
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.formato_de_salida_productos, parent,false);
        return new ViewHolder(v,this.clickListener,this.listadoProductosFiltrados);
    }

    // Para actualizar datos de una vista
    @Override
    public void onBindViewHolder(@NonNull AdaptadorBuscarProductosCliente.ViewHolder holder, int position) {
        //holder.bind(this.NombresProductos.get(position));
        holder.getTextView().setText("Nombre: "+this.listadoProductosFiltrados.get(position).getNombre() + "\n");// +"Distancia: "+ this.listadoProductos.get(position).getDistanciaRespectoUsuario()+"m");
    }

    // Numero de elementos que tiene una lista
    @Override
    public int getItemCount() {
        return this.listadoProductosFiltrados.size();
    }

    public void filtrado(String texto) {
        this.listadoProductosFiltrados = new ArrayList(this.listadoProductos);
        if(texto.length() == 0){
            //mostrar todo
            this.listadoProductosFiltrados = new ArrayList<>(this.listadoProductos);
        }else{
            //vaciar la lista de los productos
            this.listadoProductosFiltrados = new ArrayList<>();

            for (int i = 0; i< listadoProductos.size();i++){
                if(listadoProductos.get(i).getNombre().toLowerCase().contains(texto.toLowerCase())){
                    this.listadoProductosFiltrados.add(listadoProductos.get(i));
                }
            }

        }
        notifyDataSetChanged();
    }

    // clase ViewHolder

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView texto;
        private ImageView botonVer;

        public ViewHolder(View v, ItemClickListener listener, ArrayList<Producto> coleccion) {
            super(v);
            texto = v.findViewById(R.id.textoNombre);
            botonVer = v.findViewById(R.id.botonVerProducto);


            // Realiza una accion cuando se seleccione un elemento
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Adapter Productos", "Element " + " clicked.");
                }
            });

            this.botonVer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(listener != null){
                        int posicion = getBindingAdapterPosition();
                        //System.out.println("La posicion es: "+posicion+ " El producto es: "+
                        //       coleccionProductos.obtenerProductoPorIndice(posicion).getNombre()+ " y el tamaño es: " + coleccionProductos.dimensionColeccion());
                        listener.verProducto(coleccion.get(posicion).getId());
                    }
                }
            });


        }
        public TextView getTextView(){
            return this.texto;
        }
    }




}