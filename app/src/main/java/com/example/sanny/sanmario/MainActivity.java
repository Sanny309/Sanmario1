package com.example.sanny.sanmario;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText username , password;
    private Button Login , regsiter;
    String url_post="http://sanmario.atspace.cc/sanmario_api/login";

    private static final String TAG="DatabaseHelper";
    private static final String TABLE_NAME="gps";
    private  static final String COL1="ID";
    private static  final String COL2="USER_ID";
    private static final String COL3="latitude";
    private static final String COL4 ="Longitude";
    private static final String COL5="isupdated";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +COL2+",INTEGER"+ COL3+",INTEGER"+
//                COL4+",INTEGER"+
//                COL5+"ISUPDATE)";
        String createTable = "create table gps(id integer primary key autoincrement, user_id integer,latitude varchar(255),longitude varchar(255),isupdated integer)";
       // Log.d("sqlString",createTable);

        username=(EditText) findViewById(R.id.login_input_email);
        password=(EditText) findViewById(R.id.login_input_password);
        Login=(Button) findViewById(R.id.btn_login);
        regsiter=(Button) findViewById(R.id.btn_link_signup);
        regsiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, User_MapsActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        Login.setOnClickListener(this);
    }
        String isAdmin=null;
        String PREF_NAME = "UserData";
        private void insertsv(){
            StringRequest stringRequest= new StringRequest(Request.Method.POST, url_post, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                    if (response != null) {
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            JSONObject successObj = jsonObj.getJSONObject("loginSuccess");
                            Log.d("isAdmin", "admin " + successObj.get("admin"));
                            Log.d("id", "id " + successObj.get("id"));
                            Log.d("fullname", "fullname " + successObj.get("fullname"));
                            Log.d("workarea_id", "workarea_id" + successObj.get("workarea_id"));
                            Log.d("username", "username " + successObj.get("username"));
                            Log.d("mobile", "mobile" + successObj.get("mobile"));

                            isAdmin = successObj.getString("admin");

                            SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("isAdmin", isAdmin);
                            editor.putString("username",successObj.getString("username"));
                            editor.putString("id",successObj.getString("id"));
                            editor.putString("fullname",successObj.getString("fullname"));
                            editor.putString("mobile",successObj.getString("mobile"));
                            editor.putString("workarea_id",successObj.getString("workarea_id"));

                            editor.commit();



                            if(isAdmin!=null && isAdmin.contentEquals("1"))
                            {
                                Intent ai = new Intent(MainActivity.this,Admin_panel.class);
                                getApplicationContext().startActivity(ai);
                            }
                            else{
                                Intent ai = new Intent(MainActivity.this,user_main_Activity.class);
                                Intent intent=new Intent(MainActivity.this,gps_servies.class);
                                getApplicationContext().startActivity(ai);
                                startService(intent);
                            }

                            }catch (Exception e){

                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                 Toast.makeText(MainActivity.this,error+"",Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("username", username.getText().toString());
                    params.put("password", password.getText().toString());
                    return params;
                }

            };
              RequestQueue requestQueue= Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);

            };

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_login)
        {   insertsv();

        }
    }
}
