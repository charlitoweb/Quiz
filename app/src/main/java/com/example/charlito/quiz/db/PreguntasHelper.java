package com.example.charlito.quiz.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


 
public class PreguntasHelper extends SQLiteOpenHelper {


    /*
     * Table and column information
     */
    public static final String TABLA_PREGUNTAS = "PREGUNTAS";
    public static final String COLUMN_ID = "_ID";
    public static final String COLUMN_PREGUNTA = "PREGUNTA";
    public static final String COLUMN_OPCION1 = "OPCION1";
    public static final String COLUMN_OPCION2 = "OPCION2";
    public static final String COLUMN_OPCION3 = "OPCION3";
    public static final String COLUMN_OPCION4 = "OPCION4";
    public static final String COLUMN_CORRECTA = "CORRECTA";
    public static final String COLUMN_CATEGORIA= "CATEGORIA";

    /*
     * Database information
     */
    private static final String DB_NAME = "preguntas.db";
    private static final int DB_VERSION = 2; // Must increment to trigger an upgrade
    private static final String DB_CREATE =
            "CREATE TABLE " + TABLA_PREGUNTAS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_PREGUNTA + " TEXT, " +
                    COLUMN_OPCION1 + " TEXT, " +
                    COLUMN_OPCION2 + " TEXT, " +
                    COLUMN_OPCION3 + " TEXT, " +
                    COLUMN_OPCION4 + " TEXT, " +
                    COLUMN_CORRECTA + " INTEGER, " +
                    COLUMN_CATEGORIA + " TEXT "+")";
    private static final String DB_DROP ="DROP TABLE IF EXISTS " + TABLA_PREGUNTAS;

    public PreguntasHelper(Context contexto) {
        super(contexto, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creaci�n de la tabla
        db.execSQL(DB_CREATE);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        //NOTA: Por simplicidad del ejemplo aqu� utilizamos directamente la opci�n de
        //      eliminar la tabla anterior y crearla de nuevo vac�a con el nuevo formato.
        //      Sin embargo lo normal ser� que haya que migrar datos de la tabla antigua
        //      a la nueva, por lo que este m�todo deber�a ser m�s elaborado.
 
        //Se elimina la versi�n anterior de la tabla
        db.execSQL(DB_DROP);
 
        //Se crea la nueva versi�n de la tabla
        db.execSQL(DB_CREATE);
    }
}