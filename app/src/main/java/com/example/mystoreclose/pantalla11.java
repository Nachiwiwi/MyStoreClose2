package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class pantalla11 extends AppCompatActivity {
    ImageButton botonVolverPantalla11_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla11);

        botonVolverPantalla11_ = (ImageButton) findViewById(R.id.botonVolverPantalla11);

        botonVolverPantalla11_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(pantalla11.this, pantalla7_14_15_16.class);
                startActivity(intento);
            }
        });
    }
}