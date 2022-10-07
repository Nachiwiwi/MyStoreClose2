package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class VistaEncargoEmpresa extends AppCompatActivity {

    //Botones
    ImageButton botonAtras;
    Button botonAceptar;
    Button botonRechazar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_encargo_empresa);

        //Apretar flecha
        botonAtras = (ImageButton) findViewById(R.id.volverInicio);
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(VistaEncargoEmpresa.this, EncargosEmpresa.class);
                startActivity(volver);
            }
        });

        //Apretar botón aceptar
        botonAceptar = (Button) findViewById(R.id.botonAceptarSolicitud);
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aceptar = new Intent(VistaEncargoEmpresa.this, EncargosEmpresa.class);
                startActivity(aceptar);
            }
        });

        //Apretar botón rechazar
        botonRechazar = (Button) findViewById(R.id.botonRechazarSolicitud);
        botonRechazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rechazar = new Intent(VistaEncargoEmpresa.this, EncargosEmpresa.class);
                startActivity(rechazar);
            }
        });
    }
}