package adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mystoreclose.R;

import java.util.ArrayList;

import modelo.Cliente;
import modelo.Direccion;
import modelo.EmpresaMinimarket;

public class AdaptadorMinimarkets extends RecyclerView.Adapter<AdaptadorMinimarkets.ViewHolder>{
    private ArrayList<EmpresaMinimarket> listadoMinimarkets ;
    private ItemClickListener clickListener;



    public interface ItemClickListener{
        void verProducto(int posicion);
    }

    public void setItemListener(ItemClickListener itemListener){
        this.clickListener = itemListener;
    }
    public AdaptadorMinimarkets(ArrayList<EmpresaMinimarket> NombresProductos){
        this.listadoMinimarkets = NombresProductos ;
    }

    @NonNull
    // Se llama esta funcion cuando el recyclerView precise de una nueva vista para mostrar por pantalla
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.formato_de_salida_productos, parent,false);
        return new ViewHolder(v,this.clickListener ,listadoMinimarkets);
    }

    public int obtenerSize(){
        return this.listadoMinimarkets.size();
    }

    // Para actualizar datos de una vista
    @Override
    public void onBindViewHolder(@NonNull AdaptadorMinimarkets.ViewHolder holder, int position) {
        //holder.bind(this.NombresProductos.get(position));

        holder.getTextView().setText("Nombre: "+this.listadoMinimarkets.get(position).getNombreMinimarket() + "\n" +"Distancia: "+ this.listadoMinimarkets.get(position).getDistanciaRespectoUsuario()+" "+this.listadoMinimarkets.get(position).obtenerColeccion().dimensionColeccion());

    }

    // Numero de elementos que tiene una lista
    @Override
    public int getItemCount() {
        return this.listadoMinimarkets.size();
    }

    // clase ViewHolder

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView texto;
        private ImageView botonVer;

        public ViewHolder(View v, ItemClickListener listener, ArrayList<EmpresaMinimarket> coleccion) {
            super(v);
            texto = v.findViewById(R.id.textoNombre);
            botonVer = v.findViewById(R.id.botonVerProducto);
            // Realiza una accion cuando se seleccione un elemento
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Adapter Productos", "Element " + " clicked.");

                }
            });

            this.botonVer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("xdxd");
                    if (listener != null) {
                        int posicion = getBindingAdapterPosition();
                        System.out.println("La posicion es: "+posicion+ " El producto es: "+
                              coleccion.get(posicion).getNombreEmpresa()+ " y el tamaño es: " + coleccion.get(posicion).getDistancia());
                        listener.verProducto(posicion);
                    }
                }
            });
        }
        public TextView getTextView(){
            return this.texto;
        }
    }

    public void ordenarLista(Cliente clienteActual) {
        EmpresaMinimarket arregloMinimarkets[] = new EmpresaMinimarket[listadoMinimarkets.size()];
        for(int i = 0; i < listadoMinimarkets.size(); i++){
            arregloMinimarkets[i] = listadoMinimarkets.get(i);
            int distanciaTotal = calculoDistancia(clienteActual.getPosicionActual(), listadoMinimarkets.get(i).getPosicion());
            arregloMinimarkets[i].setDistanciaRespectoUsuario(distanciaTotal);
            System.out.println("la distancia es de: " + distanciaTotal);
        }

        quickSort(arregloMinimarkets, 0, listadoMinimarkets.size()-1);
        listadoMinimarkets.clear();
        for(int i = 0 ; i< arregloMinimarkets.length; i++){
            listadoMinimarkets.add(arregloMinimarkets[i]);
            System.out.println("la distancia ordenada es: "+arregloMinimarkets[i].getDistanciaRespectoUsuario());
            System.out.println("nombre: " +listadoMinimarkets.get(i).getNombreEmpresa());
            System.out.println("Distancia Empresa: "+listadoMinimarkets.get(i).getDistanciaRespectoUsuario());
        }
    }


    public int calculoDistancia(Direccion posicionCliente, Direccion posicionMinimarket){
        double distanciaTotal = 0;
        final float radioTierraKm = 6378.0F;
        double difLatitud = (posicionCliente.getLatitud() - posicionMinimarket.getLatitud()) * (Math.PI / 180);
        double difLongitud = (posicionCliente.getLongitud() -posicionMinimarket.getLongitud())* (Math.PI / 180);

        distanciaTotal = (Math.pow(Math.sin(difLatitud/2),2)) + Math.cos(posicionMinimarket.getLatitud()* (Math.PI / 180)) * Math.cos(posicionCliente.getLatitud()* (Math.PI / 180)) * (Math.pow(Math.sin(difLongitud/2),2));
        distanciaTotal = 2 * Math.atan2(Math.sqrt(distanciaTotal), Math.sqrt(1 - distanciaTotal));
        distanciaTotal = distanciaTotal * radioTierraKm;
        return (int)distanciaTotal;
    }
    private int partition(EmpresaMinimarket arrayMinimarkets[], int begin, int end) {
        int pivot = arrayMinimarkets[end].getDistanciaRespectoUsuario();
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (arrayMinimarkets[j].getDistanciaRespectoUsuario() <= pivot) {
                i++;

                EmpresaMinimarket swapTemp = arrayMinimarkets[i];
                arrayMinimarkets[i] = arrayMinimarkets[j];
                arrayMinimarkets[j] = swapTemp;
            }
        }

        EmpresaMinimarket swapTemp = arrayMinimarkets[i+1];
        arrayMinimarkets[i+1] = arrayMinimarkets[end];
        arrayMinimarkets[end] = swapTemp;

        return i+1;
    }

    public void quickSort(EmpresaMinimarket arrayMinimarkets[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arrayMinimarkets, begin, end);

            quickSort(arrayMinimarkets, begin, partitionIndex-1);
            quickSort(arrayMinimarkets, partitionIndex+1, end);
        }
    }
}

