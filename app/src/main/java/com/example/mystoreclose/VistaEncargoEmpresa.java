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
    private Button botonEncargos;
    private Button botonPerfil;
    private Button botonInicio;
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
        botonEncargos = (Button) findViewById(R.id.encargos1);
        botonEncargos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(VistaEncargoEmpresa.this, EncargosEmpresa.class);
                startActivity(volver);
            }
        });
        botonPerfil = (Button) findViewById(R.id.perfil);
        botonPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(VistaEncargoEmpresa.this, PerfilEmpresa.class);
                startActivity(volver);
            }
        });
        botonInicio = (Button) findViewById(R.id.productos);
        botonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(VistaEncargoEmpresa.this, InicioEmpresa.class);
                startActivity(volver);
            }
        });
    }
}