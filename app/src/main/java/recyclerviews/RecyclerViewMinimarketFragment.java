package recyclerviews;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mystoreclose.R;

import java.util.ArrayList;

import adapters.AdaptadorMinimarkets;
import modelo.EmpresaMinimarket;


public class RecyclerViewMinimarketFragment extends Fragment {


    private ArrayList<EmpresaMinimarket> data;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdaptadorMinimarkets adaptadorVerMinimarkets;

    public void setAdapter(AdaptadorMinimarkets ad){
        this.adaptadorVerMinimarkets = ad;
    }

    public void actualizarBusqueda(String s) {
        this.adaptadorVerMinimarkets.filtrado(s);
    }

    // Tipo de layout que se va a mostrar por pantalla
    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    private LayoutManagerType layoutManagerTypeActual;

    // Constructor del Fragmento
    public RecyclerViewMinimarketFragment() {
        this.data = new ArrayList<>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_recycler_view_minimarkets, container, false);
        vista.setTag("pupi");
        this.recyclerView = (RecyclerView) vista.findViewById(R.id.recyclerViewMinimarkets);
        this.layoutManager = new LinearLayoutManager(getActivity());

        // Se crea el adaptador

        this.recyclerView.setAdapter(this.adaptadorVerMinimarkets);
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.scrollToPosition(0);

        return vista;
    }

    public void setColeccion(ArrayList<EmpresaMinimarket> coleccion) {
        this.data = coleccion;
    }
}