package es.upv.inodos.activities;

import android.Manifest;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import es.upv.inodos.R;
import es.upv.inodos.adapters.SystemItemsAdapter;
import es.upv.inodos.data.SystemItem;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;



public class MainActivity extends AppCompatActivity {

    static final int REQUEST_COARSE_LOCATION = 200;

    private static final String TAG = "BeaconsActivity";
    private static final int RESOLVE_USER_CONSENT = 111222;
    //private GoogleApiClient googleApiClient;
    private boolean resolvingError = false;

    Realm realm;


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

        //enableGoogleApiNearby();
        //backgroundSubscribe();
    }

    // Subscribe to messages in the background.

    /*
    private void backgroundSubscribe() {
        //Log.i(TAG, "Subscribing for background updates.");
       // SubscribeOptions options = new SubscribeOptions.Builder()
        //        .setStrategy(Strategy.BLE_ONLY)
       //         .build();
       // Nearby.getMessagesClient(this).subscribe(getPendingIntent(), options);
    }

    private PendingIntent getPendingIntent() {
        //return PendingIntent.getBroadcast(this, 0, new Intent(this, BeaconMessageReceiver.class),
        //        PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void enableGoogleApiNearby(){
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Nearby.MESSAGES_API)
                .addApi(Nearby.CONNECTIONS_API)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }
*/

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
