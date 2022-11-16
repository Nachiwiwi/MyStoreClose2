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

import adapters.AdaptadorAgregarProductos;
import modelo.ColeccionProductos;
import modelo.ConectorBD;
import modelo.EmpresaMinimarket;
import recyclerviews.RecyclerViewAgregarProductoFragment;

public class AgregarProducto extends AppCompatActivity implements SearchView.OnQueryTextListener,  View.OnClickListener{

    //Botones
    private ImageButton botonAtras;
    private Button botonAgregar;
    private Button botonEncargos;
    private Button botonPerfil;
    private Button botonInicio;
    // Conexion con la base de datos
    private ConectorBD bd;

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
        botonEncargos = (Button) findViewById(R.id.encargos1);
        botonEncargos.setOnClickListener(this);

        botonPerfil = (Button) findViewById(R.id.perfil);
        botonPerfil.setOnClickListener(this);

        botonInicio = (Button) findViewById(R.id.productos);
        botonInicio.setOnClickListener(this);

        onQueryTextChange("");
        /*this.rQ = Volley.newRequestQueue(this);
        inicializarBaseDeDatos();
        */
    }
    
    public void inicializar(){
        this.coleccionProductos = new ColeccionProductos();
        bd = new ConectorBD(this.empresaMinimarket);

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
            System.out.println("Agregar P, El nombre de la empresa es: " + this.empresaMinimarket.getNombreEmpresa()
            +" y su id es: "+this.empresaMinimarket.getIdMinimarket());
        }
    }

    public void inicializarRecyclerView(){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        this.fragment = new RecyclerViewAgregarProductoFragment();
        this.adaptadorAgregarProductosP = new AdaptadorAgregarProductos(this.coleccionProductos);
        this.fragment.setAdapter(this.adaptadorAgregarProductosP);

        bd.obtenerProductosLista(this.coleccionProductos, this,this.adaptadorAgregarProductosP);

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
    public void onClick(View v) {

        switch (v.getId()){
            case(R.id.volverInicio):
                Intent volver = new Intent(AgregarProducto.this, InicioEmpresa.class);
                startActivity(volver);
                break;

            case(R.id.agregarProd2):

                if(this.idProducto != -1){
                    agregarProductoBD();
                }
                onBackPressed();;
                break;
            case (R.id.encargos1):
                Intent ventanaEncargos = new Intent(AgregarProducto.this, EncargosEmpresa.class);
                startActivity(ventanaEncargos);
                break;
            case (R.id.perfil):
                Intent ventanaPerfil = new Intent(AgregarProducto.this, PerfilEmpresa.class);
                startActivity(ventanaPerfil);
                break;
            case (R.id.productos):
                Intent ventanaInicio = new Intent(AgregarProducto.this, InicioEmpresa.class);
                startActivity(ventanaInicio);
        }
    }

    // agregarProducto(int idEmpresa, int idP, String descripcion, String precio, String imagen, int idProducto)
    public void agregarProductoBD(){
        int idEmpresa = this.empresaMinimarket.getIdMinimarket();
        int idP = this.idProducto;
        String descripcion = this.etDescripcion.getText().toString();
        String precio = this.etPrecio.getText().toString();
        String imagen = "Imagen Producto "+ this.coleccionProductos.obtenerProducto(idP);

        this.bd.agregarProducto(idEmpresa,idP,descripcion,precio,imagen,idProducto,this);


    }

}