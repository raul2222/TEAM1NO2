package es.upv.inodos.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;


import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.util.Timer;
import java.util.TimerTask;

import es.upv.inodos.R;
import es.upv.inodos.activities.MainActivity;
import es.upv.inodos.common.Constants;
import es.upv.inodos.data.Medicion;
import es.upv.inodos.receivers.BluetoothBroadcastReceiver;


import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
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


    BluetoothLeScanner btScanner;
    BluetoothAdapter btAdapter;

    private boolean mScanning;

    public MonitorService() {

    }

    public MonitorService(Context applicationContext) {
        super();
    }


    private ScanCallback leScanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            Log.e("Adress: ",result.getDevice().getAddress());
            Log.e("RSSI: ", " rssi: " + result.getRssi());
        }
    };


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void startScanning(){


        ScanSettings settings = new ScanSettings.Builder().setScanMode(ScanSettings.SCAN_MODE_LOW_POWER).build();

        BluetoothManager bluetoothManager =  (BluetoothManager) getApplicationContext().getSystemService(Context.BLUETOOTH_SERVICE);
        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
        Intent i = new Intent(this, BluetoothBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, i, FLAG_UPDATE_CURRENT);
        BluetoothLeScanner scanner = bluetoothAdapter.getBluetoothLeScanner();
        scanner.startScan(null, settings, pendingIntent);

        /*

        ScanSettings settings = (new ScanSettings.Builder().setScanMode(ScanSettings.SCAN_MODE_LOW_POWER)).build();

        List<ScanFilter> listOfFiters = new ArrayList<>();
        ScanFilter.Builder builder = new ScanFilter.Builder();
        ScanFilter filter = builder.build();
        listOfFiters.add(filter);

        BluetoothManager bluetoothManager =
                (BluetoothManager) getApplicationContext().getSystemService(Context.BLUETOOTH_SERVICE);
        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
        Intent intent2 = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 42, intent2, 0);
        //int scanRes =
        //btAdapter = BluetoothAdapter.getDefaultAdapter();

        btScanner = bluetoothAdapter.getBluetoothLeScanner();
        btScanner.startScan(listOfFiters, settings, pendingIntent);


        */

        // (scanRes is always 0 during my tests)


        /*

        btAdapter = BluetoothAdapter.getDefaultAdapter();
        btScanner = btAdapter.getBluetoothLeScanner();

        btScanner.startScan( null, new ScanSettings.Builder()
                        .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                        .setReportDelay(0) //when I use different value than 0, stop scanning
                        .build(),leScanCallback);

*/
    }

    public void stopScanning() {

        btScanner.stopScan(leScanCallback);

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
        //startScanning();

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

                // INTENTOS FALLIDOS
                /*
                Intent monitorServiceIntent2 = new Intent(getApplicationContext(), BleScanService.class);
                monitorServiceIntent2.putExtra("inputExtra", Constants.MONITOR_SERVICE_DESCRIPTION);
                ContextCompat.startForegroundService(getApplicationContext(), monitorServiceIntent2);
                */
/*

                OneTimeWorkRequest.Builder workBuilder = new OneTimeWorkRequest.Builder(BleWorker.class)
                        .setInputData(new Data.Builder()
                                .build());


                // This is just to complete the example
                WorkManager.getInstance().enqueueUniqueWork("ble",
                        ExistingWorkPolicy.REPLACE,
                        workBuilder.build());

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


}