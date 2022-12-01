package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import modelo.EmpresaMinimarket;

public class PerfilEmpresa extends AppCompatActivity {

    //Botones
    ImageButton botonAtras;
    private Button botonEncargos;
    private Button botonInicio;

    private EmpresaMinimarket minimarketActual;

    private TextView nombreEmpresa;
    private TextView nombreMinimarket;
    private TextView direccionMinimarket;
    private TextView rutEmpresa;
    private TextView numeroProductosMinimarket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_empresa);
        incializar();
        //Apretar flecha
        botonAtras = (ImageButton) findViewById(R.id.volverInicio);
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(PerfilEmpresa.this, InicioEmpresa.class);
                startActivity(volver);
            }
        });
        botonEncargos = (Button) findViewById(R.id.encargos1);
        botonEncargos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(PerfilEmpresa.this, EncargosEmpresa.class);
                startActivity(volver);
            }
        });

        botonInicio = (Button) findViewById(R.id.productos);
        botonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(PerfilEmpresa.this, InicioEmpresa.class);
                startActivity(volver);
            }
        });
    }

    private void incializar() {
        this.nombreEmpresa = findViewById(R.id.textViewNombreEmpresa);
        this.nombreMinimarket = findViewById(R.id.textViewNombreMinimarket);
        this.direccionMinimarket = findViewById(R.id.textViewDireccionMinimarket);
        this.rutEmpresa = findViewById(R.id.textViewRutEmpresa);
        this.numeroProductosMinimarket = findViewById(R.id.textViewNumeroProductosMinimarket);

        Bundle bundle = getIntent().getExtras();
        this.minimarketActual = (EmpresaMinimarket) bundle.getSerializable("minimarket");

        this.nombreEmpresa.setText(this.minimarketActual.getNombreEmpresa());
        this.nombreMinimarket.setText(this.minimarketActual.getNombreMinimarket());
        this.direccionMinimarket.setText(this.minimarketActual.getDireccion());
        this.rutEmpresa.setText(this.minimarketActual.getRutEmpresa());
        this.numeroProductosMinimarket.setText(String.valueOf(this.minimarketActual.obtenerCantidadDeProductos()));
    }
}