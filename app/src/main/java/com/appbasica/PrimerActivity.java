package com.appbasica;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PrimerActivity extends AppCompatActivity {
    //variables nesesarias para el registro del usuario ( nombre y contraseña )
    private Button Registrarse;
    EditText usuario, pasword;
    String valor1, valor2;
    Bundle variables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primer);

        //en la creacion registramos los id de los siguientes elementos
        Registrarse = (Button) findViewById(R.id.btnEnviarDatos);
        usuario = (EditText)findViewById(R.id.edtTxtName);
        pasword = (EditText)findViewById(R.id.edtTxtPasword);

        //los metodos OnClick del boton "Registrarse"
        Registrarse.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                enviarDatos();
            }
        });
    }

    public void enviarDatos(){

        //Los datos que ingresamos en los editText los guardaremos en dos variables
        valor1 = usuario.getText().toString();
        valor2 = pasword.getText().toString();

        //Inicializaremos nuestra variable bundle() y le cargaremos los datos de valor1 = "Name" y valor2 = "Pasword"
        variables = new Bundle();
        variables.putString("Name", valor1);
        variables.putString("Pasword", valor2);

        //Crearemos un Intent
        //Al Intent "Registro", le cargaremos los datos ("Name", "Pasword") para utilizarlos en la Activity de destino.
        Intent Registro = new Intent(this, SegundaActivity.class);
        Registro.putExtras(variables);
        startActivity(Registro);
    }




    //Metodo para regresar
    public void anteriorActividad(View v){
        Intent regreso = new Intent(this,MainActivity.class);
        startActivity(regreso);
        finish();
    }

}