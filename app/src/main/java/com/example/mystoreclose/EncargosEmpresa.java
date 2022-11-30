package com.example.mystoreclose;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import adapters.AdaptadorEncargosAceptadosMinimarket;
import adapters.AdaptadorEncargosNuevosMinimarket;
import modelo.ConectorBD;
import modelo.EmpresaMinimarket;
import modelo.Pedido;
import recyclerviews.RecyclerViewEncargoAceptadoMinimarketFragment;
import recyclerviews.RecyclerViewEncargoNuevoMinimarketFragment;


public class EncargosEmpresa extends AppCompatActivity {

    //Botones
    ImageButton botonAtras;
    private Button botonPerfil;
    private Button botonInicio;
    private EmpresaMinimarket minimarket;
    // Recycler view pedidos nuevos
    private RecyclerViewEncargoNuevoMinimarketFragment fragmentNuevos;
    private AdaptadorEncargosNuevosMinimarket adapterNuevos;
    // Recycler view pedidos aceptados
    private RecyclerViewEncargoAceptadoMinimarketFragment fragmentAceptados;
    private AdaptadorEncargosAceptadosMinimarket adapterAceptados;
    // Base de datos
    ConectorBD bd;
    // popup
    private AlertDialog.Builder dialogBuilder1;
    private AlertDialog dialog1;
    private AlertDialog.Builder dialogBuilder2;
    private AlertDialog dialog2;
    private Button botonEntregado;
    private Button botonCancelado;
    private Button botonConfirmarSeleccion;
    private Button botonCancelarSeleccion;
    private TextView textoConfirmacionPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encargos_empresa);

        inicializar();
        inicializarRecyclerView();

        //Apretar flecha
        botonAtras = (ImageButton) findViewById(R.id.volverInicio);
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(EncargosEmpresa.this, InicioEmpresa.class);
                startActivity(volver);
            }
        });
        botonPerfil = (Button) findViewById(R.id.perfil);
        botonPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(EncargosEmpresa.this, PerfilEmpresa.class);
                startActivity(volver);
            }
        });
        botonInicio = (Button) findViewById(R.id.productos);
        botonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(EncargosEmpresa.this, InicioEmpresa.class);
                startActivity(volver);
            }
        });
    }

    public void inicializar(){
        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            this.minimarket = (EmpresaMinimarket) bundle.getSerializable("minimarket");
            System.out.println("Buscar Producto, el nombre del minimarket es: "+ this.minimarket.getNombreEmpresa());
        }
        this.bd = new ConectorBD(this.minimarket);
    }

    public void inicializarRecyclerView(){
        FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
        this.fragmentNuevos = new RecyclerViewEncargoNuevoMinimarketFragment();
        this.adapterNuevos = new AdaptadorEncargosNuevosMinimarket(this.minimarket);
        this.fragmentNuevos.setAdapter(adapterNuevos);

        this.adapterNuevos.setItemListener(new AdaptadorEncargosNuevosMinimarket.ItemClickListener() {
            @Override
            public void rechazarPedido(int indexPedido) {
                crearContactDialogRechazarPedido(indexPedido);
            }

            @Override
            public void aceptarPedido(int indexPedido) {
                crearContactDialogAceptarPedido(indexPedido);
            }
        });

        transaction1.replace(R.id.nuevosEncargos, this.fragmentNuevos);
        transaction1.commit();

        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
        this.fragmentAceptados = new RecyclerViewEncargoAceptadoMinimarketFragment();
        this.adapterAceptados = new AdaptadorEncargosAceptadosMinimarket(this.minimarket);
        this.fragmentAceptados.setAdapter(this.adapterAceptados);

        this.adapterAceptados.setItemListener(new AdaptadorEncargosAceptadosMinimarket.ItemClickListener() {
            @Override
            public void modificarEstadoEncargo(int indexEncargo) {
                int estado = minimarket.obtenerPedidoAceptadoPorIndice(indexEncargo).getEstado();
                if ( estado == 3 )
                    crearContactDialogNuevo1(indexEncargo);
            }
        });
        transaction2.replace(R.id.encargosAceptados, this.fragmentAceptados);
        transaction2.commit();
    }

    // popup cambiar estado encargo
    public void crearContactDialogNuevo1(int indexEncargo){
        dialogBuilder1 = new AlertDialog.Builder(this);
        final View contactPopUpView1 = getLayoutInflater().inflate(R.layout.popup_cambiar_estado_encargo,null);

        this.botonCancelado = contactPopUpView1.findViewById(R.id.botonCanceladoPopup);
        this.botonEntregado = contactPopUpView1.findViewById(R.id.botonEntregadoPopup);

        this.botonCancelado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Boton cancelado del popup");
                crearContactDialogNuevo2(indexEncargo, 1);
            }
        });

        this.botonEntregado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Boton entregado del popup");
                crearContactDialogNuevo2(indexEncargo, 2);
            }
        });

        dialogBuilder1.setView(contactPopUpView1);
        dialog1 = dialogBuilder1.create();
        dialog1.show();

    }

    public void crearContactDialogNuevo2(int indexEncargo, int seleccion){
        dialogBuilder2 = new AlertDialog.Builder(this);
        final View contactPopUpView2 = getLayoutInflater().inflate(R.layout.popup_certeza,null);

        this.botonCancelarSeleccion = contactPopUpView2.findViewById(R.id.botonNoPopup);
        this.botonConfirmarSeleccion = contactPopUpView2.findViewById(R.id.botonSiPopup);
        this.textoConfirmacionPopup = contactPopUpView2.findViewById(R.id.mensajeConfirmacionPupup);

        this.textoConfirmacionPopup.setText("¿Desea cambiar el estado del encargo?");


        this.botonCancelarSeleccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog2.dismiss();
                dialog1.dismiss();
            }
        });

        this.botonConfirmarSeleccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pedido p = minimarket.obtenerPedidoAceptadoPorIndice(indexEncargo);
                switch (seleccion){
                    case 1:
                        p.setEstado(4);
                        actualizarEstadoEncargoBD(p.getId(),4);
                        break;

                    case 2:
                        p.setEstado(2);
                        actualizarEstadoEncargoBD(p.getId(),2);
                        break;
                }
                adapterAceptados.notifyDataSetChanged();
                dialog2.dismiss();
                dialog1.dismiss();

            }
        });

        dialogBuilder2.setView(contactPopUpView2);
        dialog2 = dialogBuilder2.create();
        dialog2.show();

    }

    public void crearContactDialogAceptarPedido(int indexEncargo){
        dialogBuilder2 = new AlertDialog.Builder(this);
        final View contactPopUpView2 = getLayoutInflater().inflate(R.layout.popup_certeza,null);

        this.botonCancelarSeleccion = contactPopUpView2.findViewById(R.id.botonNoPopup);
        this.botonConfirmarSeleccion = contactPopUpView2.findViewById(R.id.botonSiPopup);
        this.textoConfirmacionPopup = contactPopUpView2.findViewById(R.id.mensajeConfirmacionPupup);

        this.textoConfirmacionPopup.setText("¿Está seguro que desea aceptar el encargo?");


        this.botonCancelarSeleccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
            }
        });

        this.botonConfirmarSeleccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pedido p = minimarket.obtenerPedidoNuevoPorIndice(indexEncargo);
                p.setEstado(3);
                minimarket.eliminarPedido(p.getId());
                minimarket.agregarPedido(p);
                adapterAceptados.notifyDataSetChanged();
                adapterNuevos.notifyDataSetChanged();
                actualizarEstadoEncargoBD(p.getId(),3);
                dialog2.dismiss();

            }
        });

        dialogBuilder2.setView(contactPopUpView2);
        dialog2 = dialogBuilder2.create();
        dialog2.show();

    }

    public void crearContactDialogRechazarPedido(int indexEncargo){
        dialogBuilder2 = new AlertDialog.Builder(this);
        final View contactPopUpView2 = getLayoutInflater().inflate(R.layout.popup_certeza,null);

        this.botonCancelarSeleccion = contactPopUpView2.findViewById(R.id.botonNoPopup);
        this.botonConfirmarSeleccion = contactPopUpView2.findViewById(R.id.botonSiPopup);
        this.textoConfirmacionPopup = contactPopUpView2.findViewById(R.id.mensajeConfirmacionPupup);

        this.textoConfirmacionPopup.setText("¿Está seguro que desea rechazar el encargo?");


        this.botonCancelarSeleccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
            }
        });

        this.botonConfirmarSeleccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pedido p = minimarket.obtenerPedidoNuevoPorIndice(indexEncargo);
                p.setEstado(0);
                minimarket.eliminarPedido(p.getId());
                minimarket.agregarPedido(p);
                adapterAceptados.notifyDataSetChanged();
                adapterNuevos.notifyDataSetChanged();

                actualizarEstadoEncargoBD(p.getId(),0);
                dialog2.dismiss();

            }
        });

        dialogBuilder2.setView(contactPopUpView2);
        dialog2 = dialogBuilder2.create();
        dialog2.show();

    }

    public void actualizarEstadoEncargoBD(int idEncargo, int estadoNuevo){
        this.bd.actualizarEstadoEncargo(idEncargo,estadoNuevo,this);
    }
}