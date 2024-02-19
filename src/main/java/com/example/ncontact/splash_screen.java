package com.example.ncontact;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class  splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences pref = getSharedPreferences("log",MODE_PRIVATE);
                Boolean check = pref.getBoolean("flag",false);
                Intent intent;
                if(check)
                {
                    intent = new Intent(splash_screen.this, MainActivity.class);

                }
                else {
                    intent = new Intent(splash_screen.this,Registration.class);
                }
                startActivity(intent);
                finish();
            }
        },1500);
    }
}