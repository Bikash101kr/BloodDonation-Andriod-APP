package com.example.servehumanity.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.servehumanity.Activity.DisplayUserDonationActivity;
import com.example.servehumanity.Activity.DisplayUserRequestActivity;
import com.example.servehumanity.Activity.UpdateProfileActivity;
import com.example.servehumanity.R;
import com.example.servehumanity.Url.URL;
import com.example.servehumanity.api.ProfileAPI;
import com.example.servehumanity.bll.ImageBLL;
import com.example.servehumanity.model.Profile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    TextView tvUserId, tvFirstName, tvLastName, tvGender, tvAddress, tvDOB, tvPhone, tvLastDonation, tvBloodGroup;
    ImageView imgView;
    Button btnUpdateProfile, btnViewDonation, btnViewRequest;
    Profile profile;

    public static Boolean isUpdated = false;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);

    }
    @Override
    public void onResume() {
        super.onResume();
        if (!isUpdated) return;
        loadProfile();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        tvUserId = view.findViewById(R.id.tvUserId);
        tvFirstName = view.findViewById(R.id.tvFirstName);
        tvLastName = view.findViewById(R.id.tvLastName);
        tvGender = view.findViewById(R.id.tvGender);
        tvAddress = view.findViewById(R.id.tvAddress);
        tvDOB = view.findViewById(R.id.tvDOB);
        tvPhone = view.findViewById(R.id.tvPhone);
        tvLastDonation =view.findViewById(R.id.tvLastDonation);
        tvBloodGroup = view.findViewById(R.id.tvBloodGroup);
        imgView = view.findViewById(R.id.imgView);
        btnUpdateProfile = view.findViewById(R.id.btnUpdateProfile);
        btnViewDonation = view.findViewById(R.id.btnViewDonation);
        btnViewRequest = view.findViewById(R.id.btnViewRequest);

        loadProfile();

        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UpdateProfileActivity.class);
                intent.putExtra("isUpdate", "true");
                intent.putExtra("firstName", profile.getFirstName());
                intent.putExtra("lastName", profile.getLastName());
                intent.putExtra("address", profile.getAddress());
                intent.putExtra("gender", profile.getGender());
                intent.putExtra("bloodGroup", profile.getBloodGroup());
                intent.putExtra("phone", profile.getPhone());
                intent.putExtra("image", profile.getImage());
                intent.putExtra("dateOfBirth", profile.getDateOfBirth());
                intent.putExtra("lastDonation", profile.getLastDonation());

                startActivity(intent);
            }
        });

        btnViewDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), DisplayUserDonationActivity.class));
            }
        });
        btnViewRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), DisplayUserRequestActivity.class));
            }
        });
        return view;
    }

    private void loadProfile() {
        ProfileAPI profileAPI = URL.getInstance().create(ProfileAPI.class);
        Call<Profile> call = profileAPI.display_profile(URL.token);
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Unsuccessful!", Toast.LENGTH_LONG).show();
                    return;
                }

                    profile = response.body();
                    tvUserId.setText(URL.userID);
                    tvFirstName.setText(profile.getFirstName());
                    tvLastName.setText(profile.getLastName());
                    tvDOB.setText(profile.getDateOfBirth());
                    tvAddress.setText(profile.getAddress());
                    tvPhone.setText(profile.getPhone());
                    tvLastDonation.setText(profile.getLastDonation());
                    tvGender.setText(profile.getGender());
                    tvBloodGroup.setText(profile.getBloodGroup());

                    String imagePath = com.example.servehumanity.Url.URL.imgBase_url + "uploads/" + profile.getImage();
                    imgView.setImageBitmap(ImageBLL.loadImageFromURL(imagePath));

            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Log.i("onFailure", t.getLocalizedMessage());
            }
        });
    }
}