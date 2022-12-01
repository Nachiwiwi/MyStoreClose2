package com.example.mystoreclose;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    // Texto
    private EditText etPrecio, etDescripcion;
    private FrameLayout frameLayout;
    private TextView textoProductoNuevoCatalogo;
    // PopUp
    private AlertDialog.Builder dialogBuilder1;
    private AlertDialog dialog1;
    private Button botonAceptar;
    private Button botonCancelar;
    private TextView mensajePopup;

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
        this.textoProductoNuevoCatalogo = (TextView) findViewById(R.id.agregarProductoFueraDelCatalogo);
        this.buscarProducto.setOnQueryTextListener(this);
        this.botonAtras.setOnClickListener(this);
        this.botonAgregar.setOnClickListener(this);
        this.textoProductoNuevoCatalogo.setOnClickListener(this);

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

        transaction.replace(R.id.agregarProd, this.fragment);
        transaction.commit();

        this.adaptadorAgregarProductosP.setItemListener(new AdaptadorAgregarProductos.ItemClickListener() {
            @Override
            public void obtenerIdProducto(int idProducto) {
                asignarIdProducto(idProducto);
            }

            @Override
            public void setFalseFrameLayout(FrameLayout fL) {
                if(frameLayout != null){
                    frameLayout.setBackgroundColor(Color.parseColor("#FF393E46"));
                }
                frameLayout = fL;
            }


        });
        bd.obtenerProductosLista(this.coleccionProductos, this,this.adaptadorAgregarProductosP);
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

        this.adaptadorAgregarProductosP.filtrado(newText);

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
                EditText txtDescripcion = findViewById(R.id.editTextDescripcionProducto);;
                EditText txtPrecio = findViewById(R.id.editTextPrecioProducto);
                System.out.println("El texto del precio es: "+txtPrecio.getText().toString().equals(""));
                if(this.idProducto != -1 && !txtPrecio.getText().toString().equals("") && !txtDescripcion.getText().toString().equals("")){
                    if(revisarCeldas()) {
                        popupConfirmarOpcion();
                    }

                }
                else{
                    if(txtPrecio.getText().toString().equals("")){
                        System.out.println("Kuki1");
                        Toast.makeText(this,"Campo de precio vacío",Toast.LENGTH_SHORT).show();
                    }
                    if(txtDescripcion.getText().toString().equals("")){
                        System.out.println("Kuki2");
                        Toast.makeText(this,"Campo de descripción vacío",Toast.LENGTH_SHORT).show();
                    }
                }

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
                break;
            case R.id.agregarProductoFueraDelCatalogo:
                Intent ventanaProductoNuevo = new Intent(AgregarProducto.this, AgregarProductoNuevo.class);
                startActivity(ventanaProductoNuevo);

        }
    }

    // agregarProducto(int idEmpresa, int idP, String descripcion, String precio, String imagen, int idProducto)
    public void agregarProductoBD(){
        int idEmpresa = this.empresaMinimarket.getIdMinimarket();
        int idP = this.idProducto;
        String descripcion = this.etDescripcion.getText().toString();
        String precio = this.etPrecio.getText().toString();
        String imagen = "Imagen Producto "+ this.coleccionProductos.obtenerProducto(idP);
        System.out.println("AEIOUUUU El producto es: "+idP+ " Descripcion "+descripcion);

        this.bd.agregarProducto(idEmpresa,descripcion,precio,imagen,idProducto,this);

    }

    public void popupConfirmarOpcion(){
        dialogBuilder1 = new AlertDialog.Builder(this);
        final View contactPopUpView = getLayoutInflater().inflate(R.layout.popup_certeza,null);

        this.botonAceptar = contactPopUpView.findViewById(R.id.botonSiPopup);
        this.botonCancelar = contactPopUpView.findViewById(R.id.botonNoPopup);
        this.mensajePopup = contactPopUpView.findViewById(R.id.mensajeConfirmacionPupup);

        this.mensajePopup.setText("¿Estás seguro que quieres agregar este producto?");

        this.botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarProductoBD();
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

    @Override
    protected void onResume() {
        super.onResume();
        this.coleccionProductos.vaciarContenido();
        this.empresaMinimarket.limpiarDatos();
        bd.obtenerProductosLista(this.coleccionProductos, this,this.adaptadorAgregarProductosP);
    }


    public boolean revisarCeldas(){
        Pattern patPrecios = Pattern.compile(".*[a-zA-Z].*");
        Matcher mat = patPrecios.matcher(this.etPrecio.getText().toString());

        if(mat.matches()){
            Toast.makeText(AgregarProducto.this, "Ingresaste letras en el precio",Toast.LENGTH_LONG ).show();
            return false;
        }

        return true;
    }
}