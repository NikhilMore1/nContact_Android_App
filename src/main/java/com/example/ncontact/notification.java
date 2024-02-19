package com.example.ncontact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

public class notification extends AppCompatActivity {
public static final String Channel = "channel";
public static final int ids = 100;
NotificationManager mn;
Notification notification;

cont_info cont_info_obj = new cont_info();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.c2,null);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap large = bitmapDrawable.getBitmap();
         mn =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
             notification = new Notification.Builder(this)
                    .setLargeIcon(large)
                    .setSmallIcon(R.drawable.c2)
                    .setContentText("New Message")
                    .setSubText("Call to "+cont_info_obj.holder)
                    .setChannelId(Channel)
                    .build();
             mn.createNotificationChannel(new NotificationChannel(Channel,"update",NotificationManager.IMPORTANCE_HIGH));

        }else {

            notification = new Notification.Builder(this)
                    .setLargeIcon(large)
                    .setSmallIcon(R.drawable.c2)
                    .setContentText("New Message")
                    .setSubText("Call to "+cont_info_obj.holder)
                    .build();
        }


    }
}