package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class EncargosCliente extends AppCompatActivity {
    private ListView listadoEncargos;
    private TextView estadoEncargo;
    private String listadoEncargosIndexado[] = {"encargo1", "encargo2", "encargo3", "encargoN"};
    private String ditanciaMinimarketUsuarioIndexado[] = {"cancelado" , "a la espera", "terminado", "terminado"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_encargos_cliente);
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