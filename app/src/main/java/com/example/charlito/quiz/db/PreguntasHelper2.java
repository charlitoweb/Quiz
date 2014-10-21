package com.example.charlito.quiz.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Charlito on 20/10/2014.
 */
public class PreguntasHelper2 extends SQLiteOpenHelper {

        //Ruta por defecto de las bases de datos en el sistema Android
        private static String DB_PATH = "/data/data/com.example.charlito.quiz/databases/";
        //archivo de la base de datos que esta en la carpeta assets
        private static String DB_NAME = "preguntas.db";
        private SQLiteDatabase myDataBase;
        private final Context myContext;
        public static final String KEY_ID = "_id";

        public PreguntasHelper2(Context context) {
            //el uno corresponde a la version de la base de datos;
            // En mi caso es la primera versión
            super(context, DB_NAME, null, 1);
            this.myContext = context;
        }

        /**
         * Comprueba si la base de datos existe para evitar copiar
         * siempre el fichero cada vez que se abra la aplicación.
         */
        private boolean checkDataBase() {
            SQLiteDatabase checkDB = null;
            try {
                String myPath = DB_PATH + DB_NAME;
                checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

            } catch (SQLiteException e) {
                //si llegamos aqui es porque la base de datos no existe todavía.
            }
            if (checkDB != null) {
                checkDB.close();
            }
            return checkDB != null ? true : false;
        }

        /**
         * Copia nuestra base de datos desde la carpeta assets a la recién creada
         * base de datos en la carpeta de sistema, desde dónde podremos acceder a ella.
         */
        private void copyDataBase() throws IOException {
            //Abrimos el fichero de base de datos como entrada
            //A través del contexto accedemos a la carpeta assets
            InputStream myInput = myContext.getAssets().open(DB_NAME);

            //Ruta a la base de datos vacía recién creada
            String outFileName = DB_PATH + DB_NAME;

            //Abrimos la base de datos vacía como salida
            OutputStream myOutput = new FileOutputStream(outFileName);

            //Transferimos los bytes desde el fichero de entrada al de salida
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            //Liberamos los streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }

        /**
         * Crea una base de datos vacía en el sistema y la reescribe con nuestro fichero
         * de base de datos.
         */
        public void createDataBase() throws IOException {
            boolean dbExist = checkDataBase();

            if (dbExist) {
                //la base de datos existe y no hacemos nada.
            } else {
                //Llamando a este método se crea la base de datos vacía en la ruta por defecto
                //del sistema
                //de nuestra aplicación por lo que podremos sobreescribirla con nuestra base de datos.
                this.getWritableDatabase();

                try {
                    copyDataBase();
                } catch (IOException e) {
                    throw new Error("Error copiando Base de Datos");
                }
            }
        }
        /**
         * Abre la base de datos
         */
        public SQLiteDatabase open() throws SQLException {
            if(myDataBase==null || (!(myDataBase.isOpen())) ){
                try {
                    createDataBase(); //Si no existe crea la base de datos, si existe no hace nada
                } catch (IOException e) {
                    throw new Error("Ha sido imposible crear la Base de Datos");
                }
                String myPath = DB_PATH + DB_NAME;
                myDataBase = SQLiteDatabase.openDatabase(myPath, null,SQLiteDatabase.OPEN_READWRITE);
            }
            return myDataBase;
        }


        public void close(){
            myDataBase.close();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

