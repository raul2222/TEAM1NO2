package es.upv.no2v1;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Firebase  {
    private static final String CHANNEL_ID = "112";
    private static String ETIQUETA_LOG = "no2";
    FirebaseFirestore db = FirebaseFirestore.getInstance(); //inicializamos aqui firebase para almacenar
    private int LimiteConcentracion = 100;
    private MainActivity main;

    public Firebase(){

    }

    public void enviarMedicion(String idsen, String lat, String longi, String valor, String momento, String bateria) {
        Log.d(ETIQUETA_LOG, "empiezo a enviar a Firebase");
        Map<String, Object> dato = new HashMap<>();
        dato.put("IDSensor", idsen);
        dato.put("Latitud", lat);
        dato.put("Longitud", longi);
        dato.put("Valor", valor);
        dato.put("Momento", momento);
        dato.put("Bateria", bateria);


        db.collection("Mediciones").document().set(dato)//aqui accedemos a la coleccion creada en firebase donde se almacenaran los valores anterrioires
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(ETIQUETA_LOG, "**********  DocumentSnapshot successfully written!  *********");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(ETIQUETA_LOG, "Error writing document", e);
                    }
                });

        //Comprobar valor máximo y si supera el límite llamar a EnviarNotificación
        /*
        LimiteConcentracion = 101;
        if (Integer.parseInt( valor) > LimiteConcentracion){
            notificacionLimite();
        }*/


    }
/*
    private void notificacionLimite(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_light_normal)
                .setContentTitle("Limite excedido")
                .setContentText("Aire tóxico")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Aire tóxico"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        //NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        //notificationManager.notify(notificationId, builder.build());
        builder.notify();

    }*/


}


