package com.example.sanny.sanmario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
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

import java.util.HashMap;
import java.util.Map;

public class Add_doctor_Activity extends AppCompatActivity {

    private EditText name,workarea_id,address,mobile,qualification;
    private Button Add_doctor;
    String url_post="http://sanmario.atspace.cc/sanmario_api/doctor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor_);
         //ADD BACK BUTTON
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



         name=(EditText) findViewById(R.id.doctor_name);
         workarea_id=(EditText) findViewById(R.id.doctor_workarea);
         address=(EditText) findViewById(R.id.doctor_address);
         mobile=(EditText) findViewById(R.id.doctor_mobile);
         qualification=(EditText) findViewById(R.id.doctor_qualification);

         Add_doctor=(Button)  findViewById(R.id.btn_adddoctor);

         Add_doctor.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 insertdoc();
             }
         });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        if(id== android.R.id.home){


        }

        return super.onOptionsItemSelected(item);

    }

    private void insertdoc(){

        StringRequest stringRequest= new StringRequest(Request.Method.POST, url_post, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Add_doctor_Activity.this,error+"",Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name.getText().toString());
                params.put("address", address.getText().toString());
                params.put("mobile", mobile.getText().toString());
                params.put("qualification",qualification.getText().toString());
                params.put("workarea_id",workarea_id.getText().toString());
                return params;
            }

        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
