package com.example.servehumanity.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.servehumanity.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

public class BloodBankActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}