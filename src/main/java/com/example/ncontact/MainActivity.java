package com.example.ncontact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn, call;
    public static final String key1 = "key";
    public static final String key2 = "key2";
    public static final String Channel =  "channel";
    public static final int ids = 100;
    public static final String key3 = "key3";
    log_page ob = new log_page();
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> arrayList1 = new ArrayList<>();
    ArrayList<String> arrayList2 = new ArrayList<>();
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.c2,null);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap large = bitmapDrawable.getBitmap();
        NotificationManager mn =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this)
                    .setLargeIcon(large)
                    .setSmallIcon(R.drawable.c2)
                    .setContentTitle("App is running..")
                    .setContentText("welcome"+ob.u)
                    .setSubText("Call to ")
                    .setChannelId(Channel)
                    .build();
            mn.createNotificationChannel(new NotificationChannel(Channel,"update",NotificationManager.IMPORTANCE_HIGH));

        }else {

            notification = new Notification.Builder(this)
                    .setLargeIcon(large)
                    .setSmallIcon(R.drawable.c2)
                    .setContentText("New Message")
                    .setSubText("Call to ")
                    .build();
        }
        mn.notify(ids,notification);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Contact");
//
//            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//            View actionBarView = inflater.inflate(R.layout.activity_cust, null);
//
//            // Set the custom layout to the ActionBar
//            ActionBar.LayoutParams params = new ActionBar.LayoutParams(
//                    ActionBar.LayoutParams.MATCH_PARENT,
//                    ActionBar.LayoutParams.MATCH_PARENT
//            );
//            actionBar.setCustomView(actionBarView, params);
//
//            // Set up the logo ImageView (if needed)
//            ImageView logoImageView = actionBarView.findViewById(R.id.lo);
        }

        btn = findViewById(R.id.button);
        call = findViewById(R.id.button2);
        list = findViewById(R.id.list);
        btn.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick (View v){
        Intent intent = new Intent(MainActivity.this, addContact.class);
        startActivity(intent);
    }
    });

    DatabaseReference db = FirebaseDatabase.getInstance().getReference("Data");
                db.addValueEventListener(new

    ValueEventListener() {
        @Override
        public void onDataChange (@NonNull DataSnapshot snapshot){
            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                dataStore fd = dataSnapshot.getValue(dataStore.class);
                Log.d("tr", fd.na);
                Log.d("tr1", fd.mb);
                Log.d("tr2", fd.Email);
                arrayList.add(fd.na);
                arrayList1.add(fd.mb);
                arrayList2.add(fd.Email);
//                           ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arrayList);
//                           list.setAdapter(arrayAdapter);
                custAdaptor ar = new custAdaptor(MainActivity.this, arrayList);
                list.setAdapter(ar);

            }
        }

        @Override
        public void onCancelled (@NonNull DatabaseError error){
            Log.d("taj", error.toString());
        }
    });

                list.setOnItemClickListener(new AdapterView.OnItemClickListener()

    {
        @Override
        public void onItemClick (AdapterView < ? > parent, View view,int position, long id){
        Intent intent1 = new Intent(MainActivity.this, cont_info.class);
        intent1.putExtra(key1, arrayList.get(position));
        intent1.putExtra(key2, arrayList1.get(position));
        intent1.putExtra(key3, arrayList2.get(position));
        startActivity(intent1);
    }
    });


}

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
