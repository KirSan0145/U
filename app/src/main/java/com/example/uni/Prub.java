package com.example.uni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Prub extends AppCompatActivity {

    private EditText t1,t2,t3;
    private CheckBox cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prub);

        Spinner sp = (Spinner) findViewById(R.id.sp1);
        t1 = (EditText) findViewById(R.id.t1);
        t2 = (EditText) findViewById(R.id.t2);
        t3 = (EditText) findViewById(R.id.t3);
        cb = (CheckBox) findViewById(R.id.cb1);

        t2.setEnabled(false);

        String[] dato = {"Seleccione: ","1. Carro","2. Moto","3. Bicicleta"};
        sp.setAdapter(new ArrayAdapter<String>(this, R.layout.disenospinner, dato));
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {
                Toast.makeText(adapterView.getContext(), (String) adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // vacio

            }
        });


    }

    public void itemClicked(View v) {

        if(cb.isChecked()){
            t2.setEnabled(true);
        }else{
            t2.setEnabled(false);
        }
    }

    public void control(View view){

        Intent I = new Intent(this,menu.class);
        startActivity(I);

    }

}