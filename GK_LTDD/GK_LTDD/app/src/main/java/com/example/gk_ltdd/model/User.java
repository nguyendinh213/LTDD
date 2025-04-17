package com.example.gk_ltdd.model;

public class User {
    private int id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String image;
    private String otp;
    private boolean isActivated;

    public User(int id, String name, String username, String email, String password, String image, String otp, boolean isActivated) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.image = image;
        this.otp = otp;
        this.isActivated = isActivated;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

//    public User(int id, String username, String email, String password, String image, boolean isActivated) {
//        this.id = id;
//        this.username = username;
//        this.email = email;
//        this.password = password;
//        this.image = image;
//        this.isActivated = isActivated;
//    }
//
    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getImage() { return image; }
    public boolean isActivated() { return isActivated; }
}
