package es.upv.no2v1;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.util.Log;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class DetectarBeacon {

    private static String ETIQUETA_LOG = "no2";
    private String miUUID = "TEAM-GTI-1NO2-3A";

    private BluetoothAdapter.LeScanCallback callbackLeScan = null;

    private static int contador = 300;
    private Medicion medicion;

    public DetectarBeacon(Medicion lamedicion){
        this.medicion = lamedicion;
    }

    public void encender(){
        Log.d(ETIQUETA_LOG,"comienza la busqueda del ibeacon");

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Log.d(ETIQUETA_LOG,"llamada a Buscar iBeacon recursiva");
                buscarEsteDispositivoBTLE(miUUID);
            }
        };
        timer.scheduleAtFixedRate(task, new Date(), 1000*30);

        //buscarEsteDispositivoBTLE(miUUID);
    }

    // --------------------------------------------------------------
    // --------------------------------------------------------------
    private void buscarEsteDispositivoBTLE(String dispositivoBuscado) {
        this.callbackLeScan = new BluetoothAdapter.LeScanCallback() {
            @Override
            public void onLeScan(BluetoothDevice bluetoothDevice, int rssi, byte[] bytes) {

                //
                // dispostivo encontrado
                //

                TramaIBeacon tib = new TramaIBeacon(bytes);
                String uuidString = Utilidades.bytesToString(tib.getUUID());

                if (uuidString.compareTo(dispositivoBuscado) == 0) {
                    // se para hasta una proxima llamada desde avisador
                    detenerBusquedaDispositivosBTLE();
                    mostrarInformacionDispositivoBTLE(bluetoothDevice, rssi, bytes);
                } else {
                    //Log.d( MainActivity.ETIQUETA_LOG, " * UUID buscado >" +
                    //        Utilidades.uuidToString( dispositivoBuscado ) + "< no concuerda con este uuid = >" +
                    //        uuidString + "<");
                    //Log.d(ETIQUETA_LOG, ".");
                }

            } // onLeScan()

        }; // new LeScanCallback

        //
        //
        //
        BluetoothAdapter.getDefaultAdapter().startLeScan(this.callbackLeScan);
    } // ()


    // --------------------------------------------------------------
    // --------------------------------------------------------------
    private void mostrarInformacionDispositivoBTLE(BluetoothDevice bluetoothDevice, int rssi, byte[] bytes) {

        TramaIBeacon tib = new TramaIBeacon(bytes);
        setearMedicion(Utilidades.bytesToHexString(tib.getMajor()), Utilidades.bytesToInt(tib.getMinor()));

    } // ()

    public void setearMedicion(String major, int minor) {
        int contadorMajor = Integer.parseInt(major.substring(3, 5), 16); // extraer contador
        //extraer valor bateria
        int bateria = Integer.parseInt(major.substring(0, 2), 16);
        /*
        Log.d(ETIQUETA_LOG, " Valor : " + minor);
        Log.d(ETIQUETA_LOG, " Mayor : " + major);
        Log.d(ETIQUETA_LOG, " contadorMajor : " + contadorMajor);
        Log.d(ETIQUETA_LOG, " Ultimo_contador : " + contador);
        Log.d(ETIQUETA_LOG, " Bateria : " + bateria);
        */
        if (contador != contadorMajor) { // si el contador es distinto enviamos la lectura del sensor
            Log.d(ETIQUETA_LOG, " Valor : " + minor);
            Log.d(ETIQUETA_LOG, "******* el contador es distinto. Bien!!! ********");
            contador = contadorMajor; // guardo el ultimo valor del contador en variable global

            medicion.setIdsen("1");
            medicion.setValor(String.valueOf(minor));
            medicion.setBat(String.valueOf(bateria));
            medicion.setMomento(String.valueOf( new Momento().getMomento()));
        }
    }

    // --------------------------------------------------------------
    // --------------------------------------------------------------
    private void detenerBusquedaDispositivosBTLE() {
        if (this.callbackLeScan == null) {

        }

        //
        //
        //
        BluetoothAdapter.getDefaultAdapter().stopLeScan(this.callbackLeScan);
        this.callbackLeScan = null;
    } // ()

}
