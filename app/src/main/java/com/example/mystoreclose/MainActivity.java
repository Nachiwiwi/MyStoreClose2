package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    // Para comunicarse con la base de datos
    Button botonEmpresa;
    Button botonLogin;
    Button botonRegistro;
    Button invitado1;
    String NombreP = "XXX";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Fin prueba Base de Datos
        //obtenerProductosBD;

        //Botón provisional
        botonEmpresa = (Button) findViewById(R.id.button);
        botonEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inicioEmp = new Intent(MainActivity.this, InicioEmpresa.class );
                startActivity(inicioEmp);
            }
        });

        //Apretar botón iniciar sesión
        botonLogin = (Button) findViewById(R.id.botonLogin1);
        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inicioSes = new Intent(MainActivity.this, IniciarSesionEmpresa.class );
                startActivity(inicioSes);
            }
        });

        //Apretar botón registrarse
        botonRegistro= (Button) findViewById(R.id.botonRegistrarse1);
        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registrarse = new Intent(MainActivity.this, RegistroMinimarket.class );
                startActivity(registrarse);
            }
        });

        //Apretar botón iniciar como invitado
        invitado1= (Button) findViewById(R.id.sesionInv);
        invitado1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inicioCli = new Intent(MainActivity.this, InicioCliente.class );
                startActivity(inicioCli);
            }
        });
    }

}