package recyclerviews;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mystoreclose.R;

import adapters.AdaptadorAgregarProductos;


public class RecyclerViewAgregarProductoFragment extends Fragment {

    private RecyclerView recyclerView;
    private AdaptadorAgregarProductos adaptadorProductos;
    private RecyclerView.LayoutManager layoutManager;

    // Tipo de layout que se va a mostrar por pantalla
    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }
    private LayoutManagerType layoutManagerTypeActual;

    // Constructor del Fragmento
    public RecyclerViewAgregarProductoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        System.out.println("\nAAAAAAAAAAAAAAAAA\n");
        View vista = inflater.inflate(R.layout.fragment_recycler_view_agregar_producto, container, false);
        vista.setTag("pupi");
        this.recyclerView = (RecyclerView) vista.findViewById(R.id.recyclerViewAgregarProducto);
        this.layoutManager = new LinearLayoutManager(getActivity());
        // Se crea el adaptador
        //this.adaptadorProductos = new AdaptadorBuscarProductos(this.minimarket);
        this.recyclerView.setAdapter(this.adaptadorProductos);
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.scrollToPosition(0);

        return vista;
    }

    public void actualizarBusqueda(String texto) throws CloneNotSupportedException {
        this.adaptadorProductos.filtrado(texto);
    }

    public void setAdapter(AdaptadorAgregarProductos ad){
        this.adaptadorProductos = ad;
    }

}