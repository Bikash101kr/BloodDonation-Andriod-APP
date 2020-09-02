package com.example.servehumanity.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.servehumanity.R;
import com.example.servehumanity.Url.URL;
import com.example.servehumanity.api.RequestBloodAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestBloodActivity extends AppCompatActivity {
    TextView tvDesc;
    EditText edtPatientName, edtAge, edtAddress, edtHospital,edtUnit, edtBefore, edtReason,edtRequestDate;
    Button btnConfirmRequest;
    RadioGroup radioGroupBloodGroup, radioGroupRequirementType;
    RadioButton rBtnAP, rBtnAN, rBtnBP,rBtnBN,rBtnABP, rBtnABN, rBtnOP,rBtnON, rBtnOneOfAbove, rBtnFresh, rBtnStocked;
    String patientName, patientAge, bloodGroup, hospitalName, fullAddress, requirement, needUnit,requirementReason, requireBefore, requestDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_blood);

        tvDesc = findViewById(R.id.tvDesc);
        edtPatientName = findViewById(R.id.edtPatientName);
        edtAge = findViewById(R.id.edtAge);
        edtAddress = findViewById(R.id.edtAddress);
        edtHospital = findViewById(R.id.edtHospital);
        edtUnit = findViewById(R.id.edtUnit);
        edtBefore = findViewById(R.id.edtBefore);
        edtReason = findViewById(R.id.edtReason);
        edtRequestDate = findViewById(R.id.edtRequestDate);
        radioGroupRequirementType = findViewById(R.id.radioGroupRequirementType);
        radioGroupBloodGroup = findViewById(R.id.radioGroupBloodGroup);
        rBtnFresh = findViewById(R.id.rBtnFresh);
        rBtnStocked = findViewById(R.id.rBtnStocked);
        rBtnOneOfAbove = findViewById(R.id.rBtnOneOfAbove);
        rBtnAP = findViewById(R.id.rBtnAP);
        rBtnAN = findViewById(R.id.rBtnAN);
        rBtnBP = findViewById(R.id.rBtnBP);
        rBtnBN = findViewById(R.id.rBtnBN);
        rBtnABP = findViewById(R.id.rBtnABP);
        rBtnABN = findViewById(R.id.rBtnABN);
        rBtnOP = findViewById(R.id.rBtnOP);
        rBtnON = findViewById(R.id.rBtnON);
        btnConfirmRequest = findViewById(R.id.btnConfirmRequest);

        btnConfirmRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patientName = edtPatientName.getText().toString();
                patientAge = edtAge.getText().toString();
                hospitalName = edtHospital.getText().toString();
                fullAddress = edtAddress.getText().toString();
                needUnit = edtUnit.getText().toString();
                requirementReason = edtReason.getText().toString();
                requireBefore = edtBefore.getText().toString();
                requestDate = edtRequestDate.getText().toString();


                if (rBtnFresh.isChecked()){
                    requirement = "fresh";
                } else if (rBtnStocked.isChecked()){
                    requirement = "stocked";
                } else if (rBtnOneOfAbove.isChecked()){
                    requirement = "any of above";
                }

                if (rBtnAP.isChecked()){
                    bloodGroup = "A+";
                }  else if (rBtnAP.isChecked()) {
                    bloodGroup = "A+";
                } else if (rBtnAN.isChecked()){
                    bloodGroup = "A-";
                }else if ( rBtnBP.isChecked()) {
                    bloodGroup = "B+";
                }
                else if ( rBtnBN.isChecked()) {
                    bloodGroup = "B-";
                } else if ( rBtnABP.isChecked()) {
                    bloodGroup = "AB+";
                }else if (rBtnABN.isChecked()) {
                    bloodGroup = "AB-";
                }else if (rBtnOP.isChecked()) {
                    bloodGroup = "O+";
                }else if (rBtnON.isChecked()) {
                    bloodGroup = "O-";
                }


                RequestBloodAPI requestBloodAPI = URL.getInstance().create(RequestBloodAPI.class);
                Call<Void> call = requestBloodAPI.add_request(URL.token, patientName, patientAge, bloodGroup, hospitalName, fullAddress, requirement, needUnit,requirementReason, requireBefore, requestDate);
                Log.i("patientName", patientName);
                Log.i("patientAge", patientAge);
                Log.i("bloodGroup",bloodGroup);
                Log.i("hospitalName", hospitalName);
                Log.i("fullAddress", fullAddress);
                Log.i("requirement",requirement);
                Log.i("needUnit",needUnit);
                Log.i("requirementReason",requirementReason);
                Log.i("requireBefore",requireBefore);
                Log.i("requestDate",requestDate);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(RequestBloodActivity.this, "Error code: " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(RequestBloodActivity.this, "Success: Blood Request Added", Toast.LENGTH_SHORT).show();
                        //HomeFragment.isUpdated= true;
                        finish();

                    }


                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(RequestBloodActivity.this, "Error: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}