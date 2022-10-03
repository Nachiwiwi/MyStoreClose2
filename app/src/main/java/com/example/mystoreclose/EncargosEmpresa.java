package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class EncargosEmpresa extends AppCompatActivity {

    private ImageButton botonVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encargos_empresa);

        botonVolver = (ImageButton) findViewById(R.id.botonVolverPantalla12);
        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(EncargosEmpresa.this, VerProducto.class);
                startActivity(intento);
            }
        });
    }
}