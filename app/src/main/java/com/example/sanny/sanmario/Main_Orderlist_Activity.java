package com.example.sanny.sanmario;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.transform.Templates;

public class Main_Orderlist_Activity extends AppCompatActivity {

    private ListView orderlist12;

    String area = "";
    private ArrayList<ContextList> mContectList;
    ArrayList contactList;
    private String TAG = Admin_panel.class.getSimpleName();
    private Integer c;
    String name;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__orderlist_);

        orderlist12=(ListView) findViewById(R.id.orderlist12);

        area =  getIntent().getStringExtra("userID");
        Log.d("ID ","Id = "+area);
        Log.d("extraid ","id : "+getIntent().getExtras().getString("userID"));


        contactList = new ArrayList<>();

        new Main_Orderlist_Activity.GetContacts().execute();
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(Main_Orderlist_Activity.this, " Data is loading", Toast.LENGTH_LONG).show();

        }
          int a=23;
        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("http")
                    .authority("sanmario.atspace.cc")
                    .appendPath("sanmario_api")
                    .appendPath("order_list")
                    .appendQueryParameter("id",area );
            //http://sanmario.atspace.cc/sanmario_api/order_list?id=23
            String url = builder.build().toString();
            Log.d("URL",url);
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("orders");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                         name = c.getString("id");
                         id = c.getString("name");


                        // Phone node is JSON Object


                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value
                        contact.put("id", name);
                        contact.put("doctor_id", id);


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

            SimpleAdapter adapter=new SimpleAdapter(Main_Orderlist_Activity.this,contactList,R.layout.area_list,new String[]{"id","doctor_id"},
                    new int[]{R.id.name1, R.id.mobile1});

            orderlist12.setAdapter(adapter);




        }


    }



}
