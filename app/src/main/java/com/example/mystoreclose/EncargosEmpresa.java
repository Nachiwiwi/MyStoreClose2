package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import adapters.AdaptadorEncargosAceptadosMinimarket;
import adapters.AdaptadorEncargosNuevosMinimarket;
import adapters.AdaptadorProductos;
import modelo.ConectorBD;
import modelo.EmpresaMinimarket;
import recyclerviews.RecyclerViewEncargoAceptadoMinimarketFragment;
import recyclerviews.RecyclerViewEncargoNuevoMinimarketFragment;
import recyclerviews.RecyclerViewProductosFragment;

public class EncargosEmpresa extends AppCompatActivity {

    //Botones
    ImageButton botonAtras;
    private Button botonPerfil;
    private Button botonInicio;
    private EmpresaMinimarket minimarket;
    // Recycler view pedidos nuevos
    private RecyclerViewEncargoNuevoMinimarketFragment fragmentNuevos;
    private AdaptadorEncargosNuevosMinimarket adapterNuevos;
    // Recycler view pedidos aceptados
    private RecyclerViewEncargoAceptadoMinimarketFragment fragmentAceptados;
    private AdaptadorEncargosAceptadosMinimarket adapterAceptados;
    // Base de datos
    ConectorBD bd;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encargos_empresa);

        inicializar();
        inicializarRecyclerView();

        //Apretar flecha
        botonAtras = (ImageButton) findViewById(R.id.volverInicio);
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(EncargosEmpresa.this, InicioEmpresa.class);
                startActivity(volver);
            }
        });
        botonPerfil = (Button) findViewById(R.id.perfil);
        botonPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(EncargosEmpresa.this, PerfilEmpresa.class);
                startActivity(volver);
            }
        });
        botonInicio = (Button) findViewById(R.id.productos);
        botonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(EncargosEmpresa.this, InicioEmpresa.class);
                startActivity(volver);
            }
        });
    }

    public void inicializar(){
        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            this.minimarket = (EmpresaMinimarket) bundle.getSerializable("minimarket");
            System.out.println("Buscar Producto, el nombre del minimarket es: "+ this.minimarket.getNombreEmpresa());
        }

        this.bd = new ConectorBD(this.minimarket);
    }


    public void inicializarRecyclerView(){
        FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
        this.fragmentNuevos = new RecyclerViewEncargoNuevoMinimarketFragment();
        this.adapterNuevos = new AdaptadorEncargosNuevosMinimarket(this.minimarket);
        this.fragmentNuevos.setAdapter(adapterNuevos);

        transaction1.replace(R.id.nuevosEncargos, this.fragmentNuevos);
        transaction1.commit();

        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
        this.fragmentAceptados = new RecyclerViewEncargoAceptadoMinimarketFragment();
        this.adapterAceptados = new AdaptadorEncargosAceptadosMinimarket(this.minimarket);
        this.fragmentAceptados.setAdapter(this.adapterAceptados);

        this.adapterAceptados.setItemListener(new AdaptadorEncargosAceptadosMinimarket.ItemClickListener() {
            @Override
            public void modificarEstadoEncargo(int indexEncargo) {
                mostrarEstadoEncargo(indexEncargo);
            }
        });

        transaction2.replace(R.id.encargosAceptados, this.fragmentAceptados);
        transaction2.commit();


        /*this.adapter.setItemListener(new AdaptadorProductos.ItemClickListener() {
            @Override
            public void verProducto(int posicion) {
                verInformacionProducto(posicion);
            }
        });*/
    }

    public void mostrarEstadoEncargo(int index){
        System.out.println("El encargo es: "+this.minimarket.obtenerPedidoIndice(index).isAceptado());
    }

}