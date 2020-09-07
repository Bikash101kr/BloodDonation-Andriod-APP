package com.example.mywearapp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserAPI {

    @FormUrlEncoded
    @POST("users/register")
    Call<Void> register(@Field("email") String email, @Field("username") String username, @Field("password") String password, @Field("profile") String profile);

    @FormUrlEncoded
    @POST("users/login")
    Call<UserResponse> checkUser(@Field("username") String username, @Field("password") String password);

}

