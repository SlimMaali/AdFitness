package com.as.AdFitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.as.AdFitness.entities.Post;
import com.as.AdFitness.service.PostService;
import com.as.AdFitness.utility.Api;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);


        ImageView ivImg = (ImageView) findViewById(R.id.imageUser);
        final TextView username = findViewById(R.id.username);
        final TextView email = findViewById(R.id.emailAddress);
        LinearLayoutCompat ib = findViewById(R.id.Editbtn);

        final Post a= (Post) getIntent().getParcelableExtra("Post");

        if (a!=null) {
            username.setText(a.getTitle());
            email.setText(a.getContent());
            Picasso.with(this).load(a.getImage()).into(ivImg);

            ib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PostService userService = Api.getInstance().getPostService();
                    Call<Post> call = userService.editPost(a.getId(), username.getText().toString(), email.getText().toString(),
                            a.getImage());

                    call.enqueue(new Callback<Post>() {
                        @Override
                        public void onResponse(Call<Post> call, Response<Post> response) {
                            Intent I = new Intent(PostEdit.this, AdminHomeActivity.class);
                            startActivity(I);
                        }

                        @Override
                        public void onFailure(Call<Post> call, Throwable t) {
                            Log.e("erreur", "error");
                            Intent I = new Intent(PostEdit.this, AdminHomeActivity.class);
                            startActivity(I);
                        }
                    });


                }
            });
        }
        else
        {
            ib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PostService userService = Api.getInstance().getPostService();
                    Call<Post> call = userService.newPost( username.getText().toString(), email.getText().toString(),
                            "http://10.0.2.2/AdFitness/uploads/user_image/2.png");

                    call.enqueue(new Callback<Post>() {
                        @Override
                        public void onResponse(Call<Post> call, Response<Post> response) {
                            Intent I = new Intent(PostEdit.this, AdminHomeActivity.class);
                            startActivity(I);
                        }

                        @Override
                        public void onFailure(Call<Post> call, Throwable t) {
                            Log.e("erreur", "error");
                            Intent I = new Intent(PostEdit.this, AdminHomeActivity.class);
                            startActivity(I);
                        }
                    });


                }
            });
        }







    }
}
