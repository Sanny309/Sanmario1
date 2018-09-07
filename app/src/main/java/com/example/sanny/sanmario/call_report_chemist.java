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

public class call_report_chemist extends AppCompatActivity {

    private EditText userid, name,ordervalue,cheque,bank;
    private  Button submit;
    String url_post="http://sanmario.atspace.cc/sanmario_api/daily_call_report_chemist";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_report_chemist);
        userid=(EditText) findViewById(R.id.user_chemist);
        name=(EditText) findViewById(R.id.name_chemist);
        ordervalue=(EditText) findViewById(R.id.ordervalue);
        cheque=(EditText) findViewById(R.id.cheque);
        bank=(EditText) findViewById(R.id.bank);
        submit=(Button) findViewById(R.id.submit_report_chemist);

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
                 Toast.makeText(call_report_chemist.this,error+"",Toast.LENGTH_SHORT).show();
             }
         }) {
             @Override
             protected Map<String, String> getParams() throws AuthFailureError {
                 Map<String, String> params = new HashMap<String, String>();
                 params.put("name", name.getText().toString());
                 params.put("user_id", userid.getText().toString());
                 params.put("orderValue", ordervalue.getText().toString());
                 params.put("cheque",cheque.getText().toString());
                 params.put("bank",bank.getText().toString());
                 return params;
             }

         };
         RequestQueue requestQueue= Volley.newRequestQueue(this);
         requestQueue.add(stringRequest);


     }

}
