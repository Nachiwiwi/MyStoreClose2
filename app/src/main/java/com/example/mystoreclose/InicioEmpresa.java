package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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

import java.util.ArrayList;

public class InicioEmpresa extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    //Botones
    Button botonAgregar;
    Button botonBuscar;

    private RecyclerView listaDeObjetos;
    private  String NombreP = "XXX";
    RequestQueue rQ;
    JsonRequest jsR;
    ArrayList<String> array = new ArrayList<String>();
    private String direccionProductos = "http://192.168.178.246/Android/getProductosMinimarket.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_empresa);

        //Apretar botón agregar producto
        botonAgregar = (Button) findViewById(R.id.agregarProductosButton);
        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent agregar = new Intent(InicioEmpresa.this, AgregarProducto.class);
                startActivity(agregar);
            }
        });

        //Apretar botón buscar producto
        botonBuscar = (Button) findViewById(R.id.buscarProducto);
        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent buscar = new Intent(InicioEmpresa.this, BuscarProductoEmpresa.class);
                startActivity(buscar);
            }
        });

        array.add("Salsa de Tomate");
        array.add("Carozzi");
        array.add("Fideos");
        array.add("Papas Fritas");
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            RecyclerViewProductosFragment fragment = new RecyclerViewProductosFragment();
            transaction.replace(R.id.fragmentContentProductosEmpresa, fragment);
            transaction.commit();
        }

        rQ = Volley.newRequestQueue(this);

        //listaDeObjetos.

        inicializar();

        //obtenerProductosBD();

    }

    private void inicializar(){
        //listaDeObjetos = (ListView) findViewById(R.id.listaDetallesProducto);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(InicioEmpresa.this, error.toString(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {

        try {
            JSONArray productosJSON = response.getJSONArray("Productos");
            for(int i = 0; i < productosJSON.length(); i++){
                JSONObject pupi = productosJSON.getJSONObject(i);
                array.add(new String(pupi.getString("Nombre")));
                System.out.println(pupi.getString("Nombre"));
            }

            //listaDeObjetos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,array ));

        }catch (JSONException e){
            Toast.makeText(InicioEmpresa.this, "Error:", Toast.LENGTH_SHORT).show();
        }
    }

    private void obtenerProductosBD(){
        String dir = "http://192.168.1.102/Android/getPM.php";
        jsR = new JsonObjectRequest(Request.Method.GET, dir, null, this,this);
        rQ.add(jsR);
    }


}