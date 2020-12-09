package es.upv.inodos.services;
import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import java.util.Timer;
import java.util.TimerTask;
import es.upv.inodos.R;
import es.upv.inodos.activities.MainActivity;
import es.upv.inodos.common.Constants;
import es.upv.inodos.data.Medicion;
import es.upv.inodos.receivers.BluetoothBroadcastReceiver;
import es.upv.inodos.utils.Momento;
import es.upv.inodos.utils.Utilidades;
import es.upv.inodos.workers.NotificationWorker;

import static android.content.ContentValues.TAG;
import static es.upv.inodos.common.Constants.CHANNEL_ID;
import static es.upv.inodos.common.Constants.Tiempo_Envios;
import static es.upv.inodos.common.Constants.distancia;
import static es.upv.inodos.common.Constants.name_notification;
import static es.upv.inodos.common.Constants.tiempo;
import static es.upv.inodos.utils.SystemUtils.enviarDatosServidor;
import static es.upv.inodos.utils.SystemUtils.sendLocalNotification;

// *********************************************************
//  RAUL SANTOS LOPEZ       07/12/2020
// *********************************************************

public class MonitorService extends Service implements LocationListener {
    public int counter = 0;
    private int contadorUltimaLecturaDelSensor = 0;
    private Timer timer;
    private TimerTask timerTask;

    protected LocationManager locationManager;
    private static int contador = 300;
    private Medicion medicion;
    BluetoothAdapter blueToothAdapter;
    private long startScanAnterior = 0;
    private long startScanTotalTime = 0;
    private int numCallGps = 0;

    public MonitorService() {    }

    public MonitorService(Context applicationContext) {
        super();
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
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setColor(0xff123456)
                .build();

        startForeground(1, notification);

        startTimer();
        startScanning();
        medicion = new Medicion();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopTimer();
    }

    public void startScanning() {
        blueToothAdapter = BluetoothAdapter.getDefaultAdapter();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothDevice.EXTRA_DEVICE);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mReceiver, intentFilter);

        // configurar localizacion
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, tiempo * 1000 * 60, distancia, (LocationListener) this);
    }

    @Override
    public void onLocationChanged(Location location) {
        numCallGps++;
        Log.d(TAG, "exactitud: " + String.valueOf(location.getAccuracy()) + "    Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
        // ojo solo si el radio es menor a 30 metros
        if (location.getAccuracy() < 30) {
            //latitud = location.getLatitude();longitud = location.getLongitude();
            medicion.setLat(String.valueOf(location.getLatitude()));
            medicion.setLongi(String.valueOf(location.getLongitude()));
            medicion.setAccuracy(String.valueOf(location.getAccuracy()));
            //Log.d(ETIQUETA_LOG, "Momento:" + new Momento().getMomento());
            Log.d(TAG, "Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
            int res = enviarDatosServidor(medicion);
            // esto es una notificacion con la lanzadera de notificaciones
            //sendLocalNotification(String.valueOf(location.getAccuracy()));
        }
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
                if((counter%Tiempo_Envios) == 0) {
                    // enviar Datos al servidor
                    enviarDatosServidor(medicion);
                }
                Log.i(Constants.TAG, "Service timer " + (counter++));
                blueToothAdapter.startDiscovery();
                if((counter%60 == 0)){
                    sendLocalNotification("Tiempo BLE: " + String.valueOf(startScanTotalTime/1000) +
                            "Total Gps: " + String.valueOf(numCallGps));
                }
            }
        };
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
                if (state == BluetoothAdapter.STATE_ON) { }
                } else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                    startScanAnterior = System.currentTimeMillis();
                } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                    startScanTotalTime = startScanTotalTime + (System.currentTimeMillis() - startScanAnterior);
                    //Log.i(TAG, String.valueOf(startScanTotalTime/1000));
                    //Intent newIntent = new Intent(MainActivity.this, DeviceListActivity.class); //newIntent.putParcelableArrayListExtra("device.list", mDeviceList); //startActivity(newIntent);
                } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    if(device.getAddress().equals(Constants.SerialNumber)) {
                        blueToothAdapter.cancelDiscovery();
                        String name = device.getName();
                        String address = device.getAddress();
                        String rssi = Integer.toString(intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, Short.MIN_VALUE));
                        double distant = Utilidades.calculateDistance(-55,Double.parseDouble(rssi));
                        parseRead(name, String.format("%.2f", distant));
                        Log.i("Device FOUND!", "Name: " + name + " Address: " + address + " RSSI: " + rssi + " Distancia: " + String.format("%.2f", distant) + " meters");
                    }
            }
        }
    };

    private void parseRead(String name, String distan){
        String[] parts = name.split(" ");
        String cont = parts[0];
        String valor = parts[1];
        String temperatura = parts[2];
        String bateria = parts[3];
        if(Integer.valueOf(cont) != Integer.valueOf(parts[0])) { // si no se repite el cont
            contadorUltimaLecturaDelSensor = Integer.valueOf(parts[0]);
            medicion.setValor(valor);
            medicion.setBat(bateria);
            medicion.setContador(Integer.valueOf(cont));
            medicion.setTemperatura(temperatura);
            medicion.setDistancia(distan);
            medicion.setIdsen("1");
            medicion.setMomento(String.valueOf(new Momento().getMomento()));
        }
    }

}