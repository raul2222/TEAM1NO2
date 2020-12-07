package es.upv.inodos.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.UUID;

import es.upv.inodos.common.Constants;
import es.upv.inodos.data.SystemItem;
import es.upv.inodos.utils.SystemUtils;
import io.realm.Realm;


public class BootCompletedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(Constants.TAG, "--> BootCompletedReceiver received " + intent.getAction() + " action");

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        SystemItem systemItem = realm.createObject(SystemItem.class, UUID.randomUUID().toString());
        systemItem.setEvent(intent.getAction());
        systemItem.setTimestamp(System.currentTimeMillis());
        realm.commitTransaction();



        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            SystemUtils.launchMonitorService(context);
            SystemUtils.enqueueSystemWorkers();
        }
    }
}
