package recyclerviews;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mystoreclose.R;

import adapters.AdaptadorMinimarketsProductoS;


public class RecyclerViewMinimarketsProductoSeleccionado extends Fragment {

    private RecyclerView recyclerView;
    private AdaptadorMinimarketsProductoS adaptadorProductos;
    private RecyclerView.LayoutManager layoutManager;

    // Tipo de layout que se va a mostrar por pantalla
    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }
    private LayoutManagerType layoutManagerTypeActual;

    // Constructor del Fragmento
    public RecyclerViewMinimarketsProductoSeleccionado() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_recycler_view_minimarkets_producto_seleccionado, container, false);
        this.recyclerView = (RecyclerView) vista.findViewById(R.id.recyclerViewMinimarketsProducto);
        this.layoutManager = new LinearLayoutManager(getActivity());
        // Se crea el adaptador
        //this.adaptadorProductos = new AdaptadorProductos(this.minimarket);
        this.recyclerView.setAdapter(this.adaptadorProductos);
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.scrollToPosition(0);

        return vista;
    }


    public void setAdapter(AdaptadorMinimarketsProductoS adapter){
        this.adaptadorProductos = adapter;
    }

}