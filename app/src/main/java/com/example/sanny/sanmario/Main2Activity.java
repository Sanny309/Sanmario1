package com.example.sanny.sanmario;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity implements LocationListener {
    private LocationManager locationManager;
    private Button gps_list;
    String PREF_NAME = "UserData";
    DatabaseHelper databaseHelper;
    SharedPreferences sharedPref;
    String id = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        gps_list=(Button) findViewById(R.id.gps_location);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        gps_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity.this,ListDataActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
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
        Log.d("Location manager ","Location manager calling");
        Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
        locationManager.requestLocationUpdates(locationManager.NETWORK_PROVIDER, 400, 1, this);
        if(location!=null) {
            Log.d("Location manager ", "lat : " + location.getLatitude() + ", lng" + location.getLongitude());
        }
        else{
            Log.d("Location manager ", "lat : location not found");
        }
        sharedPref = getApplicationContext().getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        id=sharedPref.getString("id","");
    }



    @Override
    public void onLocationChanged(Location location) {

        double latitude=location.getLatitude();
        double logitude=location.getLongitude();
        databaseHelper.addData(id,String.valueOf(latitude),String.valueOf(logitude));

        Log.d("changed","lat: "+location.getLatitude()+", lng : "+location.getLongitude());


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
