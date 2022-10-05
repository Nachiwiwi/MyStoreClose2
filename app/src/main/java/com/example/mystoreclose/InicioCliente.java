package com.example.mystoreclose;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;



public class InicioCliente extends AppCompatActivity {
    Button botonBuscarMinimarkets;
    Button botonBuscarProductos;
    BottomNavigationView inicio1;
    BottomNavigationView pedidos1;
    BottomNavigationView perfil1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_cliente);

        botonBuscarMinimarkets = (Button) findViewById(R.id.buscarProductosButton);
        botonBuscarMinimarkets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantalla7 = new Intent(InicioCliente.this, BuscarMinimarketCliente.class );
                startActivity(pantalla7);
            }
        });

        botonBuscarProductos = (Button) findViewById(R.id.agregarProductosButton);
        botonBuscarProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantalla7 = new Intent(InicioCliente.this, BuscarProductoCliente.class );
                startActivity(pantalla7);
            }
        });

    }
}