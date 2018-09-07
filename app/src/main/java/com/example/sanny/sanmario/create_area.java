package com.example.sanny.sanmario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class create_area extends AppCompatActivity {

    private EditText name_area , create_route;

    private Button save , back;
    private String url_post="http://sanmario.atspace.cc/sanmario_api/workarea";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_area);


         name_area=(EditText) findViewById(R.id.work_area_name);

          save=(Button) findViewById(R.id.save_workarea);
          back=(Button) findViewById(R.id.back);


          back.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent=new Intent(create_area.this, Admin_panel.class);
                  startActivity(intent);
                  finish();
                  return;
              }
          });

          save.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  insertdoc();
              }
          });


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
                Toast.makeText(create_area.this,error+"",Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name",  name_area.getText().toString());
                params.put("create_area", create_route.getText().toString());
                return params;
            }

        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
