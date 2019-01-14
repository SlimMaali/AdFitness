package com.as.AdFitness;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.as.AdFitness.entities.User;
import com.as.AdFitness.utility.Api;
import com.as.AdFitness.service.UserService;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private UserService userService;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private AppCompatEditText usernameField,passwordField,firstNameField,lastNameField,emailField,birthdayField,phoneField;
    private LinearLayoutCompat signUpButton;
    private ProgressDialog pDialog;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        birthdayField   = (AppCompatEditText) findViewById(R.id.birthday);
        birthdayField.setInputType(InputType.TYPE_NULL);

        birthdayField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog Dialog = new DatePickerDialog(
                        SignUpActivity.this,
                        R.style.Theme_AppCompat_DayNight_Dialog_MinWidth,
                        mDateSetListener,
                        year,
                        month,
                        day);
                Dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                Dialog.show();
            }});
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                String date = year + "-" + month + "-" + day;
                birthdayField.setText(date);
            }
        };
        //Initialize view for receiving user data and interaction
        usernameField = (AppCompatEditText) findViewById(R.id.username);
        passwordField = (AppCompatEditText) findViewById(R.id.password);
        emailField   = (AppCompatEditText) findViewById(R.id.emailAddress);
        firstNameField   = (AppCompatEditText) findViewById(R.id.firstName);
        lastNameField   = (AppCompatEditText) findViewById(R.id.lastName);
        phoneField   = (AppCompatEditText) findViewById(R.id.phone);


        signUpButton = (LinearLayoutCompat) findViewById(R.id.signUpBtn);
        signUpButton.setOnClickListener(this);
        userService = Api.getInstance().getUserService();


        sharedPreferences = getSharedPreferences("AdFitness",MODE_PRIVATE);
        editor = sharedPreferences.edit();





    }


    /**
     * This gets called when user clicks or tap on a view already
     * registered above
     * @param v
     */
    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.signUpBtn) {
            if(validateUserInput())
            {
                    defaultRegister();
            }else Toast.makeText(SignUpActivity.this, "Erreur d'inscription,", Toast.LENGTH_LONG).show();
        } /*else if (v.getId() == R.id.facebookButton) {
            facebookLogin();
        } else if (v.getId() == R.id.twitterButton) {
            twitterLogin();
        } */
    }


    private void defaultRegister()
    {

        UserService userService = Api.getInstance().getUserService();
        Call<User> call = userService.registerUser(usernameField.getText().toString(),passwordField.getText().toString(),
                emailField.getText().toString(),birthdayField.getText().toString(),firstNameField.getText().toString(),lastNameField.getText().toString()
        ,phoneField.getText().toString());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User u = response.body();
                editor.putInt("id",u.getId());
                editor.putString("user",u.getUsername());
                editor.putString("password",u.getPassword());
                editor.putString("status","logged");
                editor.apply();

                Intent loggedIn = new Intent(SignUpActivity.this, DashboardActivity.class);
                loggedIn.putExtra("user",u);
                startActivity(loggedIn);
                finish();

            }


            @Override
            public void onFailure(Call<User> call, Throwable t) {
                pDialog = new ProgressDialog(SignUpActivity.this);
                pDialog.setMessage("Mot de passe erroné touchez n'importe ou");
                pDialog.setCancelable(true);
                pDialog.setCanceledOnTouchOutside(true);
                pDialog.show();
            }
        });
    }


    /**
     * This method Checks if the username already exists in database
     */
    private void usernameExist(String username)
    {
        Call<String> stringCall  = userService.usernameExist(username);
        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String responseString = response.body();
                    if(responseString.equals("true"))
                    {
                        usernameField.setError(getResources().getString(R.string.username_already_exist));
                        usernameField.requestFocus();
                        Toast.makeText(SignUpActivity.this, "Please Choose another username", Toast.LENGTH_LONG).show();
                        usernameField.setText("");
                    }
                }

            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }
    /**
     * This method Checks if the email adress already exists in database
     */
    private void emailExist(String email)
    {
        Call<String> stringCall  = userService.emailExist(email);
        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String responseString = response.body();
                    if(responseString.equals("true"))
                    {
                        emailField.setError(getResources().getString(R.string.email_already_exist));
                        emailField.requestFocus();
                        Toast.makeText(SignUpActivity.this, "Please Choose another Email", Toast.LENGTH_LONG).show();
                        emailField.setText("");
                    }
                }

            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }
    /**
     * This method helps to validate user input before submitting it
     */

    private boolean validateUserInput() {
        String username = usernameField.getText().toString();
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        String firstName = firstNameField.getText().toString();
        String lastName = lastNameField.getText().toString();
        String birthday = birthdayField.getText().toString();
        String phone = phoneField.getText().toString();
        boolean cancel = false;//
        View view = null;
        //Testing username and email in database


        if (TextUtils.isEmpty(firstName)) {
            firstNameField.setError(getResources().getString(R.string.invalid_firstName));
            view = firstNameField;
            cancel = true;
        }else if (TextUtils.isEmpty(lastName)) {
            lastNameField.setError(getResources().getString(R.string.invalid_lastName));
            view = lastNameField;
            cancel = true;
        }else if(TextUtils.isEmpty(username) || username.length() < 4)
        {
            usernameField.setError(getResources().getString(R.string.invalid_username));
            view = usernameField;
            cancel = true;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() ) {
            emailField.setError(getResources().getString(R.string.invalid_email));
            view = emailField;
            cancel = true;
        } else if (TextUtils.isEmpty(password) || password.length() < 6) {
            passwordField.setError(getResources().getString(R.string.password_error));
            view = passwordField;
            cancel = true;
        }else if (!Patterns.PHONE.matcher(phone).matches()) {
            phoneField.setError(getResources().getString(R.string.invalid_phone));
            view = phoneField;
            cancel = true;
        }else if (TextUtils.isEmpty(birthday)) {
            birthdayField.setError(getResources().getString(R.string.invalid_birthday));
            view = birthdayField;
            cancel = true;
        }
        usernameExist(username);
        emailExist(email);


        if (cancel) {
            view.requestFocus();
            return false;
        }    else return true;

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_default, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
