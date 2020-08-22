package com.example.servehumanity.Url;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class URL {
    public static String token = "Bearer ";
    public static String userID = "";
    //--------- Connect from Emulator ----------
   // public static final String base_url = "http://10.0.2.2:3001/api/";
   public static final String imgBase_url = "http://10.0.2.2:3001/";

    public static final String base_url = "http://192.168.100.5:3001/api/";
    //--------- Connect from Mobile -------------
    private static String ipAddressOfYourHost = "192.168.100.5";
//public static final String base_url = "http://"+ipAddressOfYourHost+":3001/api/";
//public static final String imgBase_url = "http://"+ipAddressOfYourHost+":3001/";
    public  static Retrofit getInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
        //10.0.2.2
    }
}
