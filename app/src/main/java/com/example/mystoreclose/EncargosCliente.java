package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class EncargosCliente extends AppCompatActivity {

    //Botones
    ImageButton botonAtras;

    private ListView listadoEncargos;
    private TextView estadoEncargo;
    private String listadoEncargosIndexado[] = {"encargo1", "encargo2", "encargo3", "encargoN"};
    private String ditanciaMinimarketUsuarioIndexado[] = {"cancelado" , "a la espera", "terminado", "terminado"};
    private Button botonPerfil;
    private Button botonInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encargos_cliente);

        //Apretar flecha
        botonAtras = (ImageButton) findViewById(R.id.volverInicio);
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(EncargosCliente.this, InicioCliente.class);
                startActivity(volver);
            }
        });

        botonPerfil = (Button) findViewById(R.id.perfil);
        botonPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(EncargosCliente.this, PerfilCliente.class);
                startActivity(volver);
            }
        });
        botonInicio = (Button) findViewById(R.id.productos);
        botonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(EncargosCliente.this, InicioCliente.class);
                startActivity(volver);
            }
        });
        /*listadoEncargos = (ListView)findViewById(R.id.listViewListadoEncargos);
        estadoEncargo = (TextView)findViewById(R.id.textViewEstadoEncargo);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.modelo_list_view, listadoEncargosIndexado);
        listadoEncargos.setAdapter(adapter);

        listadoEncargos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                estadoEncargo.setText("El " + listadoEncargos.getItemAtPosition(i) +" esta " + ditanciaMinimarketUsuarioIndexado[i]);
            }
        });*/
    }
}