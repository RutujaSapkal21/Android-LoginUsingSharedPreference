package com.example.loginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.bottomnavigationview);
        FragmentManager fragmentManager=getSupportFragmentManager();
        Intent intent=getIntent();
        String phoneno=intent.getStringExtra("userPhone");
        if (savedInstanceState==null)
        {
            fragmentManager.beginTransaction().replace(R.id.framelayout,new HomeFragment()).commit();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.home:
                        HomeFragment homeFragment=new HomeFragment();
                        fragmentManager.beginTransaction().replace(R.id.framelayout,homeFragment).commit();
                        break;
                    case R.id.activity:
                        Addfragment addFragment=new Addfragment();
                        fragmentManager.beginTransaction().replace(R.id.framelayout,addFragment).commit();
                        break;
                    case R.id.profile:
                        ProfileFragment profileFragment=new ProfileFragment();
                        Bundle bundle=new Bundle();
                        bundle.putString("phone",phoneno);
                        profileFragment.setArguments(bundle);
                        fragmentManager.beginTransaction().replace(R.id.framelayout,profileFragment).commit();
                        break;

                }
                return true;
            }
        });



    }

    public void logoutt(View view) {
        SharedPreferences sharedPreferences=getSharedPreferences("Myfile",MODE_PRIVATE);

        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("LOGEDIN","0");
        editor.commit();
        startActivity(new Intent(MainActivity.this,Launchactivity.class));
    }
}