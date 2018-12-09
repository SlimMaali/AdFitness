package com.as.AdFitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;


public class AdminHomeActivity extends AppCompatActivity{

    private ImageButton ib;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        ib = (ImageButton) findViewById(R.id.imageButton1);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomeActivity.this,AdminUserActivity.class));
            }
        });
    }

}