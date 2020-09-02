package com.example.servehumanity.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servehumanity.R;
import com.example.servehumanity.Url.URL;
import com.example.servehumanity.adapter.RecyclerUserRequestAdapter;
import com.example.servehumanity.api.RequestBloodAPI;
import com.example.servehumanity.model.RequestBlood;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayUserRequestActivity extends AppCompatActivity {
    RecyclerView rcView;
    List<RequestBlood> requestBloodList = new ArrayList<>();
    List<RequestBlood> requestBloodListFromAPI;
    RecyclerUserRequestAdapter recyclerAdapter;
    public static Boolean isRequestBloodUpdated = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user_request);
        rcView = findViewById(R.id.rcView);
        callAPI();
    }
    private void callAPI() {
        requestBloodList.clear();
        final RequestBloodAPI requestBloodAPI = URL.getInstance().create(RequestBloodAPI.class);

        Call<List<RequestBlood>> call = requestBloodAPI.display_userRequest(URL.token);

        call.enqueue(new Callback<List<RequestBlood>>() {
            @Override
            public void onResponse(Call<List<RequestBlood>> call, Response<List<RequestBlood>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Unsuccessful!", Toast.LENGTH_LONG).show();
                    return;
                }

                requestBloodListFromAPI = response.body();
                recyclerAdapter = new RecyclerUserRequestAdapter(getApplicationContext(), requestBloodList);
                if (
                        requestBloodList!= null) {
                    for (RequestBlood requestBlood:requestBloodListFromAPI) {
                        requestBloodList.add(new RequestBlood(requestBlood.get_id(), requestBlood.getPatientName(),requestBlood.getPatientAge(),
                                requestBlood.getFullAddress(),requestBlood.getBloodGroup(),requestBlood.getHospitalName(),requestBlood.getRequirement(),
                                requestBlood.getRequirementReason(),requestBlood.getRequiredBefore(),requestBlood.getRequestDate(),requestBlood.getNeedUnit(),
                                requestBlood.getOwner(),requestBlood.getProfile()));
                        Log.i("requestBloodRequestDate", requestBlood.getRequestDate());
                        Log.i("requestBloodLocation", requestBlood.getHospitalName());
                        Log.i("requestBloodPatientName", requestBlood.getPatientName());
                        Log.i("requestBloodBloodGroup", requestBlood.getBloodGroup());
                        Log.i("requestFullAddress", requestBlood.getFullAddress());

                    }
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),1,GridLayoutManager.VERTICAL,false);
                    rcView.setLayoutManager(gridLayoutManager);
                    rcView.setAdapter(recyclerAdapter);
                    return;
                }
                Toast.makeText(getApplicationContext(), "There is no request to display!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<RequestBlood>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "There is no requests to display!", Toast.LENGTH_LONG).show();
                Log.i("onFailure", t.getLocalizedMessage());
            }

        });
    }
}