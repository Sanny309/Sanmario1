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

public class report_chemist_list extends AppCompatActivity {
    private String TAG = Admin_panel.class.getSimpleName();
    private ArrayList<ContextList> mContectList;

    private ListView Chemist_list;
    private ArrayList<ContextList>ContectList;
    ArrayList contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_chemist_list);


        Chemist_list=(ListView) findViewById(R.id.call_report_chesmistList);
        contactList = new ArrayList<>();

        new report_chemist_list.GetContacts().execute();

    }


    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(report_chemist_list.this," Data is loading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "http://sanmario.atspace.cc/sanmario_api/daily_call_report_chemist_list";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("daily_call_report _list\n");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String name = c.getString("name");
                        String orderValue= c.getString(" orderValue");
                        String cheque=c.getString("cheque");
                        String bank =c.getString("bank");
                        String user_id=c.getString("user_id");



                        // Phone node is JSON Object


                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value
                        contact.put("name", name);
                        contact.put("orderValue", orderValue);
                        contact.put("cheque",cheque);
                        contact.put("bank",bank);
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
            ListAdapter adapter = new SimpleAdapter(report_chemist_list.this, contactList,
                    R.layout.area_list, new String[]{"name","orderValue"},
                    new int[]{R.id.name1, R.id.mobile1});
            Chemist_list.setAdapter(adapter);
            Chemist_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id  ) {

                    HashMap<String, String> contact = new HashMap<>();
                    String user_id=contact.get("user_id");
                    String bank=contact.get("bank");
                    String cheque=contact.get("cheque");
                    String orderValue=contact.get("orderValue");
                    String name=contact.get("name");
                    Intent intent=new Intent(report_chemist_list.this,chemistList_info.class);
                    intent.putExtra("user_id",user_id );
                    intent.putExtra("bank",bank);
                    intent.putExtra("cheque",cheque);
                    intent.putExtra("orderValue",orderValue);
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
