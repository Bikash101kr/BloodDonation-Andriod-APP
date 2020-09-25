package com.example.servehumanity.api;

import com.example.servehumanity.response.ImageResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ImageAPI {
  @Multipart
  @POST("uploads")
  Call<ImageResponse> uploadImage (@Part MultipartBody.Part img);
}