package es.upv.inodos.activities;

import android.Manifest;
import android.app.PendingIntent;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import es.upv.inodos.MainApplication;
import es.upv.inodos.R;
import es.upv.inodos.adapters.SystemItemsAdapter;
import es.upv.inodos.data.SystemItem;
import es.upv.inodos.receivers.DeviceWatcher;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerCompat;
import no.nordicsemi.android.support.v18.scanner.ScanFilter;
import no.nordicsemi.android.support.v18.scanner.ScanSettings;


public class MainActivity extends AppCompatActivity {

    static final int REQUEST_COARSE_LOCATION = 200;

    Realm realm;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //listView = (ListView)findViewById(R.id.listView);
        checkLocationPermission();


        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
         //   setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        if (Build.VERSION.SDK_INT >= 21) {
         //   setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        getWindow().getDecorView().setSystemUiVisibility(R.color.black);
        //mantener la pantalla encendida del movil encendida
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //dar los permisos necesarios para el funcionamiento de la aplicación
        darPermisosApp();
        //iniciar Webview
        //iniciarWebView();
        //encender el avisador



    }

    // pedir permisos
    private void darPermisosApp(){

        String[] permissions = {
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.BLUETOOTH_ADMIN,
                Manifest.permission.BLUETOOTH,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,

        };

        int PERMISSION_ALL = 1;

        if (!hasPermissions(this, permissions)) {
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_ALL);
        }


    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onResume(){
        super.onResume();
        realm = Realm.getDefaultInstance();
        loadData();
        loadAdapter();
    }

    @Override
    public void onPause(){
        super.onPause();
        realm.close();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_COARSE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    //TODO re-request
                }
                break;
            }
        }
    }

    private void loadAdapter() {
        SystemItemsAdapter systemItemsAdapter = new SystemItemsAdapter(loadData());
        //listView.setAdapter(systemItemsAdapter);
    }

    private RealmResults<SystemItem> loadData() {
        return realm.where(SystemItem.class).findAll().sort("timestamp", Sort.DESCENDING);
    }

    protected void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_COARSE_LOCATION);
        }
    }


}
