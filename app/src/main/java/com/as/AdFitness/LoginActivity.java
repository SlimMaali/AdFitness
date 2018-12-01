package com.as.AdFitness;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.as.AdFitness.utility.Api;
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
    private AppCompatEditText usernameField, passwordField;
    private LinearLayoutCompat signInButton, facebookButton, twitterButton;
    private AppCompatTextView forgotButton, signUpButton;
    private ProgressDialog pDialog;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialize view for receiving user data and interaction
        usernameField = (AppCompatEditText) findViewById(R.id.username);
        passwordField = (AppCompatEditText) findViewById(R.id.password);
        signInButton = (LinearLayoutCompat) findViewById(R.id.signInButton);
        facebookButton = (LinearLayoutCompat) findViewById(R.id.facebookButton);
        twitterButton = (LinearLayoutCompat) findViewById(R.id.twitterButton);

        forgotButton = (AppCompatTextView) findViewById(R.id.forgotPassword);
        signUpButton = (AppCompatTextView) findViewById(R.id.signUpButton);

        sharedPreferences = getSharedPreferences("AdFitness",MODE_PRIVATE);
        editor = sharedPreferences.edit();


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
            if(validateUserInput())
                defaultLogin();
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
    private boolean validateUserInput() {
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        boolean cancel = false;//
        View view = null;

        if (TextUtils.isEmpty(username)|| username.length() < 4 ) {
            usernameField.setError(getResources().getString(R.string.invalid_usernameOrEmail));
            view = usernameField;
            cancel = true;
        } else if (TextUtils.isEmpty(password) || password.length() < 6) {
            passwordField.setError(getResources().getString(R.string.password_error));
            view = passwordField;
            cancel = true;
        }

        if (cancel) {
            view.requestFocus();
            return false;
        } else return true;


    }


    /**
     * This method is called and all facebook login job is done within this method
     */

    private void defaultLogin()
    {

        UserService userService = Api.getInstance().getUserService();
        Call<User> call = userService.logUser(usernameField.getText().toString(),passwordField.getText().toString());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User u = response.body();
                editor.putInt("id",u.getId());
                editor.putString("user",u.getUsername());
                editor.putString("password",u.getPassword());
                editor.putString("status","logged");
                editor.apply();
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
