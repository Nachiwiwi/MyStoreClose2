package com.example.mystoreclose;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

import modelo.ColeccionProductos;
import modelo.EmpresaMinimarket;

public class AdaptadorVerProducto extends RecyclerView.Adapter<AdaptadorVerProducto.ViewHolder>{

    // metodos que debe tener la clase que utiliza el adapter
    public interface ItemClickListener{
        void verProducto(int posicion);
    }

    private ItemClickListener myListener;
    private EmpresaMinimarket minimarket;

    public void setItemListener(ItemClickListener itemListener){
        this.myListener = itemListener;
    }

    public AdaptadorVerProducto(EmpresaMinimarket minimarket){
        this.minimarket = minimarket;
    }
    @NonNull
    // Se llama esta funcion cuando el recyclerView precise de una nueva vista para mostrar por pantalla
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.formato_de_salida_productos, parent,false);
        return new ViewHolder(v, this.myListener);
    }
    // Para actualizar datos de una vista
    @Override
    public void onBindViewHolder(@NonNull AdaptadorVerProducto.ViewHolder holder, int position) {
        //holder.bind(this.NombresProductos.get(position));

        holder.getTextView().setText("Nombre: "+this.minimarket.obtenerProductoIndice(position).getNombre()
                + "\nPrecio: "+ this.minimarket.obtenerProductoIndice(position).getPrecio());


    }
    // Numero de elementos que tiene una lista
    @Override
    public int getItemCount() {
        return this.minimarket.obtenerCantidadDeProductos();
    }

    // clase ViewHolder

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView texto;
        private ImageView botonVer;

        public ViewHolder(View v, ItemClickListener listener) {
            super(v);
            texto = v.findViewById(R.id.textoNombre);
            botonVer = v.findViewById(R.id.botonVerProducto);

            // Realiza una accion cuando se seleccione un elemento
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Adapter Productos", "Element "+ getBindingAdapterPosition() +" clicked.");
                }
            });
            // botonVerProducto
            this.botonVer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int posicion = getBindingAdapterPosition();
                        listener.verProducto(posicion);
                    }
                }
            });
        }

        public TextView getTextView(){
            return this.texto;
        }

    }

}
