package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;

import java.io.Serializable;

import modelo.EmpresaMinimarket;
// Se implementa la interfaz "SearchView.OnQueryTextListener" para utilizar el SearchView y buscar objetos en tiempo real
public class BuscarProductoEmpresa extends AppCompatActivity implements SearchView.OnQueryTextListener{

    //Botones
    private ImageButton botonAtras;
    private EmpresaMinimarket minimarket= null;
    private SearchView buscarProducto;
    private RecyclerViewBuscarProductosFragment fragment;
    private AdaptadorBuscarProductos adaptadorBP;
    private Button botonEncargos;
    private Button botonPerfil;
    private Button botonInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_producto_empresa);
        inicializar();

        //Apretar flecha
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(BuscarProductoEmpresa.this, InicioEmpresa.class);
                startActivity(volver);
            }
        });

        this.buscarProducto.setOnQueryTextListener(this);

        try {
            inicializarRecyclerView();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        botonEncargos = (Button) findViewById(R.id.encargos1);
        botonEncargos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(BuscarProductoEmpresa.this, EncargosEmpresa.class);
                startActivity(volver);
            }
        });
        botonPerfil = (Button) findViewById(R.id.perfil);
        botonPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(BuscarProductoEmpresa.this, PerfilEmpresa.class);
                startActivity(volver);
            }
        });
        botonInicio = (Button) findViewById(R.id.productos);
        botonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(BuscarProductoEmpresa.this, InicioEmpresa.class);
                startActivity(volver);
            }
        });

    }

    public void inicializar(){

        this.buscarProducto = (SearchView) findViewById(R.id.searchViewBuscarProductoCliente);
        this.botonAtras = (ImageButton) findViewById(R.id.volverInicio);
        // Objeto enviado a través de Bundle
        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            this.minimarket = (EmpresaMinimarket) bundle.getSerializable("minimarket");
            System.out.println("Buscar Producto, el nombre del minimarket es: "+ this.minimarket.getNombreEmpresa());
        }
    }

    // Estos metodos nos ayudarán a buscar objetos en tiempo real
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        try {
            fragment.actualizarBusqueda(newText);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void inicializarRecyclerView() throws CloneNotSupportedException {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        this.fragment = new RecyclerViewBuscarProductosFragment();
        this.adaptadorBP = new AdaptadorBuscarProductos(this.minimarket);
        this.fragment.setAdapter(this.adaptadorBP);
        transaction.replace(R.id.buscarProd, fragment);
        transaction.commit();

        this.adaptadorBP.setItemListener(new AdaptadorBuscarProductos.ItemClickListener() {
            @Override
            public void verProducto(int idProducto)  {
                verInformacionProducto(idProducto);
            }
        });

    }
    public void verInformacionProducto(int idProducto){
        System.out.println("Dross Rotzank 777");
        Intent ventanaBuscarProducto = new Intent(BuscarProductoEmpresa.this,VerProducto.class );
        // Se pasa un objeto de clase EmpresaMinimarket para que sea manipulado por la ventana VerProducto

        Bundle bundle = new Bundle();
        bundle.putSerializable("minimarket" , (Serializable) this.minimarket);
        bundle.putInt("indice",this.minimarket.obtenerIndiceProducto(idProducto));
        ventanaBuscarProducto.putExtras(bundle);
        // Se abre la pestaña VerProducto
        startActivity(ventanaBuscarProducto);
        //System.out.println("El producto es: "+ this.minimarket.obtenerProductoIndice(posicion).getNombre());
    }
}