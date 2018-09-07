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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Order_Activity extends AppCompatActivity {

    private EditText userid,doctorid,date,from,to,place,tin,transport,m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11,m12,m13,m14,m15,m16,m17,m18,m19,m20;
    private EditText m21,m22,m23,m24,m25,m26,m27,m28,m29,m30,m31,m32;
    private Button msubmit;
    String url_post="http://sanmario.atspace.cc/sanmario_api/orders";
    String PREF_NAME = "UserData";
    String strDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_);

        userid=(EditText) findViewById(R.id.userid);
        doctorid=(EditText) findViewById(R.id.doctorId2);
        date=(EditText) findViewById(R.id.date);
        from=(EditText) findViewById(R.id.fromAddresss);
        to=(EditText) findViewById(R.id.toAddress);
        place=(EditText) findViewById(R.id.place);
        tin=(EditText) findViewById(R.id.tin);
        transport=(EditText) findViewById(R.id.transport);
        m1=(EditText) findViewById(R.id.m1);
        m2=(EditText) findViewById(R.id.m2);
        m3=(EditText) findViewById(R.id.m3);
        m4=(EditText) findViewById(R.id.m5);
        m5=(EditText) findViewById(R.id.m6);
        m6=(EditText) findViewById(R.id.m7);
        m7=(EditText) findViewById(R.id.m8);
        m8=(EditText) findViewById(R.id.m9);
        m9=(EditText) findViewById(R.id.m10);
        m10=(EditText) findViewById(R.id.m11);
        m11=(EditText) findViewById(R.id.m12);
        m12=(EditText) findViewById(R.id.m13);
        m13=(EditText) findViewById(R.id.m14);
        m14=(EditText) findViewById(R.id.m15);
        m15=(EditText) findViewById(R.id.m16);
        m16=(EditText) findViewById(R.id.m17);
        m17=(EditText) findViewById(R.id.m18);
        m18=(EditText) findViewById(R.id.m19);
        m19=(EditText) findViewById(R.id.m20);
        m20=(EditText) findViewById(R.id.m21);
        m21=(EditText) findViewById(R.id.m22);
        m22=(EditText) findViewById(R.id.m23);
        m23=(EditText) findViewById(R.id.m24);
        m24=(EditText) findViewById(R.id.m25);
        m25=(EditText) findViewById(R.id.m26);
        m26=(EditText) findViewById(R.id.m27);
        m27=(EditText) findViewById(R.id.m28);
        m28=(EditText) findViewById(R.id.m29);
        m29=(EditText) findViewById(R.id.m30);
        m30=(EditText) findViewById(R.id.m31);
        m31=(EditText) findViewById(R.id.m32);
        m32=(EditText) findViewById(R.id.m33);
        msubmit=(Button) findViewById(R.id.msubmit1);

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");
         strDate = mdformat.format(calendar.getTime());
        date.setText(strDate);
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        userid.setText(sharedPref.getString("id",""));

        msubmit.setOnClickListener(new View.OnClickListener() {
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
                Toast.makeText(Order_Activity.this,error+"",Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("doctor_id", doctorid.getText().toString());
                params.put("user_id", userid.getText().toString());
                params.put("date",date.getText().toString());
                params.put("from",from.getText().toString() );
                params.put("to",to.getText().toString());
                params.put("place",place.getText().toString());
                params.put("tin",tin.getText().toString());
                params.put("transport",transport.getText().toString());
                params.put("saniboost",m1.getText().toString());
                params.put("sanxone3mg",m2.getText().toString());
                params.put("sanxone4mg,",m3.getText().toString());
                params.put("sansod_p30ml",m4.getText().toString());
                params.put("sanmilk_ad3_1lt",m5.getText().toString());
                params.put("sanmilk_ad3_5lt",m6.getText().toString());
                params.put("sanmine_forte_1kg",m7.getText().toString());
                params.put("sanmine_forte_5kg",m8.getText().toString());
                params.put("sancure_h_120ml",m9.getText().toString());
                params.put("sancure_h_250ml",m10.getText().toString());
                params.put("sancure_h_500ml",m11.getText().toString());
                params.put("saniplex_1lt",m12.getText().toString());
                params.put("saniplex_5lt",m13.getText().toString());
                params.put("sanipac_30ml",m14.getText().toString());
                params.put("saniliv_forte_1lt",m15.getText().toString());
                params.put("saniliv_forte_5lt",m16.getText().toString());
                params.put("ssaniboost_free",m17.getText().toString());

                params.put("sanxone3mg_free",m18.getText().toString());
                params.put("sanxone4mg_free",m19.getText().toString());
                params.put("sansod_p30ml_free",m20.getText().toString());
                params.put("sanmilk_ad3_1lt_free",m21.getText().toString());
                params.put("sanmilk_ad3_5lt_free",m22.getText().toString());
                params.put("sanmine_forte_1kg_free,",m23.getText().toString());
                params.put("sanmine_forte_5kg_free",m24.getText().toString());
                params.put("sancure_h_120ml_free",m25.getText().toString());
                params.put("sancure_h_250ml_free",m26.getText().toString());
                params.put("sancure_h_500ml_free",m27.getText().toString());
                params.put("saniplex_1lt_free",m28.getText().toString());
                params.put("saniplex_5lt_free",m29.getText().toString());
                params.put("sanipac_30ml_free",m30.getText().toString());
                params.put("saniliv_forte_1lt_free",m31.getText().toString());
                params.put("saniliv_forte_5lt_free",m32.getText().toString());

                return params;
            }

        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

