package com.example.sanny.sanmario;

import android.Manifest;
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
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class gps_servies extends Service implements ConnectivityReceiver.ConnectivityReceiverListener {

    private LocationListener listener;
    private LocationManager locationManager;
    DatabaseHelper databaseHelper;
    String PREF_NAME = "UserData";
    SharedPreferences sharedPref;
    String id ;
    double latitude;
    double logitude;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;


    }
    @Override
    public void onCreate() {
        databaseHelper = new DatabaseHelper(getApplicationContext());

        sharedPref = getApplicationContext().getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        id=sharedPref.getString("id","");

        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latitude=location.getLatitude();
                logitude=location.getLongitude();


                Log.d("changed","lat: "+location.getLatitude()+", lng : "+location.getLongitude());
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        };

        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        //noinspection MissingPermission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }




        Location location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, listener );
        if(location!=null) {
            Log.d("Location manager ", "lat : " + location.getLatitude() + ", lng" + location.getLongitude());
        }
        else{
            Log.d("Location manager ", "lat : location not found");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(locationManager != null){
            //noinspection MissingPermission
            locationManager.removeUpdates(listener);
        }
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        StringRequest stringRequest;
        if (isConnected){
            String url_post="http://sanmario.atspace.cc/sanmario_api/create_usergps";

            stringRequest = new StringRequest(Request.Method.POST, url_post, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.d("Response : ", "" + response);


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Error", "error : " + String.valueOf(error));


                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("lat", String.valueOf(latitude));
                    params.put("long", String.valueOf(logitude));
                    params.put("user_id", id);
                    return params;
                }

            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }else
        {
            databaseHelper.addData(id,String.valueOf(latitude),String.valueOf(logitude));
        }

    }
}