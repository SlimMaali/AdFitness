package com.as.AdFitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.as.AdFitness.adapters.UserAdapter;
import com.as.AdFitness.entities.User;

import java.util.ArrayList;

public class AdminUserActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user);

        final ArrayList<User> Users = getIntent().getParcelableArrayListExtra("users");
        listView = findViewById(R.id.listView_Users);
        UserAdapter adapter = new UserAdapter(this,Users);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User listItem = (User) listView.getItemAtPosition(position);
                Intent edit = new Intent(AdminUserActivity.this,UserEdit.class);
                edit.putExtra("User",listItem);
                startActivity(edit);
            }
        });

        ImageButton iB = findViewById(R.id.addNew);
        iB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edit = new Intent(AdminUserActivity.this,UserEdit.class);
                startActivity(edit);
            }
        });

    }
}
