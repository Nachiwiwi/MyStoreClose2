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
    private ArrayList<EmpresaMinimarket> mostrarMinimarkets;
    private int idProducto;
    private int precioMinimo;
    private int precioMaximo;


    public AdaptadorMinimarketsProductoS(ArrayList minimarkets, int idProducto){
        this.minimarkets = minimarkets;
        this.mostrarMinimarkets = new ArrayList<>(this.minimarkets);
        precioMinimo = -1;
        precioMaximo = -1;
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

        String nombre = this.mostrarMinimarkets.get(position).getNombreEmpresa();
        String distancia = this.mostrarMinimarkets.get(position).getDistancia();
        String direccion = this.mostrarMinimarkets.get(position).getDireccion();
        String precio = this.mostrarMinimarkets.get(position).obtenerProducto(this.idProducto).getPrecio();

        holder.getTextView().setText("Nombre del minimarket: " + nombre
                        + "\nDistancia: " + distancia
                        + "\nDireccion: " + direccion
                        + "\nPrecio del Producto: " + precio);


    }

    @Override
    public int getItemCount() {
        return this.mostrarMinimarkets.size();
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

    public void filtrado(){
        int precio;

        this.mostrarMinimarkets = new ArrayList<>();

        if (this.precioMinimo == -1 && this.precioMaximo == -1) {
            this.mostrarMinimarkets = new ArrayList<>(this.minimarkets);

        }

        if (this.precioMinimo == -1 && this.precioMaximo != -1){
            this.mostrarMinimarkets = new ArrayList<>();
            for (int i = 0; i < this.minimarkets.size(); i++){
                precio = Integer.parseInt(this.minimarkets.get(i).obtenerProducto(this.idProducto).getPrecio());
                if (this.precioMaximo >= precio ){
                    this.mostrarMinimarkets.add(this.minimarkets.get(i));
                }
            }

        }

        if (this.precioMinimo != -1 && this.precioMaximo != -1){
            for (int i = 0; i < this.minimarkets.size(); i++){
                precio = Integer.parseInt(this.minimarkets.get(i).obtenerProducto(this.idProducto).getPrecio());
                if (this.precioMinimo <= precio  && this.precioMaximo >= precio ){
                    this.mostrarMinimarkets.add(this.minimarkets.get(i));
                }
            }

        }

        if (this.precioMinimo != -1 && this.precioMaximo == -1){
            for (int i = 0; i < this.minimarkets.size(); i++){
                precio = Integer.parseInt(this.minimarkets.get(i).obtenerProducto(this.idProducto).getPrecio());
                if (this.precioMinimo <= precio ){
                    this.mostrarMinimarkets.add(this.minimarkets.get(i));
                }
            }

        }

        this.notifyDataSetChanged();
    }

    public void setPrecioMaximo(int precioMaximo) {
        this.precioMaximo = precioMaximo;
    }

    public void setPrecioMinimo(int precioMinimo) {
        this.precioMinimo = precioMinimo;
    }
}
