package com.example.uni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Manual extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);


    }

    public void control(View view){

        Intent I = new Intent(this,Prub.class);
        startActivity(I);

    }

    public void control1(View view){

        Intent I = new Intent(this,Salidas.class);
        startActivity(I);

    }

}