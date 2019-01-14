package com.as.AdFitness;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.as.AdFitness.entities.User;
import com.as.AdFitness.adapters.CoachAdapter;

import java.util.ArrayList;

public class AdminUserActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user);


        final ArrayList<User> Users = getIntent().getParcelableArrayListExtra("users");
        listView = (ListView) findViewById(R.id.listView_Users);
        CoachAdapter adapter = new CoachAdapter(this,Users);
        listView.setAdapter(adapter);

    }
}
