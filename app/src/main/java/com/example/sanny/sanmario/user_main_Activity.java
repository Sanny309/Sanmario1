package com.example.sanny.sanmario;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class user_main_Activity extends AppCompatActivity {

    private Button addDoctor,doctor_list,area_list,route_list,order_doctor,order_list,logout,my_location,call_report;
    private TextView fullname , id , mobile,workarea_id;
    private Button arealist1;
    String PREF_NAME = "UserData";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_);

        addDoctor=(Button) findViewById(R.id.add_doctor);
        doctor_list=(Button) findViewById(R.id.doctor_list1);
        area_list=(Button) findViewById(R.id.doctor_area_list);
        route_list=(Button) findViewById(R.id.routes_list123);
        order_doctor=(Button) findViewById(R.id.doctor_order_list);
        order_list=(Button) findViewById(R.id.order_list);
        my_location=(Button) findViewById(R.id.your_location);
        logout=(Button) findViewById(R.id.LOGOUT2);
        fullname=(TextView) findViewById(R.id.fullname302);
        id=(TextView) findViewById(R.id.id302);
        mobile=(TextView) findViewById(R.id.mobile302);
        workarea_id=(TextView) findViewById(R.id.workarea302);
        arealist1=(Button) findViewById(R.id.area_list1);
        call_report=(Button) findViewById(R.id.call_report) ;



        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        fullname.setText(sharedPref.getString("fullname", ""));
        id.setText(sharedPref.getString("id",""));
        mobile.setText(sharedPref.getString("mobile",""));
        workarea_id.setText(sharedPref.getString("workarea_id",""));



        addDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(user_main_Activity.this,user_panel.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        order_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                


            }
        });

        doctor_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(user_main_Activity.this,Expenses_Activity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        area_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(user_main_Activity.this, daily_call_report_stockist.class);

                startActivity(intent);
                finish();
                return;
            }
        });

        route_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(user_main_Activity.this, Daily_call_report.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        my_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(user_main_Activity.this, call_report_chemist.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        arealist1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(user_main_Activity.this,Order_Activity.class);
                 startActivity(i);
                 finish();
                 return;
            }
        });

        call_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(user_main_Activity.this,Main2Activity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

    }
}
