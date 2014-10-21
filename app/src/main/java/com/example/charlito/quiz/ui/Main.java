package com.example.charlito.quiz.ui;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.charlito.quiz.R;


public class Main extends Activity {
    protected Button mBotonJugar;
    protected Button mBotonAcercaDe;
    protected Button mBotonSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mBotonJugar = (Button)findViewById(R.id.botonJugar);
        mBotonJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main.this, Preguntas.class));
            }
        });
        mBotonAcercaDe = (Button)findViewById(R.id.botonAcercaDe);
        mBotonAcercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Aqui que salga un dialogo que hable de nosotros
                // 1. Instantiate an AlertDialog.Builder with its constructor
                AlertDialog.Builder builder = new AlertDialog.Builder(Main.this);

                // 2. Chain together various setter methods to set the dialog characteristics
                builder.setMessage(R.string.dialog_message)
                        .setTitle(R.string.dialog_title);

                // 3. Get the AlertDialog from create()
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        mBotonSalir = (Button)findViewById(R.id.botonSalir);
        mBotonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //terminar la actividad y salir
                //db.close();
                System.exit(0);
            }
        });

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

}
