package es.upv.no2v1.iBeacon;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import es.upv.no2v1.Models.Medicion;
import es.upv.no2v1.Utils.Momento;
import es.upv.no2v1.Utils.Utilidades;
import es.upv.no2v1.iBeacon.TramaIBeacon;


public class DetectarBeacon {

    private static String ETIQUETA_LOG = "no2";
    private String miUUID = "TEAM-GTI-1NO2-3A";

    // Objeto callback
    private ScanCallback scanCallback = null; // for api >= 21

    private static int contador = 300;
    private Medicion medicion;

    public DetectarBeacon(Medicion lamedicion){
        this.medicion = lamedicion;
    }

    public void encender(){
        Log.d(ETIQUETA_LOG,"comienza la busqueda del ibeacon");
        buscarEsteDispositivoBTLEV21(miUUID);

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Log.d(ETIQUETA_LOG,"llamada a Buscar iBeacon recursiva");
                BluetoothAdapter.getDefaultAdapter().enable();
                buscarEsteDispositivoBTLEV21(miUUID);
            }
        };
        timer.scheduleAtFixedRate(task, new Date(), 1000*60);

        //buscarEsteDispositivoBTLE(miUUID);
    }



    private void buscarEsteDispositivoBTLEV21(String dispositivoBuscado) {
        // Declaración e implementación callback
        this.scanCallback = new ScanCallback() {
            // Se dispara cada vez que encuentra un dispositivo
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                // Escaneo de ciclo más alto ya que la app se ejecuta en segundo plano (ahorro de energía)
                super.onScanResult(ScanSettings.SCAN_MODE_LOW_LATENCY, result);
                // Dispostivo encontrado
                byte[] data = result.getScanRecord().getBytes();
                TramaIBeacon tib = new TramaIBeacon(data);
                String uuidToString = Utilidades.bytesToString(tib.getUUID());
                //Log.d(ETIQUETA_LOG, "API >= 21 - UUID dispositivo encontrado!!!!: " + tib.getUUID().toString());
                if ( uuidToString.compareTo(dispositivoBuscado)  == 0) {
                    // Detenemos la búsqueda de dispositivos
                    detenerBusquedaDispositivosBTLE();
                    // Mostramos la información de dispositivo
                    mostrarInformacionDispositivoBTLE(result.getDevice(), result.getRssi(), data);
                    // Tratamos el beacon obtenido

                } else {

                }
            } // onScanResult()

            // Se dispara si el escaneo falla por otros motivos
            @Override
            public void onScanFailed(int errorCode) {
                super.onScanFailed(errorCode);
                BluetoothAdapter.getDefaultAdapter().disable();
                encender();
                Log.d(ETIQUETA_LOG, "Error de callback con id: " + errorCode);
            } // onScanFailed()
        }; // new scanCallback

        // Inicialización callback
        BluetoothAdapter.getDefaultAdapter().getBluetoothLeScanner().startScan(this.scanCallback);
    } // ()

    /*
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

*/


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

        Log.d(ETIQUETA_LOG, " Valor : " + minor);
        Log.d(ETIQUETA_LOG, " Mayor : " + major);
        Log.d(ETIQUETA_LOG, " contadorMajor : " + contadorMajor);
        Log.d(ETIQUETA_LOG, " Ultimo_contador : " + contador);
        Log.d(ETIQUETA_LOG, " Bateria : " + bateria);

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
        if (this.scanCallback == null) {

        }else{

        }

        //
        //
        //
        BluetoothAdapter.getDefaultAdapter().getBluetoothLeScanner().stopScan(this.scanCallback);
        this.scanCallback = null;
    } // ()

}
