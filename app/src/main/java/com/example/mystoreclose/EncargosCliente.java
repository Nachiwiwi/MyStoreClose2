package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.MapsInitializer;

import java.util.ArrayList;

import adapters.AdaptadorEncargosCliente;
import modelo.Cliente;
import modelo.ConectorBD;
import modelo.EmpresaMinimarket;
import modelo.Producto;
import recyclerviews.RecyclerViewEncargosClienteFragment;

public class EncargosCliente extends AppCompatActivity {

    //Botones
    ImageButton botonAtras;

    private ListView listadoEncargos;
    private TextView estadoEncargo;
    private String listadoEncargosIndexado[] = {"encargo1", "encargo2", "encargo3", "encargoN"};
    private String ditanciaMinimarketUsuarioIndexado[] = {"cancelado" , "a la espera", "terminado", "terminado"};
    private Button botonPerfil;
    private Button botonInicio;

    private EmpresaMinimarket minimarketActual;
    private Cliente clienteActual;
    private ArrayList<Producto> listadoProductos = new ArrayList<>();

    private ConectorBD conector;
    private AdaptadorEncargosCliente adaptadorEncargos;
    private RecyclerViewEncargosClienteFragment recyclerViewEncargosClienteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encargos_cliente);
        inicializar();
        inicializarRecyclerView();
        //Listado de productos encargados
        for(int i = 0; i < listadoProductos.size(); i++){
            System.out.println(this.listadoProductos.get(i).getNombre());
        }
        //Apretar flecha
        botonAtras = (ImageButton) findViewById(R.id.volverInicio);
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(EncargosCliente.this, InicioCliente.class);
                startActivity(volver);
            }
        });

        botonPerfil = (Button) findViewById(R.id.perfil);
        botonPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(EncargosCliente.this, PerfilCliente.class);
                startActivity(volver);
            }
        });
        botonInicio = (Button) findViewById(R.id.productos);
        botonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(EncargosCliente.this, InicioCliente.class);
                startActivity(volver);
            }
        });
        obtenerDatosDB();
    }

    private void obtenerDatosDB() {

    }

    private void inicializarRecyclerView() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        this.recyclerViewEncargosClienteFragment = new RecyclerViewEncargosClienteFragment();
        this.adaptadorEncargos = new AdaptadorEncargosCliente(this.listadoProductos);
        this.recyclerViewEncargosClienteFragment.setAdapter(this.adaptadorEncargos);

        transaction.replace(R.id.listadoEncargosCliente,this.recyclerViewEncargosClienteFragment);
        transaction.commit();

        //falta la funcion del adapter de ser necesaria
    }

    private void inicializar() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            this.minimarketActual = (EmpresaMinimarket) bundle.getSerializable("minimarket");
            this.listadoProductos = (ArrayList) bundle.getSerializable("listadoProductos");
            this.clienteActual = (Cliente) bundle.getSerializable("cliente");
            //System.out.println("Cliente en EncargosCliente: "+this.clienteActual.getNombre());
            this.conector = new ConectorBD(this.clienteActual);
        }
    }
}