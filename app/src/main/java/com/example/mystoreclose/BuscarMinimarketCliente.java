package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import androidx.recyclerview.*;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import modelo.EmpresaMinimarket;

public class BuscarMinimarketCliente extends AppCompatActivity{

    //Botones
    ImageButton botonAtras;

    public static ArrayList<EmpresaMinimarket> arrayList= new ArrayList<>();
    public EmpresaMinimarket empresaMinimarket;
    RecyclerView.LayoutManager RecyclerViewListadoMinimarketsCercanos;
    RecyclerView.Adapter mAdapter;
    TextView textViewDatosMinimarket;
    private ArrayList<EmpresaMinimarket> listadoMinimarkets;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_minimarket_cliente);

        //Apretar flecha
        botonAtras = (ImageButton) findViewById(R.id.volverInicio);
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(BuscarMinimarketCliente.this, InicioCliente.class);
                startActivity(volver);
            }
        });


        queue = Volley.newRequestQueue(this);

        readerJSon();

        mostrarMinimarkets();
    }
    public void mostrarMinimarkets(){
        //Toast.makeText(BuscarMinimarketCliente.this, listadoMinimarkets.get(1).getNombreMinimarket(), Toast.LENGTH_SHORT).show();

    }
    private void readerJSon() {

        String URL1= "http://192.168.0.4/Android/metodoGET.php";

        StringRequest request = new StringRequest(Request.Method.GET, URL1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array = new JSONArray(response);
                    for(int i = 0 ; i<array.length(); i++){
                        JSONObject object = array.getJSONObject(i);
                        String idMinimarket = object.getString("IdMarket");
                        String nombreEmpresa = object.getString("Nombre_empresa");
                        String nombreMinimarket = object.getString("Nombre_local");
                        String direccion = object.getString("Direccion");
                        String rutEmpresa = object.getString("Rut_Empresa");
                        String contraseñaDueño = object.getString("ContraseñaDueño");
                        String mailLocal = object.getString("MailDueño");
                        String longitud = object.getString("Longitud");
                        String latitud = object.getString("Latitud");
                        EmpresaMinimarket nuevaEmpresa = new EmpresaMinimarket(nombreEmpresa, nombreMinimarket, rutEmpresa, direccion, mailLocal);
                        listadoMinimarkets.add(nuevaEmpresa);

                    }
                    //RecyclerViewListadoMinimarketsCercanos.setAdapter(mAdapter);
                    //mAdapter = new RecyclerAdapter(buscarMinimarketClente.this,)
                    //RecyclerViewListadoMinimarketsCercanos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_2,listadoMinimarkets ));

                }catch (JSONException e){
                    Toast.makeText(BuscarMinimarketCliente.this, "2xd:", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BuscarMinimarketCliente.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);
    }
}