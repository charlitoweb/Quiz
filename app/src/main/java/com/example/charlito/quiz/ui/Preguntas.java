package com.example.charlito.quiz.ui;


import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.charlito.quiz.R;
import com.example.charlito.quiz.db.PreguntasModelo;


public class Preguntas extends Activity {

    private Button opcion1;
    private Button opcion2;
    private Button opcion3;
    private Button opcion4;
    private ImageView vida1;
    private ImageView vida2;
    private ImageView vida3;
    private Button salir;
    private TextView pregunta;
    private ProgressBar progressBar;
    protected PreguntasModelo mPreguntasModelo;
    private MediaPlayer sonidook,sonidoko,tiempo;
    int numVidas=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        //Obtenemos las referencias a los controles

        pregunta = (TextView)findViewById(R.id.pregunta);
        opcion1 = (Button)findViewById(R.id.opcion1);
        opcion2 = (Button)findViewById(R.id.opcion2);
        opcion3 = (Button)findViewById(R.id.opcion3);
        opcion4 = (Button)findViewById(R.id.opcion4);
        salir = (Button)findViewById(R.id.salir);
        vida1 = (ImageView)findViewById(R.id.vida1);
        vida2 = (ImageView)findViewById(R.id.vida2);
        vida3 = (ImageView)findViewById(R.id.vida3);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        mPreguntasModelo= new PreguntasModelo(Preguntas.this);
        String[]datosPregunta = mPreguntasModelo.setPregunta();
        pregunta.setText(" " + datosPregunta[0]);
        opcion1.setText(" " + datosPregunta[1] );
        opcion2.setText(" " + datosPregunta[2] );
        opcion3.setText(" " + datosPregunta[3] );
        opcion4.setText(" " + datosPregunta[4] );

        opcion1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                comprobarRespuesta(1);
            }
        });

        opcion2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                comprobarRespuesta(2);
            }
        });

        opcion3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                comprobarRespuesta(3);
            }
        });
        opcion4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                comprobarRespuesta(4);
            }
        });
        salir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //db.close();
                finish();
            }
        });
        //cargarPregunta();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //mDataSource.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //mDataSource.close();
    }
    public void cargarPregunta() {

            mPreguntasModelo.montarNuevoCursor();
            String[]datosPregunta = mPreguntasModelo.setPregunta();
            pregunta.setText(" " + datosPregunta[0]);
            opcion1.setText(" " + datosPregunta[1] );
            opcion2.setText(" " + datosPregunta[2] );
            opcion3.setText(" " + datosPregunta[3] );
            opcion4.setText(" " + datosPregunta[4] );
    }
    public void comprobarRespuesta(int correcta2) {
        if (mPreguntasModelo.esCorrecta(correcta2)){
            Toast.makeText(getApplicationContext(), "Respuesta correcta",
                    Toast.LENGTH_SHORT).show();
            if (mPreguntasModelo.noesUltimaPregunta()){
                cargarPregunta();
                sonidook = MediaPlayer.create(this, R.raw.ok);
                sonidook.start();
            }else{
                Toast.makeText(getApplicationContext(), "Se acabaron las preguntas",
                        Toast.LENGTH_SHORT).show();
                tiempo = MediaPlayer.create(this, R.raw.tiempo);
                tiempo.start();
                pregunta.setText("FELICIDADES HAS LLEGADO AL FINAL");
                ocultarBotones();
            }
        }else{
            sonidoko=MediaPlayer.create(this, R.raw.fallo);
            numVidas = numVidas - 1;
            if (numVidas == 2){
                vida3.setImageResource(R.drawable.vidako);
            }
            if (numVidas == 1){
                vida2.setImageResource(R.drawable.vidako);
            }
            if (numVidas == 0){
                vida1.setImageResource(R.drawable.vidako);
                pregunta.setText("LO SENTIMOS NO LE QUEDAN MAS VIDAS");
                sonidoko.setVolume(1,1);
                sonidoko.start();
                ocultarBotones();

            }
            sonidoko.setVolume(1,1);
            sonidoko.start();
            Toast.makeText(getApplicationContext(), "Respuesta incorrecta",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void ocultarBotones() {
        opcion1.setVisibility(View.INVISIBLE);
        opcion2.setVisibility(View.INVISIBLE);
        opcion3.setVisibility(View.INVISIBLE);
        opcion4.setVisibility(View.INVISIBLE);
    }
}
