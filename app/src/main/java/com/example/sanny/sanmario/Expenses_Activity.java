package com.example.sanny.sanmario;

import android.content.Context;
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

public class Expenses_Activity extends AppCompatActivity {

    private EditText route , date, distnace, allowance,fare,others,userId;
    private Button submit;
    String strDate;
    String PREF_NAME="UserData";

    String url_post="http://sanmario.atspace.cc/sanmario_api/travelexpence";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_);

        route=(EditText) findViewById(R.id.route);
        date=(EditText) findViewById(R.id.date1);
        distnace=(EditText)findViewById(R.id.distnace);
        allowance=(EditText) findViewById(R.id.allowance);
        fare=(EditText) findViewById(R.id.fare);
        others=(EditText) findViewById(R.id.other);
        userId=(EditText)findViewById(R.id.Expenses_userid) ;
        submit=(Button) findViewById(R.id.submit_expenses);

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");
        strDate = mdformat.format(calendar.getTime());
        date.setText(strDate);

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        userId.setText(sharedPref.getString("id",""));

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
                Toast.makeText(Expenses_Activity.this,error+"",Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("route", route.getText().toString());
                params.put("date", date.getText().toString());
                params.put("distance", distnace.getText().toString());
                params.put("fare",fare.getText().toString());
                params.put("others",others.getText().toString());
                params.put("allowance",allowance.getText().toString());
                params.put("user_id" , userId.getText().toString());
                return params;
            }

        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
