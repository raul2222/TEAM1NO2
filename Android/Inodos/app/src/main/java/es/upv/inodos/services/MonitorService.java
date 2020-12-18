package es.upv.inodos.services;
import android.Manifest;
import android.app.AsyncNotedAppOp;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import java.util.Timer;
import java.util.TimerTask;
import es.upv.inodos.R;
import es.upv.inodos.activities.MainActivity;
import es.upv.inodos.common.Constants;
import es.upv.inodos.common.MySingletonClass;
import es.upv.inodos.data.Medicion;
import es.upv.inodos.receivers.BluetoothBroadcastReceiver;
import es.upv.inodos.utils.SystemUtils;
import es.upv.inodos.utils.Utilidades;


// *********************************************************
//  RAUL SANTOS LOPEZ       07/12/2020
// *********************************************************

public class MonitorService extends Service implements LocationListener {
    public int counter = 0;
    private Timer timer;
    private TimerTask timerTask;
    protected LocationManager locationManager;
    private Medicion medicion;
    private BluetoothAdapter blueToothAdapter;
    private long startScanAnterior = 0;
    private long startScanTotalTime = 0;
    private int numCallGps = 0;
    private String estadoGps = "";

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

        Notification notification = new NotificationCompat.Builder(this, Constants.CHANNEL_ID)
                .setContentTitle(Constants.name_notification)
                .setContentText(input)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setColor(0xff123456)
                .build();

        startForeground(1, notification);

        startTimer();
        startScanning();
        medicion = new Medicion();
        MySingletonClass.getInstance().setContador_Sensor(0);
        MySingletonClass.getInstance().setHibernation(false);

        return START_STICKY;
    }


    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    Constants.CHANNEL_ID,
                    "Monitor Service Active",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
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

    private void startScanning() {
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
        //configureLocationUpdates(Constants.Interval_Lectura_GPS, Constants.distancia_GPS);
    }

    private void configureLocationUpdates(int minTime, int distance){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime * 1000 * 60, distance, (LocationListener) this);
    }

    private void disableGpsUpdates(){
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        numCallGps++;
        // ojo solo si el radio es menor a 25 metros
        if (location.getAccuracy() < Constants.Min_Radius_GPS_Accuracy) {
            medicion.setLat(String.valueOf(location.getLatitude()));
            medicion.setLongi(String.valueOf(location.getLongitude()));
            medicion.setAccuracy(String.valueOf(location.getAccuracy()));
            Log.d(ContentValues.TAG, "Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
            int res = SystemUtils.enviarDatosServidor(medicion);
            SystemUtils.sendLocalNotificationTitle("Precisión GPS: "  +  location.getAccuracy());
        }
    }
    @Override
    public void onProviderEnabled(String provider) {
        Log.d("hello","onProviderEnabled");
        SystemUtils.sendLocalNotificationTitle("Locación activada");
    }
    @Override
    public void onProviderDisabled(String provider) {
        Log.d("hello","onProviderDisabled");
        SystemUtils.sendLocalNotificationTitle("Alerta Locación desactivada");
    }

    public void startTimer() {
        timer = new Timer();
        initializeTimerTask();
        timer.schedule(timerTask, Constants.TIMER_DELAY, Constants.TIMER_INTERVAL); //
    }

    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
                counter++;
                /*if((counter% Constants.Tiempo_Envios) == 0) {
                    // enviar Datos al servidor
                    SystemUtils.enviarDatosServidor(medicion);
                }*/

                if((counter%15 == 0)){
                    SystemUtils.sendLocalNotification("Tiempo BLE: " + (startScanTotalTime/1000) +
                            " Total Gps: " + numCallGps);
                    startScanAnterior = 0; startScanTotalTime = 0; numCallGps = 0;
                }
                Log.i(Constants.TAG, "Service timer " + (counter));
                blueToothAdapter.startDiscovery();
            }
        };
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void checkGps() {
        if (MySingletonClass.getInstance().isHibernation()) {
            if (estadoGps != "hibernacion") {
                SystemUtils.sendLocalNotificationTitle("Hibernation");
                disableGpsUpdates();
            }
            estadoGps = "hibernacion";
        } else {
            if (estadoGps != "alto") {
                configureLocationUpdates(Constants.Interval_Lectura_GPS, Constants.distancia_GPS);
                SystemUtils.sendLocalNotificationTitle("High performance");
            }
            estadoGps = "alto";
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
                    checkGps();
                } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                    startScanTotalTime = startScanTotalTime + (System.currentTimeMillis() - startScanAnterior);
                    //Intent newIntent = new Intent(MainActivity.this, DeviceListActivity.class); //newIntent.putParcelableArrayListExtra("device.list", mDeviceList); //startActivity(newIntent);
                } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    if(device.getAddress().equals(Constants.SerialNumber)) {
                        blueToothAdapter.cancelDiscovery();
                        String name = device.getName(); String address = device.getAddress();
                        String rssi = Integer.toString(intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, Short.MIN_VALUE));
                        double distant = Utilidades.calculateDistance(-55,Double.parseDouble(rssi));
                        Utilidades.parseRead(name, String.format("%.2f", distant), medicion);
                        Log.i("Device FOUND!", "Name: " + name + " Address: " + address + " RSSI: " + rssi + " Distancia: " + String.format("%.2f", distant) + " meters");
                    }
            }
        }
    };



}