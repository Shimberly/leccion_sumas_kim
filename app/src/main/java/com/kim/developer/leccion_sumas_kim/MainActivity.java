package com.kim.developer.leccion_sumas_kim;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText txtRespuesta;
    Button calcular;
    Integer intentos;
    List<String> respuestas;
    List<String> tipoRespuesta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentos=0;
        respuestas= new ArrayList<String>();
        tipoRespuesta= new ArrayList<String>();

        txtRespuesta=findViewById(R.id.txtRespuesta);
        calcular= findViewById(R.id.btnCalcular);
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentos++;
                respuestas.add(String.valueOf(txtRespuesta.getText()));
                Log.d("Respuesta", String.valueOf(txtRespuesta.getText()));
                if (String.valueOf(txtRespuesta.getText()).equals("5")){
                    tipoRespuesta.add("correcta");
                    MediaPlayer mp = MediaPlayer.create(view.getContext(), R.raw.bien);
                    mp.start();
                    Notification noti = new Notification.Builder(view.getContext())
                            .setContentTitle("Repsuesta correcta")
                            .setContentText("Muy bien")
                            .build();
                }else{
                    tipoRespuesta.add("incorrecta");
                    Toast.makeText(view.getContext(), "Respuesta incorrecta", Toast.LENGTH_SHORT).show();
                    Vibrator vs = (Vibrator) view.getContext().getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vs.vibrate(VibrationEffect.createOneShot(600, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        vs.vibrate(800);
                    }
                }
                if(intentos<3){


                }else{
                    Toast.makeText(view.getContext(), "Muchos intentos", Toast.LENGTH_SHORT).show();
                    Intent itemintent = new Intent(MainActivity.this, Resultados.class);
                    itemintent.putStringArrayListExtra("listaRespuestas", (ArrayList<String>) respuestas);
                    itemintent.putStringArrayListExtra("listaRespuestasTipo", (ArrayList<String>) respuestas);
                    MainActivity.this.startActivity(itemintent);
                }



            }

        });
    }


}
