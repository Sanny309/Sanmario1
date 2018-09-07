package com.example.sanny.sanmario;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Stockist_list extends AppCompatActivity {

    private String TAG = Admin_panel.class.getSimpleName();
    private ArrayList<ContextList> mContectList;
    private ArrayList<ContextList>ContectList;
    ArrayList contactList;

    private ListView stockist_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stockist_list);

        stockist_list=(ListView) findViewById(R.id.Srockist_list);

        contactList = new ArrayList<>();

        new Stockist_list.GetContacts().execute();



    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(Stockist_list.this," Data is loading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "http://sanmario.atspace.cc/sanmario_api/daily_call_report_stockist_list";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("daily_call_report_stockist_list\n");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String name = c.getString("name");
                        String orderValue= c.getString(" orderValue");
                        String product=c.getString("product");
                        String user_id=c.getString("user_id");



                        // Phone node is JSON Object


                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value
                        contact.put("name", name);
                        contact.put("orderValue", orderValue);
                        contact.put("product",product);
                        contact.put("user_id",user_id);




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
            ListAdapter adapter = new SimpleAdapter(Stockist_list.this, contactList,
                    R.layout.area_list, new String[]{"name","orderValue"},
                    new int[]{R.id.name1, R.id.mobile1});
            stockist_list.setAdapter(adapter);
            stockist_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id  ) {

                    HashMap<String, String> contact = new HashMap<>();
                    String user_id=contact.get("user_id");
                    String orderValue=contact.get("orderValue");
                    String product=contact.get("product");
                    String name=contact.get("name");
                    Intent intent=new Intent(Stockist_list.this,Stockist_list_info.class);
                    intent.putExtra("user_id",user_id );
                    intent.putExtra("orderValue",orderValue);
                    intent.putExtra("product",product);
                    intent.putExtra("name",name);


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
