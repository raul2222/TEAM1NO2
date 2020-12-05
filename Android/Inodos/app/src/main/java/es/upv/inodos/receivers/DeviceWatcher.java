package es.upv.inodos.receivers;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanResult;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;

import es.upv.inodos.common.Constants;

import static android.content.ContentValues.TAG;

public class DeviceWatcher  extends BroadcastReceiver {




        @Override
        public void onReceive(Context context, Intent intent) {
            int bleCallbackType = intent.getIntExtra(BluetoothLeScanner.EXTRA_CALLBACK_TYPE, -1);
            if (bleCallbackType != -1) {
                Log.d(TAG, "Passive background scan callback type: "+bleCallbackType);
                ArrayList<ScanResult> scanResults = intent.getParcelableArrayListExtra(
                        BluetoothLeScanner.EXTRA_LIST_SCAN_RESULT);


                // Do something with your ScanResult list here.
                // These contain the data of your matching BLE advertising packets
            }
        }

}

