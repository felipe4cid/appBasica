package com.appbasica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.appbasica.db.DbHelper;

public class SegundaActivity extends AppCompatActivity {

    //variables para mostrar los datos recibidos de la primer activity
    TextView datos;
    public String nombre;
    private String contraseña;
    Bundle variablesRecibidas;


    //elementos para la notificacion emergente (extra)
    PendingIntent pendingIntent;
    private final static String CHANNEL_ID ="Notificacion";
    private final static int NOTIFICACION_ID = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        datos = (TextView) findViewById(R.id.txtDatos);

        //crearemos un bundle que contendra los elementos recibidos "Name" y "Pasword"
        variablesRecibidas = getIntent().getExtras();

        //guardaresmos los datos que contiene el bundle creado anteriormente
        nombre = variablesRecibidas.getString("Name");
        contraseña = variablesRecibidas.getString("Pasword");
        Toast mensaje;

        //Metodo para la validaciones de la contraseña
        if (contraseña.equals("")) {
            mensaje = Toast.makeText(getApplicationContext(), "Ingrese una contraseña valida", Toast.LENGTH_LONG);
            mensaje.show();
            Regresar();
        } else {
            //le daremos un texto al textView de la activity
            datos.setText("Bienvenido al sistema " + nombre + "\n Su contraseñ es: " + contraseña);

            mensaje = Toast.makeText(getApplicationContext(), "El usuario " + this.nombre + " se a registrado en el sistema", Toast.LENGTH_LONG);
            mensaje.show();
            createNotification();
            crearDatabase();

        }

        if (nombre.equals("")) {
            mensaje = Toast.makeText(getApplicationContext(), "Ingrese un nombre valida", Toast.LENGTH_LONG);
            mensaje.show();
            Regresar();
        }

    }

    //metodo del boton regresar
    public void anteriorActividad(View v) {
        Intent Regreso = new Intent(this, PrimerActivity.class);
        startActivity(Regreso);
        finish();
    }
    //metodo utilizado en la creacion para las exepciones
    public void Regresar() {
        Intent Regreso = new Intent(this, PrimerActivity.class);
        startActivity(Regreso);
        finish();
    }



    //--- extra --- (db, notificacion)



    //creacion de la base de datos interna
    private void crearDatabase(){
        DbHelper dbHelper = new DbHelper(SegundaActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("INSERT INTO Usuarios (nombre, contraseña) VALUES ('"+nombre+"', '"+contraseña+"')");
        if(db != null){
            Toast.makeText(SegundaActivity.this, "BASE DE DATOS CREADA", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(SegundaActivity.this, "ERROR AL CREAR BASE DE DATOS", Toast.LENGTH_LONG).show();
        }
    }

    //Metodos para hacer una notificacion emergente
    private void createNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_baseline_support_24);
        builder.setContentTitle("El usuario "+nombre+" iniciado sesion");
        builder.setContentText("Esto es una prueba, el resultado de manifestar una notificacion de un boton");
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText("Esto es una prueba, el resultado de manifestar una notificacion"));
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setColor(Color.RED);
        builder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
        builder.setDefaults(Notification.DEFAULT_VIBRATE);
        builder.setDefaults(Notification.DEFAULT_SOUND);
        builder.setLights(Color.RED, 1000, 1000);
        NotificationManagerCompat notificacionMAnagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificacionMAnagerCompat.notify(NOTIFICACION_ID, builder.build());
    }

}