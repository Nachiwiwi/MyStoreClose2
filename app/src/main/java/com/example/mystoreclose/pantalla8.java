package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class pantalla8 extends AppCompatActivity {
    ImageButton botonVolverPantalla8_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla8);

        botonVolverPantalla8_ = (ImageButton) findViewById(R.id.botonVolverPantalla8);

        botonVolverPantalla8_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(pantalla8.this, pantalla7_14_15_16.class);
                startActivity(intento);
            }
        });
    }
}