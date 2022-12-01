package com.example.mystoreclose;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.sql.SQLOutput;
import java.util.ArrayList;

import adapters.AdaptadorOfertasCliente;
import modelo.Cliente;
import modelo.ConectorBD;
import modelo.Direccion;
import modelo.EmpresaMinimarket;
import modelo.Oferta;
import modelo.Producto;
import recyclerviews.RecyclerViewOfertasFragment;

public class InicioCliente extends AppCompatActivity implements SearchView.OnQueryTextListener, Response.Listener<JSONObject>, Response.ErrorListener, View.OnClickListener {

    //Botones
    Button botonBuscarMinimarkets;
    private JsonRequest jsR;
    private RequestQueue rQ;
    private Button botonEncargos;
    private Button botonPerfil;
    private Button botonBuscarProductos;
    private SearchView barraBusqueda;

    private ConectorBD bd;
    private RecyclerViewOfertasFragment ofertasDestacadas;
    private AdaptadorOfertasCliente adaptadorOfertasCliente;
    private ArrayList<Oferta> listadoOfertas = new ArrayList<>();
    TextView prueba;
    private Cliente cliente; //= new Cliente();
    //Barra navegación
    BottomNavigationView botonNav;
    private SharedPreferences preference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_cliente);
        prueba = (TextView) findViewById(R.id.textView4);
        rQ = Volley.newRequestQueue(this);
        preference = getSharedPreferences("preference",MODE_PRIVATE);

        obtenerUsuarioActual(preference);
        recyclerViewOfertas();
        obtenerOfertas();

        barraBusqueda = (SearchView) findViewById(R.id.searchViewBuscarProductoCliente);
        barraBusqueda.setOnQueryTextListener(this);

        //Apretar botón buscar minimarket
        botonBuscarMinimarkets = (Button) findViewById(R.id.buscarMini);
        botonBuscarMinimarkets.setOnClickListener(this);

        //Apretar botón buscar producto
        botonBuscarProductos = (Button) findViewById(R.id.buscarProducto);
        botonBuscarProductos.setOnClickListener(this);

        //Barra navegacion
        botonEncargos = (Button) findViewById(R.id.encargos1);
        botonEncargos.setOnClickListener(this);

        botonPerfil = (Button) findViewById(R.id.perfil);
        botonPerfil.setOnClickListener(this);
    }

    private void obtenerOfertas() {
        this.bd = new ConectorBD();
        this.bd.getOfertas(this, listadoOfertas, this.adaptadorOfertasCliente);
        this.adaptadorOfertasCliente.notifyDataSetChanged();

    }

    private void recyclerViewOfertas() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        this.ofertasDestacadas = new RecyclerViewOfertasFragment();
        this.adaptadorOfertasCliente = new AdaptadorOfertasCliente(this.listadoOfertas); //meter ofertas
        this.ofertasDestacadas.setAdapter(adaptadorOfertasCliente);

        transaction.replace(R.id.fragmentContentOfertasZona, this.ofertasDestacadas);
        transaction.commit();

    }


    //Guardar la posicion del cliente
    private void setPosicion() {

        LocationManager locationManager = (LocationManager) InicioCliente.this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                System.out.println("\n"+location.getLatitude()+" "+location.getLongitude()+"\n");
                cliente.setUbicacion(location.getLatitude(), location.getLongitude());

            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };
        int permissionCheck = ContextCompat.checkSelfPermission(InicioCliente.this, Manifest.permission.ACCESS_FINE_LOCATION);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

    }



    @Override
    public void onClick(View view) {
        System.out.println("Botones");
        switch (view.getId()) {

            case (R.id.buscarMini):
                Intent ventanaBuscarMimarket = new Intent(InicioCliente.this, BuscarMinimarketCliente.class);
                // Se pasa un objeto de clase EmpresaMinimarket para que sea manipulado por la ventana BuscarProductoEmpresa
                Bundle bundleMinimarkets = new Bundle();
                bundleMinimarkets.putSerializable("cliente", (Serializable) this.cliente);
                ventanaBuscarMimarket.putExtras(bundleMinimarkets);
                // Se abre la pestaña ventanaBuscarProducto
                startActivity(ventanaBuscarMimarket);
                break;
            case (R.id.buscarProducto):
                Intent ventanaBuscarProducto = new Intent(InicioCliente.this, BuscarProductoCliente.class);
                // Se pasa un objeto de clase EmpresaMinimarket para que sea manipulado por la ventana BuscarProductoEmpresa

                Bundle bundleProductos = new Bundle();
                bundleProductos.putSerializable("cliente", (Serializable) this.cliente);
                ventanaBuscarProducto.putExtras(bundleProductos);
                // Se abre la pestaña ventanaBuscarProducto
                startActivity(ventanaBuscarProducto);
                break;
            case (R.id.encargos1):
                Intent ventanaEncargos = new Intent(InicioCliente.this, EncargosCliente.class);
                startActivity(ventanaEncargos);
                break;
            case (R.id.perfil):
                Intent ventanaPerfil = new Intent(InicioCliente.this, PerfilCliente.class);
                Bundle perfilCliente = new Bundle();
                perfilCliente.putSerializable("cliente", (Serializable) this.cliente);
                ventanaPerfil.putExtras(perfilCliente);
                startActivity(ventanaPerfil);
        }




    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            Producto p;
            JSONArray productosJSON = response.getJSONArray("awebo");

            JSONObject usuarioBuscado = productosJSON.getJSONObject(0);
            String idCliente = usuarioBuscado.getString("IdCliente");
            String nombre = usuarioBuscado.getString("Nombre");
            String nombreUsuario = usuarioBuscado.getString("Nombre_Usuario");
            String correo = usuarioBuscado.getString("Correo_electronico");
            String clave = usuarioBuscado.getString("Contraseña");
            cliente = new Cliente(idCliente, nombre, nombreUsuario, correo, clave);
            setPosicion();


        }catch (JSONException e){
            Toast.makeText(InicioCliente.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void obtenerUsuarioActual(SharedPreferences preference){
        String Nombre_Usuario = preference.getString("Nombre_Usuario","matichief117");
        String dir;

        dir = "http://192.168.20.246/Android/perfil_usuario.php?Nombre_Usuario="+Nombre_Usuario;

        jsR = new JsonObjectRequest(Request.Method.GET, dir, null, this,this);
        rQ.add(jsR);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        ofertasDestacadas.actualizarBusqueda(s);
        return false;
    }
}