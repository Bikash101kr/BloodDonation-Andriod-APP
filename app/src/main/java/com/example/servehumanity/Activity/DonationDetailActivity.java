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
import com.example.servehumanity.model.Profile;
import com.example.servehumanity.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonationDetailActivity extends AppCompatActivity {
    TextView tvUserId, tvFirstName, tvLastName, tvGender, tvAddress, tvDOB, tvPhone, tvLastDonation, tvBloodGroup;
    TextView tvCountry, tvState, tvStreet, tvDistrict, tvCity, tvWeight, tvDate, tvLocation;
    Profile profile;

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
        tvLastDonation = findViewById(R.id.tvLastDonation);
        tvBloodGroup = findViewById(R.id.tvBloodGroup);
        tvCountry = findViewById(R.id.tvCountry);
        tvState = findViewById(R.id.tvState);
        tvDistrict = findViewById(R.id.tvDistrict);
        tvStreet = findViewById(R.id.tvStreet);
        tvCity = findViewById(R.id.tvCity);
        tvLocation = findViewById(R.id.tvLocation);
        tvDate = findViewById(R.id.tvDate);
        tvWeight = findViewById(R.id.tvWeight);
        btnUpdateDonation = findViewById(R.id.btnUpdateDonation);

        donateBlood_id = getIntent().getStringExtra("_id");
        loadDonationDetail();

    }

    private void loadDonationDetail() {
        DonateBloodAPI donateBloodAPI = URL.getInstance().create(DonateBloodAPI.class);
        Call<DonateBlood> call = donateBloodAPI.display_donation(URL.token, donateBlood_id);
        call.enqueue(new Callback<DonateBlood>() {
            @Override
            public void onResponse(Call<DonateBlood> call, Response<DonateBlood> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(DonationDetailActivity.this, "Unsuccessful! " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                DonateBlood donateBloodFromAPI = response.body();
//Response{protocol=http/1.1, code=500, message=Internal Server Error, url=http://192.168.100.5:3001/api/donateBloods/5f4f4fec48d0c133307f1e6a}
                tvCountry.setText(donateBloodFromAPI.getCountry());
                tvState.setText(donateBloodFromAPI.getState());
                tvDistrict.setText(donateBloodFromAPI.getDistrict());
                tvCity.setText(donateBloodFromAPI.getCountry());
                tvStreet.setText(donateBloodFromAPI.getStreet());
                tvWeight.setText(donateBloodFromAPI.getWeight());
                tvDate.setText(donateBloodFromAPI.getDonationDate());
                tvLocation.setText(donateBloodFromAPI.getLocation());
                Log.i("owner", donateBloodFromAPI.getOwner());

                loadProfile(donateBloodFromAPI.getOwner());


            }

            @Override
            public void onFailure(Call<DonateBlood> call, Throwable t) {
                Log.i("onFailure", t.getLocalizedMessage());
            }
        });
    }

    private void loadProfile(String user_id) {
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
                //tvUserId.setText(URL.userID);
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


//            private void loadProfile() {
//                ProfileAPI profileAPI = URL.getInstance().create(ProfileAPI.class);
//                Call<Profile> call = profileAPI.display_profile(URL.token);
//                call.enqueue(new Callback<Profile>() {
//                    @Override
//                    public void onResponse(Call<Profile> call, Response<Profile> response) {
//                        if (!response.isSuccessful()) {
//                            Toast.makeText(DonationDetailActivity.this, "Unsuccessful!", Toast.LENGTH_LONG).show();
//                            return;
//                        }
//
//                        profile = response.body();
//                        tvUserId.setText(URL.userID);
//                        tvFirstName.setText(profile.getFirstName());
//                        tvLastName.setText(profile.getLastName());
//                        tvDOB.setText(profile.getDateOfBirth());
//                        tvAddress.setText(profile.getAddress());
//                        tvPhone.setText(profile.getPhone());
//                        tvLastDonation.setText(profile.getLastDonation());
//                        tvGender.setText(profile.getGender());
//                        tvBloodGroup.setText(profile.getBloodGroup());
//                    }
//
//                    @Override
//                    public void onFailure(Call<Profile> call, Throwable t) {
//                        Log.i("onFailure", t.getLocalizedMessage());
//
//                    }


                });
            }
        }