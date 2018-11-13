package com.as.AdFitness;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.as.AdFitness.pojo.User;
import com.as.AdFitness.utility.UserService;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private AppCompatEditText emailField, passwordField;
    private LinearLayoutCompat signInButton, facebookButton, twitterButton;
    private AppCompatTextView forgotButton, signUpButton;
    private ProgressDialog pDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialize view for receiving user data and interaction
        emailField = (AppCompatEditText) findViewById(R.id.emailAddress);
        passwordField = (AppCompatEditText) findViewById(R.id.password);
        signInButton = (LinearLayoutCompat) findViewById(R.id.signInButton);
        facebookButton = (LinearLayoutCompat) findViewById(R.id.facebookButton);
        twitterButton = (LinearLayoutCompat) findViewById(R.id.twitterButton);

        forgotButton = (AppCompatTextView) findViewById(R.id.forgotPassword);
        signUpButton = (AppCompatTextView) findViewById(R.id.signUpButton);

        //Register events, such as button pressed
        signInButton.setOnClickListener(this);
        facebookButton.setOnClickListener(this);
        twitterButton.setOnClickListener(this);
        forgotButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
    }

    /**
     * This gets called when user clicks or tap on a view already
     * registered above
     * @param v
     */
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.signInButton) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8000/api/login/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            UserService userService = retrofit.create(UserService.class);
            Call<User> call = userService.logUser(emailField.getText().toString(),passwordField.getText().toString());
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User u = response.body();
                    Intent loggedIn = new Intent(LoginActivity.this, DashboardActivity.class);
                    loggedIn.putExtra("user",u);
                    startActivity(loggedIn);
                    finish();
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.d("Anas", "onFailure ");
                    Log.e("Anas", t.toString());
                    pDialog = new ProgressDialog(LoginActivity.this);
                    pDialog.setMessage("Mot de passe erroné touchez n'importe ou");
                    pDialog.setCancelable(true);
                    pDialog.setCanceledOnTouchOutside(true);
                    pDialog.show();
                }
            });

        } else if (v.getId() == R.id.facebookButton) {
            facebookLogin();
        } else if (v.getId() == R.id.twitterButton) {
            twitterLogin();
        } else if (v.getId() == R.id.forgotPassword) {
            startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
        } else if (v.getId() == R.id.signUpButton) {
            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        }
    }



    /**
     * This method helps to validate user input before submitting it
     */
    private void validateUserInput() {
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        boolean cancel = false;//
        View view = null;

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailField.setError(getResources().getString(R.string.invalid_email));
            view = emailField;
            cancel = true;
        } else if (TextUtils.isEmpty(password) || password.length() < 6) {
            passwordField.setError(getResources().getString(R.string.password_error));
            view = passwordField;
            cancel = true;
        }

        if (cancel) {
            view.requestFocus();
            return;
        } else {
            //Send email and password to server to validate
        }

    }

    /**
     * This method is called and all facebook login job is done within this method
     */
    private void facebookLogin() {

    }

    /**
     * This method is called and all twitter login job is done within this method
     */
    private void twitterLogin() {

    }
}
