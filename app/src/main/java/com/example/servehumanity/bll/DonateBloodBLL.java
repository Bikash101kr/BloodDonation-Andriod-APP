//package com.example.servehumanity.bll;
//
//import com.example.servehumanity.Url.URL;
//import com.example.servehumanity.api.DonateBloodAPI;
//import com.example.servehumanity.api.ProfileAPI;
//import com.example.servehumanity.response.ProfileResponse;
//
//import java.io.IOException;
//
//import retrofit2.Call;
//import retrofit2.Response;
//
//public class DonateBloodBLL {
//    private String  Authorization, country, state, district, city, street, location, weight, donationDate ;
//    private  boolean isSuccess = false;
//    public  static String id;
//
//    public DonateBloodBLL(String Authorization, String country, String state, String district,
//                      String city, String street, String location, String weight, String donationDate ) {
//        this.Authorization = Authorization;
//        this.country = country;
//        this.state = state;
//        this.district =district;
//        this.city = city;
//        this.street = street;
//        this.location = location;
//        this.weight = weight;
//        this.donationDate = donationDate;
//
//    }
//    public boolean addDonation() {
//        DonateBloodAPI donateBloodAPI = URL.getInstance().create(DonateBloodAPI.class);
//        Call<Void> call = donateBloodAPI.add_donation(Authorization, country, state, district, city, street, location, weight, donationDate);
//        try {
//            Response<Void> donateBloodResponseResponse = call.execute();
//            if (donateBloodResponseResponse.code() == 201) {
//                isSuccess = true;
//                id = donateBloodResponseResponse.body().get_id();
//
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return isSuccess;
//    }
//}
