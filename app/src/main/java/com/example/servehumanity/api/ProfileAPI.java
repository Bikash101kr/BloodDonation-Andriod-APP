package com.example.servehumanity.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ProfileAPI {
    @FormUrlEncoded
    @POST("profiles")
    Call<ProfileResponse> addProfile(@Field("firstName") String firstName, @Field("lastName") String lastName,
                                     @Field("streetAddress") String streetAddress, @Field("cityName") String cityName,
                                     @Field("district") String district, @Field("phoneNo") String phoneNo,
                                     @Field("hidePhone") Boolean hidePhone, @Field("profilePhoto") String profilePhoto) {
        return null;
    }

    @GET("profiles")
    Call<Profile> display_profile(@Header("Authorization") String header) {
        return null;
    }

    @FormUrlEncoded
    @PUT("profiles")
    Call<Profile> update_profile(@Header("Authorization") String header, @Field("firstName") String firstName, @Field("lastName") String lastName,
                                 @Field("streetAddress") String streetAddress, @Field("cityName") String cityName,
                                 @Field("district") String district, @Field("phoneNo") String phoneNo,
                                 @Field("hidePhone") Boolean hidePhone, @Field("profilePhoto") String profilePhoto) {
        return null;
}
