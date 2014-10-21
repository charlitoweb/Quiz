package com.example.charlito.quiz.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_mail);
		final Button send = (Button) this.findViewById(R.id.button1);
        final Button salir = (Button) this.findViewById(R.id.salirPreg);
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
                opcionVerdaderaText = (TextView) findViewById(R.id.opcionVerdaderaText);

                String emailBody = "Pregunta: "+preguntaText.getText().toString();
                emailBody = emailBody + "<br>Opcion 1: " +opcion1Text.getText().toString();
                emailBody = emailBody + "<br>Opcion 2: " +opcion2Text.getText().toString();
                emailBody = emailBody + "<br>Opcion 3: " +opcion3Text.getText().toString();
                emailBody = emailBody + "<br>Opcion 4: " +opcion4Text.getText().toString();
                emailBody = emailBody + "<br>Opcion Correcta: " +opcionVerdaderaText.getText().toString();
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
        opcionVerdaderaText.setText("");
    }
}
