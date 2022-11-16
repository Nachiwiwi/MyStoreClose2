package com.example.mystoreclose;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewVistaMinimarketCliente extends Fragment {
    private RecyclerView recyclerView;
    private AdaptadorVistaProductosCliente adaptadorProductos;
    private RecyclerView.LayoutManager layoutManager;
    private LayoutManagerType layoutManagerTypeActual;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    public RecyclerViewVistaMinimarketCliente(){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        System.out.println("\nRRRRRRRRRRRRRR\n");
        View vistaRecyclerView = inflater.inflate(R.layout.fragment_recycler_view_ver_minimarket_cliente, container, false);
        vistaRecyclerView.setTag("vistaMinimarketCliente");
        this.recyclerView = (RecyclerView) vistaRecyclerView.findViewById(R.id.recyclerViewVerMinimarketCliente);
        this.layoutManager = new LinearLayoutManager(getActivity());
        this.recyclerView.setAdapter(this.adaptadorProductos);
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.scrollToPosition(0);
        return vistaRecyclerView;
    }

    public void setAdapter(AdaptadorVistaProductosCliente adapterProductos) {
        this.adaptadorProductos = adapterProductos;
    }
}
