package com.example.ncontact;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class addContact extends AppCompatActivity {
EditText ed1,ed2,ed3;
    String keys;
    StorageReference storageReference;

Button save;
ImageView log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Data");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Contact --> add new one");
        FirebaseApp.initializeApp(addContact.this);
        storageReference = FirebaseStorage.getInstance().getReference();

        ed1 = findViewById(R.id.edit1);
        ed2 = findViewById(R.id.edit2);
        ed3 = findViewById(R.id.edit3);
        save = findViewById(R.id.save);
        log = findViewById(R.id.imgg);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ed1.getText().toString();
                String mob = ed2.getText().toString();
                String mail = ed3.getText().toString();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Data");
                if(!name.isEmpty()&&!mob.isEmpty()) {
                    databaseReference.orderByChild("na").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                Toast.makeText(addContact.this, "Data is exist", Toast.LENGTH_SHORT).show();
                            } else {
                                keys = databaseReference.push().getKey();
                                if ((!name.contains("1")||!name.contains("2")||!name.contains("3")||!name.contains("4")||!name.contains("5")||!name.contains("6")||!name.contains("7")||!name.contains("9")||!name.contains("0"))) {
                                    if (mob.length() == 10 || mob.length() == 12) {
                                        dataStore obj = new dataStore(name, mob, mail, log);
                                        databaseReference.child(keys).setValue(obj);
                                        ed1.setText("");
                                        ed2.setText("");
                                        ed3.setText("");
                                        Toast.makeText(addContact.this, "Saved", Toast.LENGTH_SHORT).show();
                                        Intent in = new Intent(addContact.this, MainActivity.class);
                                        startActivity(in);
                                    } else {
                                        Toast.makeText(addContact.this, "please enter atleast 10 digit No", Toast.LENGTH_SHORT).show();
                                    }
                                }else {
                                    Toast.makeText(addContact.this, "Please enter valid name", Toast.LENGTH_SHORT).show();
                                }


                            }

                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.d("tag", error.toString());
                        }


                    });
                }else{
                    ed1.setHintTextColor(Color.parseColor("#de282e"));
                    ed2.setHintTextColor(Color.parseColor("#de282e"));
                    Toast.makeText(addContact.this, "Please fill require information", Toast.LENGTH_SHORT).show();
                }

            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setImg = new Intent(Intent.ACTION_PICK);

                activityResultLauncher.launch(setImg);
            }
        });

//        getDtata.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                uploadIm(imag);
//            }
//        });
//
//    }
//
// private void    uploadIm(Uri imag){
//        StorageReference reference = storageReference.child("/imn"+ UUID.randomUUID().toString());
//        reference.putFile(imag).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                Toast.makeText(addContact.this, "uploaded", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(addContact.this, "Try again", Toast.LENGTH_SHORT).show();
//            }
//        });
 }
   ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
           ge->{
       if(ge.getResultCode()==RESULT_OK)
       {
          Uri urii = ge.getData().getData();
           log.setImageURI(urii);
       }
           });

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