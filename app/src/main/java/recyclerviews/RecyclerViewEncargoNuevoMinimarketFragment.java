package recyclerviews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mystoreclose.R;

import adapters.AdaptadorBuscarProductos;
import adapters.AdaptadorEncargosNuevosMinimarket;

public class RecyclerViewEncargoNuevoMinimarketFragment extends Fragment {
    private RecyclerView recyclerView;
    private AdaptadorEncargosNuevosMinimarket adaptadorProductos;
    private RecyclerView.LayoutManager layoutManager;

    // Tipo de layout que se va a mostrar por pantalla
    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }
    private RecyclerViewEncargoNuevoMinimarketFragment.LayoutManagerType layoutManagerTypeActual;

    // Constructor del Fragmento
    public RecyclerViewEncargoNuevoMinimarketFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_recycler_view_nuevos_encargos_minimarket, container, false);
        vista.setTag("pupi");
        this.recyclerView = (RecyclerView) vista.findViewById(R.id.recyclerViewEncargosNuevos);
        this.layoutManager = new LinearLayoutManager(getActivity());
        this.recyclerView.setAdapter(this.adaptadorProductos);
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.scrollToPosition(0);

        return vista;
    }

    /*public void setColeccion(EmpresaMinimarket minimarket){
        this.minimarket = minimarket;
    }*/

    /*public void actualizarBusqueda(String texto) throws CloneNotSupportedException {
        this.adaptadorProductos.filtrado(texto);
    }*/

    public void setAdapter(AdaptadorEncargosNuevosMinimarket ad){
        this.adaptadorProductos = ad;
    }

}
