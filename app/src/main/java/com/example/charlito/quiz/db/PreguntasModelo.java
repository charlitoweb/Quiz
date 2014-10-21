package com.example.charlito.quiz.db;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


public class PreguntasModelo {

    private SQLiteDatabase mDatabase;       // The actual DB!
    private PreguntasHelper2 mPreguntasHelper; // Helper class for creating and opening the DB
    private Context mContext;

    private MediaPlayer sonidook,sonidoko,tiempo;
    private TextView pregunta;

    private Button opcion1;
    private Button opcion2;
    private Button opcion3;
    private ImageView vida1;
    private ImageView vida2;
    private ImageView vida3;
    private Button salir;

    private int correcta = 0;
    private int id = 0;


    int registros,num;
    int numVidas=3;
//    UpdateProgress updateProgressBar;
    ProgressBar progressBar;
    boolean tareLevantada = false;

    Cursor c;

    public PreguntasModelo(Context context) {
        mContext = context;
        mPreguntasHelper = new PreguntasHelper2(mContext);
        mDatabase = mPreguntasHelper.open();
        PonerDatos();
    }

    /*
     * Open the db. Will create if it doesn't exist
     */
    public void open() throws SQLException {
        mDatabase = mPreguntasHelper.getWritableDatabase();
    }

    /*
     * We always need to close our db connections
     */
    public void close() {
        mDatabase.close();
    }
    public void PonerDatos(){
        if(mDatabase != null) {
            Log.e("oeoeoe","antes de poner datos");
//            mDatabase.execSQL("INSERT INTO Preguntas (PREGUNTA,OPCION1,OPCION2,OPCION3,OPCION4,CORRECTA) " +
//                    "VALUES ('¿Quien dice plan de hoy?','Hector','El abuelo','Carlos','Pedro',2)");
//            mDatabase.execSQL("INSERT INTO Preguntas (PREGUNTA,OPCION1,OPCION2,OPCION3,OPCION4,CORRECTA) " +
//                    "VALUES ('¿Quien es el mejor amigo de Xoubiña (ironicamente)?','El Trankas','Javi','Fon','Ivan',4)");
//            mDatabase.execSQL("INSERT INTO Preguntas (PREGUNTA,OPCION1,OPCION2,OPCION3,OPCION4,CORRECTA) " +
//                    "VALUES ('¿Quien esta porculenando todo el dia con el depor?','Juanele','Javi','Xavi','Prieto',1)");
            Log.e("oeoeoe","despues de poner datos");
            //Alternativa 2: método delete()
            String[] campos = new String[]{"_ID", "PREGUNTA", "OPCION1", "OPCION2", "OPCION3", "OPCION4", "CORRECTA"};
            c = mDatabase.query("PREGUNTAS", campos, null, null, null, null, null);
            Log.e("oeoeoe","despues de recuperar datos y ponerlos en el cursor");
            registros = c.getCount();
            num = (int) (Math.random() * registros) + 1;
            /*updateProgressBar = new UpdateProgress();
            tareLevantada = true;
            updateProgressBar.execute();*/
            montarNuevoCursor();
        }
    }


    public void montarNuevoCursor() {
        if (c == null){
            PonerDatos();
        }else {
            int exclueRef = id;
            MatrixCursor newCursor = new MatrixCursor(new String[]{"id", "pregunta", "opcion1", "opcion2", "opcion3", "opcion4","correcta"});
            if (c.moveToFirst()) {
                do {
                    // skip the copy of this one ....
                    if (c.getInt(0) == (exclueRef))
                        continue;
                    newCursor.addRow(new Object[]{c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5),c.getInt(6)});
                } while (c.moveToNext());
                c = newCursor;
            }
            registros = c.getCount();
            num = (int) (Math.random() * registros) + 1;
        }
    }
    public String[] setPregunta() {
        c.move(num);
        id = c.getInt(0);
        String preg = c.getString(1);
        String opc1 = c.getString(2);
        String opc2 = c.getString(3);
        String opc3 = c.getString(4);
        String opc4 = c.getString(5);
        correcta = c.getInt(6);

        String[]datosPregunta=new String[5];
        datosPregunta[0]=preg;
        datosPregunta[1]=opc1;
        datosPregunta[2]=opc2;
        datosPregunta[3]=opc3;
        datosPregunta[4]=opc4;

      /*  updateProgressBar = new UpdateProgress();
        tareLevantada = true;
        updateProgressBar.execute();*/

        return datosPregunta;

    }

//    public boolean finalCursor() {
//        if (c.getCount()!= 0){
//            return false;
//        }else{
//            c.close();
//            return true;
//        }
//    }

    public boolean esCorrecta(int correcta2) {
        if (correcta == correcta2){
            return true;
        }else{
            return false;
        }
    }

    public boolean noesUltimaPregunta() {
        if(c.getCount()>1){
            return true;
        }else{
            return false;
        }
    }

  /*  public class UpdateProgress extends AsyncTask<Void, Integer, Void> {
        int progress;
        @Override
        protected void onPreExecute() {
            progress = 0;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            if (isCancelled()){
                tareLevantada=false;
                progressBar.setProgress(0);
            }
            progressBar.setProgress(values[0]);
        }
        @Override
        protected Void doInBackground(Void... params) {
            while (progress < 100){
                if(tareLevantada == true){
                    progress++;
                    publishProgress(progress);
                    SystemClock.sleep(100);
                }else{
                    cancel(true);
                }
            }
            cancel(true);
            return null;
        }*/

       /* @Override
        protected void onCancelled() {
            tareLevantada=false;
        }*/


//			@Override
//			protected void onPostExecute(Void result) {
//				if (isCancelled() && Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
//			        onCancelled();
//			    } else {
//			    	pregunta.setText("SE ACABO EL TIEMPO!!");
//			    }
//
//			}
//			@Override
//			  protected void onCancelled() {
//			    //tareLevantada=false;
//			}
//



//    }
    public int getId() {
        return id;
    }
}
