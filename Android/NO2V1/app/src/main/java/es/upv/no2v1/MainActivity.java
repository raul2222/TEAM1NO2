package es.upv.no2v1;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {

    private static String ETIQUETA_LOG = "no2";
    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent serviceIntent = new Intent(this, Foreground.class);
        serviceIntent.putExtra("inputExtra", "NO2_FOREGROUND");
        ContextCompat.startForegroundService(this, serviceIntent);


        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        getWindow().getDecorView().setSystemUiVisibility(R.color.black);
        //mantener la pantalla encendida del movil encendida
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //dar los permisos necesarios para el funcionamiento de la aplicación
        darPermisosApp();
        //iniciar Webview
        iniciarWebView();
        //encender el avisador
        new Avisador(1, 25, this).encenderAvisador();
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
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



/*

        int permissionCheck = PackageManager.PERMISSION_GRANTED;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[].{Manifest.permission.READ_PHONE_STATE}, 0);
            requestPermissions(new String[].{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }

        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this,
                    permissions[i]);

            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                Log.e("main", "denied" + permissions[i]);
                ActivityCompat.requestPermissions(this, permissions, i);
                if
                (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        permissions[i])) {
                    Log.e("main", "shouldshow" + permissions[i]);

                } else {
                    Log.e("main", "requesting" + permissions[i]);
                    ActivityCompat.requestPermissions(this, permissions, i);

                }
                break;
            } else {
                Log.e("main", "granted" + permissions[i]);

            }

        }




        int permissionCheck = ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, ACCESS_FINE_LOCATION)){
                Log.d(ETIQUETA_LOG,  "The permission to get BLE location data is required");
            }else{
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION}, 1);
            }
        }else{
            Log.d(ETIQUETA_LOG,  "Location permissions already granted");
        }

        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);

         */
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



    private void iniciarWebView(){
        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new MyBrowser());

        webView.getSettings().setJavaScriptEnabled(true);
        //webView.getSettings().setLoadWithOverviewMode(true);
        //webView.getSettings().setUseWideViewPort(true);

        webView.loadUrl("http://www.beecop.ovh/no2/inodos/index.html");
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


}