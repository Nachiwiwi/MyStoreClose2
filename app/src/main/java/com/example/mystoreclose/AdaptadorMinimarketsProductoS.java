package com.example.mystoreclose;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import modelo.EmpresaMinimarket;

public class AdaptadorMinimarketsProductoS extends RecyclerView.Adapter<AdaptadorMinimarketsProductoS.ViewHolder> {

    private ArrayList<EmpresaMinimarket> minimarkets;
    private int idProducto;


    public AdaptadorMinimarketsProductoS(ArrayList minimarkets, int idProducto){
        this.minimarkets = minimarkets;
        this.idProducto = idProducto;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.formato_minimarkets_producto_seleccionado, parent,false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorMinimarketsProductoS.ViewHolder holder, int position) {
        String nombre = minimarkets.get(position).getNombreEmpresa();
        String distancia = minimarkets.get(position).getDistancia();
        String direccion = minimarkets.get(position).getDireccion();
        String precio = minimarkets.get(position).obtenerProducto(this.idProducto).getPrecio();
        holder.getTextView().setText("Nombre del minimarket: "+nombre
        +"\nDistancia: "+distancia
        +"\nDireccion: "+direccion
        +"\nPrecio del Producto: "+precio);
    }

    @Override
    public int getItemCount() {
        return this.minimarkets.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView texto;

        public ViewHolder(View v) {
            super(v);
            texto = v.findViewById(R.id.descripcionMinimarket);

            // Realiza una accion cuando se seleccione un elemento
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Adapter Minimarket Prod", "Element "+ getBindingAdapterPosition() +" clicked.");
                }
            });
        }

        public TextView getTextView(){
            return this.texto;
        }
    }
}
