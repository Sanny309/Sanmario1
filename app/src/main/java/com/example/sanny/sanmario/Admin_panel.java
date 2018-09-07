package com.example.sanny.sanmario;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;

public class Admin_panel extends AppCompatActivity {

    private Button ADDuser,userlist,create_workarea,workarea_list,create_route,route_list,user_location,doctor_list,log_out,orderlist;
    private TextView name;
    String PREF_NAME = "UserData";



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        ADDuser=(Button) findViewById(R.id.add_emp);
        userlist=(Button) findViewById(R.id.userlist);
        create_workarea=(Button) findViewById(R.id.create_area);
        workarea_list=(Button) findViewById(R.id.area_list);
        create_route=(Button) findViewById(R.id.create_route);
        route_list=(Button) findViewById(R.id.routes);
        user_location=(Button) findViewById(R.id.user_location);
        doctor_list=(Button) findViewById(R.id.doctor_list);
        log_out=(Button) findViewById(R.id.LOGOUT);
        name=(TextView) findViewById(R.id.admin_name);
        orderlist=(Button) findViewById(R.id.orderlist1);

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Boolean fullname = sharedPref.getString("fullname", "").isEmpty();
        sharedPref.contains("name");
        name.setText(sharedPref.getString("mobile", ""));



        ADDuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin_panel.this,Expances_list.class);

                startActivity(intent);
                finish();
                return;
            }
        });

        userlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin_panel.this, Admin_Activity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        user_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Admin_panel.this,User_MapsActivity.class);
                startActivity(intent);
                finish();
                return;

            }
        });
        create_workarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin_panel.this,Daily_call_report_list.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        workarea_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin_panel.this, Stockist_list.class);
                startActivity(intent);
                finish();
                return;
            }
        });


        create_route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin_panel.this,report_chemist_list.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        route_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin_panel.this, com.example.sanny.sanmario.route_list.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        doctor_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin_panel.this,user_panel.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        orderlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin_panel.this, Orderlist_Activity.class);
                startActivity(intent);
                finish();
                return;

            }
        });

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin_panel.this,MainActivity.class);
                startActivity(intent);


            }
        });



        }






}
