package es.upv.inodos;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;

import es.upv.inodos.utils.SystemUtils;
import io.realm.Realm;
import io.realm.RealmConfiguration;



public class MainApplication  extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        /* TODO DE MOMENTO NO
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("inodos.realm").build();
        Realm.setDefaultConfiguration(config);
        */
        SystemUtils.launchMonitorService(getApplicationContext());
        SystemUtils.enqueueSystemWorkers();

    }


}