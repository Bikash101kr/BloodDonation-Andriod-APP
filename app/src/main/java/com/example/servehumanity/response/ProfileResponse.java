package com.example.servehumanity.response;

public class ProfileResponse {
    private String _id ;

    public ProfileResponse(String _id) {
        this._id = _id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
