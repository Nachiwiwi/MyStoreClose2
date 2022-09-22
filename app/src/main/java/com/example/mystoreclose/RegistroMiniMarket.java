package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class RegistroMiniMarket extends AppCompatActivity {
    ImageButton a;
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