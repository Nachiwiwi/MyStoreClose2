package adapters;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mystoreclose.R;

import modelo.ColeccionProductos;

public class AdaptadorAgregarProductos extends RecyclerView.Adapter<AdaptadorAgregarProductos.ViewHolder>{

    public interface ItemClickListener{
        void obtenerIdProducto(int idProducto);
        void setFalseFrameLayout(FrameLayout fL);
    }
    private ColeccionProductos coleccionProductos;
    private ColeccionProductos coleccionMostrar;

    private ItemClickListener myListener;

    public AdaptadorAgregarProductos(ColeccionProductos coleccionProductos){
        this.coleccionProductos = coleccionProductos;
        this.coleccionMostrar = coleccionProductos.duplicarColeccion();
    }

    public void setItemListener(AdaptadorAgregarProductos.ItemClickListener itemListener){
        this.myListener = itemListener;
    }
    @NonNull
    @Override
    public AdaptadorAgregarProductos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.formato_agregar_producto, parent,false);
        return new ViewHolder(v,this.myListener, this.coleccionMostrar);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        getItemViewType(position);

        holder.getTextView().setText("Nombre: "+this.coleccionMostrar.obtenerProductoPorIndice(position).getNombre());

    }



    @Override
    public int getItemCount() {
        return this.coleccionMostrar.dimensionColeccion();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView texto;
        private FrameLayout frameLayout;
        private int itemSeleccionado = -1;
        //private Button botonAgregarProducto;

        public ViewHolder(View v, ItemClickListener listener, ColeccionProductos coleccionProductos) {
            super(v);
            this.texto = v.findViewById(R.id.nombreProductoAgergar);
            this.frameLayout = v.findViewById(R.id.frameLayoutProductoAgregar);

            ////this.botonAgregarProducto = (Button) v.findViewById(R.id.agregarProd2);

            // Realiza una accion cuando se seleccione un elemento
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //setItemSeleccionado(getBindingAdapterPosition());
                    if (listener != null){
                        System.out.println(coleccionProductos.obtenerProductoPorIndice(getBindingAdapterPosition()).getId()+ "-----"+coleccionProductos.obtenerProductoPorIndice(getBindingAdapterPosition()).getNombre());
                        System.out.println(coleccionProductos.dimensionColeccion());
                        if(coleccionProductos.dimensionColeccion() >= getBindingAdapterPosition() ) {
                            listener.obtenerIdProducto(coleccionProductos.obtenerProductoPorIndice(getBindingAdapterPosition()).getId());
                            listener.setFalseFrameLayout(frameLayout);
                        }
                    }

                    frameLayout.setBackgroundColor(Color.parseColor("#FF00ADB5"));


                    Log.d("Adapter Productos", getBindingAdapterPosition() + " Element " + coleccionProductos.dimensionColeccion() +" clicked.");
                }
            });


        }


        public TextView getTextView(){
            return this.texto;
        }


        public FrameLayout getFrameLayout(){
            return this.frameLayout;
        }
    }

    public void filtrado(String textoEq) {

        if(textoEq.length() == 0){
            this.coleccionMostrar = this.coleccionProductos.duplicarColeccion();
        }else{

            this.coleccionMostrar.vaciarContenido();

            for (int i = 0; i< this.coleccionProductos.dimensionColeccion() ;i++){
                if(this.coleccionProductos.obtenerProductoPorIndice(i).getNombre().toLowerCase().contains(textoEq.toLowerCase())){
                    this.coleccionMostrar.agregarProducto(this.coleccionProductos.obtenerProductoPorIndice(i));
                }
            }

        }
        notifyDataSetChanged();
    }

}
