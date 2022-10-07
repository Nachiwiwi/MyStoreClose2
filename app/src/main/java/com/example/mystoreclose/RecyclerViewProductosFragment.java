package com.example.mystoreclose;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class RecyclerViewProductosFragment extends Fragment {

    private RecyclerView recyclerView;
    private AdaptadorProductos adaptadorProductos;
    private String[] data;
    private int tamanioDatos = 10;
    private RecyclerView.LayoutManager layoutManager;

    // Tipo de layout que se va a mostrar por pantalla
    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }
    private LayoutManagerType layoutManagerTypeActual;

    // Constructor del Fragmento
    public RecyclerViewProductosFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataset();
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
        ArrayList<String> array = new ArrayList<>();
        this.replace(array);
        this.adaptadorProductos = new AdaptadorProductos(array);
        this.recyclerView.setAdapter(this.adaptadorProductos);
        this.recyclerView.setLayoutManager(layoutManager);


        return vista;
    }

    private void initDataset(){
        this.data = new String[this.tamanioDatos];
        for (int i = 0; i < this.tamanioDatos; i++){
            this.data[i] = "El elemento es: "+ i;
        }
    }

    private void replace(ArrayList arrayList){
        for (int i = 0; i < this.tamanioDatos; i++){
            arrayList.add(this.data[i]);
        }
    }
}