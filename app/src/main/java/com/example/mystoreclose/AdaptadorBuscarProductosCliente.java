package com.example.mystoreclose;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import modelo.EmpresaMinimarket;
import modelo.Producto;

public class AdaptadorBuscarProductosCliente extends RecyclerView.Adapter<AdaptadorBuscarProductosCliente.ViewHolder>{
    private ArrayList<Producto> listadoProductos = new ArrayList<>();
    private ArrayList<Producto> listadoProductosFiltrados = new ArrayList<>();
    public AdaptadorBuscarProductosCliente(ArrayList<Producto> NombresProductos){
        this.listadoProductos = new ArrayList<>(NombresProductos) ;
        this.listadoProductosFiltrados = new ArrayList<>(NombresProductos) ;
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
        if(texto.length() == 0){
            //mostrar todo
            this.listadoProductosFiltrados = new ArrayList<>(listadoProductos);
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