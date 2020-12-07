package es.upv.inodos;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;

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
        /*
        Context con = getApplicationContext();

        Intent intent = new Intent(con, DeviceWatcher.class); // explicite intent
        intent.setAction("es.upv.ACTION_FOUND");
        intent.putExtra("some.extra", "value"); // optional
        PendingIntent pendingIntent = PendingIntent.getBroadcast(con, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        BluetoothLeScannerCompat scanner = BluetoothLeScannerCompat.getScanner();
        no.nordicsemi.android.support.v18.scanner.ScanSettings settings = new no.nordicsemi.android.support.v18.scanner.ScanSettings.Builder()
                .setScanMode(ScanSettings.SCAN_MODE_LOW_POWER)
                .setReportDelay(10000)
                .build();
        List<ScanFilter> filters = new ArrayList<>();
        //filters.add(new ScanFilter.Builder().setServiceUuid(mUuid).build());
        scanner.startScan(filters, settings, con, pendingIntent);
*/
    }


}