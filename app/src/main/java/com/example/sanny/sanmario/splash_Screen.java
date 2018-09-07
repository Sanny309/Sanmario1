package com.example.sanny.sanmario;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static java.lang.Thread.sleep;

public class splash_Screen extends AppCompatActivity {

    String PREF_NAME="UserData";
    String admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);

        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        admin=sharedPreferences.getString("isAdmin",null);
        //Log.d("Admin value",);
        Thread myThread=new Thread() {

            @Override
            public void run() {
                try {
                    sleep(3000);
                    if (admin!=null  ){
                        if (admin.contentEquals("1")){
                            Intent intent=new Intent(splash_Screen.this,Admin_panel.class);
                            startActivity(intent);

                        }else {
                            Intent intent=new Intent(splash_Screen.this,user_main_Activity.class);
                            startActivity(intent);
                        }

                    }else {
                        Intent intent=new Intent(splash_Screen.this,MainActivity.class);
                        startActivity(intent);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


        };myThread.start();







    }




}
