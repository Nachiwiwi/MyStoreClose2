package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegistroMinimarket extends AppCompatActivity implements View.OnClickListener{

    EditText MKRut, MKNombreEmp, MKDireccion, MKNombreLoc, MKCorreo, MKcontrasenia, MKCContraseña;
    Button btnRegistrar;

    RequestQueue requestQueue;
    //ip debe ser del pc (ipconfig en el cmd para ver la ip)/ la carpeta ubicada en el xampp (android en este caso)/ el archivo php (agregar_cliente.php)
    private static final String URL1= "http://192.168.1.102/Android/agregar_minimarket.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_minimarket);
        requestQueue = Volley.newRequestQueue(this);
        initUI();
        btnRegistrar.setOnClickListener(this);
    }

    private void initUI(){

        MKRut=findViewById(R.id.etName);
        MKNombreEmp=findViewById(R.id.etNameUser);
        MKDireccion=findViewById(R.id.MKDireccion);
        MKNombreLoc=findViewById(R.id.MKNombreLoc);
        MKCorreo=findViewById(R.id.etEmail);
        MKcontrasenia=findViewById(R.id.etPassword);
        MKCContraseña=findViewById(R.id.MKCContraseña);
        //buttons
        btnRegistrar=findViewById(R.id.registroEmpr);

    }

    @Override
    public void onClick(View v){
        int id=v.getId();

        if (id == R.id.btnIniciarSesionEmpresa){
            String Rut= MKRut.getText().toString().trim();
            String NombreEmp=MKNombreEmp.getText().toString().trim();
            String Direccion=MKDireccion.getText().toString().trim();
            String NombreLoc=MKNombreLoc.getText().toString().trim();
            String Correo= MKCorreo.getText().toString().trim();
            String contrasenia=MKcontrasenia.getText().toString().trim();
            createMinimarket(Rut,NombreEmp,Direccion,NombreLoc,Correo,contrasenia);
        }

    }

    private void createMinimarket(final String Rut,final String NombreEmp,final String Direccion, final String NombreLoc, final String Correo, final String contrasenia) {
        StringRequest stringRequest =new StringRequest(
                Request.Method.POST,
                URL1,
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
}