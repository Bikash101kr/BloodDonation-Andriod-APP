package com.example.servehumanity.bll;

import com.example.servehumanity.Url.URL;
import com.example.servehumanity.api.ProfileAPI;
import com.example.servehumanity.response.ProfileResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class ProfileBLL {
    private String  firstName, lastName, lastDonationDate, address, dateOfBirth, phone, gender, bloodGroup, image ;
    private  boolean isSuccess = false;
    public  static String id;

    public ProfileBLL(String firstName, String lastName, String lastDonationDate,
                      String address, String dateOfBirth, String phone, String gender, String bloodGroup,
                      String image ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastDonationDate = lastDonationDate;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.image = image;
    }
    public boolean addProfile() {
        ProfileAPI profileAPI = URL.getInstance().create(ProfileAPI.class);
        Call<ProfileResponse> call = profileAPI.addProfile(firstName, lastName, lastDonationDate, address, dateOfBirth, phone, gender, bloodGroup, image);
        try {
            Response<ProfileResponse> profileResponseResponse = call.execute();
            if (profileResponseResponse.code() == 201) {
                isSuccess = true;
                id = profileResponseResponse.body().get_id();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }
    public boolean displayprofile() {
        ProfileAPI profileAPI = URL.getInstance().create(ProfileAPI.class);
        Call<ProfileResponse> call = profileAPI.addProfile(firstName, lastName, lastDonationDate, address, dateOfBirth, phone, gender, bloodGroup, image);
        try {
            Response<ProfileResponse> profileResponseResponse = call.execute();
            if (profileResponseResponse.code() == 201) {
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }
    public boolean updateProfile() {
        ProfileAPI profileAPI = URL.getInstance().create(ProfileAPI.class);
        Call<ProfileResponse> call = profileAPI.addProfile(firstName, lastName, lastDonationDate, address, dateOfBirth, phone, gender, bloodGroup, image);
        try {
            Response<ProfileResponse> profileResponseResponse = call.execute();
            if (profileResponseResponse.code() == 201) {
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }
}


