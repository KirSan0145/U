package com.example.uni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class Historial extends AppCompatActivity {

    static List<Usuario> listaUsuarios = new ArrayList<>();

    private Button btnGenerar;
    private EditText etTexto;

    private final String stringFilePath = Environment.getExternalStorageDirectory().getPath() + "/Download/ProgrammerWorld.pdf";
    private final File file = new File(stringFilePath);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);



        etTexto = findViewById(R.id.gett1);
        btnGenerar = findViewById(R.id.desc);

        listaUsuarios.add(new Usuario("Santiago","Chaves Socha","1007694911",
                "3228247560","Estudiante","CARRO","BNL032","8:50",
                "null","Entrada","Edwardo"));

        //verificarpermisos();

        btnGenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearPDF();
            }
        });

    }

    private void verificarpermisos(){

        int permisoswrite = ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE);
        int permisosread = ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE);

        if (permisoswrite == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Permisos write A",Toast.LENGTH_LONG).show();
            if (permisosread == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permisos read A",Toast.LENGTH_LONG).show();
            }else{
                requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,
                        READ_EXTERNAL_STORAGE},200);
            }
        }

    }
    public static void crearPDF() {
        try {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Uappli";

            File dir = new File(path);
            if(!dir.exists()) {
                dir.mkdirs();
            }

            File file = new File(dir, "Registros.pdf");
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            Document documento = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(documento, fileOutputStream);

            documento.open();

            Paragraph titulo = new Paragraph(
                    "Lista de Registros \n\n\n",
                    FontFactory.getFont("arial", 22, Font.BOLD, BaseColor.BLUE)
            );

            documento.add(titulo);

            PdfPTable tabla = new PdfPTable(11);
            tabla.setWidthPercentage(100);
            tabla.setSpacingBefore(0f);
            tabla.setSpacingAfter(0f);

            tabla.addCell("Nombre");
            tabla.addCell("Apellido");
            tabla.addCell("Cedula");
            tabla.addCell("Telefono");
            tabla.addCell("Rol");
            tabla.addCell("Tipo de vehiculo");
            tabla.addCell("Placa");
            tabla.addCell("Entrada");
            tabla.addCell("Salida");
            tabla.addCell("Estado");
            tabla.addCell("Admin");

            for (int i = 0 ; i < listaUsuarios.size() ; i++) {
                tabla.addCell(listaUsuarios.get(i).nombre);
                tabla.addCell(listaUsuarios.get(i).apellido);
                tabla.addCell(listaUsuarios.get(i).cedula);
                tabla.addCell(listaUsuarios.get(i).telefono);
                tabla.addCell(listaUsuarios.get(i).rol);
                tabla.addCell(listaUsuarios.get(i).tVehiculo);
                tabla.addCell(listaUsuarios.get(i).placa);
                tabla.addCell(listaUsuarios.get(i).Entrada);
                tabla.addCell(listaUsuarios.get(i).Salida);
                tabla.addCell(listaUsuarios.get(i).Estado);
                tabla.addCell(listaUsuarios.get(i).Admin);
            }

            documento.add(tabla);

            documento.close();


        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
    }

    private boolean checkPermission() {
        int permission1 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int permission2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, 200);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 200) {
            if(grantResults.length > 0) {
                boolean writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if(writeStorage && readStorage) {
                    Toast.makeText(this, "Permiso concedido", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Permiso denegado", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        }
    }

}