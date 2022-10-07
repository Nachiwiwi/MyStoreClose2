package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class AgregarProducto extends AppCompatActivity {

    //Botones
    ImageButton botonAtras;
    Button botonAgregar;

    EditText etNombre, etPrecio, etDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

        //Apretar flecha
        botonAtras = (ImageButton) findViewById(R.id.volverInicio);
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(AgregarProducto.this, InicioEmpresa.class);
                startActivity(volver);
            }
        });

        //Apretar botón agregar producto
        botonAgregar = (Button) findViewById(R.id.agregarProd2);
        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent agregar = new Intent(AgregarProducto.this, InicioEmpresa.class);
                startActivity(agregar);
            }
        });
    }
}