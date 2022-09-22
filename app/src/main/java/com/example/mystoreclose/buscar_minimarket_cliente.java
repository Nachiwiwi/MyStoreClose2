package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class buscar_minimarket_cliente extends AppCompatActivity {
    private TextView nombreMinimarket;
    private ListView listaMinimarkets;

    //los siguientes arreglos deben ser añadidos desde la base de datos en un futuro solo son ejemplos

    private String listadoMInimarketsIndexado[] = {"minimarket1", "minimarket2", "minimarket3", "minimarketN"};
    private String ditanciaMinimarketUsuarioIndexado[] = {"distancia1" , "distancia2", "distancia3", "distanciaN"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_minimarket_cliente);
        listaMinimarkets = (ListView)findViewById(R.id.listViewListadoMinimarketsCercanos);
        nombreMinimarket = (TextView)findViewById(R.id.textViewDatosMinimarket);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.modelo_list_view, listadoMInimarketsIndexado);
        listaMinimarkets.setAdapter(adapter);

        listaMinimarkets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                nombreMinimarket.setText("La distancia entre usted y el " + listaMinimarkets.getItemAtPosition(i) +" es de " + ditanciaMinimarketUsuarioIndexado[i]);
            }
        });
    }
}