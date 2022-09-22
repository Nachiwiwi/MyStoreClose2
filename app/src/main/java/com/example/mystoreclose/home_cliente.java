package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home_cliente extends AppCompatActivity {
    Button botonBuscarMinimarkets;
    Button botonBuscarProductos;
    Button botonMisEncargos;
    Button botonMInimarket;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cliente);

        botonBuscarMinimarkets = (Button) findViewById(R.id.buscarMinimarketsButton);
        botonBuscarMinimarkets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantalla7 = new Intent(home_cliente.this, buscar_minimarket_cliente.class );
                startActivity(pantalla7);
            }
        });

        botonBuscarProductos = (Button) findViewById(R.id.buscarProductosButton);
        botonBuscarProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantalla7 = new Intent(home_cliente.this, buscar_producto_cliente.class );
                startActivity(pantalla7);
            }
        });
        botonMisEncargos = (Button) findViewById(R.id.misEncargosButton);
        botonMisEncargos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantalla7 = new Intent(home_cliente.this, lista_encargos_cliente.class );
                startActivity(pantalla7);
            }
        });
        botonMInimarket = (Button) findViewById(R.id.minimarket);
        botonMInimarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantalla7 = new Intent(home_cliente.this, minimarket_seleccionado_cliente.class );
                startActivity(pantalla7);
            }
        });
    }
}