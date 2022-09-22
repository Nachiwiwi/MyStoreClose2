package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class pantalla9_10 extends AppCompatActivity {
    private ListView listaDeObjetos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla9_10);
        ArrayList<String> array = new ArrayList<String>();
        array.add("Salsa de Tomate");
        array.add("Carozzi");
        array.add("Precio");


        listaDeObjetos = (ListView) findViewById(R.id.listaDetallesProducto);
        listaDeObjetos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,array ));


    }
}