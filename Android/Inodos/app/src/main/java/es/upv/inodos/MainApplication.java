package es.upv.inodos;

import android.Manifest;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.SystemClock;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

import es.upv.inodos.receivers.DeviceWatcher;
import es.upv.inodos.services.BleScanService;
import es.upv.inodos.utils.SystemUtils;
import io.realm.Realm;
import io.realm.RealmConfiguration;


public class MainApplication  extends Application {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("inodos.realm").build();
        Realm.setDefaultConfiguration(config);



        SystemUtils.launchMonitorService(getApplicationContext());
        SystemUtils.enqueueSystemWorkers();




    }






}