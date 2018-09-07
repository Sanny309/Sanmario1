package com.example.sanny.sanmario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Stockist_list_info extends AppCompatActivity {

    private String userid, orderValue,product,name;
    private TextView muserid,mname,mproduct,morderValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stockist_list_info);
         muserid=(TextView) findViewById(R.id.Stockist_info_userid);
         mname=(TextView) findViewById(R.id.Stodckist_info_name);
         mproduct=(TextView) findViewById(R.id.Stockist_info_product);
         morderValue=(TextView) findViewById(R.id.chemist_info_orderValue);





        userid=getIntent().getStringExtra("name");
        orderValue=getIntent().getStringExtra("user_id");
        product=getIntent().getStringExtra("orderValue");
        name=getIntent().getStringExtra("product");

        muserid.setText(userid);
        mname.setText(name);
        mproduct.setText(product);
        morderValue.setText(orderValue);


    }
}
