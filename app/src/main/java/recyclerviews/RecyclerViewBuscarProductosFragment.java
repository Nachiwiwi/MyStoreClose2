package recyclerviews;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mystoreclose.R;

import adapters.AdaptadorBuscarProductos;

public class RecyclerViewBuscarProductosFragment extends Fragment {

    private RecyclerView recyclerView;
    private AdaptadorBuscarProductos adaptadorProductos;
    private RecyclerView.LayoutManager layoutManager;

    // Tipo de layout que se va a mostrar por pantalla
    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }
    private LayoutManagerType layoutManagerTypeActual;

    // Constructor del Fragmento
    public RecyclerViewBuscarProductosFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_recycler_view_buscar_productos, container, false);
        this.recyclerView = (RecyclerView) vista.findViewById(R.id.recyclerViewBuscarProductosEmpresa);
        this.layoutManager = new LinearLayoutManager(getActivity());
        this.recyclerView.setAdapter(this.adaptadorProductos);
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.scrollToPosition(0);

        return vista;
    }

    /*public void setColeccion(EmpresaMinimarket minimarket){
        this.minimarket = minimarket;
    }*/

    public void actualizarBusqueda(String texto) throws CloneNotSupportedException {
        this.adaptadorProductos.filtrado(texto);
    }

    public void setAdapter(AdaptadorBuscarProductos ad){
        this.adaptadorProductos = ad;
    }

}