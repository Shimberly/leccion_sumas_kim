package com.kim.developer.leccion_sumas_kim;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Resultados extends AppCompatActivity {
    ArrayList<String> listaRespuestas;
    ArrayList<String> listaRespuestasTipo;
    TextView txtRespuesta1;
    TextView txtRespuesta2;
    TextView txtRespuesta3;
    Button guardar;
    String path="https://leccionhilosrest.herokuapp.com/guardarRespuesta";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        txtRespuesta1=findViewById(R.id.txtRespuesta1);
        txtRespuesta2=findViewById(R.id.txtRespuesta2);
        txtRespuesta3=findViewById(R.id.txtRespuesta3);
        guardar=findViewById(R.id.btnGuardar);

        listaRespuestas = (ArrayList<String>) getIntent().getStringArrayListExtra("listaRespuestas");
        listaRespuestasTipo = (ArrayList<String>) getIntent().getStringArrayListExtra("listaRespuestasTipo");

        txtRespuesta1.setText(listaRespuestas.get(0));
        txtRespuesta2.setText(listaRespuestas.get(1));
        txtRespuesta3.setText(listaRespuestas.get(2));


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LooperThread t= new LooperThread();
                t.run();
            }

        });

    }


    class Simulator implements Runnable{
        Guardar guardarIntentos= new Guardar("Hola");
        @Override
        public void run() {

            //guardarIntentos.guardarI();
            //MyHandlerThread handlerThread = new MyHandlerThread("MyHandlerThread");
            //handlerThread.start();

        }
    }


    private class MyHandlerThread extends HandlerThread {

        Handler handler;

        public MyHandlerThread(String name) {
            super(name);
        }

        @Override
        protected void onLooperPrepared() {
            handler = new Handler(getLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    HttpURLConnection urlConnection = null;
                    Map<String, String> stringMap = new HashMap<>();

                    Log.i("MainActivity", "onCreate -> else -> Todos los EditText estan llenos.");
                    stringMap.put("respuesta1", String.valueOf(txtRespuesta1.getText()));
                    stringMap.put("respuesta2", String.valueOf(txtRespuesta2.getText()));
                    stringMap.put("respuesta3", String.valueOf(txtRespuesta3.getText()));
                    String requestBody = Utils.buildPostParameters(stringMap);
                    try {
                        urlConnection = (HttpURLConnection) Utils.makeRequest("POST", path, null, "application/x-www-form-urlencoded", requestBody);
                        InputStream inputStream;
                        // get stream
                        if (urlConnection.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
                            inputStream = urlConnection.getInputStream();
                        } else {
                            inputStream = urlConnection.getErrorStream();
                        }
                        // parse stream
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        String temp, response = "";
                        while ((temp = bufferedReader.readLine()) != null) {
                            response += temp;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {

                    }
                }
            };
        }
    }

    class Guardar extends HandlerThread {
        Handler handler;
        public Guardar(String name) {
            super(name);
        }
        public synchronized void guardarI(){
            handler =
                    new Handler(getLooper()) {
                        @Override
                        public void handleMessage(Message msg) {
                            HttpURLConnection urlConnection = null;
                            Map<String, String> stringMap = new HashMap<>();

                            Log.i("MainActivity", "onCreate -> else -> Todos los EditText estan llenos.");
                            stringMap.put("respuesta1", String.valueOf(txtRespuesta1.getText()));
                            stringMap.put("respuesta2", String.valueOf(txtRespuesta2.getText()));
                            stringMap.put("respuesta3", String.valueOf(txtRespuesta3.getText()));
                            String requestBody = Utils.buildPostParameters(stringMap);
                            try {
                                urlConnection = (HttpURLConnection) Utils.makeRequest("POST", path, null, "application/x-www-form-urlencoded", requestBody);
                                InputStream inputStream;
                                // get stream
                                if (urlConnection.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
                                    inputStream = urlConnection.getInputStream();
                                } else {
                                    inputStream = urlConnection.getErrorStream();
                                }
                                // parse stream
                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                                String temp, response = "";
                                while ((temp = bufferedReader.readLine()) != null) {
                                    response += temp;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }finally {

                            }
                        }
                    };
        }
    }

    class LooperThread extends Thread {
        public Handler mHandler;

        public void run() {
            if (Looper.myLooper() == null)
            {
                Looper.prepare();
            }

            mHandler = new Handler() {
                public void handleMessage(Message msg) {
                    txtRespuesta1.setText("sdsaa");
                    HttpURLConnection urlConnection = null;
                    Map<String, String> stringMap = new HashMap<>();

                    Log.i("MainActivity", "onCreate -> else -> Todos los EditText estan llenos.");
                    stringMap.put("respuesta1", String.valueOf(txtRespuesta1.getText()));
                    stringMap.put("respuesta2", String.valueOf(txtRespuesta2.getText()));
                    stringMap.put("respuesta3", String.valueOf(txtRespuesta3.getText()));
                    String requestBody = Utils.buildPostParameters(stringMap);
                    try {
                        urlConnection = (HttpURLConnection) Utils.makeRequest("POST", path, null, "application/x-www-form-urlencoded", requestBody);
                        InputStream inputStream;
                        // get stream
                        if (urlConnection.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
                            inputStream = urlConnection.getInputStream();
                        } else {
                            inputStream = urlConnection.getErrorStream();
                        }
                        // parse stream
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        String temp, response = "";
                        while ((temp = bufferedReader.readLine()) != null) {
                            response += temp;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {

                    }
                }
                //Looper.quit();
            };

        Looper.loop();
        }
    }


        public static class Utils {
            public static String buildPostParameters(Object content) {
                String output = null;
                if ((content instanceof String) ||
                        (content instanceof JSONObject) ||
                        (content instanceof JSONArray)) {
                    output = content.toString();
                } else if (content instanceof Map) {
                    Uri.Builder builder = new Uri.Builder();
                    HashMap hashMap = (HashMap) content;
                    if (hashMap != null) {
                        Iterator entries = hashMap.entrySet().iterator();
                        while (entries.hasNext()) {
                            Map.Entry entry = (Map.Entry) entries.next();
                            builder.appendQueryParameter(entry.getKey().toString(), entry.getValue().toString());
                            entries.remove(); // avoids a ConcurrentModificationException
                        }
                        output = builder.build().getEncodedQuery();
                    }
                }

                return output;
            }

            public static URLConnection makeRequest(String method, String apiAddress, String accessToken, String mimeType, String requestBody) throws IOException {
                URL url = null;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(apiAddress);

                    urlConnection = (HttpURLConnection) url.openConnection();

                    urlConnection.setDoInput(true);
                    urlConnection.setDoOutput(!method.equals("GET"));
                    urlConnection.setRequestMethod(method);

                    urlConnection.setRequestProperty("Authorization", "Bearer " + accessToken);

                    urlConnection.setRequestProperty("Content-Type", mimeType);
                    OutputStream outputStream = new BufferedOutputStream(urlConnection.getOutputStream());
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
                    writer.write(requestBody);
                    writer.flush();
                    writer.close();
                    outputStream.close();

                    urlConnection.connect();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return urlConnection;
            }
        }


}
