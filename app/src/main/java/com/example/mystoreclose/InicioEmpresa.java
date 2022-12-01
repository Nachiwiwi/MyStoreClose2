package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import java.io.Serializable;

import adapters.AdaptadorProductos;
import modelo.ConectorBD;
import modelo.EmpresaMinimarket;
import modelo.Oferta;
import recyclerviews.RecyclerViewProductosFragment;

public class InicioEmpresa extends AppCompatActivity implements View.OnClickListener{
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
    private ConectorBD bd;

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

    private void obtenerProductosBD(){
        this.bd.setMinimarket(this.minimarket);
        this.bd.obtenerProductos(this,this.adapterProductos);
        //System.out.println("La cosa tiene: " + this.minimarket.obtenerCantidadDeProductos());
        this.adapterProductos.notifyDataSetChanged();

    }

    private void Inicializar(SharedPreferences preference){
        this.botonAgregarProducto = (Button) findViewById(R.id.agregarProductosButton);
        this.botonBuscarProductos = (Button) findViewById(R.id.buscarProductosButton);
        this.botonVerProducto = (ImageView) findViewById(R.id.botonVerProducto);
        //this.botonActualizar = (ImageButton) findViewById(R.id.refreshButtonInicioEmpresa);
        botonBuscarProductos.setOnClickListener(this);
        botonAgregarProducto.setOnClickListener(this);
        //botonActualizar.setOnClickListener(this);

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
        this.bd = new ConectorBD(this.minimarket);

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

            /*case(R.id.refreshButtonInicioEmpresa):
            actualizar();
                break;*/
            case (R.id.encargos1):
                Intent ventanaEncargos = new Intent(InicioEmpresa.this, EncargosEmpresa.class);
                bundle.putSerializable("minimarket" , (Serializable) this.minimarket);
                ventanaEncargos.putExtras(bundle);
                startActivity(ventanaEncargos);
                break;
            case (R.id.perfil):
                Intent ventanaPerfil = new Intent(InicioEmpresa.this, PerfilEmpresa.class);
                bundle.putSerializable("minimarket" , (Serializable) this.minimarket);
                ventanaPerfil.putExtras(bundle);
                startActivity(ventanaPerfil);
        }
    }

    public void actualizar(){
        this.minimarket.limpiarDatos();
        this.obtenerProductosBD();
        this.obtenerOfertas();
        this.bd.obtenerEncargosEmpresa(this);
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

        this.bd.obtenerOfertasProductos(this);

    }
    public void asignarOferta(int idP, Oferta oferta){
        if(this.minimarket.obtenerProducto(idP) != null) {
            this.minimarket.obtenerProducto(idP).setOferta(oferta);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        actualizar();
    }
}



