package com.as.AdFitness;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;

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
            int idUser=sendloginurl(emailField.getText().toString(),passwordField.getText().toString());
            if (idUser != 0)
            {
                Intent loggedIn = new Intent(LoginActivity.this, DashboardActivity.class);
                loggedIn.putExtra("idUser",idUser);
                startActivity(loggedIn);
                finish();
            }
            else
            {
                pDialog = new ProgressDialog(LoginActivity.this);
                pDialog.setMessage("Wrong Password");
                pDialog.setCancelable(true);
                pDialog.setCanceledOnTouchOutside(true);
                pDialog.show();
            }
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
