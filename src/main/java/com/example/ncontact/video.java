package com.example.ncontact;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

public class  video extends AppCompatActivity {
    EditText editText;
    Button btn;
    public static final String k = "us";
    TextView tex;
    String userName,userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ActionBar actionBar = getSupportActionBar();
       actionBar.hide();
        editText = findViewById(R.id.user_edit);
        btn = findViewById(R.id.btn_start);
        Intent inte = getIntent();
       String us =  inte.getStringExtra(cont_info.k);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 userId = editText.getText().toString().trim();
                if (userId.isEmpty()){
                    return;
                }
                startService(userId);
                Intent intent = new Intent(video.this,call.class);
                intent.putExtra(k,userId);
                startActivity(intent);
            }

        });

    }

    void startService(String userId)
    {
        Application application = getApplication(); // Android's application context
        long appID = 1606209274;   // yourAppID
        String appSign ="11cf5e40387136b9ab3d01937db1f27d8b8964c126cd594e61a90b9c56102ad0";  // yourAppSign
        // yourUserID, userID should only contain numbers, English characters, and '_'.
        userName =userId;   // yourUserName

        ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();

        ZegoUIKitPrebuiltCallInvitationService.init(getApplication(), appID, appSign, userId, userName,callInvitationConfig);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ZegoUIKitPrebuiltCallInvitationService.unInit();
    }


}