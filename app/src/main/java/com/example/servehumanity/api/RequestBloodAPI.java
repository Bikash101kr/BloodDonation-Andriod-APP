package com.example.servehumanity.api;

import com.example.servehumanity.model.DonateBlood;
import com.example.servehumanity.model.Profile;
import com.example.servehumanity.model.RequestBlood;

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

public interface RequestBloodAPI {

    @GET("RequestBlood")
    Call<List<RequestBlood>> display_requests();

    @GET("RequestBlood/{request_id}")
    Call<RequestBlood>display_request(@Path("request_id") String donation_id);


    @FormUrlEncoded
    @POST("RequestBlood")
    Call<Void>add_request(@Header("Authorization") String header,
                           @Field("patientName") String patientName,
                           @Field("patientAge") String patientAge,
                           @Field("bloodGroup") String bloodGroup,
                           @Field("hospitalName") String hospitalName,
                           @Field("fullAddress") String fullAddress,
                           @Field("requirement") String requirement,
                           @Field("needUnit") String needUnit,
                          @Field("requirementReason") String requirementReason,
                          @Field("requireBefore") String requireBefore);
    @DELETE("RequestBlood/{Request_id}")
    Call<Void> delete_userRequest(@Path("request_id") String donation_id ,@Header("Authorization") String header);

    @FormUrlEncoded
    @PUT("RequestBlood/{request_id}")
    Call<Profile> update_profile(@Header("Authorization") String header,
                                 @Field("patientName") String patientName,
                                 @Field("patientAge") String patientAge,
                                 @Field("bloodGroup") String bloodGroup,
                                 @Field("hospitalName") String hospitalName,
                                 @Field("fullAddress") String fullAddress,
                                 @Field("requirement") String requirement,
                                 @Field("needUnit") String needUnit,
                                 @Field("requirementReason") String requirementReason,
                                 @Field("requireBefore") String requireBefore);
}
