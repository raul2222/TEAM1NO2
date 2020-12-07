package es.upv.inodos.receivers;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ScanBleReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        String action = intent.getAction();
        Log.i("Action", action);

        if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
            //statusTextView.setText("Done!");
            //searchButton.setEnabled(true);
        } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

            String name = device.getName();
            String address = device.getAddress();
            String rssi = Integer.toString(intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, Short.MIN_VALUE));
            Log.i("Device FOUND!", "Name: " + name + " Address: " + address + " RSSI: " + rssi);

        }


    }
}
