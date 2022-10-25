package com.example.uni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.ScanMode;

import static com.budiyev.android.codescanner.CodeScanner.ALL_FORMATS;

public class lector_qr1 extends AppCompatActivity {

        private CodeScanner codeScanner;
        private CodeScannerView scannerView;
        private TextView text;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lector_qr1);
            text = findViewById(R.id.text1);
            scannerView = findViewById(R.id.scanner_view);

            int PERMISSION_ALL = 1;
            String[] PERMISSIONS = {
                    Manifest.permission.CAMERA
            };

            if (!hashPermissions(this,PERMISSIONS)){
                ActivityCompat.requestPermissions(this,PERMISSIONS,PERMISSION_ALL);
            }else{
                runCodeScanner();
            }
        }

    public void runCodeScanner(){

            codeScanner = new CodeScanner(this, scannerView);
            codeScanner.setCamera(0);
            codeScanner.setFormats(ALL_FORMATS);
            codeScanner.setScanMode(ScanMode.CONTINUOUS);
            codeScanner.setAutoFocusEnabled(true);
            codeScanner.setDecodeCallback(result -> runOnUiThread(() -> {
                String data = result.getText();

                String dataSe = data.substring(data.indexOf(";")+1,data.lastIndexOf(";"));

                String datapri = data.substring(data.indexOf(";E")+1,data.lastIndexOf("@"));
                String datapri1 = data.substring(data.indexOf(";A")+1,data.lastIndexOf("@"));

                    if (datapri.length() > 20){

                    }else {
                        text.setText(dataSe+" - "+datapri);
                    }

                    if (datapri1.length() > 20) {

                    }else{
                        text.setText(dataSe+" - "+datapri1);
                    }

            }));

            scannerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    codeScanner.startPreview();
                }
            });

    }

    public static boolean hashPermissions(Context context,String... permissions){

            if (context != null && permissions != null){
                for (String permission : permissions){
                    if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED){
                        return false;
                    }
                }
            }
        return true;
    }

    public void manu(View view){

        Intent I = new Intent(this,Manual.class);
        startActivity(I);

    }

}