package com.example.servehumanity.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.servehumanity.R;

public class ProfileActivity extends AppCompatActivity {

    EditText edtFirstName, edtLastName, edtAddress, edtPhone, edtDOB, edtLastDonation;
    Button btnUploadProfile, btnCreate;
    ImageView imgViewProfile;
    RadioGroup radioGroup, radioGroup2;
    RadioButton rBtnMale, rBtnFemale, rBtnOthers;
    RadioButton rBtnAP, rBtnAN, rBtnBP,rBtnBN,rBtnABP, rBtnABN, rBtnOP,rBtnON;
    String imagePath, path, firstName, lastName, address, phone, lastDonation, dateOfBirth, gender, bloodGroup, profileId;
    AlertDialog.Builder builder;

    private final int CHOOSE_FROM_GALLERY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        edtAddress = findViewById(R.id.edtAddress);
        edtDOB = findViewById(R.id.edtDOB);
        edtPhone = findViewById(R.id.edtPhone);
        edtLastDonation = findViewById(R.id.edtLastDonation);
        btnCreate = findViewById(R.id.btnCreate);
        btnUploadProfile = findViewById(R.id.btnUploadProfile);
        imgViewProfile = findViewById(R.id.imgViewProfile);
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup2 = findViewById(R.id.radioGroup2);
        rBtnMale = findViewById(R.id.rBtnMale);
        rBtnFemale = findViewById(R.id.rBtnFemale);
        rBtnOthers = findViewById(R.id.rBtnOthers);
        rBtnAP = findViewById(R.id.rBtnAP);
        rBtnAN = findViewById(R.id.rBtnAN);
        rBtnBP = findViewById(R.id.rBtnBP);
        rBtnBN = findViewById(R.id.rBtnBN);
        rBtnABP = findViewById(R.id.rBtnABP);
        rBtnABN = findViewById(R.id.rBtnABN);
        rBtnOP = findViewById(R.id.rBtnOP);
        rBtnON = findViewById(R.id.rBtnON);

        btnUploadProfile.setOnClickListener(this);
        btnCreate.setOnClickListener(this);

        path = "";

    }


}