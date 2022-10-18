package com.example.mystoreclose;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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

        //Solicitar permisos de ubicacion
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if(permissionCheck == PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
            }else{
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
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
                Intent inicioSes = new Intent(MainActivity.this, IniciarSesionClienteVentana.class );
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
                //Intent inicioCli = new Intent(MainActivity.this, MostrarMinimarketsConProductoSeleccionado.class );
                startActivity(inicioCli);
            }
        });
    }

}