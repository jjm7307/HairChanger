package com.example.a2020falll_deep_fake.picture;

import com.google.gson.annotations.SerializedName;

public class PictureResponse {
    @SerializedName("status")
    private String upload_status;

    public Boolean check_status() {
        String pass = "OK";
        if (upload_status.equals(pass)){
            return true;
        } else{
            return false;
        }
    }
}