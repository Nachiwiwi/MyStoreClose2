package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class pantalla13 extends AppCompatActivity {

    private ImageButton botonVolverPantalla13_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla13);



        botonVolverPantalla13_ = (ImageButton) findViewById(R.id.botonVolverPantalla13);

        botonVolverPantalla13_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(pantalla13.this, pantalla7_14_15_16.class);
                startActivity(intento);
            }
        });
    }
}