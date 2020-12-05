package es.upv.inodos;

import android.app.Application;

import es.upv.inodos.utils.SystemUtils;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainApplication  extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("blescan.realm").build();
        Realm.setDefaultConfiguration(config);

        SystemUtils.launchMonitorService(getApplicationContext());
        SystemUtils.enqueueSystemWorkers();
    }
}