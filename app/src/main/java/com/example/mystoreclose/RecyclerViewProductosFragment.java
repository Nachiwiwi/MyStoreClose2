package com.example.mystoreclose;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import modelo.ColeccionProductos;


public class RecyclerViewProductosFragment extends Fragment {

    private RecyclerView recyclerView;
    private AdaptadorProductos adaptadorProductos;
    private ColeccionProductos data;
    private RecyclerView.LayoutManager layoutManager;

    // Tipo de layout que se va a mostrar por pantalla
    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }
    private LayoutManagerType layoutManagerTypeActual;

    // Constructor del Fragmento
    public RecyclerViewProductosFragment() {
        this.data = new ColeccionProductos();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        System.out.println("\nAAAAAAAAAAAAAAAAA\n");
        View vista = inflater.inflate(R.layout.fragment_recycler_view_productos, container, false);
        vista.setTag("pupi");
        this.recyclerView = (RecyclerView) vista.findViewById(R.id.recyclerViewProductosEmpresa);
        this.layoutManager = new LinearLayoutManager(getActivity());
        // Se crea el adaptador
        this.adaptadorProductos = new AdaptadorProductos(this.data);
        this.recyclerView.setAdapter(this.adaptadorProductos);
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.scrollToPosition(0);

        return vista;
    }

    public void setColeccion(ColeccionProductos coleccion){
        this.data = coleccion;
    }

}