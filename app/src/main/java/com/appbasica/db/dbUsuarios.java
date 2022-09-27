package com.appbasica.db;
/*

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class dbUsuarios extends DbHelper {

    Context context;

    public dbUsuarios(@Nullable Context context) {
        super(context);
        this.context=context;
    }
    public long ingresarUsuario(String nombre, String contraseña){
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("contraseña", contraseña);

            long id = db.insert(T_USUARIOS, null, values);
            return id;
        }catch (Exception ex){
            ex.toString();
        }



    }
}
*/