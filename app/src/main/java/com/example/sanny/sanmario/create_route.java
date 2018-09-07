package com.example.sanny.sanmario;

import android.app.Activity;
import android.content.Intent;
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

public class create_route extends Activity {

    private EditText route , workarea_id1;
    private Button submit , back;
    private String url_post="http://sanmario.atspace.cc/sanmario_api/route";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_route);

        route=(EditText) findViewById(R.id.Route_name);
        workarea_id1=(EditText) findViewById(R.id.workarea_route_id);
        submit=(Button) findViewById(R.id.save_route);
        back=(Button) findViewById(R.id.back2);

        String id=getIntent().getStringExtra("id");
        workarea_id1.setText(id);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(create_route.this, Admin_panel.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
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
                Toast.makeText(create_route.this,error+"",Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name",  route.getText().toString());
                params.put("workarea_id", workarea_id1.getText().toString());
                return params;
            }

        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
