package recyclerviews;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mystoreclose.R;

import adapters.AdaptadorEncargosAceptadosMinimarket;
import adapters.AdaptadorEncargosNuevosMinimarket;

public class RecyclerViewEncargoAceptadoMinimarketFragment extends Fragment {
    private RecyclerView recyclerView;
    private AdaptadorEncargosAceptadosMinimarket adaptador;
    private RecyclerView.LayoutManager layoutManager;

    // Tipo de layout que se va a mostrar por pantalla
    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }
    private RecyclerViewEncargoAceptadoMinimarketFragment.LayoutManagerType layoutManagerTypeActual;

    // Constructor del Fragmento
    public RecyclerViewEncargoAceptadoMinimarketFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_recycler_view_encargos_aceptados_minimarket, container, false);
        vista.setTag("pupi");
        this.recyclerView = (RecyclerView) vista.findViewById(R.id.recyclerViewEncargosAceptados);
        this.layoutManager = new LinearLayoutManager(getActivity());
        this.recyclerView.setAdapter(this.adaptador);
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

    public void setAdapter(AdaptadorEncargosAceptadosMinimarket ad){
        this.adaptador = ad;
    }
}
