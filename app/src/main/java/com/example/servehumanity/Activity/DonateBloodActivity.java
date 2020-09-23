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

public class DonateBloodActivity extends AppCompatActivity {

    TextView tvWeight, tvDesc, tvAddress, tvDonationDate;
    EditText edtCountry, edtState, edtDistrict, edtCity, edtStreet, edtLocation, edtWeight, edtDate;
    Button btnConfirmDonation;
    String country, state, district, city, street, location, weight, donationDate, profile, user;
    public static Boolean isUpdated = false;
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
        edtDate = findViewById(R.id.edtDate);
        btnConfirmDonation = findViewById(R.id.btnConfirmDonation);

        btnConfirmDonation.setOnClickListener(new View.OnClickListener() {
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
                Call<Void> call = donateBloodAPI.add_donation(URL.token, country, state, district,city, street, location, weight, donationDate);
                Log.i("country", country);
                Log.i("state", state);
                Log.i("district", district);
                Log.i("city", city);
                Log.i("street", street);
                Log.i("weight", weight);
                Log.i("donationDate", donationDate);


                call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(DonateBloodActivity.this, "Error code: " + response.code(), Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Toast.makeText(DonateBloodActivity.this, "Success: Donation Added", Toast.LENGTH_SHORT).show();
                           // DisplayUserDonationActivity.isDonateBloodUpdated= true;
                            finish();
                        }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(DonateBloodActivity.this, "Error: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                        //Intent intent = new Intent(DonateBloodActivity.this, DonateBloodActivity.class);
                    }
                });
            }
        });
    }
}