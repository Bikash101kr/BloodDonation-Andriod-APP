package com.example.servehumanity.api;

import com.example.servehumanity.model.User;
import com.example.servehumanity.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserAPI {
    //abstract method
    @POST("users/register")
    Call<UserResponse>registerUser(@Body User user); //calling POST req, sending user in body.

    @FormUrlEncoded
    @POST("users/login")
    Call<UserResponse> checkUser(@Field("username")String username, @Field("password")String password);
}

