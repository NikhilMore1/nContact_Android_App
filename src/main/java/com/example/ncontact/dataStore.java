package com.example.ncontact;

import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class dataStore {
    String na;
    String mb;
    ImageView nb;
    String Email;
    dataStore(String na,String mb,String Email,ImageView nb)
    {
        this.nb = nb;
        this.na = na;
        this.mb = mb;
        this.Email= Email;
    }
    dataStore()
    {

    }

    public String getNa() {
        return na;
    }

    public void setNa(String na) {
        this.na = na;
    }

    public String getMb() {
        return mb;
    }

    public void setMb(String mb) {
        this.mb = mb;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }


}
