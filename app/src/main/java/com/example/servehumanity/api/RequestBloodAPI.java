package com.example.servehumanity.api;

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

public interface RequestBloodAPI {

    @GET("RequestBlood")
    Call<List<RequestBlood>> display_requests(@Header("Authorization") String header);

    @GET("RequestBlood")
    Call<List<RequestBlood>> display_userRequest(@Header("Authorization") String header);


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
                          @Field("requireBefore") String requireBefore,
                          @Field("requestDate") String requestDate);
    @DELETE("RequestBlood")
    Call<Void> delete_userRequest(@Header("Authorization") String header);

    @FormUrlEncoded
    @PUT("RequestBlood/{request_id}")
    Call<RequestBlood> update_profile(@Header("Authorization") String header,
                                 @Field("patientName") String patientName,
                                 @Field("patientAge") String patientAge,
                                 @Field("bloodGroup") String bloodGroup,
                                 @Field("hospitalName") String hospitalName,
                                 @Field("fullAddress") String fullAddress,
                                 @Field("requirement") String requirement,
                                 @Field("needUnit") String needUnit,
                                 @Field("requirementReason") String requirementReason,
                                 @Field("requireBefore") String requireBefore,
                                      @Field("requestDate") String requestDate);
}
