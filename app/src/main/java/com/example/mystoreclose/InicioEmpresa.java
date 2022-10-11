package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
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

import modelo.Direccion;
import modelo.EmpresaMinimarket;
import modelo.Producto;

public class InicioEmpresa extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener, View.OnClickListener{
    private RequestQueue rQ;
    private JsonRequest jsR;
    EmpresaMinimarket minimarket = new EmpresaMinimarket(0,"Empresa1","Minimarket1","1","777","111","casabenja@gmail.com",-33.0418,-71.6485);
    private Button botonAgregarProducto;
    private Button botonBuscarProductos;
    private ImageButton botonActualizar;
    private ImageView botonVerProducto;
    private RecyclerViewProductosFragment recyclerViewProductos;
    private AdaptadorProductos adapterProductos;
    private String idRelacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_empresa);
        this.inicializarRecyclerView();
        this.Inicializar();


        // Se obtienen los datos de la base de datos

        rQ = Volley.newRequestQueue(this);
        this.obtenerProductosBD();


        System.out.println("El tamaño de la coleccion es: "+this.minimarket.obtenerCantidadDeProductos());
        // Tuve que poner estas sentencias es el metodo onResponse porque me abría la página antes que leerme los datos de la Base de datos

    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(InicioEmpresa.this, error.toString(),Toast.LENGTH_LONG).show();
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
            }
            this.adapterProductos.notifyDataSetChanged();


        }catch (JSONException e){
            Toast.makeText(InicioEmpresa.this, "Error:", Toast.LENGTH_SHORT).show();
        }
    }

    private void obtenerProductosBD(){
        String dir = "http://192.168.0.4/Android/getPM.php";
        jsR = new JsonObjectRequest(Request.Method.GET, dir, null, this,this);
        rQ.add(jsR);
    }

    private void Inicializar(){
        this.botonAgregarProducto = (Button) findViewById(R.id.agregarProductosButton);
        this.botonBuscarProductos = (Button) findViewById(R.id.buscarProductosButton);
        this.botonVerProducto = (ImageView) findViewById(R.id.botonVerProducto);
        this.botonActualizar = (ImageButton) findViewById(R.id.refreshButtonInicioEmpresa);
        botonBuscarProductos.setOnClickListener(this);
        botonAgregarProducto.setOnClickListener(this);
        botonActualizar.setOnClickListener(this);

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
}



