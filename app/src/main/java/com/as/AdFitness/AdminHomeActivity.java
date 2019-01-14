package com.as.AdFitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.as.AdFitness.entities.User;
import com.as.AdFitness.utility.Api;
import com.as.AdFitness.service.UserService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
    }

}