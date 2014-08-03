package com.example.lightbike.app;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.baidu.mapapi.SDKInitializer;
import com.example.lightbike.ble.BlunoLibrary;
import com.example.lightbike.ui.EmptyUI;
import com.example.lightbike.ui.StolenUI;

import java.util.logging.Handler;

public class BikeApplication extends BlunoLibrary {


    private static final String TAG = "BikeApplication";


    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);


    }

    @Override
    public void onConectionStateChange(connectionStateEnum theConnectionState) {
        switch (theConnectionState) {											//Four connection state
            case isConnected:
                Log.i(TAG, "Connected");
                break;
            case isConnecting:
                Log.i(TAG, "Connecting");
                break;
            case isToScan:
                Log.i(TAG, "Scan");
                break;
            case isScanning:
                Log.i(TAG, "Scanning");
                break;
            case isDisconnecting:
                Log.i(TAG, "isDisconnecting");
                break;
            default:
                break;
        }
    }

    @Override
    public void onSerialReceived(String theString) {
        if (theString.equals("S")) {
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setClass(this, StolenUI.class);
            startActivity(intent);
        }
    }

    public void sendCommand(String command) {
        serialSend(command);
    }

    public void tryConnect() {
        buttonScanOnClickProcess();
    }

    public void init(Context context) {
        setContext(context);
        serialBegin(115200);
        onCreateProcess();
        onResumeProcess();
    }

    public void bleClose() {
        onPauseProcess();
        onStopProcess();
        onDestroyProcess();
    }
}
