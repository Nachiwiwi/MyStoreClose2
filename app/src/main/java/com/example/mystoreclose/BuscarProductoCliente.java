package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import adapters.AdaptadorBuscarProductosCliente;
import modelo.Cliente;
import modelo.Producto;
import recyclerviews.RecyclerViewBuscarProductosClienteFragment;

public class BuscarProductoCliente extends AppCompatActivity implements SearchView.OnQueryTextListener, Response.Listener<JSONObject>, Response.ErrorListener {

    private JsonRequest request;
    private RequestQueue queue;
    ArrayList<Producto> listadoProductos ;
    SearchView barraBusqueda;
    RecyclerViewBuscarProductosClienteFragment fragment;
    AdaptadorBuscarProductosCliente adapter ;
    private Cliente clienteActual;

    ImageButton botonAtras;
    private Button botonEncargos;
    private Button botonPerfil;
    private Button botonInicio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_producto_cliente);
        inicializar();
        this.listadoProductos = new ArrayList<>();
        //Apretar flecha
        botonAtras = (ImageButton) findViewById(R.id.volverInicio);
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(BuscarProductoCliente.this, InicioCliente.class);
                startActivity(volver);
            }
        });
        botonEncargos = (Button) findViewById(R.id.encargos1);
        botonEncargos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(BuscarProductoCliente.this, EncargosCliente.class);
                startActivity(volver);
            }
        });
        botonPerfil = (Button) findViewById(R.id.perfil);
        botonPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ventanaPerfil = new Intent(BuscarProductoCliente.this, PerfilCliente.class);
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
                Intent volver = new Intent(BuscarProductoCliente.this, InicioCliente.class);
                startActivity(volver);
            }
        });


        barraBusqueda = (SearchView) findViewById(R.id.searchViewBuscarProductoCliente);
        barraBusqueda.setOnQueryTextListener(this);

        queue = Volley.newRequestQueue(this);
        inicializarRecyclerView();
        cargarProductosBaseDatos();


    }
    private void inicializar() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            this.clienteActual = (Cliente) bundle.getSerializable("cliente");
            //System.out.println(this.clienteActual.getNombre());
        }
    }
    public void inicializarRecyclerView(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        this.fragment = new RecyclerViewBuscarProductosClienteFragment();
        this.adapter = new AdaptadorBuscarProductosCliente(this.listadoProductos);
        this.fragment.setAdapter(this.adapter);
        transaction.replace(R.id.buscarProdCli, this.fragment);
        transaction.commit();

        this.adapter.setItemListener(new AdaptadorBuscarProductosCliente.ItemClickListener() {
            @Override
            public void verProducto(int posicion) {
                verInformacionProducto(posicion);
            }
        });
    }

    public void verInformacionProducto(int posicion){
        Intent ventanaBuscarProducto = new Intent(BuscarProductoCliente.this,MostrarMinimarketsConProductoSeleccionado.class );
        Bundle bundle = new Bundle();
        bundle.putString("idProducto", String.valueOf(posicion));
        Bundle bundle1 = new Bundle();
        bundle1.putSerializable("cliente",(Serializable) clienteActual);
        ventanaBuscarProducto.putExtras(bundle);
        ventanaBuscarProducto.putExtras(bundle1);
        startActivity(ventanaBuscarProducto);

    }

    private void cargarProductosBaseDatos() {

        //String URL1= "http://192.168.1.102/Android/Producto_Lista.php"; //ip benja
        String URL1= "http://192.168.0.4/Android/Producto_Lista.php"; // ip andres
        this.request = new JsonObjectRequest(Request.Method.GET, URL1, null,this,this);
        this.queue.add(request);
    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        try {
            fragment.actualizarBusqueda(s);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(BuscarProductoCliente.this, error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {

        try {
            JSONArray array = response.getJSONArray("productos");
            for(int i = 0 ; i<array.length(); i++){
                JSONObject object = array.getJSONObject(i);
                int idProducto = Integer.parseInt(object.getString("IdProducto"));
                String nombre = new String(object.getString("Nombre"));
                //System.out.println("Nombre: "+Nombre);
                Producto producto = new Producto(nombre, idProducto);
                this.listadoProductos.add(producto);

            }
            System.out.println(this.listadoProductos.size());
            System.out.println("xdddd");


            inicializarRecyclerView();
        }catch (JSONException e){
            Toast.makeText(BuscarProductoCliente.this, e.toString(), Toast.LENGTH_SHORT).show();
        }


    }
}