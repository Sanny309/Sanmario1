package com.example.sanny.sanmario;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
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

public class MyService extends Service implements ConnectivityReceiver.ConnectivityReceiverListener {

     DatabaseHelper databaseHelper;

    String url_post = "http://sanmario.atspace.cc/sanmario_api/create_usergps";

    public MyService() {




    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        send_(isConnected);
    }

    private void send_(boolean isConnected) {
       if (isConnected==true) {
           isertdoc();

           if (databaseHelper.getItemID("").equals("1")){

               databaseHelper.deleteData("");

           }

       }

    }


    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        send_(isConnected);
     if (isConnected==true){
    Log.d(String.valueOf(isConnected), "is connecte is working: ");
    }else {
     Log.d(String.valueOf(isConnected),"isconnted is not working");
     }

    }

    public void isertdoc(){

        StringRequest mstringRequest = new StringRequest(Request.Method.POST, url_post, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("Response : ", ""+response);

                if (response != null) {

                    databaseHelper.updateData("1","","","");


                    }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", "error : "+String.valueOf(error));


            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("lat",databaseHelper.getData().getString(3));
                params.put("long", databaseHelper.getData().getString(4));
                params.put("user_id", databaseHelper.getData().getString(1));
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(mstringRequest);


    }

  //  public  getData(){
        String TABLE_NAME="gps";
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String query = "SELECT * FROM " +  TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
  //      return data;
   // }

}
