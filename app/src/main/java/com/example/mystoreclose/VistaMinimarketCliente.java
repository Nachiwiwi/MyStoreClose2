package com.example.mystoreclose;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import modelo.Cliente;
import modelo.ConectorBD;
import modelo.EmpresaMinimarket;
import modelo.Producto;

public class VistaMinimarketCliente extends AppCompatActivity implements View.OnClickListener{

    TextView nombreMinimarket;
    //Botones
    ImageButton botonAtras;
    private Button botonConfirmar;

    private Button botonEncargos;
    private Button botonPerfil;
    private Button botonInicio;

    private Cliente clienteActual;
    private EmpresaMinimarket minimarketActual;
    private ConectorBD conector;


    private RecyclerViewVistaMinimarketCliente recyclerViewProductosMinimarket;
    private AdaptadorVistaProductosCliente adapterProductos;
    private ArrayList<Producto> listadoProductosSeleccionados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_minimarket_cliente);
        botonConfirmar = (Button) findViewById(R.id.encargar);
        botonConfirmar.setVisibility(View.INVISIBLE);
        botonConfirmar.setEnabled(false);
        //tomar los datos del minimarket
        inicializar();
        System.out.println(this.minimarketActual.getNombreMinimarket());
        this.nombreMinimarket = (TextView) findViewById(R.id.labelNombreProducto);
        this.nombreMinimarket.setText(this.minimarketActual.getNombreMinimarket());

        try {
            listadoProductosSeleccionados = inicializarRecyclerView(this.botonConfirmar, listadoProductosSeleccionados);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


        //System.out.println(minimarketActual.obtenerCantidadDeProductos());
        //Apretar flecha
        botonAtras = (ImageButton) findViewById(R.id.volverInicio);
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(VistaMinimarketCliente.this, InicioCliente.class);
                startActivity(volver);
            }
        });

        //Apretar botón confirmar encargo


        botonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent confirmar = new Intent(VistaMinimarketCliente.this, EncargosCliente.class );
                Bundle bundleListaProductos = new Bundle();
                Bundle bundleCliente = new Bundle();
                Bundle bundleMinimarket = new Bundle();
                bundleListaProductos.putSerializable("listadoProductos", listadoProductosSeleccionados);
                bundleCliente.putSerializable("cliente", clienteActual);
                bundleMinimarket.putSerializable("minimarket",minimarketActual);
                confirmar.putExtras(bundleListaProductos);
                confirmar.putExtras(bundleCliente);
                confirmar.putExtras(bundleMinimarket);
                startActivity(confirmar);
            }
        });
        botonEncargos = (Button) findViewById(R.id.encargos1);
        botonEncargos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(VistaMinimarketCliente.this, EncargosCliente.class);
                startActivity(volver);
            }
        });
        botonPerfil = (Button) findViewById(R.id.perfil);
        botonPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(VistaMinimarketCliente.this, PerfilCliente.class);
                startActivity(volver);
            }
        });
        botonInicio = (Button) findViewById(R.id.productos);
        botonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(VistaMinimarketCliente.this, InicioCliente.class);
                startActivity(volver);
            }
        });
        obtenerDatosDB();
        //System.out.println("El tamaño de la coleccion es: "+this.minimarketActual.obtenerCantidadDeProductos());
    }

    private void inicializar() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            this.minimarketActual = (EmpresaMinimarket) bundle.getSerializable("minimarket");
            this.clienteActual = (Cliente) bundle.getSerializable("cliente");
            //System.out.println(this.clienteActual.getNombre());
            this.conector = new ConectorBD(this.minimarketActual);
        }

    }

    private ArrayList<Producto> inicializarRecyclerView(Button botonConfirmar,ArrayList<Producto> listadoProductosSeleccionados) throws CloneNotSupportedException{
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        this.recyclerViewProductosMinimarket = new RecyclerViewVistaMinimarketCliente();
        this.adapterProductos = new AdaptadorVistaProductosCliente(this.minimarketActual);
        this.recyclerViewProductosMinimarket.setAdapter(this.adapterProductos);

        transaction.replace(R.id.fragmentoVistaMinimarketCliente,this.recyclerViewProductosMinimarket);
        transaction.commit();

        this.adapterProductos.setItemListener(new AdaptadorVistaProductosCliente.ItemClickListener() {
            @Override
            public void activarBoton(Producto producto) {
                botonConfirmar.setEnabled(true);
                listadoProductosSeleccionados.add(producto);
                createInicioDialogEncargar();
                /*for(int i = 0; i < listadoProductosSeleccionados.size();i++){
                    System.out.println(listadoProductosSeleccionados.get(i).getNombre());
                }*/
            }
        });
        //falta colocar los botones para encargar
        return listadoProductosSeleccionados;
    }

    private void obtenerDatosDB(){

        this.conector.setMinimarket(this.minimarketActual);
        this.conector.obtenerProductos(this, this.adapterProductos);
        System.out.println("La cosa tiene: " + this.minimarketActual.obtenerCantidadDeProductos());
        this.adapterProductos.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {

    }

    public void verInformacionMinimarket(ArrayList<Producto> listadoProductosSeleccionados){
        System.out.println("Tamaño arrayList: "+listadoProductosSeleccionados.size());

        Intent ventanaVerMinimarket = new Intent(this, EncargosCliente.class );
        Bundle bundle = new Bundle();
        bundle.putSerializable("listadoProductos", listadoProductosSeleccionados);
        ventanaVerMinimarket.putExtras(bundle);
        startActivity(ventanaVerMinimarket);

    }
    public void createInicioDialogEncargar(){
        AlertDialog.Builder encargarPopup = new AlertDialog.Builder(VistaMinimarketCliente.this);
        LayoutInflater pestaña = getLayoutInflater();
        View view = pestaña.inflate(R.layout.encargar_popup, null);
        encargarPopup.setView(view);
        final AlertDialog dialog = encargarPopup.create();
        dialog.show();

        Button encargar = view.findViewById(R.id.botonEncargar);
        encargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ventanaEncargos = new Intent(VistaMinimarketCliente.this,EncargosCliente.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("listadoProductos", listadoProductosSeleccionados);
                ventanaEncargos.putExtras(bundle);
                startActivity(ventanaEncargos);
            }
        });

        Button cancelar = view.findViewById(R.id.botonCancelar);
        encargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ventanaEncargos = new Intent(VistaMinimarketCliente.this,EncargosCliente.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("listadoProductos", listadoProductosSeleccionados);
                ventanaEncargos.putExtras(bundle);
                startActivity(ventanaEncargos);
            }
        });
    }
}