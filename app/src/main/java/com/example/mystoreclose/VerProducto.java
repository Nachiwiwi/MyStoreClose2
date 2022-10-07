package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class VerProducto extends AppCompatActivity {

    //Botones
    ImageButton botonAtras;
    Button botonModificar;
    Button botonEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_producto);

        //Apretar flecha
        botonAtras = (ImageButton) findViewById(R.id.volverInicio);
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(VerProducto.this, InicioEmpresa.class);
                startActivity(volver);
            }
        });

        //Apretar botón modificar producto
        botonModificar = (Button) findViewById(R.id.modificarProd1);
        botonModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent modificar = new Intent(VerProducto.this, ModificarProducto.class);
                startActivity(modificar);
            }
        });

        //Apretar botón eliminar producto
        botonEliminar = (Button) findViewById(R.id.eliminarProd1);
        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(VerProducto.this, InicioEmpresa.class);
                startActivity(intento);
            }
        });
    }
}