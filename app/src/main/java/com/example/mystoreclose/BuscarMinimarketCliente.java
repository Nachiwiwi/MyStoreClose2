package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import modelo.EmpresaMinimarket;

public class BuscarMinimarketCliente extends AppCompatActivity{

    public static ArrayList<EmpresaMinimarket> arrayList= new ArrayList<>();
    public EmpresaMinimarket empresaMinimarket;
    ListView listViewListadoMinimarketsCercanos;
    TextView textViewDatosMinimarket;

    private RequestQueue queue;
    //los siguientes arreglos deben ser añadidos desde la base de datos en un futuro solo son ejemplos

    private String listadoMInimarketsIndexado[] ;
    private String ditanciaMinimarketUsuarioIndexado[] = {"distancia1" , "distancia2", "distancia3", "distanciaN"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_minimarket_cliente);

        queue = Volley.newRequestQueue(this);

        readerJSon();

        Toast.makeText(buscar_minimarket_cliente.this, "fin:", Toast.LENGTH_SHORT).show();

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
                        String nombre = object.getString("Nombre");
                        String latitud = object.getString("Latitud");
                        Toast.makeText(buscar_minimarket_cliente.this, nombre, Toast.LENGTH_SHORT).show();
                        Toast.makeText(buscar_minimarket_cliente.this, latitud, Toast.LENGTH_SHORT).show();
                    }
                }catch (JSONException e){
                    Toast.makeText(buscar_minimarket_cliente.this, "2xd:", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(buscar_minimarket_cliente.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);
    }
}