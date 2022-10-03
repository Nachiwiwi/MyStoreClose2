package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class AgregarProducto extends AppCompatActivity {
    EditText etNombre, etPrecio, etDescripcion;

    ImageButton botonVolverPantalla8_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

        botonVolverPantalla8_ = (ImageButton) findViewById(R.id.botonVolverPantalla8);

        botonVolverPantalla8_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(AgregarProducto.this, VerProducto.class);
                startActivity(intento);
            }
        });
    }
}