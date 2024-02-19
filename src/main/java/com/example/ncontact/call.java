package com.example.ncontact;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;
import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoAcceptCallInvitationButton;
import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton;
import com.zegocloud.uikit.service.defines.ZegoUIKitUser;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

public class call extends AppCompatActivity {
    TextView editText;
    TextView tex1,textView4;
    String user;
    String userName;
    ZegoSendCallInvitationButton v;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        tex1 = findViewById(R.id.tex1);
        textView4 = findViewById(R.id.textView4);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        editText = findViewById(R.id.user_edit);
        user = editText.getText().toString().trim();
        v = (ZegoSendCallInvitationButton) findViewById(R.id.video_call);
        Intent io = getIntent();
        String user = io.getStringExtra(video.k);
        textView4.setText("Welcome "+user);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               String target = editText.getText().toString().trim();
               setVideo(target);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss",Locale.getDefault());
        String formatDate = dateFormat.format(date);
        String formatTime = timeFormat.format(date);
        tex1.setText(formatDate);

    }

    void setVideo(String target){
        v.setIsVideoCall(true);
//        v.callOnClick();
        v.setResourceID("zego_uikit_call"); // Please fill in the resource ID name that has been configured in the ZEGOCLOUD's console here.
        v.setInvitees(Collections.singletonList(new ZegoUIKitUser(target)));
//        startService(target);
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