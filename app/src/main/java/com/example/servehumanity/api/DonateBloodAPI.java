package com.example.servehumanity.api;

import com.example.servehumanity.model.DonateBlood;
import com.example.servehumanity.model.Profile;
import com.example.servehumanity.response.DonateBloodResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DonateBloodAPI {
    @GET ("DonateBlood")
    Call<List<DonateBlood>> display_donations();

    @GET("DonateBlood/{donation_id}")
    Call<DonateBlood>display_donation(@Path("donation_id") String donation_id);


    @FormUrlEncoded
    @POST("DonateBlood")
    Call<Void>add_donation(@Header("Authorization") String header,
                                          @Field("country") String country,
                                          @Field("state") String state,
                                          @Field("district") String district,
                                          @Field("city") String city,
                                          @Field("street") String street,
                                          @Field("location") String location,
                                          @Field("weight") String weight
                                          );
    @DELETE("DonateBlood/{donation_id}")
    Call<Void> delete_userDonation(@Path("donation_id") String donation_id ,@Header("Authorization") String header);

    @FormUrlEncoded
    @PUT("DonateBlood/{donation_id}")
    Call<Profile> update_donation(@Header("Authorization") String header,
                                  @Field("country") String country,
                                  @Field("state") String state,
                                  @Field("district") String district,
                                  @Field("city") String city,
                                  @Field("street") String street,
                                  @Field("location") String location,
                                  @Field("weight") String weight);

    @FormUrlEncoded
    @PUT("DonateBlood/{donation_id}/status")
    Call<DonateBlood> update_donationStatus(@Header("Authorization") String header,
                                  @Field("status") String status);
    @GET("DonateBlood/{donation_id}/status")
    Call<DonateBlood>display_donationStatus(@Path("status") String status);

}
