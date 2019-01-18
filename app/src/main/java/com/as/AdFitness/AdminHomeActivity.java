package com.as.AdFitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.as.AdFitness.entities.Post;
import com.as.AdFitness.entities.User;
import com.as.AdFitness.service.PostService;
import com.as.AdFitness.service.UserService;
import com.as.AdFitness.utility.Api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdminHomeActivity extends AppCompatActivity{

    private ImageButton ibU;
    private ImageButton ibP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        ibU = (ImageButton) findViewById(R.id.imageButton1);
        ibU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserService US = Api.getInstance().getUserService();
                Call<ArrayList<User>> call = US.getUsers();

                call.enqueue(new Callback<ArrayList<User>>() {
                    @Override
                    public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                       ArrayList<User> users = response.body();
                        Intent I = new Intent(AdminHomeActivity.this,AdminUserActivity.class);
                        Bundle b = new Bundle();
                        b.putParcelableArrayList("users",users);
                        I.putExtras(b);
                        startActivity(I);
                    }
                    @Override
                    public void onFailure(Call<ArrayList<User>> call, Throwable t) {

                    }
                });


            }
        });

        ibP = (ImageButton) findViewById(R.id.imageButton2);
        ibP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostService US = Api.getInstance().getPostService();
                Call<ArrayList<Post>> call = US.getPosts();

                call.enqueue(new Callback<ArrayList<Post>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                        ArrayList<Post> posts = response.body();
                        Intent I = new Intent(AdminHomeActivity.this,AdminPostsActivity.class);
                        Bundle b = new Bundle();
                        b.putParcelableArrayList("posts",posts);
                        I.putExtras(b);
                        startActivity(I);
                    }
                    @Override
                    public void onFailure(Call<ArrayList<Post>> call, Throwable t) {

                    }
                });


            }
        });
    }

}