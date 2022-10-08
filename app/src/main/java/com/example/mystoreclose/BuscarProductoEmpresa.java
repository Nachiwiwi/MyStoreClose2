package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;

import modelo.ColeccionProductos;
import modelo.EmpresaMinimarket;
// Se implementa la interfaz "SearchView.OnQueryTextListener" para utilizar el SearchView y buscar objetos en tiempo real
public class BuscarProductoEmpresa extends AppCompatActivity implements SearchView.OnQueryTextListener{

    //Botones
    private ImageButton botonAtras;
    private EmpresaMinimarket minimarket= null;
    private SearchView buscarProducto;
    private RecyclerViewBuscarProductosFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_producto_empresa);
        inicializar();

        //Apretar flecha
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(BuscarProductoEmpresa.this, InicioEmpresa.class);
                startActivity(volver);
            }
        });

        this.buscarProducto.setOnQueryTextListener(this);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragment = new RecyclerViewBuscarProductosFragment();
        fragment.setColeccion(this.minimarket);
        transaction.replace(R.id.buscarProd, fragment);
        transaction.commit();


    }

    public void inicializar(){

        this.buscarProducto = (SearchView) findViewById(R.id.searchView);
        this.botonAtras = (ImageButton) findViewById(R.id.volverInicio);
        // Objeto enviado a través de Bundle
        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            this.minimarket = (EmpresaMinimarket) bundle.getSerializable("minimarket");
            System.out.println(this.minimarket.getNombreEmpresa());
        }
    }

    // Estos metodos nos ayudarán a buscar objetos en tiempo real
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        fragment.actualizarBusqueda(newText);
        return false;
    }
}