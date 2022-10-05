package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    // Para comunicarse con la base de datos


    Button botonxdxd;
    Button botonLogin;
    Button botonRegistro;
    Button invitado1;
    String NombreP = "XXX";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        // Fin prueba Base de Datos
        //obtenerProductosBD;
        botonxdxd = (Button) findViewById(R.id.button);
        botonxdxd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantalla8 = new Intent(MainActivity.this, InicioEmpresa.class );
                startActivity(pantalla8);
            }
        });
        botonLogin = (Button) findViewById(R.id.botonLogin1);
        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantalla10 = new Intent(MainActivity.this, IniciarSesionEmpresa.class );
                startActivity(pantalla10);
            }
        });
        botonRegistro= (Button) findViewById(R.id.botonRegistrarse1);
        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantalla9 = new Intent(MainActivity.this, RegistroMinimarket.class );
                startActivity(pantalla9);
            }
        });
        invitado1= (Button) findViewById(R.id.button4);
        invitado1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent pantalla11 = new Intent(MainActivity.this, BuscarMinimarketCliente.class );
                startActivity(pantalla11);
            }
        });
    }

}