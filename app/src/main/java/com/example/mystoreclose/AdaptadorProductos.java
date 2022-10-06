package com.example.mystoreclose;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.ViewHolder>{
    private ArrayList<String> NombresProductos;
    public AdaptadorProductos(ArrayList<String> NombresProductos){
        this.NombresProductos = NombresProductos;
    }
    @NonNull
    // Se llama esta funcion cuando el recyclerView precise de una nueva vista para mostrar por pantalla
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.formato_de_salida_productos, parent,false);
        return new ViewHolder(v);
    }
    // Para actualizar datos de una vista
    @Override
    public void onBindViewHolder(@NonNull AdaptadorProductos.ViewHolder holder, int position) {
        //holder.bind(this.NombresProductos.get(position));
        holder.getTextView().setText(this.NombresProductos.get(position));
    }
    // Numero de elementos que tiene una lista
    @Override
    public int getItemCount() {
        return this.NombresProductos.size();
    }

    // clase ViewHolder

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView texto;

        public ViewHolder(View v) {
            super(v);
            texto = v.findViewById(R.id.textoNombre);

            // Realiza una accion cuando se seleccione un elemento
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Adapter Productos", "Element " + " clicked.");
                }
            });
        }
        public TextView getTextView(){
            return this.texto;
        }
    }
}
