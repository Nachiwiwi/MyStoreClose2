package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class pantalla7_14_15_16 extends AppCompatActivity {

    private Button botonAgregarProductos;
    private Button botonBuscarProducto;
    private Button botonEncargosSolicitados;
    private ImageButton botonVolverPantalla7_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla7_14_15_16);

        // Para acceder a la pantalla donde se agregan los productos
        botonAgregarProductos = (Button) findViewById(R.id.botonAgregarProducto7);
        botonAgregarProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(pantalla7_14_15_16.this, pantalla8.class);
                startActivity(intento);
            }
        });

        // Para volver a la pantalla anterior
        botonVolverPantalla7_ = (ImageButton) findViewById(R.id.botonVolverPantalla7);
        botonVolverPantalla7_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(pantalla7_14_15_16.this, MainActivity.class);
                startActivity(intento);
            }
        });


        // Para acceder a la pantalla donde se agregan los productos
        botonEncargosSolicitados = (Button) findViewById(R.id.botonEncargosSol);
        botonEncargosSolicitados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(pantalla7_14_15_16.this, pantalla12.class);
                startActivity(intento);
            }
        });

    }

}