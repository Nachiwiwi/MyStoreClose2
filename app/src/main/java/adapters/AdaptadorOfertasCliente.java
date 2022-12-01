package adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mystoreclose.R;

import java.util.ArrayList;
import java.util.Locale;

import modelo.Oferta;

public class AdaptadorOfertasCliente extends RecyclerView.Adapter<AdaptadorOfertasCliente.ViewHolder>{
    private ArrayList<Oferta> listadoOfertas;
    private ArrayList<Oferta> listadoOfertasFiltradas;
    private ItemClickListener clickListener;
    private int flag = 0;

    public AdaptadorOfertasCliente(ArrayList<Oferta> listadoOfertas){
        this.listadoOfertas = listadoOfertas;
        this.listadoOfertasFiltradas = this.listadoOfertas;
    }

    public void filtrado(String s) {
        this.listadoOfertasFiltradas = new ArrayList(listadoOfertas);
        if(s.length() == 0){
            this.listadoOfertasFiltradas = new ArrayList<>(this.listadoOfertas);
        }else{
            this.listadoOfertasFiltradas = new ArrayList<>();
            for(int i = 0; i < listadoOfertas.size(); i++){
                if(listadoOfertas.get(i).getNombreProducto().toLowerCase().contains(s.toLowerCase())){
                    this.listadoOfertasFiltradas.add(listadoOfertas.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }

    public interface ItemClickListener{

    }
    public void setItemListener(ItemClickListener itemListener){
        this.clickListener = itemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.formato_de_salida_ofertas, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorOfertasCliente.ViewHolder holder, int position) {
        holder.getTextView().setText("Local: "+this.listadoOfertasFiltradas.get(position).getNombreLocal() + "\n" +"Nombre producto: "+ this.listadoOfertasFiltradas.get(position).getNombreProducto()+ "\nPrecio original " + this.listadoOfertasFiltradas.get(position).getPrecioOriginal()
        + "\nPrecio oferta: " + this.listadoOfertasFiltradas.get(position).getPrecioOferta());
        //Formato de la salida
    }

    @Override
    public int getItemCount() {
        return this.listadoOfertasFiltradas.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView texto;

        public ViewHolder(View v){
            super(v);
            texto = v.findViewById(R.id.textoOferta);
        }
        public TextView getTextView(){
            return this.texto;
        }
    }
}
