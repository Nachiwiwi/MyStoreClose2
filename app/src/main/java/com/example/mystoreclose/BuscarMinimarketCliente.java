package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.*;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;
import modelo.Direccion;
import modelo.EmpresaMinimarket;

public class BuscarMinimarketCliente extends AppCompatActivity{

    //Botones
    ImageButton botonAtras;

    public static ArrayList<EmpresaMinimarket> arrayList= new ArrayList<>();
    public EmpresaMinimarket empresaMinimarket;
    RecyclerView.LayoutManager RecyclerViewListadoMinimarketsCercanos;
    RecyclerView.Adapter mAdapter;
    TextView textViewDatosMinimarket;
    private ArrayList<EmpresaMinimarket> listadoMinimarkets;
    private RequestQueue queue;
    private Cliente clienteActual;
   // private EmpresaMinimarket arrayMinimarkets[] = new EmpresaMinimarket[listadoMinimarkets.size()];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_minimarket_cliente);
        queue = Volley.newRequestQueue(this);
        inicializar();
        readerJSon();

        //Apretar flecha
        botonAtras = (ImageButton) findViewById(R.id.volverInicio);
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(BuscarMinimarketCliente.this, InicioCliente.class);
                startActivity(volver);
            }
        });


        queue = Volley.newRequestQueue(this);




        mostrarMinimarkets();
    }

    private void inicializar() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            this.clienteActual = (Cliente) bundle.getSerializable("cliente");
            System.out.println(this.clienteActual.getNombre());
        }
    }

    public void mostrarMinimarkets(){
        //Toast.makeText(BuscarMinimarketCliente.this, listadoMinimarkets.get(1).getNombreMinimarket(), Toast.LENGTH_SHORT).show();

    }
    private void readerJSon() {

        String URL1= "http://192.168.0.4/Android/metodoGET.php?";
        StringRequest request = new StringRequest(Request.Method.GET, URL1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<EmpresaMinimarket> listadoMinimarkets = new ArrayList<>();
                try {
                    JSONArray array = new JSONArray(response);
                    for(int i = 0 ; i<array.length(); i++){
                        JSONObject object = array.getJSONObject(i);
                        int idMinimarket = object.getInt("IdMarket");
                        String nombreEmpresa = object.getString("Nombre_empresa");
                        String nombreMinimarket = object.getString("Nombre_local");
                        String direccion = object.getString("Direccion");
                        String rutEmpresa = object.getString("Rut_empresa");
                        String contraseñaDueño = object.getString("ContraseñaDueño");
                        String mailLocal = object.getString("MailDueño");
                        double longitud = object.getDouble("Longitud");
                        double latitud = object.getDouble("Latitud");
                        //System.out.println("longitud: "+longitud+"latitud: "+latitud);
                        EmpresaMinimarket nuevaEmpresa = new EmpresaMinimarket(idMinimarket, nombreEmpresa, nombreMinimarket, direccion, rutEmpresa,contraseñaDueño,mailLocal,latitud,longitud);
                        listadoMinimarkets.add(nuevaEmpresa);
                        //System.out.println("longitud: " + nuevaEmpresa.getPosicion().getLongitud()+ "latitud: "+ nuevaEmpresa.getPosicion().getLatitud());
                    }
                    listadoMinimarkets = ordenarListadoPorDistancia(clienteActual, listadoMinimarkets);
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    //System.out.println("Latitud: " +clienteActual.getPosicionActual().getLatitud()+ "Longitud: "+ clienteActual.getPosicionActual().getLongitud());
                    RecyclerViewMinimarketFragment fragment = new RecyclerViewMinimarketFragment();
                    fragment.setColeccion(listadoMinimarkets);
                    // Antes salia R.id.recycleViewBuscarMinimarket, pero no funcionaba
                    transaction.replace(R.id.buscarMiniCli, fragment);
                    transaction.commit();

                }catch (JSONException e){
                    Toast.makeText(BuscarMinimarketCliente.this, "2xd:", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BuscarMinimarketCliente.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        this.queue.add(request);
    }

    public ArrayList<EmpresaMinimarket> ordenarListadoPorDistancia(Cliente clienteActual, ArrayList<EmpresaMinimarket> listadoMinimarkets){
        EmpresaMinimarket arregloMinimarkets[] = new EmpresaMinimarket[listadoMinimarkets.size()];
        for(int i = 0; i < listadoMinimarkets.size(); i++){
            arregloMinimarkets[i] = listadoMinimarkets.get(i);
            int distanciaTotal = calculoDistancia(clienteActual.getPosicionActual(), listadoMinimarkets.get(i).getPosicion());
            arregloMinimarkets[i].setDistanciaRespectoUsuario(distanciaTotal);
            System.out.println("la distancia es de: " + distanciaTotal);
        }

        quickSort(arregloMinimarkets, 0, listadoMinimarkets.size()-1);
        listadoMinimarkets.clear();
        for(int i = 0 ; i< arregloMinimarkets.length; i++){
            listadoMinimarkets.add(arregloMinimarkets[i]);
            System.out.println("la distancia ordenada es: "+arregloMinimarkets[i].getDistanciaRespectoUsuario());
            System.out.println("nombre: " +listadoMinimarkets.get(i).getNombreEmpresa());
            System.out.println("Distancia Empresa: "+listadoMinimarkets.get(i).getDistanciaRespectoUsuario());
        }
        return listadoMinimarkets;
    }

    public int calculoDistancia(Direccion posicionCliente, Direccion posicionMinimarket){
        double distanciaTotal = 0;
        final float radioTierraKm = 6378.0F;
        double difLatitud = (posicionCliente.getLatitud() - posicionMinimarket.getLatitud()) * (Math.PI / 180);
        double difLongitud = (posicionCliente.getLongitud() -posicionMinimarket.getLongitud())* (Math.PI / 180);

        distanciaTotal = (Math.pow(Math.sin(difLatitud/2),2)) + Math.cos(posicionMinimarket.getLatitud()* (Math.PI / 180)) * Math.cos(posicionCliente.getLatitud()* (Math.PI / 180)) * (Math.pow(Math.sin(difLongitud/2),2));
        distanciaTotal = 2 * Math.atan2(Math.sqrt(distanciaTotal), Math.sqrt(1 - distanciaTotal));
        distanciaTotal = distanciaTotal * radioTierraKm;
        return (int)distanciaTotal;
    }
    private int partition(EmpresaMinimarket arrayMinimarkets[], int begin, int end) {
        int pivot = arrayMinimarkets[end].getDistanciaRespectoUsuario();
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (arrayMinimarkets[j].getDistanciaRespectoUsuario() <= pivot) {
                i++;

                EmpresaMinimarket swapTemp = arrayMinimarkets[i];
                arrayMinimarkets[i] = arrayMinimarkets[j];
                arrayMinimarkets[j] = swapTemp;
            }
        }

        EmpresaMinimarket swapTemp = arrayMinimarkets[i+1];
        arrayMinimarkets[i+1] = arrayMinimarkets[end];
        arrayMinimarkets[end] = swapTemp;

        return i+1;
    }

    public void quickSort(EmpresaMinimarket arrayMinimarkets[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arrayMinimarkets, begin, end);

            quickSort(arrayMinimarkets, begin, partitionIndex-1);
            quickSort(arrayMinimarkets, partitionIndex+1, end);
        }
    }
}
