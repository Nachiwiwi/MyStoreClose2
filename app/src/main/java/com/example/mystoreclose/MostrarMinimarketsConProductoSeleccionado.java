package com.example.mystoreclose;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modelo.EmpresaMinimarket;
import modelo.Oferta;
import modelo.Producto;

public class MostrarMinimarketsConProductoSeleccionado extends AppCompatActivity implements View.OnClickListener, Response.Listener<JSONObject>, Response.ErrorListener{
    private ArrayList<EmpresaMinimarket> minimarkets = new ArrayList<>();
    private String idProducto;
    // Conexion con la base de datos
    private RequestQueue rQ;
    private JsonRequest jsR;
    private RecyclerViewMinimarketsProductoSeleccionado recyclerView;
    private AdaptadorMinimarketsProductoS adapter;
    //
    private Button botonFiltrar;
    // variables para el pop up
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private Button aceptarFiltro;
    private Button cancelarFiltro;
    private EditText filtroPrecioMinimo;
    private EditText filtroPrecioMaximo;
    private int minimo = -1;
    private int maximo = -1;
    private Button botonEncargos;
    private Button botonPerfil;
    private Button botonInicio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_minimarkets_con_producto_seleccionado);
        inicializar();
        inicializarRecyclerView();
        obtenerMinimarketsConProducto();

        botonEncargos = (Button) findViewById(R.id.encargos1);
        botonEncargos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(MostrarMinimarketsConProductoSeleccionado.this, EncargosCliente.class);
                startActivity(volver);
            }
        });
        botonPerfil = (Button) findViewById(R.id.perfil);
        botonPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(MostrarMinimarketsConProductoSeleccionado.this, PerfilCliente.class);
                startActivity(volver);
            }
        });
        botonInicio = (Button) findViewById(R.id.productos);
        botonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(MostrarMinimarketsConProductoSeleccionado.this, InicioCliente.class);
                startActivity(volver);
            }
        });
    }

    public void inicializar(){
        this.rQ = Volley.newRequestQueue(this);
        this.botonFiltrar = findViewById(R.id.botonFiltroPrecio);

        this.botonFiltrar.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            this.idProducto = bundle.getString("idProducto");
        }
        //this.idProducto="7";
    }

    public void inicializarRecyclerView(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        this.recyclerView = new RecyclerViewMinimarketsProductoSeleccionado();
        this.adapter = new AdaptadorMinimarketsProductoS(this.minimarkets,Integer.valueOf(this.idProducto));
        this.recyclerView.setAdapter(this.adapter);

        transaction.replace(R.id.fragmentContentMinimarketProducto, this.recyclerView);
        transaction.commit();

    }

    public void obtenerMinimarketsConProducto(){
        String url = "http://192.168.0.4/Android/getMinimarketsPorProducto.php?IdProducto="+this.idProducto;

        jsR = new JsonObjectRequest(Request.Method.GET, url, null, this,this);
        this.adapter.notifyDataSetChanged();
        rQ.add(jsR);
        System.out.println("El tamaño de la coleccion es: "+ this.minimarkets.size());
    }

    public void crearContactDialogNuevo(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopUpView = getLayoutInflater().inflate(R.layout.popup,null);

        this.aceptarFiltro = contactPopUpView.findViewById(R.id.botonFiltrar);
        this.cancelarFiltro = contactPopUpView.findViewById(R.id.botonCancelarFiltro);
        this.filtroPrecioMinimo = contactPopUpView.findViewById(R.id.editTextPrecioMinimo);
        this.filtroPrecioMaximo = contactPopUpView.findViewById(R.id.editTextPrecioMaximo);

        this.aceptarFiltro.setOnClickListener(this);
        this.cancelarFiltro.setOnClickListener(this);

        dialogBuilder.setView(contactPopUpView);
        dialog = dialogBuilder.create();
        dialog.show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.botonFiltroPrecio):
                crearContactDialogNuevo();
                System.out.println("mostrar filtrado");
                break;

            case (R.id.botonFiltrar):
                if (verificarTextosPrecio()) {
                    dialog.dismiss();
                }
                break;

            case (R.id.botonCancelarFiltro):
                dialog.dismiss();
                break;

        }
    }

    public boolean verificarTextosPrecio(){

        Pattern patPrecios = Pattern.compile(".*[a-zA-Z].*");
        Matcher mat = patPrecios.matcher(this.filtroPrecioMinimo.getText().toString());

        if(mat.matches()){
            Toast.makeText(MostrarMinimarketsConProductoSeleccionado.this, "Ingresaste letras en el precio Mínimo",Toast.LENGTH_LONG ).show();
            this.minimo = -1;
            return false;
        }
        else
        {
            this.minimo = Integer.parseInt(this.filtroPrecioMinimo.getText().toString());
        }

        mat = patPrecios.matcher(this.filtroPrecioMaximo.getText().toString());
        if(mat.matches()){
            Toast.makeText(MostrarMinimarketsConProductoSeleccionado.this, "Ingresaste letras en el precio Máximo",Toast.LENGTH_LONG ).show();
            this.maximo = -1;
            return false;
        }else
        {
            this.maximo = Integer.parseInt(this.filtroPrecioMaximo.getText().toString());
        }

        return true;

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        System.out.println(error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        int idMinimarket;
        String nombreEmpresa;
        String nombreMinimarket;
        String direccion;
        String rutEmpresa;
        String ClaveAdminE;
        String mailAdmMin;
        double latitud;
        double longitud;

        String nombreProducto;
        int idRelacion;
        String precio;
        String descripcion;

        try {
            JSONArray ofertasJSON = response.getJSONArray("Minimarkets");
            for (int i = 0; i < ofertasJSON.length(); i++){
                JSONObject current = ofertasJSON.getJSONObject(i);
                System.out.println(current.getString("IdMarket"));
                idMinimarket = Integer.parseInt(current.getString("IdMarket"));
                nombreEmpresa = new String(current.getString("Nombre_empresa"));
                nombreMinimarket = new String(current.getString("Nombre_local"));
                direccion = new String(current.getString("Direccion"));
                rutEmpresa = new String(current.getString("Rut_empresa"));
                ClaveAdminE = new String(current.getString("Contraseñadueño"));
                mailAdmMin = new String(current.getString("MailDueño"));
                latitud = Double.valueOf(current.getString("Latitud"));
                longitud = Double.valueOf(current.getString("Longitud"));

                nombreProducto = new String(current.getString("Nombre"));
                idRelacion = Integer.parseInt(current.getString("IdRel"));
                precio = new String(current.getString("PrecioUnitario"));
                descripcion = new String(current.getString("Descripcion"));

                Producto p = new Producto(nombreProducto,precio,Integer.parseInt(this.idProducto),descripcion,idRelacion);

                EmpresaMinimarket e = new EmpresaMinimarket(idMinimarket,nombreEmpresa,nombreMinimarket,
                        direccion,rutEmpresa,ClaveAdminE,mailAdmMin, latitud,longitud);

                e.agregarProducto(p);

                this.minimarkets.add(e);
                //agregarMinimarket(idMinimarket,nombreEmpresa,nombreMinimarket,direccion,rutEmpresa,ClaveAdminE,mailAdmMin,latitud,longitud);
                //System.out.println(idMinimarket+ " "+ nombreEmpresa+ " "+ nombreMinimarket
                //+" "+direccion+ " "+ rutEmpresa+" "+latitud+ " "+longitud);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.adapter.notifyDataSetChanged();
    }
}