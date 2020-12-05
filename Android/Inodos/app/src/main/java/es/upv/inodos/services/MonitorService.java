package es.upv.inodos.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.os.ParcelUuid;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import es.upv.inodos.R;
import es.upv.inodos.activities.MainActivity;
import es.upv.inodos.common.Constants;
import es.upv.inodos.data.Medicion;
import es.upv.inodos.receivers.BluetoothBroadcastReceiver;
import es.upv.inodos.receivers.DeviceWatcher;
import es.upv.inodos.utils.Momento;
import es.upv.inodos.utils.SystemUtils;
import es.upv.inodos.utils.TramaIBeacon;
import es.upv.inodos.utils.Utilidades;
import no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerCompat;
import no.nordicsemi.android.support.v18.scanner.ScanFilter;
import no.nordicsemi.android.support.v18.scanner.ScanSettings;

import static android.content.ContentValues.TAG;
import static androidx.core.app.ActivityCompat.startActivityForResult;
import static es.upv.inodos.common.Constants.CHANNEL_ID;
import static es.upv.inodos.common.Constants.name_notification;


public class MonitorService extends Service {

    public int counter = 0;
    private Timer timer;
    private TimerTask timerTask;

    private String miUUID = "TEAM-GTI-1NO2-3A";

    // Objeto callback
    //private ScanCallback scanCallback = null; // for api >= 21

    private static int contador = 300;
    private Medicion medicion;




    private boolean mScanning;

    public MonitorService() {

    }

    public MonitorService(Context applicationContext) {
        super();
    }



    public void startScanning(){

        Context con = getApplicationContext();

        Intent intent = new Intent(con, DeviceWatcher.class); // explicite intent
        intent.setAction("es.upv.ACTION_FOUND");
        intent.putExtra("some.extra", "value"); // optional
        PendingIntent pendingIntent = PendingIntent.getBroadcast(con, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        BluetoothLeScannerCompat scanner = BluetoothLeScannerCompat.getScanner();
        no.nordicsemi.android.support.v18.scanner.ScanSettings settings = new no.nordicsemi.android.support.v18.scanner.ScanSettings.Builder()
                .setScanMode(ScanSettings.SCAN_MODE_LOW_POWER)
                .setReportDelay(10000)
                .build();
        List<ScanFilter> filters = new ArrayList<>();
        //filters.add(new ScanFilter.Builder().setServiceUuid(mUuid).build());
        scanner.startScan(filters, settings, con, pendingIntent);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        BluetoothBroadcastReceiver bluetoothBroadcastReceiver = new BluetoothBroadcastReceiver();
        registerReceiver(bluetoothBroadcastReceiver, new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED));
        String input = intent.getStringExtra("inputExtra");
        createNotificationChannel();
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(name_notification)
                .setContentText(input)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);

        startTimer();
        startScanning();

        return START_STICKY;
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        stopTimer();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Monitor Service Active",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    public void startTimer() {
        timer = new Timer();
        initializeTimerTask();
        timer.schedule(timerTask, Constants.TIMER_DELAY, Constants.TIMER_INTERVAL); //
    }

    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
                Log.i(Constants.TAG, "Service timer " + (counter++));
                /*
                Intent monitorServiceIntent2 = new Intent(getApplicationContext(), BleScanService.class);
                monitorServiceIntent2.putExtra("inputExtra", Constants.MONITOR_SERVICE_DESCRIPTION);
                ContextCompat.startForegroundService(getApplicationContext(), monitorServiceIntent2);
                */






            }
        };
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }


    public  void sendNotification(String text){
        Notification notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setContentTitle(name_notification)
                .setContentText(String.valueOf(text))
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build();

        startForeground(1, notification);
    }

    public double calculateDistance(int txPower, double rssi) {
        if (rssi == 0) {
            return -1.0; // if we cannot determine accuracy, return -1.
        }
        double ratio = rssi*1.0/txPower;
        if (ratio < 1.0) {
            return Math.pow(ratio,10);
        }
        else {
            double accuracy =  (0.89976)*Math.pow(ratio,7.7095) + 0.111;
            return accuracy;
        }
    }

    private String getDistance(double accuracy) {
        if (accuracy == -1.0) {
            return "Unknown";
        } else if (accuracy < 1) {
            return "Immediate";
        } else if (accuracy < 3) {
            return "Near";
        } else {
            return "Far";
        }
    }
}