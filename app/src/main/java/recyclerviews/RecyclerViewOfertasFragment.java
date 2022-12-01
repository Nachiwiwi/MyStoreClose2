package recyclerviews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mystoreclose.R;

import adapters.AdaptadorOfertasCliente;

public class RecyclerViewOfertasFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdaptadorOfertasCliente adaptadorOfertasCliente;

    public void actualizarBusqueda(String s) {
        this.adaptadorOfertasCliente.filtrado(s);
    }


    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }
    public RecyclerViewOfertasFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void setAdapter(AdaptadorOfertasCliente adaptadorOfertasCliente) {
        this.adaptadorOfertasCliente = adaptadorOfertasCliente;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View vista = inflater.inflate(R.layout.fragment_ofertas_cliente, container, false);
        vista.setTag("tag");

        this.recyclerView = (RecyclerView) vista.findViewById(R.id.recyclerViewVerOfertas);
        this.layoutManager = new LinearLayoutManager(getActivity());

        this.recyclerView.setAdapter(this.adaptadorOfertasCliente);
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.scrollToPosition(0);

        return vista;

    }

}
