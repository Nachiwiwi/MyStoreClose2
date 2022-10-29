package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class IniciarSesionClienteVentana extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion_cliente_ventana);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.escenariocli,new IniciarSesionCliente()).commit();
    }
}