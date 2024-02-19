package com.example.ncontact;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
EditText editText3,editText4;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.hide();
        setContentView(R.layout.activity_registration);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        btn = findViewById(R.id.btn3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username  = editText3.getText().toString();
                String password = editText4.getText().toString();
                if(!username.isEmpty()&&!password.isEmpty()) {
                    SharedPreferences sharedPreferences = getSharedPreferences("Reg", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("user", username.trim());
                    editor.putString("pass", password.trim());
                    editor.apply();
                    Intent intent = new Intent(Registration.this, log_page.class);
                    startActivity(intent);
                }else {
                    editText3.setHintTextColor(Color.parseColor("#de282e"));
                    editText4.setHintTextColor(Color.parseColor("#de282e"));
                    Toast.makeText(Registration.this, "Please fill requred data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}