package com.example.charlito.quiz.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.charlito.quiz.R;
import com.example.charlito.quiz.mail.SendMailTask;

import java.util.Arrays;
import java.util.List;

public class SendMailActivity extends Activity {

	private TextView preguntaText;
    private TextView opcion1Text;
    private TextView opcion2Text;
    private TextView opcion3Text;
    private TextView opcion4Text;
    private TextView opcionVerdaderaText;
    private Spinner cmbOpciones;
    private int verd; //opcion seleccionada del spinner

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_mail);
		final Button send = (Button) this.findViewById(R.id.button1);
        final Button salir = (Button) this.findViewById(R.id.salirPreg);
        //Definimos el adaptador para el spinner
        final ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,R.array.valores_pregunta_correcta,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);//Enlazamos nuestro spinner
        cmbOpciones = (Spinner)findViewById(R.id.CmbOpciones);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        cmbOpciones.setAdapter(adapter);

        cmbOpciones.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {
                        //lblMensaje.setText("Seleccionado: " + datos[position]);
                        verd = position + 1;
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        verd = 1;
                    }
                });

            salir.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    finish();
                }
            });
            send.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

                Log.i("SendMailActivity", "Send Button Clicked.");

                String fromEmail = "preguntasfreak@gmail.com";
                String fromPassword = "8AIJ39HD9";
                List<String> toEmailList = Arrays.asList("preguntasfreak@gmail.com");
                preguntaText = (TextView) findViewById(R.id.preguntaText);
                opcion1Text = (TextView) findViewById(R.id.opcion1Text);
                opcion2Text = (TextView) findViewById(R.id.opcion2Text);
                opcion3Text = (TextView) findViewById(R.id.opcion3Text);
                opcion4Text = (TextView) findViewById(R.id.opcion4Text);
                //opcionVerdaderaText = (TextView) findViewById(R.id.opcionVerdaderaText);







                String emailBody = "Pregunta: "+preguntaText.getText().toString();
                emailBody = emailBody + "<br>Opcion 1: " +opcion1Text.getText().toString();
                emailBody = emailBody + "<br>Opcion 2: " +opcion2Text.getText().toString();
                emailBody = emailBody + "<br>Opcion 3: " +opcion3Text.getText().toString();
                emailBody = emailBody + "<br>Opcion 4: " +opcion4Text.getText().toString();
                /*String verd = CmbOpciones.getText().toString();
                if (verd == "Opcion 1"){
                    verd = "1";
                }else if(verd == "Opcion 2"){
                    verd = "2";
                }else if (verd == "Opcion 3"){
                    verd = "3";
                }else{
                    verd = "4";
                }*/
                emailBody = emailBody + "<br>Opcion Correcta: " +verd;
                new SendMailTask(SendMailActivity.this).execute(fromEmail,
                        fromPassword, toEmailList, "nueva pregunta de Preguntas Freak", emailBody);
                borraDatos();
			}
		});
	}

    private void borraDatos() {
        preguntaText.setText("");
        opcion1Text.setText("");
        opcion2Text.setText("");
        opcion3Text.setText("");
        opcion4Text.setText("");
//        opcionVerdaderaText.setText("");
    }
}
