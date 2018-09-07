package com.example.sanny.sanmario;


import android.content.Intent;
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

import java.util.ArrayList;
import java.util.HashMap;


public class Admin_Activity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();

  private ListView lv;
    ArrayList<HashMap<String, String>> contactList;
    String  id12;

    private Button Add_emp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_);
        Add_emp=(Button) findViewById(R.id.add_emp);




        Add_emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin_Activity.this,Add_doctor_Activity.class);
                startActivity(intent);
                finish();
                return;
            }
        });



        contactList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.userlist);



        new GetContacts().execute();
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(Admin_Activity.this," Data is loading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "http://sanmario.atspace.cc/sanmario_api/users_list";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("AllUsers");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String username = c.getString("username");
                        String id= c.getString("id");
                        String mobile=c.getString("mobile");
                        String latiude=c.getString("latitude");
                        String longitude=c.getString("langitude");
                        String workarea=c.getString("workarea_id");


                        // Phone node is JSON Object


                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value
                        contact.put("username", username);
                        contact.put("id",id);
                        contact.put("mobile",mobile);
                        contact.put("latitude",latiude);
                        contact.put("langitude",longitude);
                        contact.put("workarea_id",workarea);

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
            ListAdapter adapter = new SimpleAdapter(Admin_Activity.this, contactList,
                    R.layout.admin_list, new String[]{"username","id"},
                    new int[]{R.id.fullname123, R.id.mobile});
                    lv.setAdapter(adapter);
           lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                   Intent intent = new Intent(Admin_Activity.this,user_info.class);
                   HashMap<String, String> contact = new HashMap<>();
                   contact = contactList.get(position);
                 Log.d("before sending" , position+"ID : "+contact.get("id"));
                 String a=contact.get("id");
                 String username=contact.get("username");
                 String mobile=contact.get("mobile");
                 String latitude=contact.get("latitude");
                 String langitude=contact.get("langitude");
                 String workarea=contact.get("workarea_id");
                 intent.putExtra("userID",""+a);
                 intent.putExtra("username",username);
                 intent.putExtra("mobile",mobile);
                 intent.putExtra("latitude",latitude);
                 intent.putExtra("langitude",langitude);
                 intent.putExtra("workarea_id",workarea);

                 startActivity(intent);




               }
           });




        }
    }
}






