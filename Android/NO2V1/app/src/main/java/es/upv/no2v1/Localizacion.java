package es.upv.no2v1;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;


import androidx.core.app.ActivityCompat;




public class Localizacion implements LocationListener {

    private double latitud;
    private double longitud;
    private long momentoultimaLocazion;
    protected LocationManager locationManager;
    private static String ETIQUETA_LOG = "no2";
    private Activity mainActivity;
    private CallbackLocalizacion recepcionNuevaLocalizacion;
    private Medicion medicion;
    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }
    public long getMomentoUltimaLocazion() {
        return momentoultimaLocazion;
    }

    public Localizacion(int tiempo, int distancia, Activity main, Medicion med) {
        this.latitud = 0;
        this.longitud = 0;
        this.momentoultimaLocazion = 0;
        this.medicion = med;
        mainActivity = main;
        locationManager = (LocationManager) mainActivity.getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, tiempo* 1000 * 60, distancia, (LocationListener) this);
    }

    @Override
    public void onLocationChanged(Location location) {
        latitud = location.getLatitude();
        longitud = location.getLongitude();
        momentoultimaLocazion = new Momento().getMomento();
        Log.d(ETIQUETA_LOG,"Momento:" + new Momento().getMomento());
        Log.d(ETIQUETA_LOG,"Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());

        CharSequence text = "Momento: " + String.valueOf(new Momento().getMomento()) + " Latitude: " + location.getLatitude() + ", Longitude: " + location.getLongitude();
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(mainActivity, text, duration);
        toast.show();
        medicion.setLat(String.valueOf(latitud));
        medicion.setLongi(String.valueOf(longitud));

        if(recepcionNuevaLocalizacion != null){
            //este el callBack para avisar de nuevo evento de locacion
            recepcionNuevaLocalizacion.nuevaLocacion();
        }
    }

    public CallbackLocalizacion getNueva() {
        return recepcionNuevaLocalizacion;
    }


    public void setCallback(CallbackLocalizacion nueva) {
        this.recepcionNuevaLocalizacion = nueva;
    }



}
