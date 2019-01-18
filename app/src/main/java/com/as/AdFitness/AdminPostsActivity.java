package com.as.AdFitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.as.AdFitness.adapters.PostAdapter;
import com.as.AdFitness.entities.Post;

import java.util.ArrayList;

public class AdminPostsActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_post);

        final ArrayList<Post> posts =getIntent().getParcelableArrayListExtra("posts");
        listView = findViewById(R.id.listView_Users);
        PostAdapter adapter = new PostAdapter(this,posts);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Post listItem = (Post) listView.getItemAtPosition(position);
                Intent edit = new Intent(AdminPostsActivity.this,PostEdit.class);
                edit.putExtra("Post",listItem);
                startActivity(edit);
            }
        });

        ImageButton iB = findViewById(R.id.addNew);
        iB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edit = new Intent(AdminPostsActivity.this,PostEdit.class);
                startActivity(edit);
            }
        });

    }
}
