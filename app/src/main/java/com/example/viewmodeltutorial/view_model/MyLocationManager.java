package com.example.viewmodeltutorial.view_model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import java.util.List;

public class MyLocationManager implements LifecycleObserver {
    private Context context;
    private LocationListenerCallback callBack;
    private LocationManager mLocationManager;

    public MyLocationManager(Context context, LocationListenerCallback callBack) {
        this.context = context;
        this.callBack = callBack;
    }

    @SuppressLint("MissingPermission")
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void start() {
        mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, callBack);

        Location lastLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (lastLocation != null) {
            callBack.onLocationChanged(lastLocation);
        }
        Toast.makeText(context, "MyLocationManager started", Toast.LENGTH_SHORT).show();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void stop() {
        if (mLocationManager == null) {
            return;
        }
        mLocationManager.removeUpdates(callBack);
        mLocationManager = null;
        Toast.makeText(context, "MyLocationManager paused", Toast.LENGTH_SHORT).show();
    }
}
