package adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mystoreclose.AdaptadorVistaProductosCliente;
import com.example.mystoreclose.R;

import java.util.ArrayList;

import modelo.Pedido;
import modelo.Producto;

public class AdaptadorEncargosCliente extends RecyclerView.Adapter<AdaptadorEncargosCliente.ViewHolder> {

    private ArrayList<Producto> listadoProductos;
    private Pedido nuevoEncargo;
    public AdaptadorEncargosCliente(ArrayList<Producto> listadoProductos) {
        this.listadoProductos = listadoProductos;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.formato_de_salida_encargos_cliente, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTextView().setText("Nombre: "+this.listadoProductos.get(position).getNombre()
                + "\nPrecio: "+ this.listadoProductos.get(position).getPrecio());
    }

    @Override
    public int getItemCount() {
        return this.listadoProductos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView texto;
        public ViewHolder(View v) {
            super(v);
            this.texto = v.findViewById(R.id.textoNombre);
        }
        public TextView getTextView(){
            return this.texto;
        }
    }
}
