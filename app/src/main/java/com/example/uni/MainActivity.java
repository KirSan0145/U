package com.example.uni;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    private EditText admincreds,adminpass;
    private Button btnlogin;
    String admis,contras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        admincreds = (EditText) findViewById(R.id.admincred);
        adminpass = (EditText) findViewById(R.id.adminpass);
        btnlogin = (Button) findViewById(R.id.login);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),menu.class);
                startActivity(i);

            }
        });

        /*
        verificarpermisos();

        admis = admincreds.getText().toString();
        contras = adminpass.getText().toString();

        recuperarPreferencias();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!admis.isEmpty() && contras.isEmpty()) {
                    validaradmin("http://192.168.1.109/Uappli/validarAdmin.php");
                }else{
                    Toast.makeText(MainActivity.this,"Campos vacíos",Toast.LENGTH_LONG).show();
                }
            }
        });*/




    }

    private void verificarpermisos() {

        int permisosinternet = ContextCompat.checkSelfPermission(this, INTERNET);

        if (permisosinternet == PackageManager.PERMISSION_GRANTED) {
            //Toast.makeText(this, "Permisos internet", Toast.LENGTH_LONG).show();
        } else {
            requestPermissions(new String[]{INTERNET}, 200);
        }
    }

        private  void validaradmin(String url){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()){
                    guardarPreferencias();
                    Intent i = new Intent(getApplicationContext(),menu.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(MainActivity.this,"Usuario o contraseña incorrectas",Toast.LENGTH_LONG).show();
                }
            }
        }, error -> Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show()){
            @NotNull
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("admin",admis);
                parametros.put("pass",contras);


                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void guardarPreferencias(){
        SharedPreferences preferences = getSharedPreferences("PreferenciasLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("admin",admis);
        editor.putString("contraseña",contras);
        editor.putBoolean("sesion",true);
        editor.commit();
    }

    private void recuperarPreferencias(){
        SharedPreferences preferences=getSharedPreferences("PreferenciasLogin",Context.MODE_PRIVATE);
        admincreds.setText(preferences.getString("admin",""));
        adminpass.setText(preferences.getString("contraseña",""));
    }

}