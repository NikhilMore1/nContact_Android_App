package com.example.ncontact;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class log_page extends AppCompatActivity {
EditText editText1,editText2;
String user,u;
Button bt1,bt2;
Notification notification;
NotificationManager mn;
public static final String Channel = "channel";
public static final int ids = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.hide();
        setContentView(R.layout.activity_log_page);
        editText1 = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        bt1 = findViewById(R.id.button2);
        bt2 = findViewById(R.id.button3);
        SharedPreferences pre = getSharedPreferences("Reg",MODE_PRIVATE);
         u =    pre.getString("user","");
        String p =    pre.getString("pass","");
        Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.c2,null);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap large = bitmapDrawable.getBitmap();
          mn =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(log_page.this)
                    .setLargeIcon(large)
                    .setSmallIcon(R.drawable.c2)
                    .setContentText("connect with voice")
                    .setContentTitle("Welcome "+u)
                    .setChannelId(Channel)
                    .build();
            mn.createNotificationChannel(new NotificationChannel(Channel,"update",NotificationManager.IMPORTANCE_HIGH));

        }else {

            notification = new Notification.Builder(log_page.this)
                    .setLargeIcon(large)
                    .setSmallIcon(R.drawable.c2)
                    .setContentText("New Message")
                    .setSubText("Call to ")
                    .build();
        }
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 user = editText1.getText().toString();
                String pass = editText2.getText().toString();

                SharedPreferences pp = getSharedPreferences("log",MODE_PRIVATE);
                SharedPreferences.Editor editor = pp.edit();
                editor.putBoolean("flag",true);
//                editor.putString("user",user);
//                editor.putString("pass",pass);

                if(user.equals(u)&&pass.equals(p)) {
                        editor.apply();
                        Intent in = new Intent(log_page.this, MainActivity.class);
                    startActivity(in);
                        Toast.makeText(log_page.this, "Welcome " + u, Toast.LENGTH_SHORT).show();
                    }
                else {
                    Toast.makeText(log_page.this, "Please enter right password or username", Toast.LENGTH_SHORT).show();
                }

            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent2 = new Intent(log_page.this, Registration.class);

                startActivity(intent2);
            }
        });



        mn.notify(ids,notification);
    }
}