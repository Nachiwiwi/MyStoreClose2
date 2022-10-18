package com.example.mystoreclose;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class RegistroCliente extends AppCompatActivity implements View.OnClickListener{
    EditText etNameUser, etPassword, etEmail, etName;
    Button btnRegistrar;

    RequestQueue requestQueue;
    //ip debe ser del pc (ipconfig en el cmd para ver la ip)/ la carpeta ubicada en el xampp (android en este caso)/ el archivo php (agregar_cliente.php)
    //ip original 192.168.178.246
    private static final String URL1= "http://192.168.56.1/Android/agregar_minimarket.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_cliente);
        requestQueue = Volley.newRequestQueue(this);
        initUI();
        btnRegistrar.setOnClickListener(this);
    }

    private void initUI(){

        etName=findViewById(R.id.etName);
        etNameUser=findViewById(R.id.etNameUser);
        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);

        //buttons
        btnRegistrar=findViewById(R.id.btnIniciarSesionEmpresa);
    }

    @Override
    public void onClick(View v){
        int id=v.getId();

        if (id == R.id.btnIniciarSesionEmpresa){
            String name= etName.getText().toString().trim();
            String email=etEmail.getText().toString().trim();
            String password=etPassword.getText().toString().trim();
            String nameUser=etNameUser.getText().toString().trim();
            createClient(name,nameUser,password,email);
        }
    }

    private void createClient(final String name, final String nameUser, final String password, final String email) {
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
}



