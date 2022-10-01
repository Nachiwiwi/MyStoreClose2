package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class RegistroMiniMarket extends AppCompatActivity {
    ImageButton a;
    EditText MKRut, MKNombreEmp, MKDireccion, MKNombreLoc, MKCorreo, MKcontrasenia, MKCContraseña;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_mini_market);
        a = (ImageButton) findViewById(R.id.imageButton);
        a.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent pantallaa = new Intent(RegistroMiniMarket.this, MainActivity.class );
                startActivity(pantallaa);
            }
        });
    }
}