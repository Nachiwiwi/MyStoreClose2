package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import modelo.EmpresaMinimarket;

public class buscar_minimarket_cliente extends AppCompatActivity{

    public static ArrayList<EmpresaMinimarket> arrayList= new ArrayList<>();
    public EmpresaMinimarket empresaMinimarket;

    private TextView nombreMinimarket;
    private ListView listaMinimarkets;

    //los siguientes arreglos deben ser añadidos desde la base de datos en un futuro solo son ejemplos

    private String listadoMInimarketsIndexado[] = {"minimarket1", "minimarket2", "minimarket3", "minimarketN"};
    private String ditanciaMinimarketUsuarioIndexado[] = {"distancia1" , "distancia2", "distancia3", "distanciaN"};

    private static final String URL1= "http://192.168.0.3/Android/metodoGET.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_minimarket_cliente);
        readerJSon();
        /*listaMinimarkets = (ListView)findViewById(R.id.listViewListadoMinimarketsCercanos);
        nombreMinimarket = (TextView)findViewById(R.id.textViewDatosMinimarket);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.modelo_list_view, listadoMInimarketsIndexado);
        listaMinimarkets.setAdapter(adapter);

        listaMinimarkets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                nombreMinimarket.setText("La distancia entre usted y el " + listaMinimarkets.getItemAtPosition(i) +" es de " + ditanciaMinimarketUsuarioIndexado[i]);
            }
        });*/
    }

    private void readerJSon() {
        JsonObjectRequest JsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL1,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        arrayList.clear();
                        String nombreEmpresa,nombreMinimarket,rut,direccion,correo;
                        try {

                            System.out.println(response);

                            nombreEmpresa=response.getString("Nombre_empresa");
                            nombreMinimarket=response.getString("Nombre_local");
                            rut=response.getString("Rut_empresa");
                            direccion=response.getString("Direccion");
                            correo=response.getString("MailDueño");

                            empresaMinimarket = new EmpresaMinimarket(nombreEmpresa,nombreMinimarket,rut,direccion,correo);
                            arrayList.add(empresaMinimarket);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        System.out.println(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }

        );

    }
}