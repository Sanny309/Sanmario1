package com.example.sanny.sanmario;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class route_list extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    private ListView route_list123;
    private EditText workarea_id;
    private Button back12,route_list1234;
    String PREF_NAME="UserData";
    String B;
    String area="";
    ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_list);

        back12=(Button) findViewById(R.id.back3);
        route_list1234=(Button) findViewById(R.id.submit_route123);
        workarea_id=(EditText) findViewById(R.id.route_workarea_id);
        area=getIntent().getStringExtra("id");




        Log.d("",area);

        route_list1234.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent=new Intent(route_list.this,create_route.class);
             intent.putExtra("id",area);
             startActivity(intent);


            }
        });



        contactList = new ArrayList<>();
        route_list123 = (ListView) findViewById(R.id.Route_list123);
        new route_list.GetContacts().execute();

    }


    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(route_list.this," Data is loading",Toast.LENGTH_LONG).show();


        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response


            Uri.Builder builder = new Uri.Builder();
            builder.scheme("http")
                    .authority("sanmario.atspace.cc")
                    .appendPath("sanmario_api")
                    .appendPath("route_list")
                    .appendQueryParameter("id",area );
            String url = builder.build().toString();
            Log.d("URL",url);
            //Toast.makeText(route_list.this ,url,Toast.LENGTH_SHORT).show();
                String jsonStr = sh.makeServiceCall(url);


            Log.e(TAG, "Response from url: " + jsonStr);


            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray route = jsonObj.getJSONArray("route");

                    // looping through All Contacts
                    for (int i = 0; i < route.length(); i++) {
                        JSONObject c = route.getJSONObject(i);
                        String name = c.getString("route_name");
                        String workarea_id = c.getString("workarea_id");

                        // Phone node is JSON Object


                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value
                        contact.put("name", name);
                        contact.put("workarea_id", workarea_id);

                        // adding contact to contact list
                        contactList.add(contact);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListAdapter adapter = new SimpleAdapter(route_list.this, contactList,
                    R.layout.route_list309, new String[]{"name","workarea_id"},
                    new int[]{R.id.name1123, R.id.mobile1234});
            route_list123.setAdapter(adapter);
        }
    }

}
