package com.example.mystoreclose;

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

import modelo.EmpresaMinimarket;
import modelo.Producto;

public class VerProducto extends AppCompatActivity implements View.OnClickListener{
    private EmpresaMinimarket minimarket;
    private Producto producto;
    private RequestQueue rQ;
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
    private SimpleDateFormat formato;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_producto);

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
        // fecha
        this.formato = new SimpleDateFormat("yyyy-MM-dd");
        this.calendar = Calendar.getInstance();
        // DB
        this.rQ = Volley.newRequestQueue(this);

        Bundle bundle = getIntent().getExtras();
        this.minimarket = (EmpresaMinimarket) bundle.getSerializable("minimarket");
        this.producto = this.minimarket.obtenerProductoIndice(bundle.getInt("indice"));
        System.out.println(this.producto.getNombre()+ " "+bundle.getInt("indice") );

        precio.setText(this.producto.getPrecio());
        precioOferta.setText(this.producto.precioOferta());
        duracionOferta.setText(this.producto.tiempoRestanteOferta());
        descripcion.setText(this.producto.getDescripcion());

        this.nombreProducto.setText(this.producto.getNombre());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.eliminarProd1):

                if(this.producto.tieneOferta()){
                    this.eliminarOferta();
                }
                eliminarProductoEmpresa();

                break;
            case(R.id.volverInicio):
                break;
            case(R.id.modificarProd1):
                System.out.println("Modificar Producto");

                if(revisarCeldas()){

                    String precioOfertaF = this.precioOferta.getText().toString();
                    String tiempoDeLaOferta = this.duracionOferta.getText().toString();

                    modificarElProductoEmpresa();

                    // Si el producto tiene oferta y las casillas de precioOferta y duracionOferta están marcadas,
                    // Se modifica el producto y luego las caracteristicas de la oferta
                    if(producto.tieneOferta() && !tiempoDeLaOferta.equals("") && !precioOfertaF.equals("")){
                        modificarOferta();
                    }

                    // Si el producto tiene oferta y las casillas de precioOferta y duracionOferta no están marcadas,
                    // Se modifica el producto y se elimina la oferta la oferta

                    if(producto.tieneOferta() && tiempoDeLaOferta.equals("") && precioOfertaF.equals("")){
                        eliminarOferta();
                    }

                    // Si el producto no tiene oferta y las casillas de precioOferta y duracionOferta están marcadas,
                    // se crea una oferta
                    if(!producto.tieneOferta() && !tiempoDeLaOferta.equals("") && !precioOfertaF.equals("")){
                        crearOferta();
                    }

                }

                break;
        }
        onBackPressed();
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

        Calendar dataFinal = Calendar.getInstance( );
        dataFinal.add(Calendar.DAY_OF_YEAR,duracion);

        String fechaTerminal = this.formato.format(dataFinal.getTime());
        String fechaInicial = this.formato.format(this.calendar.getTime());

        //System.out.println( this.formato.format(this.calendar.getTime())+ " "+ this.formato.format(dataFinal.getTime()));

        String dir = "http://192.168.1.102/Android/putModificarOff.php";

        StringRequest stringRequest =new StringRequest(
                Request.Method.POST,
                dir,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(context: MainActivity.this, text: "Correct", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(VerProducto.this,error.toString(), Toast.LENGTH_LONG);
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("IdRel",idRelacion);
                params.put("PrecioOferta",precioOferta);
                params.put("FechaInicio",fechaInicial);
                params.put("FechaTermino",fechaTerminal);
                return params;

            }
        };

        this.rQ.add(stringRequest);

    }

    public void eliminarOferta(){
        String idRelacion = String.valueOf(this.producto.getIdRelacion());

        String dir = "http://192.168.1.102/Android/deleteOferta.php";

        StringRequest stringRequest =new StringRequest(
                Request.Method.POST,
                dir,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(context: MainActivity.this, text: "Correct", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(VerProducto.this,error.toString(), Toast.LENGTH_LONG);
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("IdRel",idRelacion);
                return params;

            }
        };
        this.rQ.add(stringRequest);

    }

    public void crearOferta(){
        String idRelacion = String.valueOf(this.producto.getIdRelacion());
        String precioOferta = this.precioOferta.getText().toString();
        int duracion =  Integer.parseInt(this.duracionOferta.getText().toString());

        Calendar dataFinal = Calendar.getInstance( );
        dataFinal.add(Calendar.DAY_OF_YEAR,duracion);

        String fechaTerminal = this.formato.format(dataFinal.getTime());
        String fechaInicial = this.formato.format(this.calendar.getTime());

        //System.out.println( this.formato.format(this.calendar.getTime())+ " "+ this.formato.format(dataFinal.getTime()));

        String dir = "http://192.168.1.102/Android/postOferta.php";

        StringRequest stringRequest =new StringRequest(
                Request.Method.POST,
                dir,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(context: MainActivity.this, text: "Correct", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(VerProducto.this,error.toString(), Toast.LENGTH_LONG);
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("IdRel",idRelacion);
                params.put("PrecioOferta",precioOferta);
                params.put("FechaInicio",fechaInicial);
                params.put("FechaTermino",fechaTerminal);
                return params;

            }
        };

        this.rQ.add(stringRequest);

    }

    public void eliminarProductoEmpresa(){
        String idRelacion = String.valueOf(this.producto.getIdRelacion());

        String dir = "http://192.168.1.102/Android/deleteRelmarkprod.php";//?PrecioUnitario="+precio+"&Descripcion="+descripcion+"&IdMarket="+idEmpresa+ "&Imagen=imagen del producto&IdProducto="+idProducto;

        StringRequest stringRequest =new StringRequest(
                Request.Method.POST,
                dir,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(context: MainActivity.this, text: "Correct", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(VerProducto.this,error.toString(), Toast.LENGTH_LONG);
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("IdRel",idRelacion);
                return params;

            }
        };

        this.rQ.add(stringRequest);
    }

    public void modificarElProductoEmpresa(){

        //**********
        String idRelacion = String.valueOf(this.producto.getIdRelacion());
        String precioUnitario = this.precio.getText().toString();
        String descripcion = this.descripcion.getText().toString();
        String imagen = "Imagen Producto "+ this.producto.getNombre();

        String dir = "http://192.168.1.102/Android/putRelmarkprod.php";//?PrecioUnitario="+precio+"&Descripcion="+descripcion+"&IdMarket="+idEmpresa+ "&Imagen=imagen del producto&IdProducto="+idProducto;

        StringRequest stringRequest =new StringRequest(
                Request.Method.POST,
                dir,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(context: MainActivity.this, text: "Correct", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(VerProducto.this,error.toString(), Toast.LENGTH_LONG);
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("IdRel",idRelacion);
                params.put("PrecioUnitario",precioUnitario);
                params.put("Descripcion",descripcion);
                params.put("Imagen", imagen);
                return params;

            }
        };

        this.rQ.add(stringRequest);
    }
}