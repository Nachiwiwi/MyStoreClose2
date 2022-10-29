package com.example.mystoreclose;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import modelo.SesionCliente;
import modelo.SesionMinimarket;

public class IniciarSesionCliente extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {

        RequestQueue rq;
        JsonRequest jrq;
        EditText etuser, etpwd;
        Button btnIniciar;
        private SharedPreferences preference;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_iniciar_sesion_cliente, container, false);
        init(vista);

        //iniciar el preference para almacenar los datos de la clase user cuando inicie sesion.
        preference = getActivity().getSharedPreferences("preference", Context.MODE_PRIVATE);

        //boton para realizar la funcion de inicio sesion.
        btnIniciar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        iniciarsesion();
        }
        });

        return vista;

        }


        @Override
        public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No se encontró el usuario " + error.toString(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResponse(JSONObject response) {
        Toast.makeText(getContext(), "Se encontro el usuario" + etuser.getText().toString(), Toast.LENGTH_SHORT).show();
        //crear clase usuario para acceder y almacenar datos de la consulta php, un array que accede a los datos guardados en el php ("datos")
        // y preference para almacenar los datos mientras dure la sesion.
        SesionCliente minimarket = new SesionCliente();
        SharedPreferences.Editor editor = preference.edit();
        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;
        try {
        jsonObject = jsonArray.getJSONObject(0);
        minimarket.setNombre_Usuario(jsonObject.optString("Nombre_Usuario"));
        minimarket.setNombre(jsonObject.optString("Nombre"));
        minimarket.setContraseña(jsonObject.optString("Contraseña"));
        minimarket.setIdCliente(jsonObject.optInt("IdCliente"));
        minimarket.setCorreo_electronico(jsonObject.optString("Correo_electronico"));
        minimarket.setLatitud(jsonObject.optDouble("Latitud"));
        minimarket.setLongitud(jsonObject.optDouble("Longitud"));
        } catch (Exception e) {
        e.printStackTrace();
        }

        //almacenar el inicio sesion de los datos guardados en la clase(usuario)y guardarlos en editor con un
        // key para mas tarde poder acceder en la aplicacion(ej key: "Nombre")
        editor.putString("Nombre_Usuario", minimarket.getNombre_Usuario());
        editor.putString("Nombre", minimarket.getNombre());
        editor.putString("Contraseña", minimarket.getContraseña());
        editor.putString("Correo_electronico", minimarket.getCorreo_electronico());
        editor.putInt("IdCliente", minimarket.getIdCliente());
        editor.putFloat("Latitud", (float) minimarket.getLatitud());
        editor.putFloat("Longitud", (float) minimarket.getLongitud());
        editor.commit();


        //cambiar pestaña
        Intent intencion = new Intent(getContext(), InicioCliente.class);
        startActivity(intencion);

        }


         private void iniciarsesion() {
        //acceder al php (API) para el inicio sesion, al igual que llamar el metodo que se utilizara(GET).
        String url = "http://192.168.56.1/Android/iniciar_sesion_cliente_v2.php?Nombre_Usuario=" + etuser.getText().toString()+"&Contraseña=" + etpwd.getText().toString();
        jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        rq.add(jrq);
        }


         private void init(View vista) {
        //iniciar y acceder los datos que se obtendran de los edittext.
        etuser = (EditText) vista.findViewById(R.id.etEmail);
        etpwd = (EditText) vista.findViewById(R.id.MKCContraseña);
        btnIniciar = (Button) vista.findViewById(R.id.inicioSesionCli);
        rq = Volley.newRequestQueue(getContext());
        }

}