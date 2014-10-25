package com.example.charlito.quiz.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.charlito.quiz.R;
import com.example.charlito.quiz.adaptador.Lista_adaptador;
import com.example.charlito.quiz.clases.Lista_entrada;

import java.util.ArrayList;


public class Puntuaciones extends Activity {
    protected Button salirPuntuaciones;
    protected ListView listaPuntuaciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_puntuaciones);
//        SharedPreferences preferencias = getPreferences(MODE_PRIVATE);
//        String nombre = preferencias.getString("nombre","valor por defecto");
//        int edad = preferencias.getInt("edad", -1);

        //listadoPuntuaciones
        ArrayList<Lista_entrada> datos = new ArrayList<Lista_entrada>();
        datos.add(new Lista_entrada("Varela","1000"));
        datos.add(new Lista_entrada("Hector","800"));
        datos.add(new Lista_entrada("Carlos","250"));
        datos.add(new Lista_entrada("Varela","100"));
        datos.add(new Lista_entrada("Hector","90"));
        datos.add(new Lista_entrada("Hector","80"));
        datos.add(new Lista_entrada("Carlos","50"));
        datos.add(new Lista_entrada("Hector","10"));
        listaPuntuaciones =(ListView)findViewById(R.id.listadoPuntuaciones);
        listaPuntuaciones.setAdapter(new Lista_adaptador(this, R.layout.activity_puntuaciones, datos){
            @Override
            public void onEntrada(Object entrada, View view) {

                TextView usuario = (TextView) view.findViewById(R.id.usuario);
                if (usuario != null)
                    usuario.setText(((Lista_entrada) entrada).get_usuario());

                TextView puntuacion = (TextView) view.findViewById(R.id.puntuacion);
                if (puntuacion != null)
                    puntuacion.setText(((Lista_entrada) entrada).get_puntuacion());

            }
        });

        /*salirPuntuaciones = (Button)findViewById(R.id.botonSalirPuntuaciones);
        salirPuntuaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/
    }

}
