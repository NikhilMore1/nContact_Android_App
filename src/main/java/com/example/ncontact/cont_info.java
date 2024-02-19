package com.example.ncontact;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.security.Permission;
import java.util.ArrayList;

public class cont_info extends AppCompatActivity {
ArrayList<String> array = new ArrayList<>();

dataStore ds  = new dataStore();
TextView t1,t2,t3,t4;
public static final String k = "us";
public static final String kk = "use";
public static final String k1 = "k1";
public static final String k2 = "k2";
String mobileHolder,holder;
NotificationManager mn;
Notification notification;
public static final String Channel = "channel";
public static final int noteId = 100;
Button bt1,bt2,btn3,btn4;

//notification note = new notification();
 static  int PERMISSION_CODE = 100;
ImageView profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Contact --> general information");
        setContentView(R.layout.activity_cont_info);
        t1 = findViewById(R.id.te1);
        t2 = findViewById(R.id.te2);
        t3 = findViewById(R.id.te3);
        btn3 = findViewById(R.id.btn3);
        bt1 = findViewById(R.id.btn1);
        t4 = findViewById(R.id.msg);
        bt2 = findViewById(R.id.btn2);
        btn4 = findViewById(R.id.btn4);
        profile = findViewById(R.id.imageView2);
        Intent intent2 = getIntent();
        holder = intent2.getStringExtra(MainActivity.key1);
         mobileHolder = intent2.getStringExtra(MainActivity.key2);
        String mailHolder = intent2.getStringExtra(MainActivity.key3);
        t1.setText(holder);
        t2.setText(mobileHolder);
        t3.setText(mailHolder);
        if(ContextCompat.checkSelfPermission(cont_info.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(cont_info.this,new String[]{Manifest.permission.CALL_PHONE},PERMISSION_CODE);

        }
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL);
                Uri uri = Uri.parse("tel:"+mobileHolder);
                i.setData(uri);
                startActivity(i);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] mailt = {mailHolder};
                Intent intent3 = new Intent(Intent.ACTION_SEND);
                intent3.setType("*/*");
                intent3.putExtra(Intent.EXTRA_EMAIL,mailt);
                intent3.putExtra(Intent.EXTRA_TEXT,"This is sample mail you can here write your own mail ");
                intent3.putExtra(Intent.EXTRA_SUBJECT,"Hello from "+ holder);
                if (intent3.resolveActivity(getPackageManager())!=null)
                {
                    startActivity(intent3);
                }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(cont_info.this, video.class);
                inte.putExtra(k,holder);
                inte.putExtra(kk,mobileHolder);
                startActivity(inte);
            }
        });

        t4.setText("Send a Message");
        t4.setMovementMethod(LinkMovementMethod.getInstance());
        t4.setOnClickListener(v -> {
            Intent in = new Intent(Intent.ACTION_SENDTO);
            Uri uri = Uri.parse("smsto:"+mobileHolder);
            in.setData(uri);
            in.putExtra(Intent.EXTRA_TEXT,"you can edit this message");
            in.putExtra(Intent.EXTRA_SUBJECT,"Hello "+holder);
            startActivity(in);
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_PICK);
                activityResultLauncher.launch(in);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(cont_info.this);
                builder.setTitle("Are you sure to delete..");
                builder.setMessage(holder);
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Data");
           Query query =  databaseReference.orderByChild("mb").equalTo(holder);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                 String id = snapshot.getKey();
                 for(DataSnapshot ds : snapshot.getChildren())
                 {
                     String keys = ds.getKey();
                     Log.d("ta",keys);
                 }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("es",error.toString() );
                }
            });
                        Toast.makeText(cont_info.this, "Deleted Succesfully", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setCancelable(false);
                builder.show();
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

    ActivityResultLauncher <Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
          ge->{
      if(ge.getResultCode()==RESULT_OK)
      {
          Uri uri = ge.getData().getData();
          profile.setImageURI(uri);
      }
          });
}