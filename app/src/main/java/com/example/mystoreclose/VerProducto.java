package com.example.mystoreclose;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modelo.ConectorBD;
import modelo.EmpresaMinimarket;
import modelo.Producto;

public class VerProducto extends AppCompatActivity implements View.OnClickListener{
    private EmpresaMinimarket minimarket;
    private Producto producto;
    //Botones
    private ImageButton botonAtras;
    private Button botonModificar;
    private Button botonEliminar;
    // Edit Text
    private EditText precio;
    private EditText precioOferta;
    private EditText duracionOferta;
    private EditText descripcion;
    // label
    private TextView nombreProducto;
    // fechas
    private Button botonEncargos;
    private Button botonPerfil;
    private Button botonInicio;
    // Base de datos
    private ConectorBD conectorBD;
    // PopUp
    private AlertDialog.Builder dialogBuilder1;
    private AlertDialog dialog1;
    private Button botonAceptar;
    private Button botonCancelar;
    private TextView mensajePopup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_producto);
        botonEncargos = (Button) findViewById(R.id.encargos1);
        botonEncargos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(VerProducto.this, EncargosEmpresa.class);
                startActivity(volver);
            }
        });
        botonPerfil = (Button) findViewById(R.id.perfil);
        botonPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(VerProducto.this, PerfilEmpresa.class);
                startActivity(volver);
            }
        });
        botonInicio = (Button) findViewById(R.id.productos);
        botonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(VerProducto.this, InicioEmpresa.class);
                startActivity(volver);
            }
        });
        inicializar();

    }

    public void inicializar(){

        // Botones
        botonEliminar = (Button) findViewById(R.id.eliminarProd1);
        botonAtras = (ImageButton) findViewById(R.id.volverInicio);
        botonModificar = (Button) findViewById(R.id.modificarProd1);
        botonEliminar.setOnClickListener(this);
        botonAtras.setOnClickListener(this);
        botonModificar.setOnClickListener(this);

        // Edit text
        precio = findViewById(R.id.editTextPrecioProductoVer);
        precioOferta = findViewById(R.id.editTextPrecioOfertaProductoVer);
        duracionOferta = findViewById(R.id.editTextDurOfertaProductoVer);
        descripcion = findViewById(R.id.editTextDescripcionProductoVer);
        // label
        nombreProducto = findViewById(R.id.labelNombreProducto);

        Bundle bundle = getIntent().getExtras();
        this.minimarket = (EmpresaMinimarket) bundle.getSerializable("minimarket");
        this.producto = this.minimarket.obtenerProductoIndice(bundle.getInt("indice"));
        System.out.println(this.producto.getNombre()+ " "+bundle.getInt("indice") );

        precio.setText(this.producto.getPrecio());
        precioOferta.setText(this.producto.precioOferta());
        duracionOferta.setText(this.producto.tiempoRestanteOferta());
        descripcion.setText(this.producto.getDescripcion());

        this.nombreProducto.setText(this.producto.getNombre());

        this.conectorBD = new ConectorBD(this.minimarket);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.eliminarProd1):
                popupConfirmarOpcionEliminarProducto();
                break;
            case(R.id.volverInicio):
                break;
            case(R.id.modificarProd1):
                System.out.println("Modificar Producto");

                if(revisarCeldas()){
                    popupConfirmarOpcionModificarProducto();
                }
                break;
        }

    }

    public boolean revisarCeldas(){
        Pattern patPrecios = Pattern.compile(".*[a-zA-Z].*");
        Matcher mat = patPrecios.matcher(this.precio.getText().toString());

        if(mat.matches()){
            Toast.makeText(VerProducto.this, "Ingresaste letras en el precio",Toast.LENGTH_LONG ).show();
            return false;
        }
        mat = patPrecios.matcher(this.precioOferta.getText().toString());

        if(mat.matches()){
            Toast.makeText(VerProducto.this, "Ingresaste letras en el precio",Toast.LENGTH_LONG ).show();
            return false;
        }

        mat = patPrecios.matcher(this.duracionOferta.getText().toString());

        if(mat.matches()){
            Toast.makeText(VerProducto.this, "La duracion no es válida",Toast.LENGTH_LONG ).show();
            return false;
        }

        if(this.descripcion.getText().toString().equals("")){
            Toast.makeText(VerProducto.this, "Tu producto no tiene descripcion",Toast.LENGTH_LONG ).show();
            return false;
        }


        if(this.precioOferta.getText().toString().equals("") && !this.duracionOferta.getText().toString().equals("")){
            Toast.makeText(VerProducto.this, "La oferta de tu producto tiene duración pero no precio",Toast.LENGTH_LONG ).show();
            return false;
        }

        if(!this.precioOferta.getText().toString().equals("") && this.duracionOferta.getText().toString().equals("")){
            Toast.makeText(VerProducto.this, "La oferta de tu producto tiene precio pero no duración",Toast.LENGTH_LONG ).show();
            return false;
        }
        return true;
    }



    public void modificarOferta(){
        String idRelacion = String.valueOf(this.producto.getIdRelacion());
        String precioOferta = this.precioOferta.getText().toString();
        int duracion =  Integer.parseInt(this.duracionOferta.getText().toString());

        this.conectorBD.modificarOferta(idRelacion, precioOferta, duracion, this);

    }

    public void eliminarOferta(){
        String idRelacion = String.valueOf(this.producto.getIdRelacion());

        this.conectorBD.eliminarOferta(idRelacion, this);
    }

    public void crearOferta(){
        String idRelacion = String.valueOf(this.producto.getIdRelacion());
        String precioOferta = this.precioOferta.getText().toString();
        int duracion =  Integer.parseInt(this.duracionOferta.getText().toString());

        this.conectorBD.crearOferta(idRelacion,precioOferta,duracion, this);

    }

    public void eliminarProductoEmpresa(){
        String idRelacion = String.valueOf(this.producto.getIdRelacion());

        this.conectorBD.eliminarProductoEmpresa(idRelacion, this);
    }

    public void modificarElProductoEmpresa(){
        //**********
        String idRelacion = String.valueOf(this.producto.getIdRelacion());
        String precioUnitario = this.precio.getText().toString();
        String descripcion = this.descripcion.getText().toString();
        String imagen = "Imagen Producto "+ this.producto.getNombre();

        this.conectorBD.modificarProductoEmpresa(idRelacion,precioUnitario,descripcion,imagen,this);

    }

    public void popupConfirmarOpcionModificarProducto(){
        dialogBuilder1 = new AlertDialog.Builder(this);
        final View contactPopUpView = getLayoutInflater().inflate(R.layout.popup_certeza,null);

        this.botonAceptar = contactPopUpView.findViewById(R.id.botonSiPopup);
        this.botonCancelar = contactPopUpView.findViewById(R.id.botonNoPopup);
        this.mensajePopup = contactPopUpView.findViewById(R.id.mensajeConfirmacionPupup);

        this.mensajePopup.setText("¿Estás seguro/a que quieres modificar este producto?");

        this.botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String precioOfertaF = precioOferta.getText().toString();
                String tiempoDeLaOferta = duracionOferta.getText().toString();

                if( Integer.parseInt(producto.getPrecio()) < Integer.parseInt(precioOfertaF)){
                    Toast.makeText(getBaseContext(),"El precio de la oferta es mayor que el precio corriente",Toast.LENGTH_SHORT).show();
                    return;
                }
                modificarElProductoEmpresa();

                // Si el producto tiene oferta y las casillas de precioOferta y duracionOferta están marcadas,
                // Se modifica el producto y luego las caracteristicas de la oferta
                if(producto.tieneOferta() && !tiempoDeLaOferta.equals("") && !precioOfertaF.equals("")){
                    modificarOferta();
                }

                // Si el producto tiene oferta y las casillas de precioOferta y duracionOferta no están marcadas,
                // Se modifica el producto y se elimina la oferta

                if(producto.tieneOferta() && tiempoDeLaOferta.equals("") && precioOfertaF.equals("")){
                    eliminarOferta();
                }

                // Si el producto no tiene oferta y las casillas de precioOferta y duracionOferta están marcadas,
                // se crea una oferta
                if(!producto.tieneOferta() && !tiempoDeLaOferta.equals("") && !precioOfertaF.equals("")){
                    crearOferta();
                }
                onBackPressed();
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

    public void popupConfirmarOpcionEliminarProducto(){
        dialogBuilder1 = new AlertDialog.Builder(this);
        final View contactPopUpView = getLayoutInflater().inflate(R.layout.popup_certeza,null);

        this.botonAceptar = contactPopUpView.findViewById(R.id.botonSiPopup);
        this.botonCancelar = contactPopUpView.findViewById(R.id.botonNoPopup);
        this.mensajePopup = contactPopUpView.findViewById(R.id.mensajeConfirmacionPupup);

        this.mensajePopup.setText("¿Estás seguro/a que quieres eliminar este producto?");

        this.botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //agregarProductoBD();
                if(producto.tieneOferta()){
                    eliminarOferta();
                }
                eliminarProductoEmpresa();
                onBackPressed();
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
}