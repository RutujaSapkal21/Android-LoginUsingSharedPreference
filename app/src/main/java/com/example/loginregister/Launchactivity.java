package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class Launchactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_launchactivity);
        @SuppressLint("WrongConstant") SharedPreferences sharedPreferences=getSharedPreferences("Myfile",MODE_APPEND);
        if (sharedPreferences.getString("LOGEDIN","").equals("1"))
        {
            startActivity(new Intent(Launchactivity.this,MainActivity.class));

        }
        else
        {
            startActivity(new Intent(Launchactivity.this,LoginActivity.class));
        }
    }
}