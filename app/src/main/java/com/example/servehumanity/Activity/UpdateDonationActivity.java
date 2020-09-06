package com.example.servehumanity.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.servehumanity.R;
import com.example.servehumanity.Url.URL;
import com.example.servehumanity.api.DonateBloodAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateDonationActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvWeight, tvDesc, tvAddress, tvDonationDate;
    EditText edtCountry, edtState, edtDistrict, edtCity, edtStreet, edtLocation, edtWeight, edtDate;
    Button btnConfirmDonation;
    String country, state, district, city, street, location, weight, donationDate, profile, user;
    public static Boolean isUpdated = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_blood);

        changeLayout();
        edtCountry = findViewById(R.id.edtCountry);
        edtCity = findViewById(R.id.edtCity);
        edtState= findViewById(R.id.edtState);
        edtDistrict = findViewById(R.id.edtDistrict);
        edtStreet = findViewById(R.id.edtStreet);
        edtLocation= findViewById(R.id.edtLocation);
        edtWeight = findViewById(R.id.edtWeight);
        edtDate = findViewById(R.id.edtDate);
        btnConfirmDonation = findViewById(R.id.btnConfirmDonation);


        getIntentValue();
        edtCountry.setText(country);
        edtState.setText(state);
        edtDistrict.setText(district);
        edtCity.setText(city);
        edtLocation.setText(location);
        edtWeight.setText(weight);
        edtDistrict.setText(donationDate);
        btnConfirmDonation.setOnClickListener(this);

    }

    private void getIntentValue() {
        country = getIntent().getStringExtra("country");
        state = getIntent().getStringExtra("state");
        district = getIntent().getStringExtra("district");
        city = getIntent().getStringExtra("city");
        street = getIntent().getStringExtra("street");
        weight = getIntent().getStringExtra("weight");
        location = getIntent().getStringExtra("location");
        donationDate = getIntent().getStringExtra("donationDate");
    }

    private void changeLayout() {

       //if (getIntent().getStringExtra("isUpdate").equals("true")) {
            tvDesc = findViewById(R.id.tvDesc);
            btnConfirmDonation = findViewById(R.id.btnConfirmDonation);;

            tvDesc.setText("Update Donation");
            btnConfirmDonation.setText("Update");
        //}
    }

    @Override
    public void onClick(View v) {
        country = edtCountry.getText().toString();
        state = edtState.getText().toString();
        district = edtDistrict.getText().toString();
        city = edtCity.getText().toString();
        street = edtStreet.getText().toString();
        location = edtLocation.getText().toString();
        weight = edtWeight.getText().toString();
        donationDate = edtDate.getText().toString();

        DonateBloodAPI donateBloodAPI = URL.getInstance().create(DonateBloodAPI.class);
        Call<Void> call = donateBloodAPI.update_donation(URL.token, country, state, district,city, street, location, weight, donationDate);
        Log.i("country", country);
        Log.i("state", state);
        Log.i("district", district);
        Log.i("city", city);
        Log.i("street", street);
        Log.i("weight", weight);
        Log.i("location", location);
        Log.i("donationDate", donationDate);


        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(UpdateDonationActivity.this, "Error code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                DonationDetailActivity.isUpdated = true;

                Toast.makeText(UpdateDonationActivity.this, "Success: Updated", Toast.LENGTH_SHORT).show();
                DonationDetailActivity.isDonateBloodUpdated= true;
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateDonationActivity.this, "Error: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                //Intent intent = new Intent(DonateBloodActivity.this, DonateBloodActivity.class);
            }
        });

    }
}