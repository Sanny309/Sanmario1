package com.example.sanny.sanmario;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;

public class gps_services extends Service implements LocationListener {
    private LocationManager locationManager;
    String PREF_NAME = "UserData";
    DatabaseHelper databaseHelper;

    public gps_services() {

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


    }

    @SuppressLint("MissingPermission")
    Location location=locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");


    }

    SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    Boolean user_id = sharedPref.getString("id", "").isEmpty();




    @Override
    public void onLocationChanged(Location location) {
        double latitude=location.getLatitude();
        double logitude=location.getLongitude();
//         databaseHelper.addData(String.valueOf(latitude));
//         databaseHelper.addData(String.valueOf(logitude));
//         databaseHelper.addData(String.valueOf(user_id));

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
