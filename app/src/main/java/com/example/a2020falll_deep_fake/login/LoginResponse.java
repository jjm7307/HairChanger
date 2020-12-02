package com.example.a2020falll_deep_fake.login;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("user_id")
    private String user_id;

    @SerializedName("user_name")
    private String user_name;

    @SerializedName("user_type")
    private int user_type;

    @SerializedName("status")
    private String login_status;

    public String get_id() {
        return user_id;
    }
    public String get_name() {
        return user_name;
    }
    public int get_type() {
        return user_type;
    }
    public String get_status(){
        return login_status;
    }
    public Boolean check_status() {
        String pass = "OK";
        if (login_status.equals(pass)){
            return true;
        } else{
            return false;
        }
    }
}