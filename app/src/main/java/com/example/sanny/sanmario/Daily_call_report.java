package com.example.sanny.sanmario;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class Daily_call_report extends AppCompatActivity {
    private EditText userid , doctor_id,last_visted,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19;
    private Button submit;
    String PREF_NAME="UserData";
    String url_post="http://sanmario.atspace.cc/sanmario_api/daily_call_report";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_call_report);

        userid=(EditText) findViewById(R.id.userid_callreport);
        doctor_id=(EditText) findViewById(R.id.doctor_callreport);
        last_visted=(EditText) findViewById(R.id.last_visted);
        r1=(EditText) findViewById(R.id.product_presc);
        r2=(EditText) findViewById(R.id.gift);
        r3=(EditText) findViewById(R.id.town);
        r4=(EditText) findViewById(R.id.cov_from);
        r5=(EditText) findViewById(R.id.hq);
        r6=(EditText) findViewById(R.id.worked_with);
        r7=(EditText) findViewById(R.id.dcr_no);
        r8=(EditText) findViewById(R.id.saxe);
        r9=(EditText) findViewById(R.id.saod);
        r10=(EditText) findViewById(R.id.snac);
        r11=(EditText) findViewById(R.id.bust);
        r12=(EditText) findViewById(R.id.sado);
        r13=(EditText) findViewById(R.id.smsn);
        r14=(EditText) findViewById(R.id.scur);
        r15=(EditText) findViewById(R.id.slex);
        r16=(EditText) findViewById(R.id.sliv);
        r17=(EditText) findViewById(R.id.szan);
        r18=(EditText) findViewById(R.id.spnc);
        r19=(EditText) findViewById(R.id.snmx);
        submit=(Button) findViewById(R.id.submit_callreport);
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        userid.setText(sharedPref.getString("id",""));


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
                Toast.makeText(Daily_call_report.this,error+"",Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id", userid.getText().toString());
                params.put("doctor_id", doctor_id.getText().toString());
                params.put("last_visited", last_visted.getText().toString());
                params.put("product_presc",r1.getText().toString());
                params.put("gift",r2.getText().toString());
                params.put("town",r3.getText().toString());
                params.put("cov_from",r4.getText().toString());
                params.put("hq",r5.getText().toString());
                params.put("worked_with",r6.getText().toString());
                params.put("dcr_no",r7.getText().toString());
                params.put("saxe",r8.getText().toString());
                params.put("saod",r9.getText().toString());
                params.put("snac",r10.getText().toString());
                params.put("bust",r11.getText().toString());
                params.put("sado",r12.getText().toString());
                params.put("smsn",r13.getText().toString());
                params.put("scur",r14.getText().toString());
                params.put("slex",r15.getText().toString());
                params.put("sliv",r16.getText().toString());
                params.put("szan",r17.getText().toString());
                params.put("spnc",r18.getText().toString());
                params.put("snmx",r19.getText().toString());
                return params;
            }

        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);



    }


}