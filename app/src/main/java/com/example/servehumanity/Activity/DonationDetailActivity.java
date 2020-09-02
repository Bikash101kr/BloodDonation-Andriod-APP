package com.example.servehumanity.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.servehumanity.R;
import com.example.servehumanity.Url.URL;
import com.example.servehumanity.api.DonateBloodAPI;
import com.example.servehumanity.api.UserAPI;
import com.example.servehumanity.model.DonateBlood;
import com.example.servehumanity.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonationDetailActivity extends AppCompatActivity {
    TextView tvUserId, tvFirstName, tvLastName, tvGender, tvAddress, tvDOB, tvPhone, tvLastDonation, tvBloodGroup;
    TextView tvCountry, tvState,tvStreet, tvDistrict, tvCity, tvWeight, tvDate, tvLocation;

    Button btnUpdateDonation;


    String donateBlood_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_detail);
        tvUserId = findViewById(R.id.tvUserId);
        tvFirstName = findViewById(R.id.tvFirstName);
        tvLastName = findViewById(R.id.tvLastName);
        tvGender = findViewById(R.id.tvGender);
        tvAddress = findViewById(R.id.tvAddress);
        tvDOB = findViewById(R.id.tvDOB);
        tvPhone = findViewById(R.id.tvPhone);
        tvLastDonation =findViewById(R.id.tvLastDonation);
        tvBloodGroup = findViewById(R.id.tvBloodGroup);
        btnUpdateDonation =findViewById(R.id.btnUpdateDonation);

        donateBlood_id = getIntent().getStringExtra("_id");
        loadDonationDetail();

    }

    private void loadDonationDetail() {
        DonateBloodAPI DonateBloodAPI = URL.getInstance().create(DonateBloodAPI.class);
        Call<DonateBlood> call = DonateBloodAPI.display_donation(donateBlood_id);
        call.enqueue(new Callback<DonateBlood>() {
            @Override
            public void onResponse(Call<DonateBlood> call, Response<DonateBlood> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(DonationDetailActivity.this, "Unsuccessful!", Toast.LENGTH_LONG).show();
                    return;
                }
                DonateBlood donateBloodFromAPI = response.body();

                tvCountry.setText(donateBloodFromAPI.getCountry());
                tvState.setText(donateBloodFromAPI.getState());
                tvDistrict.setText(donateBloodFromAPI.getDistrict());
                tvCity.setText(donateBloodFromAPI.getCountry());
                tvStreet.setText(donateBloodFromAPI.getStreet());
                tvWeight.setText(donateBloodFromAPI.getWeight());
                tvDate.setText(donateBloodFromAPI.getDonationDate());
                tvLocation.setText(donateBloodFromAPI.getLocation());
                Log.i("owner", donateBloodFromAPI.getOwner());
                loadProfileDetail(donateBloodFromAPI.getOwner());


            }

            @Override
            public void onFailure(Call<DonateBlood> call, Throwable t) {
                Log.i("onFailure", t.getLocalizedMessage());
            }
        });
    }

    private void loadProfileDetail(String user_id) {
        UserAPI userAPI = URL.getInstance().create(UserAPI.class);
        Call<User> call = userAPI.display_user(user_id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(DonationDetailActivity.this, "Unsuccessful!", Toast.LENGTH_LONG).show();
                    return;
                }
                User user = response.body();
                tvUserId.setText(URL.userID);
                tvFirstName.setText(user.getProfile().getFirstName());
                tvLastName.setText(user.getProfile().getLastName());
                tvDOB.setText(user.getProfile().getDateOfBirth());
                tvAddress.setText(user.getProfile().getAddress());
                tvPhone.setText(user.getProfile().getPhone());
                tvLastDonation.setText(user.getProfile().getLastDonation());
                tvGender.setText(user.getProfile().getGender());
                tvBloodGroup.setText(user.getProfile().getBloodGroup());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("onFailure", t.getLocalizedMessage());

            }


        });
    }
}