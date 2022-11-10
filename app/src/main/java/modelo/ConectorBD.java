package modelo;

import android.content.Context;
import android.net.wifi.WifiManager;
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
import com.example.mystoreclose.AdaptadorAgregarProductos;
import com.example.mystoreclose.AdaptadorProductos;
import com.example.mystoreclose.AgregarProducto;
import com.example.mystoreclose.BuscarProductoCliente;
import com.example.mystoreclose.IniciarSesionCliente;
import com.example.mystoreclose.IniciarSesionClienteVentana;
import com.example.mystoreclose.IniciarSesionMinimarketVentana;
import com.example.mystoreclose.InicioCliente;
import com.example.mystoreclose.InicioEmpresa;
import com.example.mystoreclose.RegistroCliente;
import com.example.mystoreclose.RegistroMinimarket;
import com.example.mystoreclose.VerProducto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class ConectorBD {
    EmpresaMinimarket minimarket;

    //String url = "http://10.8.226.244/Android/";
    String url = "http://192.168.1.102/Android/";
    private JsonRequest jsR;
    StringRequest sR;

    public ConectorBD(EmpresaMinimarket minimarket){
        this.minimarket = minimarket;
    }

    public void agregarProducto(int idEmpresa, int idP, String descripcion, String precio, String imagen, int idProducto, AgregarProducto agregarProducto){

        imagen = "Imagen Producto "+ imagen;
        RequestQueue requestQueue= Volley.newRequestQueue(agregarProducto);
        String dir = url + "post_relmarkprod.php";//?PrecioUnitario="+precio+"&Descripcion="+descripcion+"&IdMarket="+idEmpresa+ "&Imagen=imagen del producto&IdProducto="+idProducto;

        sR =new StringRequest(
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

        requestQueue.add(sR);
    }

    public void obtenerProductosLista(ColeccionProductos c, AgregarProducto agregarProducto,AdaptadorAgregarProductos adaptador){
        //getProductos.php

        RequestQueue requestQueue = Volley.newRequestQueue(agregarProducto);
        jsR = new JsonObjectRequest(Request.Method.GET, this.url+"getProductos.php", null, new Response.Listener<JSONObject>(){

            @Override
            public void onResponse(JSONObject response){
                try {
                    Producto p;
                    JSONArray productosJSON = response.getJSONArray("Productos");
                    for(int i = 0; i < productosJSON.length(); i++){
                        JSONObject pupi = productosJSON.getJSONObject(i);
                        p = new Producto( new String(pupi.getString("Nombre")) ,Integer.parseInt(pupi.getString("IdProducto")) );
                        c.agregarProducto(p);

                        System.out.println("El nombre es: " + p.getNombre()+ " id: "+p.getId());
                    }

                    System.out.println("Fiumbiu");
                    adaptador.notifyDataSetChanged();

                }catch (JSONException e){
                    //Toast.makeText(AgregarProducto.this, e.toString(), Toast.LENGTH_SHORT).show();
                }


            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                System.out.println("Error");
            }
        });

        requestQueue.add(jsR);
    }

    public void obtenerOfertasProductos(InicioEmpresa inicioEmpresa){
        String dir = this.url + "getProdConOff.php?Nombre_empresa="+ this.minimarket.getNombreEmpresa();
        RequestQueue requestQueue = Volley.newRequestQueue(inicioEmpresa);
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

        requestQueue.add(jsR);
        //new JsonObjectRequest(Request.Method.GET, dir, null, this,this)

        //rQ.add(jsR);
    }

    public void obtenerProductos(InicioEmpresa contexto, AdaptadorProductos adaptadorProductos){

        RequestQueue requestQueue = Volley.newRequestQueue(contexto);
        System.out.println(this.minimarket.getNombreEmpresa());
        String dir = this.url + "getPM.php?Nombre_empresa=" + this.minimarket.getNombreEmpresa();
        //String dir ="http://192.168.1.102/Android/getPM.php?Nombre_empresa=COFFE MASTER";
        System.out.println(dir);
        jsR = new JsonObjectRequest(Request.Method.GET, dir, null, new Response.Listener<JSONObject>() {
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
                        agregarPM(p);
                        System.out.println("El nombre es: " + pupi.getString("Nombre")
                                + " El precio es: "+ pupi.getString("PrecioUnitario"));
                    }

                    adaptadorProductos.notifyDataSetChanged();

                }catch (JSONException e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsR);
    }

    // Clase VerProducto
    public void modificarProductoEmpresa(String idRelacion, String precioUnitario, String descripcion, String imagen, VerProducto verProducto){
        String dir = this.url + "putRelmarkprod.php";
        RequestQueue requestQueue = Volley.newRequestQueue(verProducto);

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
                params.put("IdRel",idRelacion);
                params.put("PrecioUnitario",precioUnitario);
                params.put("Descripcion",descripcion);
                params.put("Imagen", imagen);
                return params;

            }
        };

        requestQueue.add(stringRequest);

    }

    public void eliminarProductoEmpresa(String idRel, VerProducto verProducto){
        String dir = this.url + "deleteRelmarkprod.php";
        RequestQueue requestQueue = Volley.newRequestQueue(verProducto);

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
                params.put("IdRel",idRel);
                return params;

            }
        };

        requestQueue.add(stringRequest);
    }

    public void crearOferta(String idRelacion, String precioOferta, int duracion, VerProducto verProducto){
        String dir = this.url + "postOferta.php";
        RequestQueue requestQueue = Volley.newRequestQueue(verProducto);

        Calendar dataFinal = Calendar.getInstance( );
        dataFinal.add(Calendar.DAY_OF_YEAR,duracion);

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        String fechaTerminal = formato.format(dataFinal.getTime());
        String fechaInicial = formato.format(calendar.getTime());

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
                params.put("IdRel",idRelacion);
                params.put("PrecioOferta",precioOferta);
                params.put("FechaInicio",fechaInicial);
                params.put("FechaTermino",fechaTerminal);
                return params;

            }
        };
        requestQueue.add(stringRequest);
    }

    public void eliminarOferta(String idRelacion, VerProducto verProducto){
        String dir = this.url + "deleteOferta.php";
        RequestQueue requestQueue = Volley.newRequestQueue(verProducto);

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
                params.put("IdRel",idRelacion);
                return params;

            }
        };
        requestQueue.add(stringRequest);
    }

    public void modificarOferta(String idRelacion, String precioOferta, int duracion, VerProducto verProducto){

        String dir =  this.url + "putModificarOff.php";
        RequestQueue requestQueue = Volley.newRequestQueue(verProducto);

        Calendar dataFinal = Calendar.getInstance( );
        dataFinal.add(Calendar.DAY_OF_YEAR,duracion);

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        String fechaTerminal = formato.format(dataFinal.getTime());
        String fechaInicial = formato.format(calendar.getTime());

        //System.out.println( this.formato.format(this.calendar.getTime())+ " "+ this.formato.format(dataFinal.getTime()));

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
                params.put("IdRel",idRelacion);
                params.put("PrecioOferta",precioOferta);
                params.put("FechaInicio",fechaInicial);
                params.put("FechaTermino",fechaTerminal);
                return params;

            }
        };

        requestQueue.add(stringRequest);
    }


    public void crearCliente(String name, String nameUser, String password, String email, RegistroCliente registroCliente){
        String dir =  this.url + "agregar_cliente.php";
        RequestQueue requestQueue = Volley.newRequestQueue(registroCliente);

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
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("Nombre",name);
                params.put("Contraseña",password);
                params.put("Correo_electronico",email);
                params.put("Nombre_Usuario",nameUser);
                return params;

            }
        };

        requestQueue.add(stringRequest);
    }

    public void crearMinimarket(String Rut, String NombreEmp, String Direccion, String NombreLoc, String Correo, String contrasenia, RegistroMinimarket registroMinimarket){
        String dir =  this.url + "agregar_minimarket.php";
        RequestQueue requestQueue = Volley.newRequestQueue(registroMinimarket);

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
                params.put("Rut_empresa",Rut);
                params.put("ContraseñaDueño",contrasenia);
                params.put("MailDueño",Correo);
                params.put("Nombre_local",NombreLoc);
                params.put("Nombre_empresa",NombreEmp);
                params.put("Direccion",Direccion);
                return params;

            }
        };
        requestQueue.add(stringRequest);
    }

    public void obtenerCliente(String nombreUsuario, InicioCliente inicioCliente){
        /*

        String dir = this.url + "perfil_usuario.php?Nombre_Usuario="+ nombreUsuario;
        RequestQueue requestQueue = Volley.newRequestQueue(inicioCliente);
        Cliente cliente;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, dir, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Producto p;
                    JSONArray productosJSON = response.getJSONArray("awebo");

                    JSONObject usuarioBuscado = productosJSON.getJSONObject(0);
                    String idCliente = usuarioBuscado.getString("IdCliente");
                    String nombre = usuarioBuscado.getString("Nombre");
                    String nombreUsuario = usuarioBuscado.getString("Nombre_Usuario");
                    String correo = usuarioBuscado.getString("Correo_electronico");
                    String clave = usuarioBuscado.getString("Contraseña");
                    System.out.println("La frase magica es: "+ correo);

                    cliente = new Cliente(idCliente, nombre, nombreUsuario, correo, clave);
                    setPosicion();


                }catch (JSONException e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);

        */

    }

    // Incompleto
    public void obtenerDatosClienteInicioSesion(String nombreUsuario, String password, IniciarSesionClienteVentana inicioSesion){
        RequestQueue requestQueue = Volley.newRequestQueue(inicioSesion);

        String dir = this.url + "iniciar_sesion_cliente.php?Nombre_Usuario=" + nombreUsuario+"&Contraseña=" + password;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, dir, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue.add(jsonObjectRequest);


    }

    // Incompleto
    public void obtenerDatosMinimarketInicioSesion(String rutEmpresa, String password, IniciarSesionMinimarketVentana inicioSesionM){
        RequestQueue requestQueue = Volley.newRequestQueue(inicioSesionM);

        String dir = this.url + "iniciar_sesion_empresa.php?Rut_empresa=" + rutEmpresa+"&ContraseñaDueño=" + password;;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, dir, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);

    }

    // incompleto
    public void obtenerListaProductos(BuscarProductoCliente buscarProductoCliente, ArrayList listadoProductos){
        String dir = this.url + "Producto_Lista.php";
        RequestQueue requestQueue = Volley.newRequestQueue(buscarProductoCliente);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, dir, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("productos");
                    for(int i = 0 ; i<array.length(); i++){
                        JSONObject object = array.getJSONObject(i);
                        int idProducto = Integer.parseInt(object.getString("IdProducto"));
                        String nombre = new String(object.getString("Nombre"));
                        //System.out.println("Nombre: "+Nombre);
                        Producto producto = new Producto(nombre, idProducto);
                        listadoProductos.add(producto);

                    }

                    System.out.println(listadoProductos.size());
                    System.out.println("xdddd");

                    // simular esto
                    //inicializarRecyclerView();
                }catch (JSONException e){

                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue.add(jsonObjectRequest);

    }










    
    public void setMinimarket(EmpresaMinimarket e){
        this.minimarket = e;
    }


    public void agregarPM(Producto p){
        this.minimarket.agregarProducto(p);
    }

    public void asignarOferta(int idP, Oferta oferta){
        if(this.minimarket.obtenerProducto(idP) != null) {
            this.minimarket.obtenerProducto(idP).setOferta(oferta);
        }
    }

}
