package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class VistaMinimarketCliente extends AppCompatActivity {

    //Botones
    ImageButton botonAtras;
    Button botonConfirmar;

    private TextView nombreMinimarket;
    private ListView listaProductos;

    //los siguientes arreglos deben ser añadidos desde la base de datos en un futuro solo son ejemplos

    private String listaProductosIndexado[] = {"producto1", "producto2", "producto3", "productoN"};
    private String precioProductosIndexado[] = {"precio1" , "precio2", "precio3", "precioN"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_minimarket_cliente);

        //Apretar flecha
        botonAtras = (ImageButton) findViewById(R.id.volverInicio);
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(VistaMinimarketCliente.this, InicioCliente.class);
                startActivity(volver);
            }
        });

        //Apretar botón confirmar encargo
        botonConfirmar = (Button) findViewById(R.id.encargar);
        botonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent confirmar = new Intent(VistaMinimarketCliente.this, InicioCliente.class);
                startActivity(confirmar);
            }
        });


        /*listaProductos = (ListView)findViewById(R.id.lv1);
        nombreMinimarket = (TextView)findViewById(R.id.textViewMinimarketSeleccionado);
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, R.layout.modelo_list_view, listaProductosIndexado);
        listaProductos.setAdapter(adapter);

        listaProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                nombreMinimarket.setText("El precio del producto " + listaProductos.getItemAtPosition(i) +" es " + precioProductosIndexado[i]);
            }
        });*/
    }
}