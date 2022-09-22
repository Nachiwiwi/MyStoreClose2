package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class pantalla12 extends AppCompatActivity {

    private ImageButton botonVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla12);

        botonVolver = (ImageButton) findViewById(R.id.botonVolverPantalla12);
        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(pantalla12.this, pantalla7_14_15_16.class);
                startActivity(intento);
            }
        });
    }
}