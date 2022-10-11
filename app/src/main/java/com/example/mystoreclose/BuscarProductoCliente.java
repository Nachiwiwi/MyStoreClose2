package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import modelo.Cliente;
import modelo.EmpresaMinimarket;
import modelo.Producto;

public class BuscarProductoCliente extends AppCompatActivity implements SearchView.OnQueryTextListener {
    //ArrayList<EmpresaMinimarket> listadoMinimarkets;
    private JsonRequest request;
    private RequestQueue queue;
    ArrayList<Producto> listadoProductos = new ArrayList<>();
    SearchView barraBusqueda;
    RecyclerViewBuscarProductosClienteFragment fragment = new RecyclerViewBuscarProductosClienteFragment();
    ImageButton botonAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_producto_cliente);
        barraBusqueda = (SearchView) findViewById(R.id.searchViewBuscarProductoCliente);
        barraBusqueda.setOnQueryTextListener(this);
        queue = Volley.newRequestQueue(this);
        cargarProductosBaseDatos();
        //Apretar flecha
        botonAtras = (ImageButton) findViewById(R.id.volverInicio);
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(BuscarProductoCliente.this, InicioCliente.class);
                startActivity(volver);
            }
        });
    }

    private void cargarProductosBaseDatos() {

        String URL1= "http://192.168.0.4/Android/Producto_Lista.php?";
        StringRequest request = new StringRequest(Request.Method.GET, URL1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array = new JSONArray(response);
                    for(int i = 0 ; i<array.length(); i++){
                        JSONObject object = array.getJSONObject(i);
                        int idProducto = object.getInt("IdProducto");
                        String nombre = object.getString("Nombre");
                        //System.out.println("Nombre: "+Nombre);
                        Producto producto = new Producto(nombre, idProducto);
                        listadoProductos.add(producto);
                    }
                    System.out.println("xdddd");
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    System.out.println("a");

                    System.out.println("d");
                    fragment.setColeccion(listadoProductos);
                    System.out.println("xdddd");
                    transaction.replace(R.id.buscarProdCli, fragment);
                    System.out.println("xdddd");
                    transaction.commit();

                }catch (JSONException e){
                    Toast.makeText(BuscarProductoCliente.this, "2xd:", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BuscarProductoCliente.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

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
}