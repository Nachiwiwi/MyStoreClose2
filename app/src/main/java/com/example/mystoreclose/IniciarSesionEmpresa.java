package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public class IniciarSesionEmpresa extends AppCompatActivity {
    //ImageButton a;
    Button botonInicioSesion;
    private static final String URL1= "http://192.168.1.102/Android/agregar_minimarket.php"; // Ip benya


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion_empresa);


        botonInicioSesion = (Button) findViewById(R.id.btnIniciarSesionEmpresa);

        botonInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentIrMainEmpresa = new Intent(IniciarSesionEmpresa.this , InicioEmpresa.class);
                startActivity(intentIrMainEmpresa);
            }
        });


    }



}