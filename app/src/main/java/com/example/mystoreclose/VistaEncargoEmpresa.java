package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class VistaEncargoEmpresa extends AppCompatActivity {

    private ImageButton botonVolverPantalla13_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_encargo_empresa);



        botonVolverPantalla13_ = (ImageButton) findViewById(R.id.botonVolverPantalla13);

        botonVolverPantalla13_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(VistaEncargoEmpresa.this, VerProducto.class);
                startActivity(intento);
            }
        });
    }
}