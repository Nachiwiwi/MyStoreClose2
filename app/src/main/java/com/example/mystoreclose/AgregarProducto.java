package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import modelo.ColeccionProductos;
import modelo.EmpresaMinimarket;
import modelo.Producto;

public class AgregarProducto extends AppCompatActivity implements SearchView.OnQueryTextListener,  Response.Listener<JSONObject>, Response.ErrorListener, View.OnClickListener{

    //Botones
    private ImageButton botonAtras;
    private Button botonAgregar;

    // Conexion con la base de datos
    private RequestQueue rQ;
    private JsonRequest jsR;
    private String url;
    // reglas del negocio
    private SearchView buscarProducto;
    private RecyclerViewAgregarProductoFragment fragment;
    private AdaptadorAgregarProductos adaptadorAgregarProductosP;
    private ColeccionProductos coleccionProductos;
    private EmpresaMinimarket empresaMinimarket;

    private int idProducto;
    EditText etPrecio, etDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);
        inicializar();
        inicializarRecyclerView();

        this.rQ = Volley.newRequestQueue(this);
        inicializarBaseDeDatos();

    }
    
    public void inicializar(){
        this.coleccionProductos = new ColeccionProductos();
        this.url = "http://192.168.1.102/Android/getProductos.php";
        this.buscarProducto = (SearchView) findViewById(R.id.searchViewAgregarProducto);
        this.botonAtras = (ImageButton) findViewById(R.id.volverInicio);
        this.botonAgregar = (Button) findViewById(R.id.agregarProd2);
        this.etDescripcion = (EditText) findViewById(R.id.editTextDescripcionProducto);
        this.etPrecio = (EditText) findViewById(R.id.editTextPrecioProducto);
        this.buscarProducto.setOnQueryTextListener(this);
        this.botonAtras.setOnClickListener(this);
        this.botonAgregar.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            this.empresaMinimarket = (EmpresaMinimarket) bundle.getSerializable("minimarket");
            System.out.println("Agregar P, El nombre de la empresa es: " + this.empresaMinimarket.getNombreEmpresa());
        }
    }

    public void inicializarRecyclerView(){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        this.fragment = new RecyclerViewAgregarProductoFragment();
        this.adaptadorAgregarProductosP = new AdaptadorAgregarProductos(this.coleccionProductos);
        this.fragment.setAdapter(this.adaptadorAgregarProductosP);

        transaction.replace(R.id.agregarProd, this.fragment);
        transaction.commit();

        this.adaptadorAgregarProductosP.setItemListener(new AdaptadorAgregarProductos.ItemClickListener() {
            @Override
            public void obtenerIdProducto(int idProducto) {
                asignarIdProducto(idProducto);
            }
        });
    }

    private void asignarIdProducto(int id){
        //System.out.println("Dentro de Agregar Producto, El Nombre es: "+ this.coleccionProductos.obtenerProducto(id).getNombre());
        //System.out.println("El id es: "+ id + " "+this.coleccionProductos.obtenerProducto(id).getId());
        this.idProducto = id;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        try {
            this.fragment.actualizarBusqueda(newText);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(AgregarProducto.this, error.toString(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            Producto p;
            JSONArray productosJSON = response.getJSONArray("Productos");
            for(int i = 0; i < productosJSON.length(); i++){
                JSONObject pupi = productosJSON.getJSONObject(i);
                p = new Producto( new String(pupi.getString("Nombre")) ,Integer.parseInt(pupi.getString("IdProducto")) );
                this.coleccionProductos.agregarProducto(p);

                System.out.println("El nombre es: " + p.getNombre()+ " id: "+p.getId());
            }

            this.adaptadorAgregarProductosP.notifyDataSetChanged();

        }catch (JSONException e){
            Toast.makeText(AgregarProducto.this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    public void inicializarBaseDeDatos(){
        jsR = new JsonObjectRequest(Request.Method.GET, this.url, null, this,this);
        rQ.add(jsR);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case(R.id.volverInicio):
                Intent volver = new Intent(AgregarProducto.this, InicioEmpresa.class);
                startActivity(volver);
                break;

            case(R.id.agregarProd2):
                Intent agregar = new Intent(AgregarProducto.this, InicioEmpresa.class);

                if(this.idProducto != -1){
                    /*Bundle bundle = new Bundle();
                    Intent ventanaBuscarProducto = new Intent(AgregarProducto.this,InicioEmpresa.class );
                    bundle.putSerializable("producto" , (Serializable) this.coleccionProductos.obtenerProducto(this.idProducto));
                    ventanaBuscarProducto.putExtras(bundle);*/

                    agregarProductoBD();

                }
                startActivity(agregar);
                break;
        }
    }

    public void agregarProductoBD(){

        int idEmpresa = 3;
        int idP = this.idProducto;
        String descripcion = this.etDescripcion.getText().toString();
        String precio = this.etPrecio.getText().toString();
        String imagen = "Imagen Producto "+ this.coleccionProductos.obtenerProducto(idP);

        String dir = "http://192.168.1.102/Android/post_relmarkprod.php";//?PrecioUnitario="+precio+"&Descripcion="+descripcion+"&IdMarket="+idEmpresa+ "&Imagen=imagen del producto&IdProducto="+idProducto;

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

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("PrecioUnitario",precio);
                params.put("Descripcion",descripcion);
                params.put("IdMarket",String.valueOf(idEmpresa));
                params.put("Imagen","Imagen del producto");
                params.put("IdProducto",String.valueOf(idProducto));
                return params;

            }
        };

        this.rQ.add(stringRequest);


        //System.out.println(idEmpresa+ " "+idProducto+ " "+ descripcion+ " "+precio);
    }
}