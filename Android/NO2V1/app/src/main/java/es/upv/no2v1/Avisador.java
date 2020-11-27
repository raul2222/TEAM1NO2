package es.upv.no2v1;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static android.os.SystemClock.sleep;

public class Avisador {

    private static String ETIQUETA_LOG = "no2";
    private int _minutosAlarma;
    private int _distancia;
    private Localizacion loc;
    private Activity main;
    private Medicion medicion;
    private DetectarBeacon deciBeacon;
    private Firebase fb;
    private int contadorInactividad = 0;
    private Long ultimaMedicion;

    // Contructor de la clase
    public Avisador(int minutos, int distancia, Activity mainActivity){
        _minutosAlarma = minutos;
        _distancia = distancia;
        main = mainActivity;
        medicion = new Medicion();

    }
    // encendido del avisador
    public void encenderAvisador(){

        //enciende la deteccion del ibeacon
        fb = new Firebase();
        deciBeacon = new DetectarBeacon(medicion);
        deciBeacon.encender();
        loc = new Localizacion(_minutosAlarma,_distancia, main, medicion);
        loc.setCallback(new CallbackLocalizacion() {
             @Override
             public void nuevaLocacion() {
                 Log.d(ETIQUETA_LOG,"llamada a enviarFirebase desde cambio de localizacion");
                 ultimaMedicion = medicion.getUltimaMedicion();
                 enviarFirebase();
             }
        });
        Log.d(ETIQUETA_LOG,"antes del setalarma");
        comprobarInactividad();
        setAlarma(_minutosAlarma);
    }

    //Tarea repetitiva de envio temporal
    private void setAlarma(int _minutosAlarma)  {

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Log.d(ETIQUETA_LOG,"llamada a enviar Firebase desde tarea recursiva");
                enviarFirebase();
            }
        };
        timer.scheduleAtFixedRate(task, new Date(), _minutosAlarma*1000*60);

    }


    //enviar datos al servidor la llamada puede ser desde
    //un cambio mayor a distancia o por tienpo entre medidas
    private void enviarFirebase(){

        Log.d(ETIQUETA_LOG,"enviarDatosFirebase");
        // si medición a pasado por la detección de beacon
        if (medicion.getIdsen() != "0") {
            Log.d(ETIQUETA_LOG,"el idSensor es distinto a 0");
            //si la medición tiene latitud y longitud
            if(medicion.getLat() != "" && medicion.getLongi() !="") {
                Log.d(ETIQUETA_LOG,"hay lat y long");
                try {

                    long segundosAlarma = _minutosAlarma * 60;
                    long momnetoActual = new Momento().getMomento();

                    if (medicion.getMomento() == ""){
                        Log.d(ETIQUETA_LOG, "getmomentoVacio");
                    }
                    long momentoDeLaUltimaMedicion = Long.parseLong(medicion.getMomento());

                    long diff = new Momento().getMomento() - Long.parseLong(medicion.getMomento());

                    if (diff < segundosAlarma) {
                        Log.d(ETIQUETA_LOG, "********  lectura no antigua ***********");



                        fb.enviarMedicion(medicion.getIdsen(), medicion.getLat(), medicion.getLongi(),
                                medicion.getValor(), medicion.getMomento(), medicion.getBat());

                        medicion.borrarMedicion();
                    } else {
                        Log.d(ETIQUETA_LOG, "lectura es muyyy antigua");
                    }

                } catch (Exception ex){

                    Log.d(ETIQUETA_LOG, "error antes de firebase");

                    Log.d(ETIQUETA_LOG, ex.toString());
                }

            }else {
                Log.d(ETIQUETA_LOG, "No hay datos de localizacon");
            }
        } else {
            Log.d(ETIQUETA_LOG, "No hay datos de medicion");
        }


    }


    public void apagarAvisador(){

    }

    private void comprobarInactividad(){
        ultimaMedicion = new Momento().getMomento();
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Log.d("INACTIVIDAD: ", "entro al run");
                if(new Momento().getMomento() - ultimaMedicion >= 60){
                    Log.d("INACTIVIDAD: ", "hay inactividad de 60s");
                    contadorInactividad++;
                }
                if(contadorInactividad == 1){
                    Log.d("INACTIVIDAD: ", "envio notificacion");
                    enviarNotificacion();
                    contadorInactividad = 0;
                }
                else{
                    contadorInactividad = 0;
                }
            }
        };
        timer.scheduleAtFixedRate(task, new Date(), 60*1000);
    }

    private void enviarNotificacion(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(main.getApplicationContext(), "Inactividad")
                .setContentTitle("Sensor inactivo")
                .setContentText("Ha pasado mucho tiempo desde la última lectura recibida por el teléfono")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setAutoCancel(true);

        NotificationManagerCompat notificacionManager = NotificationManagerCompat.from(main.getApplicationContext());
        notificacionManager.notify(1, builder.build());
    }


}
