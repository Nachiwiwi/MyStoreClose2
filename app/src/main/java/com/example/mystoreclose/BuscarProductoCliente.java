package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

import modelo.EmpresaMinimarket;
import modelo.Producto;

public class BuscarProductoCliente extends AppCompatActivity {
    ArrayList<EmpresaMinimarket> listadoMinimarkets;
    //ArrayList<Producto> listadoProductos;
    //Botones
    ImageButton botonAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_producto_cliente);

        //Apretar flecha
        botonAtras = (ImageButton) findViewById(R.id.volverInicio);
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(BuscarProductoCliente.this, InicioCliente.class);
                startActivity(volver);
            }
        });
    }

}