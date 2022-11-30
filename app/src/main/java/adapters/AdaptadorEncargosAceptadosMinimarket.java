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

public class AdaptadorEncargosAceptadosMinimarket extends RecyclerView.Adapter<AdaptadorEncargosAceptadosMinimarket.ViewHolder>{

    public interface ItemClickListener{
        void modificarEstadoEncargo(int indexEncargo);
    }

    //private ColeccionPedidos coleccionPedidos;

    private EmpresaMinimarket minimarket;
    private ItemClickListener myListener;

    public void setItemListener(ItemClickListener itemListener){
        this.myListener = itemListener;
    }

    public AdaptadorEncargosAceptadosMinimarket(EmpresaMinimarket minimarket){
        this.minimarket = minimarket;
    }

    @NonNull
    // Se llama esta funcion cuando el recyclerView precise de una nueva vista para mostrar por pantalla
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.formato_de_salida_encargos_aceptados, parent,false);
        return new ViewHolder(v, this.myListener);
    }

    // Para actualizar datos de una vista
    @Override
    public void onBindViewHolder(@NonNull AdaptadorEncargosAceptadosMinimarket.ViewHolder holder, int position) {
        //holder.bind(this.NombresProductos.get(position));
        Pedido p = this.minimarket.obtenerPedidoAceptadoPorIndice(position);
        if (p != null)
            holder.getTextView().setText("Nombre Producto: "+ p.getProductoSolicitado().getNombre() +"\n" +
                    "Numero de unidades: "+ p.getCantidadProductosSolicitados()
                    +"\nNombre Cliente: " + p.getCliente().getNombre()+ "\nDescripción : " +p.getProductoSolicitado().getDescripcion());

        switch (p.getEstado()){
            case 2:
                holder.getButton().setText("Entregado");
                break;

            case 3:
                holder.getButton().setText("Pendiente");
                break;

            case 4:
                holder.getButton().setText("Cancelado");
                break;
        }
    }

    // Numero de elementos que tiene una lista
    @Override
    public int getItemCount() {
        return this.minimarket.dimensionColeccionPedidosAceptados();
    }

    // clase ViewHolder

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView texto;
        private Button botonVer;

        private ImageView botonRechazar;
        private ImageView botonAceptar;

        public ViewHolder(View v, ItemClickListener listener) {
//        public ViewHolder(View v, ItemClickListener listener, ColeccionProductos coleccionProductos) {
            super(v);
            this.texto = v.findViewById(R.id.encargosAceptadosText);
            this.botonVer = v.findViewById(R.id.botonModificarEstadoEncargo);

            this.botonVer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posicion = getBindingAdapterPosition();
                    listener.modificarEstadoEncargo(posicion);
                    System.out.println("AAAAAAAAA: "+posicion);
                }
            });

            // Realiza una accion cuando se seleccione un elemento
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.d("Adapter Productos", getBindingAdapterPosition() + " Element " + coleccionProductos.dimensionColeccion() +" clicked.");
                }
            });
            
        }

        public TextView getTextView(){
            return this.texto;
        }
        public Button getButton(){
            return this.botonVer;
        }
    }

}
