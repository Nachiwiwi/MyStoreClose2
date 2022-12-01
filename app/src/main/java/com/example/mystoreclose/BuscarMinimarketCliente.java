package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import adapters.AdaptadorMinimarkets;
import modelo.Cliente;
import modelo.ConectorBD;
import modelo.Direccion;
import modelo.EmpresaMinimarket;
import recyclerviews.RecyclerViewMinimarketFragment;

public class BuscarMinimarketCliente extends AppCompatActivity implements SearchView.OnQueryTextListener{

    //Botones
    ImageButton botonAtras;

    public static ArrayList<EmpresaMinimarket> arrayList= new ArrayList<>();
    public EmpresaMinimarket empresaMinimarket;
    RecyclerView.LayoutManager RecyclerViewListadoMinimarketsCercanos;
    RecyclerViewMinimarketFragment fragment;
    AdaptadorMinimarkets mAdapter;
    TextView textViewDatosMinimarket;
    private ArrayList<EmpresaMinimarket> listadoMinimarkets = new ArrayList<>();
    private RequestQueue queue;
    private Cliente clienteActual;
    private JsonRequest request;
    private Button botonEncargos;
    private Button botonPerfil;
    private Button botonInicio;
    private SearchView barraBusqueda;
    ConectorBD conector = new ConectorBD();
   // private EmpresaMinimarket arrayMinimarkets[] = new EmpresaMinimarket[listadoMinimarkets.size()];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_minimarket_cliente);
        inicializar();//inicializa la variable cliente
        inicializarRecyclerView();
        //readerJSon();

        //Apretar flecha
        botonAtras = (ImageButton) findViewById(R.id.volverInicio);
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(BuscarMinimarketCliente.this, InicioCliente.class);
                startActivity(volver);
            }
        });
        botonEncargos = (Button) findViewById(R.id.encargos1);
        botonEncargos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(BuscarMinimarketCliente.this, EncargosCliente.class);
                startActivity(volver);
            }
        });
        botonPerfil = (Button) findViewById(R.id.perfil);
        botonPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ventanaPerfil = new Intent(BuscarMinimarketCliente.this, PerfilCliente.class);
                Bundle perfilCliente = new Bundle();
                perfilCliente.putSerializable("cliente", (Serializable) clienteActual);
                ventanaPerfil.putExtras(perfilCliente);
                startActivity(ventanaPerfil);
            }
        });
        botonInicio = (Button) findViewById(R.id.productos);
        botonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(BuscarMinimarketCliente.this, InicioCliente.class);
                startActivity(volver);
            }
        });

        barraBusqueda = (SearchView) findViewById(R.id.searchViewBuscarProductoCliente);
        barraBusqueda.setOnQueryTextListener(this);


    }

    private void inicializar() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            this.clienteActual = (Cliente) bundle.getSerializable("cliente");
            //System.out.println(this.clienteActual.getNombre());
        }
    }

    public void verInformacionMinimarket(int posicion){
       // System.out.println("la posicion de la lista es: "+posicion);

        Intent ventanaVerMinimarket = new Intent(BuscarMinimarketCliente.this, VistaMinimarketCliente.class );
        Bundle bundle = new Bundle();
        Bundle bundle1 = new Bundle();
        bundle.putSerializable("minimarket", listadoMinimarkets.get(posicion));
        bundle1.putSerializable("cliente", this.clienteActual);
        ventanaVerMinimarket.putExtras(bundle);
        ventanaVerMinimarket.putExtras(bundle1);
        startActivity(ventanaVerMinimarket);

    }

    public void inicializarRecyclerView(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        this.fragment = new RecyclerViewMinimarketFragment();
        this.mAdapter = new AdaptadorMinimarkets(this.listadoMinimarkets);
        this.fragment.setAdapter(this.mAdapter);
        transaction.replace(R.id.buscarMiniCli, this.fragment);
        transaction.commit();

        this.mAdapter.setItemListener(new AdaptadorMinimarkets.ItemClickListener() {
            @Override
            public void verProducto(int posicion) {
                verInformacionMinimarket(posicion);
            }
        });
        this.conector.obtenerMinimarkets(this, mAdapter, this.listadoMinimarkets,this.mAdapter,this.clienteActual);
        //this.listadoMinimarkets = ordenarListadoPorDistancia(this.clienteActual, this.listadoMinimarkets);
    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        fragment.actualizarBusqueda(s);
        return false;
    }
}
