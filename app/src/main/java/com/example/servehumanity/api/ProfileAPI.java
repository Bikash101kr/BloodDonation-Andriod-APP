package com.example.servehumanity.api;

import com.example.servehumanity.model.Profile;
import com.example.servehumanity.response.ProfileResponse;

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
    Call<ProfileResponse> addProfile(@Field("fistName") String firstName,
                                     @Field("lastName") String lastName,
                                     @Field("address") String address,
                                     @Field("phone") String phone,
                                     @Field("image") String image,
                                     @Field("dateOfBirth") String dateOfBirth,
                                     @Field("gender") String gender,
                                     @Field("bloodGroup") String bloodGroup,
                                     @Field("lastDonation" ) String lastDonation);
    @GET("profiles")
    Call<Profile> display_profile(@Header("Authorization") String header);

    @FormUrlEncoded
    @PUT("profiles")
    Call<Profile> update_profile(@Header("Authorization") String header, @Field("firstName") String firstName, @Field("lastName") String lastName,
                                 @Field("address") String address, @Field("phone") String phone,
                                 @Field("image") String image,
                                 @Field("dateOfBirth") String dateOfBirth,
                                 @Field("gender") String gender,
                                 @Field("bloodGroup") String bloodGroup,
                                 @Field("lastDonation" ) String lastDonation);
}
