package com.example.servehumanity.api;

import com.example.servehumanity.model.User;
import com.example.servehumanity.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserAPI {
//    @FormUrlEncoded
//    @POST("users/login")
//    Call<UserResponse> login(@Field("username") String username, @Field("password") String password);
//
//    @FormUrlEncoded
//    @POST("users/register")
//    Call<Void> register(@Field("email") String email, @Field("username") String username, @Field("password") String password, @Field("profile") String profile);
//
//
//    @GET("users/{user_id}")
//    Call<User> display_user(@Path("user_id") String user_id);
    //abstract method
    @POST("users/register")
    Call<UserResponse>registerUser(@Body User user); //calling POST req, sending user in body.

    @FormUrlEncoded
    @POST("users/login")
    Call<UserResponse> checkUser(@Field("username")String username, @Field("password")String password);
}

