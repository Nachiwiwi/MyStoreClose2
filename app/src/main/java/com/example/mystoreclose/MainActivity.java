package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button botonxdxd;
    Button botonLogin;
    Button botonRegistro;
    Button invitado1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        botonxdxd = (Button) findViewById(R.id.button);
        botonxdxd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantalla8 = new Intent(MainActivity.this, InicioEmpresa.class );
                startActivity(pantalla8);
            }
        });
        botonLogin = (Button) findViewById(R.id.botonLogin1);
        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantalla10 = new Intent(MainActivity.this, IniciarSesionEmpresa.class );
                startActivity(pantalla10);
            }
        });
        botonRegistro= (Button) findViewById(R.id.botonRegistrarse1);
        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantalla9 = new Intent(MainActivity.this, RegistroMinimarket.class );
                startActivity(pantalla9);
            }
        });
        invitado1= (Button) findViewById(R.id.button4);
        invitado1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent pantalla11 = new Intent(MainActivity.this, BuscarMinimarketCliente.class );
                startActivity(pantalla11);
            }
        });
    }




}