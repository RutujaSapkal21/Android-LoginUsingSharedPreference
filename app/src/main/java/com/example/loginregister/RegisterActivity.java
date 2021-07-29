package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    Button regbtn;
    EditText useredt,mobedt,emailedt,passedt,repassedt;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        regbtn=findViewById(R.id.registerr);
        useredt=findViewById(R.id.username);
        mobedt=findViewById(R.id.mobile);
        emailedt=findViewById(R.id.Email);
        passedt=findViewById(R.id.Password);
        repassedt=findViewById(R.id.rePassword);

        database=new Database(this);

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Flag = 0;
                if (TextUtils.isEmpty(emailedt.getText().toString())) {
                    emailedt.requestFocus();
                    emailedt.setError("Please enter email");
                    Flag = 1;
                } else if (!isValidEmail(emailedt.getText().toString())) {
                    emailedt.requestFocus();
                    emailedt.setError("Please enter valid email");
                    Flag = 1;
                }
                if (TextUtils.isEmpty(useredt.getText().toString())) {
                    useredt.requestFocus();
                    useredt.setError("Please enter username");
                    Flag = 1;
                }

                if (TextUtils.isEmpty(passedt.getText().toString())) {
                    passedt.requestFocus();
                    passedt.setError("Please enter password");
                    Flag = 1;
                }
                if (passedt.getText().toString().trim().length() < 8) {
                    passedt.requestFocus();
                    passedt.setError("password should be 8 length long");
                    Flag = 1;
                }
                if(!passedt.getText().toString().trim().matches("(.*[0-9].*)"))
                {
                    passedt.requestFocus();
                    passedt.setError("password should contain at least one digit");
                    Flag = 1;

                }
                if(!passedt.getText().toString().trim().matches("(.*[A-Z].*)"))
                {
                    passedt.requestFocus();
                    passedt.setError("password should contain at least one upper alphabet");
                    Flag = 1;

                }

                if(!passedt.getText().toString().trim().matches("(.*[a-z].*)"))
                {
                    passedt.requestFocus();
                    passedt.setError("password should contain at least one lower alphabet");
                    Flag = 1;

                }
                if(!passedt.getText().toString().trim().matches("^(?=.*[_.()$&@]).*$"))
                {
                    passedt.requestFocus();
                    passedt.setError("password should contains at least one special symbol ");
                    Flag = 1;

                }
                if (TextUtils.isEmpty(repassedt.getText().toString())) {
                    repassedt.requestFocus();
                    repassedt.setError("Please enter conformpassword");
                    Flag = 1;
                }/*else if (conformpassword.getText().toString().trim().length() < 8) {
                    conformpassword.requestFocus();
                    conformpassword.setError("Please enter 8 conform password");
                    Flag = 1;
                }*/
                if (TextUtils.isEmpty(mobedt.getText().toString())) {
                    mobedt.requestFocus();
                    mobedt.setError("Please enter mobile");
                    Flag = 1;
                } else if (mobedt.getText().toString().trim().length() < 10) {
                    mobedt.requestFocus();
                    mobedt.setError("Please enter 10 digit mobile number");
                    Flag = 1;
                }

                if (Flag == 0) {
                    signup();
//                    SharedPreferences sharedPreferences=getSharedPreferences("Myfile",MODE_PRIVATE);
//                    SharedPreferences.Editor editor=sharedPreferences.edit();
//                    editor.putString("username",useredt.getText().toString());
//                    editor.putString("mobile",mobedt.getText().toString());
//                    editor.putString("Email",emailedt.getText().toString());
//                    editor.putString("password",passedt.getText().toString());
//                    editor.commit();

                }



            }
        });

    }
    public void signup()
    {
        User user=new User();
        user.setUsername(useredt.getText().toString());
        user.setEmail(emailedt.getText().toString());
        user.setMobile(mobedt.getText().toString());
        user.setPassword(passedt.getText().toString());
        database.adduser(user);

        Toast.makeText(this,"user Registered sucessfully..",Toast.LENGTH_LONG).show();
        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
    }

    private boolean isValidEmail(String email) {
        if (!TextUtils.isEmpty(email)) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
        return false;
    }



}