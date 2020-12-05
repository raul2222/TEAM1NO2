package es.upv.inodos.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.content.ContextCompat;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import es.upv.inodos.common.Constants;
import es.upv.inodos.services.MonitorService;
import es.upv.inodos.workers.SystemCheckWorker;


public class SystemUtils {

    public static boolean isServiceRunning(Context context, String serviceName){
        boolean serviceRunning = false;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> l = am.getRunningServices(50);
        Iterator<ActivityManager.RunningServiceInfo> i = l.iterator();
        while (i.hasNext()) {
            ActivityManager.RunningServiceInfo runningServiceInfo = i.next();
            Log.d(Constants.TAG, runningServiceInfo.service.getShortClassName());
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

        if (!es.upv.inodos.utils.SystemUtils.isServiceRunning(context, ".services.MonitorService")) {
            Intent monitorServiceIntent = new Intent(context, MonitorService.class);
            monitorServiceIntent.putExtra("inputExtra", Constants.MONITOR_SERVICE_DESCRIPTION);
            ContextCompat.startForegroundService(context, monitorServiceIntent);
        }
    }
}
