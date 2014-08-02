package com.example.lightbike.app;

import android.app.Application;
import com.baidu.mapapi.SDKInitializer;

public class BikeApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
    }
}
