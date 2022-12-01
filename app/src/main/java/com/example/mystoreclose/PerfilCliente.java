package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import modelo.Cliente;
import modelo.ConectorBD;
import modelo.EmpresaMinimarket;

public class PerfilCliente extends AppCompatActivity {

    //Botones
    ImageButton botonAtras;
    private Button botonEncargos;
    private Button botonInicio;

    private TextView nombreCliente;
    private TextView correoCliente;
    //private TextView claveCliente;
    private Cliente clienteActual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_cliente);
        inicializar();
        //Apretar flecha
        botonAtras = (ImageButton) findViewById(R.id.volverInicio);
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(PerfilCliente.this, InicioCliente.class);
                startActivity(volver);
            }
        });
        botonEncargos = (Button) findViewById(R.id.encargos1);
        botonEncargos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(PerfilCliente.this, EncargosCliente.class);
                startActivity(volver);
            }
        });

        botonInicio = (Button) findViewById(R.id.productos);
        botonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(PerfilCliente.this, InicioCliente.class);
                startActivity(volver);
            }
        });
    }

    private void inicializar() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            this.clienteActual = (Cliente) bundle.getSerializable("cliente");
            System.out.println(clienteActual.getNombre());
            this.nombreCliente = (TextView) findViewById(R.id.textViewNombreCliente);
            this.nombreCliente.setText(clienteActual.getNombre());
            this.correoCliente = (TextView) findViewById(R.id.textViewCorreoCliente);
            this.correoCliente.setText(clienteActual.getCorreo());
        }

    }
}