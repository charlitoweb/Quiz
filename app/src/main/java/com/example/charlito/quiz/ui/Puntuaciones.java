package com.example.charlito.quiz.ui;

import android.app.Activity;
import android.content.SharedPreferences;
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
    protected SharedPreferences prefs;
    protected ArrayList<Lista_entrada> datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_puntuaciones);
        escribirPuntuaciones();
        cargarPuntuaciones();

        /*datos.add(new Lista_entrada(nombrePref ,Integer.toString(puntuacionPref)));
        datos.add(new Lista_entrada("Hector","800"));
        datos.add(new Lista_entrada("Carlos","250"));
        datos.add(new Lista_entrada("Varela","100"));
        datos.add(new Lista_entrada("Hector","90"));
        datos.add(new Lista_entrada("Hector","80"));
        datos.add(new Lista_entrada("Carlos","50"));
        datos.add(new Lista_entrada("Hector","10"));*/
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
    private void escribirPuntuaciones() {
        prefs = getSharedPreferences("Puntuaciones",this.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();

        for (int n = 9; n >= 0; n--) {

            editor.putString("puntuacion" + n,"Hector "+Integer.toString(n*100));
        }
       /* editor.putString("puntuacion0", puntos + " " + nombre);
        editor.putInt("puntuacionPref",1000);
        editor.putString("nombrePref", "Hector");*/
        editor.commit();
    }
    private void cargarPuntuaciones() {
        String [] puntuaciones = new String[10];
        String [] nombrePref = new String[10];
        String [] puntuacionPref = new String[10];
        for (int n = 9; n >= 0; n--) {
            puntuaciones[n] = prefs.getString("puntuacion"+n,"");
            String[]result = puntuaciones[n].split("\\s");
            nombrePref[n]=result[0];
            puntuacionPref[n]=result[1];
            //String nombrePref = prefs.getString("nombrePref", "valor por defecto");
            //int puntuacionPref = prefs.getInt("puntuacionPref", -1);
        }
        //listadoPuntuaciones
        datos = new ArrayList<Lista_entrada>();
        for (int n = 9; n >= 0; n--) {
            datos.add(new Lista_entrada(nombrePref[n] ,puntuacionPref[n]));
        }
    }

}
