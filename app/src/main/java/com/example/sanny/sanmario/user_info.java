package com.example.sanny.sanmario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class user_info extends AppCompatActivity {

    private TextView id , username , mobile, workid;
    private Button gps , order;
     String Id,username1, mmobile,latiude,longitude,workareaid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        id=(TextView) findViewById(R.id.id_user_info);
        username=(TextView) findViewById(R.id.username_userinfo);
        mobile=(TextView) findViewById(R.id.mobile_userinfo);

        gps=(Button) findViewById(R.id.gps_userinfo);
        order=(Button) findViewById(R.id.order_userinfo);

        Id=getIntent().getStringExtra("userID");
        workid=(TextView) findViewById(R.id.workid_userinfo);

        id.setText(Id);
        workareaid=getIntent().getStringExtra("workarea_id");
        workid.setText(workareaid);

        username1=getIntent().getStringExtra("username");
        username.setText(username1);
        mmobile=getIntent().getStringExtra("mobile");
        mobile.setText(mmobile);

        latiude=getIntent().getStringExtra("latitude");
        longitude=getIntent().getStringExtra("langitude");

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(user_info.this,Main_Orderlist_Activity.class);
                String userid=Id;
                intent.putExtra("userID",userid);
                startActivity(intent);

            }
        });

        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(user_info.this,User_MapsActivity.class);

                intent.putExtra("latitude",latiude);
                intent.putExtra("longitude",longitude);

                startActivity(intent);
            }
        });




    }





}
