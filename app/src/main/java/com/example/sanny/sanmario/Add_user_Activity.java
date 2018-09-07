package com.example.sanny.sanmario;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


public class Add_user_Activity extends AppCompatActivity {

    private EditText usernmae , password, mobile , fullname;
    private EditText workarea_id;

    private Button adduser;
    String url_post="http://sanmario.atspace.cc/sanmario_api/user_register";


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_);

        usernmae=(EditText) findViewById(R.id.username_emp);
        password=(EditText) findViewById(R.id.password_emp);
        mobile=(EditText) findViewById(R.id.mobile);
        fullname=(EditText) findViewById(R.id.full_name);
        workarea_id=(EditText) findViewById(R.id.areaid);


        adduser=(Button)  findViewById(R.id.btn_adduser);


        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertsv();
            }
        });
        }

    private void insertsv(){

        StringRequest stringRequest= new StringRequest(Request.Method.POST, url_post, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Add_user_Activity.this,error+"",Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", usernmae.getText().toString());
                params.put("password", password.getText().toString());
                params.put("mobile", mobile.getText().toString());
                params.put("fullname",fullname.getText().toString());
                params.put("workarea_id",workarea_id.getText().toString());
                return params;
            }

        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


}
