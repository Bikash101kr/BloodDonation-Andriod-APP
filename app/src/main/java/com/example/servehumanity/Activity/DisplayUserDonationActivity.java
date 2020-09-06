package com.example.servehumanity.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servehumanity.R;
import com.example.servehumanity.Url.URL;
import com.example.servehumanity.adapter.RecyclerUserDonationAdapter;
import com.example.servehumanity.api.DonateBloodAPI;
import com.example.servehumanity.model.DonateBlood;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayUserDonationActivity extends AppCompatActivity {
    RecyclerView rcView;
    List<DonateBlood> donateBloodList = new ArrayList<>();
    List<DonateBlood> donateBloodListFromAPI;
    RecyclerUserDonationAdapter recyclerAdapter;
    Boolean hasStarted = false;
    public static Boolean isDonateBloodUpdated = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("onCreateView", "Created");
        setContentView(R.layout.activity_display_user_donation);
        rcView = findViewById(R.id.rcView);
        rcView.setAdapter(recyclerAdapter);
        rcView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        callAPI();
        return;
    }
    private void callAPI() {
        donateBloodList.clear();
        final DonateBloodAPI donateBloodAPI = URL.getInstance().create(DonateBloodAPI.class);

        Call<List<DonateBlood>> call = donateBloodAPI.display_donations(URL.token);

        call.enqueue(new Callback<List<DonateBlood>>() {
            @Override
            public void onResponse(Call<List<DonateBlood>> call, Response<List<DonateBlood>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(DisplayUserDonationActivity.this, "Unsuccessful!", Toast.LENGTH_LONG).show();
                    return;
                }

                donateBloodListFromAPI = response.body();
                recyclerAdapter = new RecyclerUserDonationAdapter(getApplicationContext(), donateBloodList);
                if (
                        donateBloodList!= null) {
                    for (DonateBlood donateBlood:donateBloodListFromAPI) {
                        donateBloodList.add(new DonateBlood(donateBlood.get_id(), donateBlood.getCountry(), donateBlood.getState(),donateBlood.getDistrict(),
                                donateBlood.getCity(),donateBlood.getStreet(), donateBlood.getLocation(),donateBlood.getWeight(),donateBlood.getDonationDate(),
                                donateBlood.getOwner(),donateBlood.getProfile()));
                        Log.i("donateBloodDonationDate", donateBlood.getDonationDate());
                        Log.i("donateBloodLocation", donateBlood.getLocation());
                    }
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),1,GridLayoutManager.VERTICAL,false);
                    rcView.setLayoutManager(gridLayoutManager);
                    rcView.setAdapter(recyclerAdapter);
                    return;
                }
                Toast.makeText(DisplayUserDonationActivity.this, "There is no donation to display!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<DonateBlood>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "There is no donations to display!", Toast.LENGTH_LONG).show();
                Log.i("onFailure", t.getLocalizedMessage());
            }

        });

    }

}