package es.upv.inodos.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.List;

import es.upv.inodos.MainApplication;
import es.upv.inodos.R;
import es.upv.inodos.activities.MainActivity;

public class BleScanService extends Service {

    BluetoothLeScanner btScanner;
    BluetoothAdapter btAdapter;

    public static final String CHANNEL_ID = "ForegroundServiceChannel";



    private ScanCallback leScanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            Log.e("Adress: ",result.getDevice().getAddress());
            Log.e("RSSI: ", " rssi: " + result.getRssi());
        }
    };

    public void startScanning(){
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        btScanner = btAdapter.getBluetoothLeScanner();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                btScanner.startScan( null, new ScanSettings.Builder()
                        .setScanMode(ScanSettings.SCAN_MODE_LOW_POWER)
                        .setReportDelay(0) //when I use different value than 0, stop scanning
                        .build(),leScanCallback);
            }
        });
    }

    public void stopScanning() {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                btScanner.stopScan(leScanCallback);
            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
/*
        createNotificationChannel();
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Foreground Service")
                .setContentText("Scanning")
                .setPriority(Notification.PRIORITY_MAX)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);

*/
        Log.e("Adress: ","empieza");
        startScanning();
        return START_STICKY;
    }

    private void createNotificationChannel()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}