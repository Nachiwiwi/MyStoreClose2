package com.example.mystoreclose;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;

public class InicioCliente extends AppCompatActivity {

    //Botones
    Button botonBuscarMinimarkets;
    Button botonBuscarProductos;

    //Barra navegación
    BottomNavigationView botonNav;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_cliente);

        //Apretar botón buscar minimarket
        botonBuscarMinimarkets = (Button) findViewById(R.id.buscarMini);
        botonBuscarMinimarkets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent buscMini = new Intent(InicioCliente.this, BuscarMinimarketCliente.class );
                startActivity(buscMini);
            }
        });

        //Apretar botón buscar producto
        botonBuscarProductos = (Button) findViewById(R.id.buscarProducto);
        botonBuscarProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent buscProd = new Intent(InicioCliente.this, BuscarProductoCliente.class );
                startActivity(buscProd);
            }
        });

        //Funcionamiento barra navegación
        BottomNavigationView barraNav = findViewById(R.id.bottomNavigationView);
        //barraNav.setOnNavigationItemSelectedListener(navListener);
    }

   /* private final BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        // By using switch we can easily get
        // the selected fragment
        // by using there id.
        Fragment selectedFragment = null;
        int itemId = item.getItemId();
        if (itemId == R.id.algorithm) {
            selectedFragment = new AlgorithmFragment();
        } else if (itemId == R.id.course) {
            selectedFragment = new CourseFragment();
        } else if (itemId == R.id.profile) {
            selectedFragment = new ProfileFragment();
        }
        // It will help to replace the
        // one fragment to other.
        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        }
        return true;
    };*/
}