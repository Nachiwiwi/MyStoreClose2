package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ModificarProducto extends AppCompatActivity {

    //Botones
    ImageButton botonAtras;
    Button botonConfirmar;
    private Button botonEncargos;
    private Button botonPerfil;
    private Button botonInicio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_producto);

        //Apretar flecha
        botonAtras= (ImageButton) findViewById(R.id.volverInicio);
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(ModificarProducto.this, VerProducto.class);
                startActivity(intento);
            }
        });

        //Apretar botón confirmar encargo
        botonConfirmar = (Button) findViewById(R.id.modificarProd);
        botonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent confirm = new Intent(ModificarProducto.this, VerProducto.class);
                startActivity(confirm);
            }
        });
        botonEncargos = (Button) findViewById(R.id.encargos1);
        botonEncargos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(ModificarProducto.this, EncargosEmpresa.class);
                startActivity(volver);
            }
        });
        botonPerfil = (Button) findViewById(R.id.perfil);
        botonPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(ModificarProducto.this, PerfilEmpresa.class);
                startActivity(volver);
            }
        });
        botonInicio = (Button) findViewById(R.id.productos);
        botonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(ModificarProducto.this, InicioEmpresa.class);
                startActivity(volver);
            }
        });
    }
}