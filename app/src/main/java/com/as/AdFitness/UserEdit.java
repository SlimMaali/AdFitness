package com.as.AdFitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.as.AdFitness.entities.User;
import com.as.AdFitness.service.UserService;
import com.as.AdFitness.utility.Api;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);


        ImageView ivImg = (ImageView) findViewById(R.id.imageUser);
        final TextView username = findViewById(R.id.username);
        final TextView email = findViewById(R.id.emailAddress);
        final TextView birthday= findViewById(R.id.birthday);
        final TextView fN = findViewById(R.id.firstName);
        final TextView lN = findViewById(R.id.lastName);
        final TextView phone = findViewById(R.id.phone);
        final TextView password = findViewById(R.id.password);
        LinearLayoutCompat ib = findViewById(R.id.Editbtn);

        final User a= (User) getIntent().getParcelableExtra("User");

        if (a!=null) {
            username.setText(a.getUsername());
            email.setText(a.getEmail());
            birthday.setText(a.getBirthday());
            fN.setText(a.getFirstName());
            lN.setText(a.getLastName());
            phone.setText(a.getPhone());
            password.setText(a.getPassword());
            Picasso.with(this).load("http://10.0.2.2/AdFitness/uploads/user_image/2.png").into(ivImg);

            ib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserService userService = Api.getInstance().getUserService();
                    Call<User> call = userService.editUser(a.getId(), username.getText().toString(), password.getText().toString(),
                            email.getText().toString(), birthday.getText().toString(), a.getRole(), fN.getText().toString(), lN.getText().toString()
                            , phone.getText().toString());

                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            Intent I = new Intent(UserEdit.this, AdminHomeActivity.class);
                            startActivity(I);
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Log.e("erreur", "error");
                            Intent I = new Intent(UserEdit.this, AdminHomeActivity.class);
                            startActivity(I);
                        }
                    });


                }
            });
        }
        else
        {
            Picasso.with(this).load("http://10.0.2.2/AdFitness/uploads/user_image/2.png").into(ivImg);

            ib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserService userService = Api.getInstance().getUserService();
                    Call<User> call = userService.registerUser( username.getText().toString(), password.getText().toString(),
                            email.getText().toString(), birthday.getText().toString(), fN.getText().toString(), lN.getText().toString()
                            , phone.getText().toString());

                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            Intent I = new Intent(UserEdit.this, AdminHomeActivity.class);
                            startActivity(I);
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Log.e("erreur", "error");
                            Intent I = new Intent(UserEdit.this, AdminHomeActivity.class);
                            startActivity(I);
                        }
                    });


                }
            });
        }







    }
}
