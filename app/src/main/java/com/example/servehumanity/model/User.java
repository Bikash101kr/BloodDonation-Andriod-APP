package com.example.servehumanity.model;

public class User {
    private String username;
    Profile profile;

    public User(String username, Profile profile) {
        this.username = username;
        this.profile = profile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
