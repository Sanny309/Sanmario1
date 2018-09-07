package com.example.sanny.sanmario;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class workarea_list extends AppCompatActivity {
    private String TAG = Admin_panel.class.getSimpleName();
    private Button back1;
    private ListView workarea_list1;
    private static final String EXTRA_ID="id";


    String PREF_NAME = "UserData";

    int areaid;
    boolean admin;
    private ArrayList<ContextList>mContectList;
    String B;
    ArrayList contactList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workarea_list);
        back1=(Button) findViewById(R.id.back1);

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

         B=sharedPref.getString("isAdmin", "");
         String admin=sharedPref.getString("workarea","");

        back1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

              Intent intent=new Intent(workarea_list.this,create_area.class);
              startActivity(intent);
              finish();
              return;

           }
       });


        contactList = new ArrayList<>();
        workarea_list1 = (ListView) findViewById(R.id.workarea_list1);
        new workarea_list.GetContacts().execute();

    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(workarea_list.this," Data is loading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "http://sanmario.atspace.cc/sanmario_api/workarea_list";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("workarea");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String name = c.getString("name");
                        String id = c.getString("id");


                        // Phone node is JSON Object


                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value
                        contact.put("name", name);
                        contact.put("id", id);


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
            ListAdapter adapter = new SimpleAdapter(workarea_list.this, contactList,
                    R.layout.area_list, new String[]{"name","id"},
                    new int[]{R.id.name1, R.id.mobile1});
            workarea_list1.setAdapter(adapter);
            workarea_list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id  ) {

                    HashMap<String, String> contact = new HashMap<>();
                    String a=contact.get("id");
                    Intent intent=new Intent(workarea_list.this,route_list.class);
                    intent.putExtra("id",a );


                    startActivity(intent);


                    Log.d("click","Hey ! i am Clicked"+contactList.get(position));
                }
            });
        }
        private int booltoint(boolean b)
        {
            return b?1:0;
        }
    }


}
