package com.example.mystoreclose;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import modelo.ColeccionProductos;
import modelo.EmpresaMinimarket;
import modelo.Producto;

public class AdaptadorVistaProductosCliente extends RecyclerView.Adapter<AdaptadorVistaProductosCliente.ViewHolder>{

    public interface ItemClickListener{
        void activarBoton(Producto producto);
    }

    private EmpresaMinimarket minimarketSeleccionado;
    private ColeccionProductos listadoProductosMinimarket;
    private ItemClickListener myListener;

    public void setItemListener(ItemClickListener itemListener){
        this.myListener = itemListener;
    }

    public AdaptadorVistaProductosCliente(EmpresaMinimarket minimarket) throws CloneNotSupportedException{
        this.minimarketSeleccionado = minimarket;
        //this.listadoProductosMinimarket = minimarket.obtenerColeccion();
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.formato_de_salida_productos_encargos_cliente, parent,false);
        //System.out.println("largoxd"+this.minimarketSeleccionado.obtenerCantidadDeProductos()+"productos " + this.listadoProductosMinimarket.obtenerProducto(0).getNombre()+ "productoxd ");
        try {
            return new ViewHolder(v,this.myListener, this.minimarketSeleccionado.obtenerColeccion());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    @NonNull
    /*@Override
    public AdaptadorVistaProductosCliente.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.minimarket = minimarket;
        this.productosMostrados = minimarket.obtenerColeccion();
    }*/

    @Override
    public void onBindViewHolder(@NonNull AdaptadorVistaProductosCliente.ViewHolder holder, int position) {
        holder.getTextView().setText("Nombre: "+this.minimarketSeleccionado.obtenerProductoIndice(position).getNombre()
                + "\nPrecio: "+ this.minimarketSeleccionado.obtenerProductoIndice(position).getPrecio());
    }

    public int getlargo(){
        return this.minimarketSeleccionado.obtenerCantidadDeProductos();
    }

    @Override
    public int getItemCount() {
        return this.minimarketSeleccionado.obtenerCantidadDeProductos();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView texto;
        private Button botonVer;

        public ViewHolder(View v, ItemClickListener listener, ColeccionProductos coleccionProductos) {
            super(v);
            this.texto = v.findViewById(R.id.textoNombre);
            this.botonVer = v.findViewById(R.id.bottonAgregarAlCarro);

            this.botonVer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int posicion = getBindingAdapterPosition();
                        System.out.println(coleccionProductos.dimensionColeccion());
                        listener.activarBoton(coleccionProductos.obtenerProductoPorIndice(posicion));
                    }
                }
            });

        }
        public TextView getTextView(){
            return this.texto;
        }
    }
}
