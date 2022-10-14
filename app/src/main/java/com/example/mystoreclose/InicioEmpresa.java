package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
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

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import modelo.Direccion;
import modelo.EmpresaMinimarket;
import modelo.Oferta;
import modelo.Producto;

public class InicioEmpresa extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener, View.OnClickListener{
    private RequestQueue rQ;
    private JsonRequest jsR;
    EmpresaMinimarket minimarket;// = new EmpresaMinimarket(0,"Empresa","Minimarket1","1","777","111","casabenja@gmail.com",-33.0418,-71.6485);
    private Button botonAgregarProducto;
    private Button botonBuscarProductos;
    private ImageButton botonActualizar;
    private ImageView botonVerProducto;
    private Button botonEncargos;
    private Button botonPerfil;
    private RecyclerViewProductosFragment recyclerViewProductos;
    private AdaptadorProductos adapterProductos;
    private String idRelacion;
    private String nombreEmpresa;
    private SharedPreferences preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_empresa);
        preference = getSharedPreferences("preference",MODE_PRIVATE);
        this.Inicializar(preference);
        this.inicializarRecyclerView();
        botonEncargos = (Button) findViewById(R.id.encargos1);
        botonEncargos.setOnClickListener(this);

        botonPerfil = (Button) findViewById(R.id.perfil);
        botonPerfil.setOnClickListener(this);


        // Se obtienen los datos de la base de datos

        rQ = Volley.newRequestQueue(this);
        this.obtenerProductosBD();
        this.obtenerOfertas();


        System.out.println("El tamaño de la coleccion es: "+this.minimarket.obtenerCantidadDeProductos());
        // Tuve que poner estas sentencias es el metodo onResponse porque me abría la página antes que leerme los datos de la Base de datos

    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(InicioEmpresa.this, error.toString()+ "Error QLO ",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {

        try {
            Producto p;
            JSONArray productosJSON = response.getJSONArray("Productos");
            for(int i = 0; i < productosJSON.length(); i++){
                JSONObject pupi = productosJSON.getJSONObject(i);
                p = new Producto( new String(pupi.getString("Nombre")),
                        new String(pupi.getString("PrecioUnitario")),
                        Integer.parseInt(pupi.getString("IdProducto")),
                        new String(pupi.getString("Descripcion")),
                        Integer.parseInt(pupi.getString("IdRel")));
                this.minimarket.agregarProducto(p);

                this.idRelacion = new String(pupi.getString("IdRel"));

                System.out.println("El nombre es: " + pupi.getString("Nombre")
                        + " El precio es: "+ pupi.getString("PrecioUnitario"));
                //System.out.println(pupi.getString("Nombre"));
            }
            this.adapterProductos.notifyDataSetChanged();


        }catch (JSONException e){
            Toast.makeText(InicioEmpresa.this, "Error:", Toast.LENGTH_SHORT).show();
        }
    }

    private void obtenerProductosBD(){
        //url original creo XD = 192.168.0.4
        String dir = "http://192.168.56.1/Android/getPM.php?Nombre_empresa="+this.minimarket.getNombreEmpresa();
        jsR = new JsonObjectRequest(Request.Method.GET, dir, null, this,this);
        rQ.add(jsR);
    }

    private void Inicializar(SharedPreferences preference){
        this.botonAgregarProducto = (Button) findViewById(R.id.agregarProductosButton);
        this.botonBuscarProductos = (Button) findViewById(R.id.buscarProductosButton);
        this.botonVerProducto = (ImageView) findViewById(R.id.botonVerProducto);
        this.botonActualizar = (ImageButton) findViewById(R.id.refreshButtonInicioEmpresa);
        botonBuscarProductos.setOnClickListener(this);
        botonAgregarProducto.setOnClickListener(this);
        botonActualizar.setOnClickListener(this);
        //acceder a los datos guardados en el inicio secion
        String Nombre_empresa = preference.getString("Nombre_empresa",null);
        String Nombre_local = preference.getString("Nombre_local",null);
        //String Nombredueño = preference.getString("Nombredueño",null);
        String MailDueño = preference.getString("MailDueño",null);
        String Direccion = preference.getString("Direccion",null);
        String Rut_empresa = preference.getString("Rut_empresa",null);
        int IdMarket = preference.getInt("IdMarket",0);
        //double Latitud = preference.getFloat("IdMarket",0);
        //double Longitud = preference.getFloat("IdMarket",0);

        //prueba para guardar datos
        this.nombreEmpresa = Nombre_empresa;
        this.minimarket = new EmpresaMinimarket(IdMarket,this.nombreEmpresa,Nombre_local,Direccion,Rut_empresa,"",MailDueño,10,10);

        //original
        /*
        this.nombreEmpresa = "COFFE MASTER";
        this.minimarket = new EmpresaMinimarket(1,this.nombreEmpresa,"COFFE MASTER","Pedro infante con Villa Marina","","","",10,10);
        */
    }


    @Override
    public void onClick(View v) {
        System.out.println("Botones");
        Bundle bundle = new Bundle();

        switch (v.getId()){
            case (R.id.agregarProductosButton):
                Intent ventanaAgregarProducto = new Intent(InicioEmpresa.this, AgregarProducto.class);
                bundle.putSerializable("minimarket" , (Serializable) this.minimarket);
                ventanaAgregarProducto.putExtras(bundle);

                startActivity(ventanaAgregarProducto);
                break;

            case (R.id.buscarProductosButton):
                Intent ventanaBuscarProducto = new Intent(InicioEmpresa.this,BuscarProductoEmpresa.class );
                // Se pasa un objeto de clase EmpresaMinimarket para que sea manipulado por la ventana BuscarProductoEmpresa

                bundle.putSerializable("minimarket" , (Serializable) this.minimarket);
                ventanaBuscarProducto.putExtras(bundle);
                // Se abre la pestaña ventanaBuscarProducto
                startActivity(ventanaBuscarProducto);
                break;

            case(R.id.refreshButtonInicioEmpresa):
                this.minimarket.limpiarDatos();
                this.obtenerProductosBD();
                this.obtenerOfertas();
                break;
            case (R.id.encargos1):
                Intent ventanaEncargos = new Intent(InicioEmpresa.this, EncargosEmpresa.class);
                startActivity(ventanaEncargos);
                break;
            case (R.id.perfil):
                Intent ventanaPerfil = new Intent(InicioEmpresa.this, PerfilEmpresa.class);
                startActivity(ventanaPerfil);
        }
    }

    public void inicializarRecyclerView(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        this.recyclerViewProductos = new RecyclerViewProductosFragment();
        this.adapterProductos = new AdaptadorProductos(this.minimarket);
        this.recyclerViewProductos.setAdapter(adapterProductos);

        transaction.replace(R.id.fragmentContentProductosEmpresa, this.recyclerViewProductos);
        transaction.commit();

        this.adapterProductos.setItemListener(new AdaptadorProductos.ItemClickListener() {
            @Override
            public void verProducto(int posicion) {
                verInformacionProducto(posicion);
            }
        });
    }

    public void verInformacionProducto(int posicion){
        System.out.println("Dross Rotzank");
        Intent ventanaBuscarProducto = new Intent(InicioEmpresa.this,VerProducto.class );
        // Se pasa un objeto de clase EmpresaMinimarket para que sea manipulado por la ventana VerProducto

        Bundle bundle = new Bundle();
        bundle.putSerializable("minimarket" , (Serializable) this.minimarket);
        bundle.putInt("indice",posicion);
        ventanaBuscarProducto.putExtras(bundle);
        // Se abre la pestaña VerProducto
        startActivity(ventanaBuscarProducto);
        //System.out.println("El producto es: "+ this.minimarket.obtenerProductoIndice(posicion).getNombre());
    }

    public void obtenerOfertas(){
        //url original (creo XD) = 192.168.178.246
        String dir = "http://192.168.56.1/Android/getProdConOff.php?Nombre_empresa="+this.nombreEmpresa;

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        jsR = new JsonObjectRequest(Request.Method.GET, dir, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Oferta off;
                try {
                    JSONArray ofertasJSON = response.getJSONArray("Ofertas");
                    for (int i = 0; i < ofertasJSON.length(); i++){
                        JSONObject current = ofertasJSON.getJSONObject(i);
                        Calendar dataFinal = Calendar.getInstance( );
                        dataFinal.setTime(formato.parse(new String(current.getString("FechaTermino"))));
                        int resta = dataFinal.get(Calendar.DAY_OF_YEAR) - calendar.get(Calendar.DAY_OF_YEAR);

                        off = new Oferta(new String(current.getString("PrecioOferta")),String.valueOf(resta), new String(current.getString("IdOferta")) );

                        asignarOferta(Integer.parseInt(new String(current.getString("IdProducto"))),off);
                        System.out.println("La resta es:"+ resta + " "+current.getString("FechaInicio")+ " "+ current.getString("FechaTermino") );
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Problema con getProdConOff.php");
                //Toast.makeText(InicioEmpresa.this, error.toString()+" se vienen cositas", Toast.LENGTH_SHORT).show();
            }
        });

    //new JsonObjectRequest(Request.Method.GET, dir, null, this,this)

        rQ.add(jsR);

    }
    public void asignarOferta(int idP, Oferta oferta){
        if(this.minimarket.obtenerProducto(idP) != null) {
            this.minimarket.obtenerProducto(idP).setOferta(oferta);
        }
    }
}



