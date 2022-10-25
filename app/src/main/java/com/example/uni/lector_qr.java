package com.example.uni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class lector_qr extends AppCompatActivity {

    Button btn_scanner;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lector_qr);

        btn_scanner = findViewById(R.id.btn_scanner);
        text = findViewById(R.id.textCode);

        btn_scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(lector_qr.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Lector - QR"); // leyenda del lector
                integrator.setCameraId(0); //0 camara trasera //1 camara delantera
                integrator.setBeepEnabled(true); //sonido al scannear
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan(); // iniciar
            }
        });

    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null){
           if (result.getContents() == null){

               Toast.makeText(this,"Lectura cancelada",Toast.LENGTH_LONG).show();

           }else{

               Toast.makeText(this,result.getContents(),Toast.LENGTH_LONG).show();
               text.setText(result.getContents());

           }
        }else{

            super.onActivityResult(requestCode, resultCode, data);

        }

    }

}