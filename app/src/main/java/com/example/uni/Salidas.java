package com.example.uni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class Salidas extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salidas);

    }

    public void control(View view){

        Intent I = new Intent(this,Manual.class);
        startActivity(I);

    }

    public void control1(View view){

        Intent I = new Intent(this,menu.class);
        startActivity(I);

    }

}