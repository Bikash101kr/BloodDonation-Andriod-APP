package com.example.servehumanity.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.servehumanity.R;

public class DonateBloodActivity extends AppCompatActivity {
    TextView tvWeight, tvDesc, tvAddress;
    EditText edtCountry, edtState, edtDistrict, edtCity, edtStreet, edtLocation, edtWeight;
    Button btnConfirmDonation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_blood);

        tvWeight = findViewById(R.id.tvWeight);
        tvDesc = findViewById(R.id.tvDesc);
        tvAddress = findViewById(R.id.tvAddress);
        edtCountry = findViewById(R.id.edtCountry);
        edtCity = findViewById(R.id.edtCity);
        edtState= findViewById(R.id.edtState);
        edtDistrict = findViewById(R.id.edtDistrict);
        edtStreet = findViewById(R.id.edtStreet);
        edtLocation= findViewById(R.id.edtLocation);
        edtWeight = findViewById(R.id.edtWeight);
        btnConfirmDonation = findViewById(R.id.btnConfirmDonation);
    }
}