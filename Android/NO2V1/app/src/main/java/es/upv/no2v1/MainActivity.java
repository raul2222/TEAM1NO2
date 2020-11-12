package es.upv.no2v1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {

    private static String ETIQUETA_LOG = "no2";
    private WebView webView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //quitar el navigation bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //mantener la pantalla encendida del movil encendida
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //dar los permisos necesarios para el funcionamiento de la aplicación
        darPermisosApp();
        //iniciar Webview
        iniciarWebView();
        //encender el avisador
        new Avisador(1, 10, this).encenderAvisador();
    }

    // pedir permisos
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void darPermisosApp(){

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
    }

    public void iniciarWebView(){
        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.loadUrl("http://www.beecop.ovh/no2/index.html");
    }


}