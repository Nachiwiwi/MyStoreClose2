package com.example.mystoreclose;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import modelo.ColeccionProductos;
import modelo.Producto;

public class AdaptadorAgregarProductos extends RecyclerView.Adapter<AdaptadorAgregarProductos.ViewHolder>{

    public interface ItemClickListener{
        void obtenerIdProducto(int idProducto);
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
    public void onBindViewHolder(@NonNull AdaptadorAgregarProductos.ViewHolder holder, int position) {
        holder.getTextView().setText("Nombre: "+this.coleccionMostrar.obtenerProductoPorIndice(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return this.coleccionMostrar.dimensionColeccion();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView texto;
        private int itemSeleccionado = -1;
        //private Button botonAgregarProducto;

        public ViewHolder(View v, ItemClickListener listener, ColeccionProductos coleccionProductos) {
            super(v);
            this.texto = v.findViewById(R.id.nombreProductoAgergar);
            //this.botonAgregarProducto = (Button) v.findViewById(R.id.agregarProd2);

            // Realiza una accion cuando se seleccione un elemento
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setItemSeleccionado(getBindingAdapterPosition());
                    if (listener != null){
                        listener.obtenerIdProducto(coleccionProductos.obtenerProductoPorIndice(getItemSeleccionado()).getId());
                    }

                    Log.d("Adapter Productos", getBindingAdapterPosition() + " Element " + coleccionProductos.dimensionColeccion() +" clicked.");
                }
            });

        }

        public int getItemSeleccionado(){
            return this.itemSeleccionado;
        }

        public void setItemSeleccionado(int value){
            this.itemSeleccionado = value;
        }

        public TextView getTextView(){
            return this.texto;
        }
    }

    public void filtrado(String textoEq) throws CloneNotSupportedException {

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
