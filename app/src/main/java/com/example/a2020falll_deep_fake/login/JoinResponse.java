package com.example.a2020falll_deep_fake.login;

import com.google.gson.annotations.SerializedName;

public class JoinResponse {

    @SerializedName("status")
    private String signin_status;

    /*@SerializedName("message")
    private String message;*/

    public String get_status(){
        return signin_status;
    }
    public Boolean check_status() {
        String pass = "OK";
        if (signin_status.equals(pass)){
            return true;
        } else{
            return false;
        }
    }

    /*public String getMessage() {
        return message;
    }*/
}