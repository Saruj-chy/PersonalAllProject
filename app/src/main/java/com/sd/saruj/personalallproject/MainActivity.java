package com.sd.saruj.personalallproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sd.saruj.personalallproject.MoreDataLoad.MoreDataLoadActivity;
import com.sd.saruj.personalallproject.VolleySessionToken.AllSiteActivity;

public class MainActivity extends AppCompatActivity {

    Button mTokenBtn, mMoreDataBtn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTokenBtn = findViewById(R.id.btn_token) ;
        mMoreDataBtn = findViewById(R.id.btn_more_data) ;

        mTokenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AllSiteActivity.class));
            }
        });

        mMoreDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MoreDataLoadActivity.class));
            }
        });
    }
}