package com.example.sanny.sanmario;

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

public class daily_call_report_stockist extends AppCompatActivity {

    private EditText userid,name,product,ordervalue;
    private Button submit;
    String url_post="http://sanmario.atspace.cc/sanmario_api/daily_call_report_stockist";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_call_report_stockist);

        userid=(EditText) findViewById(R.id.user_id_report_stockist);
        name=(EditText) findViewById(R.id.name_report_stockist);
        product=(EditText) findViewById(R.id.product);
        ordervalue=(EditText) findViewById(R.id.orderValue1);
        submit=(Button) findViewById(R.id.submit_report_stockist);

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
                Toast.makeText(daily_call_report_stockist.this,error+"",Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id", userid.getText().toString());
                params.put("name", name.getText().toString());
                params.put("product", product.getText().toString());
                params.put("orderValue",ordervalue.getText().toString());
                return params;
            }

        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
