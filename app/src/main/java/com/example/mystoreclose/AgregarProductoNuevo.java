package com.example.mystoreclose;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import modelo.ConectorBD;

public class AgregarProductoNuevo extends AppCompatActivity implements View.OnClickListener{
    // componentes pantalla
    private EditText textoNombre;
    private Button botonAgregarProducto;
    private Button botonEncargos;
    private Button botonPerfil;
    private Button botonInicio;
    // base de datos
    private ConectorBD bd;
    // popup
    private AlertDialog.Builder dialogBuilder1;
    private AlertDialog dialog1;
    private Button botonAceptar;
    private Button botonCancelar;
    private TextView mensajePopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto_nuevo);

        Inicializar();
    }

    public void Inicializar(){
        this.textoNombre =  findViewById(R.id.editTextProductoCatalogo);
        this.botonAgregarProducto =  findViewById(R.id.botonProductoCatalogo);
        this.botonEncargos = findViewById(R.id.encargos1);
        this.botonPerfil = findViewById(R.id.perfil);
        this.botonInicio = findViewById(R.id.productos);
        this.bd = new ConectorBD();

        this.botonAgregarProducto.setOnClickListener(this);
        this.botonEncargos.setOnClickListener(this);
        this.botonPerfil.setOnClickListener(this);
        this.botonInicio.setOnClickListener(this);
    }

    public void popupConfirmarOpcion(String nombre){
        dialogBuilder1 = new AlertDialog.Builder(this);
        final View contactPopUpView = getLayoutInflater().inflate(R.layout.popup_certeza,null);

        this.botonAceptar = contactPopUpView.findViewById(R.id.botonSiPopup);
        this.botonCancelar = contactPopUpView.findViewById(R.id.botonNoPopup);
        this.mensajePopup = contactPopUpView.findViewById(R.id.mensajeConfirmacionPupup);

        this.mensajePopup.setText("¿Estás seguro que quieres agregar este producto al catálogo?");

        this.botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarProductoBD(nombre);
                dialog1.dismiss();
            }
        });

        this.botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });

        dialogBuilder1.setView(contactPopUpView);
        this.dialog1 = dialogBuilder1.create();
        this.dialog1.show();
    }

    public void agregarProductoBD(String nombre){
        this.bd.agregarProducto(nombre,this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.botonProductoCatalogo):
                popupConfirmarOpcion(textoNombre.getText().toString());
                break;
            case(R.id.encargos1):
                Intent volver = new Intent(AgregarProductoNuevo.this, EncargosEmpresa.class);
                startActivity(volver);
                break;
            case(R.id.perfil):
                Intent volver2 = new Intent(AgregarProductoNuevo.this, PerfilEmpresa.class);
                startActivity(volver2);
                break;
            case(R.id.productos):
                Intent volver3 = new Intent(AgregarProductoNuevo.this, InicioEmpresa.class);
                startActivity(volver3);

                break;
        }
    }
}