package com.ghtdeveloper.infoapp.modelo;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
    Clase utilizada
    para definir
    la estructura de la
    Base de datos

 **/
public class OpenHelper extends SQLiteOpenHelper
{

    //Database Nombre
    private  static  final String DB_NAME ="infoAppDB.db";
    //Database version
    private  static  final int DB_VERSION = 1;
    //Create Table Estudiante

    private static final String ESTUDIANTE_TABLE_CREATE = "CREATE TABLE Estudiantes" +
            "(idEstudiante INTEGER PRIMARY KEY AUTOINCREMENT,nombreCompleto TEXT," +
            "matricula TEXT" + ",descripcion TEXT,lugarNacimiento TEXT,imagen BLOB)";

    public OpenHelper(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(ESTUDIANTE_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}//Fin de la class OpenHelper
