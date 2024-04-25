package com.example.laba15.domain.models;

public class User {
    private String UserName;
    private String UserImage;
    public User(String userName, String userImage) {
        UserName = userName;
        UserImage = userImage;
    }
    public String getUserName() { return UserName; }
    public void setUserName(String userName) { UserName = userName; }
    public String getUserImage() { return UserImage; }
    public void setUserImage(String userImage) { UserImage = userImage; }
}



