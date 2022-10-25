package com.example.uni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menu extends AppCompatActivity {

    private Button btnCerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        btnCerrar = (Button) findViewById(R.id.cerrar);

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*SharedPreferences preferences = getSharedPreferences("PreferenciasLogin", Context.MODE_PRIVATE);
                preferences.edit().clear().apply();*/
                Intent I = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(I);
                finish();
            }
        });

    }

    public void control(View view){

        Intent I = new Intent(this,lector_qr1.class);
        startActivity(I);

    }

    public void hist(View view){

        Intent I = new Intent(this,Historial.class);
        startActivity(I);

    }

}