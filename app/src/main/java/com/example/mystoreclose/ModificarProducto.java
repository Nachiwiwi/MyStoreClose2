package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ModificarProducto extends AppCompatActivity {
    ImageButton botonVolverPantalla11_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_producto);

        botonVolverPantalla11_ = (ImageButton) findViewById(R.id.botonVolverPantalla11);

        botonVolverPantalla11_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(ModificarProducto.this, VerProducto.class);
                startActivity(intento);
            }
        });
    }
}