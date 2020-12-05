package es.upv.inodos.receivers;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import es.upv.inodos.common.Constants;
import es.upv.inodos.data.SystemItem;
import es.upv.inodos.workers.NotificationWorker;
import io.realm.Realm;


public class BluetoothBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(Constants.TAG, "--> BluetoothBroadcastReceiver received " + intent.getAction() + " action");

        int res = 0;
        String event = intent.getAction();
        final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
        switch(state) {
            case BluetoothAdapter.STATE_OFF:
                event += " - STATE_OFF";
                break;
            case BluetoothAdapter.STATE_TURNING_OFF:
                event += " - STATE_TURNING_OFF";
                break;
            case BluetoothAdapter.STATE_ON:
                res = 1;
                event += " - STATE_ON";
                break;
            case BluetoothAdapter.STATE_TURNING_ON:
                event += " - STATE_TURNING_ON";
                break;
        }

        String value = "";
        if (res == 1){
            value = "Bluetooth connected";
        }else{
            value = "Bluetooth ins'n connected";
        }

        OneTimeWorkRequest.Builder workBuilder = new OneTimeWorkRequest.Builder(NotificationWorker.class)
                .setInitialDelay(6, TimeUnit.SECONDS)
                .setInputData(new Data.Builder().putString("text",value)
                        .build());


        // This is just to complete the example
        WorkManager.getInstance().enqueueUniqueWork("Notification",
                ExistingWorkPolicy.REPLACE,
                workBuilder.build());


        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        SystemItem systemItem = realm.createObject(SystemItem.class, UUID.randomUUID().toString());
        systemItem.setEvent(event);
        systemItem.setTimestamp(System.currentTimeMillis());
        realm.commitTransaction();
    }
}
