package com.example.servehumanity.bll;

import com.example.servehumanity.Url.URL;
import com.example.servehumanity.api.UserAPI;
import com.example.servehumanity.response.UserResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class UserBLL {
    private String username, password, email, profile;
    private  boolean isSuccess = false;

    public UserBLL(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserBLL(String username, String password,  String email,  String profile) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.profile = profile;
    }

    public boolean registerUser() {
        UserAPI userAPI = URL.getInstance().create(UserAPI.class);
        Call<Void> call = userAPI.register(email, username, password, profile);
        try {
            Response<Void> userResponseResponse = call.execute();
            if (userResponseResponse.code() == 201) {
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    public boolean loginUser() {
        UserAPI userAPI = URL.getInstance().create(UserAPI.class);
        Call<UserResponse> call = userAPI.checkUser(username, password);
        try {
            Response<UserResponse> userResponseResponse = call.execute();
            if (userResponseResponse.body().getStatus().equals("Login Sucessful")) {
                URL.token = userResponseResponse.body().getToken();
                URL.userID = username;
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }
}
