package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText mobile,password;
    Button loginbtn,regisbtn;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mobile = findViewById(R.id.logmob);
        password = findViewById(R.id.logpass);
        loginbtn = findViewById(R.id.login);
        regisbtn = findViewById(R.id.register);

        database = new Database(this);



    }

    public void gotoHome(View view) {
        User user=new User();
        user.setMobile(mobile.getText().toString());
        user.setPassword(password.getText().toString());

        boolean status=database.loginUser(user);
        Log.d("Status",String.valueOf(status));
        if(status)
        {
            SharedPreferences sharedPreferences=getSharedPreferences("Myfile",MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("LOGEDIN","1");
            editor.putString("username",user.getUsername());
            editor.putString("mobile",user.getMobile());
            editor.putString("Email",user.getEmail());
          //  editor.putString("password",user.getPassword());

            editor.commit();
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            intent.putExtra("userPhone",mobile.getText().toString());
            startActivity(intent);
        }
        else
        {
            Toast.makeText(LoginActivity.this,"please enter valid username or password", Toast.LENGTH_LONG).show();
        }
    }

    public void gotoregister(View view) {
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
    }
}