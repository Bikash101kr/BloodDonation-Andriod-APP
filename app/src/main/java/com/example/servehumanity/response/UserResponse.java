package com.example.servehumanity.response;

public class UserResponse {

    String token;
    String status;

    public UserResponse(String token, String status) {
        this.token = token;
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    //    String string ;
//
//    public UserResponse(String string) {
//        this.string = string;
//    }
//
//    public String getString() {
//        return string;
//    }
//
//    public void setString(String string) {
//        this.string = string;
//    }
}
