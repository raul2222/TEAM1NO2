package es.upv.inodos.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.content.ContextCompat;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import es.upv.inodos.adapters.Firebase;
import es.upv.inodos.common.Constants;
import es.upv.inodos.common.MySingletonClass;
import es.upv.inodos.data.Medicion;
import es.upv.inodos.services.BackgroundDetectedActivitiesService;

import es.upv.inodos.services.HeartService;
import es.upv.inodos.workers.NotificationWorker;
import es.upv.inodos.workers.NotificationWorkerTitle;
import es.upv.inodos.workers.SystemCheckWorker;

import static es.upv.inodos.common.Constants.TAG;


public class SystemUtils {


    public static boolean isServiceRunning(Context context, String serviceName){
        boolean serviceRunning = false;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> l = am.getRunningServices(50);
        Iterator<ActivityManager.RunningServiceInfo> i = l.iterator();
        while (i.hasNext()) {
            ActivityManager.RunningServiceInfo runningServiceInfo = i.next();
            Log.d(TAG, runningServiceInfo.service.getShortClassName());
            if(runningServiceInfo.service.getShortClassName().equals(serviceName)){
                serviceRunning = true;
                if(runningServiceInfo.foreground)
                {
                    //service run in foreground
                }
            }
        }
        return serviceRunning;
    }

    public static void enqueueSystemWorkers() {
        PeriodicWorkRequest.Builder systemCheckWorkerBuilder =
                new PeriodicWorkRequest.Builder(SystemCheckWorker.class, 15, TimeUnit.MINUTES);
        PeriodicWorkRequest request = systemCheckWorkerBuilder.build();
        WorkManager.getInstance().enqueueUniquePeriodicWork("system", ExistingPeriodicWorkPolicy.KEEP , request);
    }

    public static void launchMonitorService(Context context) {

        if (!es.upv.inodos.utils.SystemUtils.isServiceRunning(context, ".services.HeartService")) {
            Intent monitorServiceIntent = new Intent(context, HeartService.class);
            monitorServiceIntent.putExtra("inputExtra", Constants.MONITOR_SERVICE_DESCRIPTION);
            ContextCompat.startForegroundService(context, monitorServiceIntent);
        }


        if (!es.upv.inodos.utils.SystemUtils.isServiceRunning(context, ".services.BackgroundDetectedActivitiesService")) {
            Intent monitorServiceIntent = new Intent(context, BackgroundDetectedActivitiesService.class);
            monitorServiceIntent.putExtra("inputExtra", Constants.MONITOR_SERVICE_DESCRIPTION);
            ContextCompat.startForegroundService(context, monitorServiceIntent);
        }



    }

    public static int enviarDatosServidor(Medicion medicion){

        if(medicion.getLongi() == "" || medicion.getValor() == "" || medicion.getMomento() == "")
        {
            //Log.i(TAG,"Faltan datos");
            if (medicion.getLongi() == "") {Log.i(TAG, "Falta localizacion");}
            if (medicion.getValor() == "") {Log.i(TAG, "Falta medida");}
            if (medicion.getMomento() == "") {Log.i(TAG, "Falta medida caso 2");}
            return 1;
        } else {
            // mandamos medicion
            Log.i(TAG, medicion.getIdsen());
            Log.i(TAG, medicion.getLat());
            Log.i(TAG, medicion.getLongi());
            Log.i(TAG, medicion.getAccuracy());
            Log.i(TAG, "valor: " + medicion.getValor());
            Log.i(TAG, "temp: " +medicion.getTemperatura());
            Log.i(TAG, medicion.getMomento());
            Log.i(TAG, medicion.getDistancia());
            Log.i(TAG, medicion.getBat());
            Log.i(TAG, medicion.getBatVolt());

            Firebase.enviarMedicion(medicion.getIdsen(),medicion.getLat(),medicion.getLongi(),
                    medicion.getValor(),medicion.getMomento(),medicion.getBat(),medicion.getTemperatura(),
                    medicion.getDistancia(),medicion.getAccuracy(), medicion.getBatVolt());
            medicion.borrarMedicion();
            return 2;
        }
    }

    public static void sendLocalNotification(String message){ //TODO
        if (message != "") {
            String textOfContent = "";
            if (MySingletonClass.getInstance().getMensajeNotificacionTitle() == null) {
                textOfContent = Constants.name_notification;
            } else {
                textOfContent = MySingletonClass.getInstance().getMensajeNotificacionTitle();
            }

            OneTimeWorkRequest.Builder workBuilder = new OneTimeWorkRequest.Builder(NotificationWorker.class)
                    .setInputData(new Data.Builder().putString("text", message).putString("textOfContent", textOfContent)
                            .build());
            WorkManager.getInstance().enqueueUniqueWork("Notification",
                    ExistingWorkPolicy.REPLACE,
                    workBuilder.build());
            MySingletonClass.getInstance().setmensajeNotificacion(message);
        }
    }

    public static void sendLocalNotificationTitle(String textOfContent){
        if(textOfContent != "") {
            String message = "";
            if (MySingletonClass.getInstance().getmensajeNotificacion() == null) {
                message = Constants.MONITOR_SERVICE_DESCRIPTION;
            } else {
                 message = MySingletonClass.getInstance().getmensajeNotificacion();
            }
            OneTimeWorkRequest.Builder workBuilder = new OneTimeWorkRequest.Builder(NotificationWorkerTitle.class)
                    .setInputData(new Data.Builder().putString("text", message).putString("textOfContent", textOfContent)
                            .build());
            WorkManager.getInstance().enqueueUniqueWork("NotificationTitle",
                    ExistingWorkPolicy.REPLACE,
                    workBuilder.build());
            MySingletonClass.getInstance().setMensajeNotificacionTitle(textOfContent);
        }
    }

}
