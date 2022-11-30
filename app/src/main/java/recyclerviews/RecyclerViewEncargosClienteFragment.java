package recyclerviews;

import android.app.Activity;
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

import adapters.AdaptadorEncargosCliente;

public class RecyclerViewEncargosClienteFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdaptadorEncargosCliente adaptadorEncargos;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }
    public RecyclerViewEncargosClienteFragment(){

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vistaRecyclerView = inflater.inflate(R.layout.fragment_recycler_view_encargos_cliente, container, false);
        vistaRecyclerView.setTag("vistaEncargosCliente");
        this.recyclerView = (RecyclerView) vistaRecyclerView.findViewById(R.id.recyclerViewEncargosCliente);
        this.layoutManager = new LinearLayoutManager(getActivity());
        this.recyclerView.setAdapter(this.adaptadorEncargos);
        this.recyclerView.setLayoutManager(layoutManager);
        return vistaRecyclerView;
    }

    public void setAdapter(AdaptadorEncargosCliente adaptadorEncargos) {
        this.adaptadorEncargos = adaptadorEncargos;
    }
}
