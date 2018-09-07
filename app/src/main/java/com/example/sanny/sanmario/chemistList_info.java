package com.example.sanny.sanmario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class chemistList_info extends AppCompatActivity {



    private TextView user_id,name,cheque,ordervalue,bank;

    String mid ,mname,cheque1,morderValue,mbank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chemist_list_info);

        user_id=(TextView) findViewById(R.id.chemist_info_userid);
        name=(TextView) findViewById(R.id.chemist_info_name);
        cheque=(TextView) findViewById(R.id.chemist_info_cheque);
        ordervalue=(TextView) findViewById(R.id.chemist_info_orderValue);
        bank=(TextView) findViewById(R.id.bank);

        mid=getIntent().getStringExtra("user_id");
        mname=getIntent().getStringExtra("name");
        cheque1=getIntent().getStringExtra("cheque");
        morderValue=getIntent().getStringExtra("orderValue");
        mbank=getIntent().getStringExtra("bank");

        user_id.setText(mid);
        name.setText(mname);
        cheque.setText(cheque1);
        ordervalue.setText(morderValue);
        bank.setText(mbank);



    }
}
