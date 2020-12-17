package com.example.a2020falll_deep_fake.picture;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CompletePictureResponse {
    @SerializedName("result")
    private String complete_picture;

    @SerializedName("status")
    private String status;

    public String get_picture() {
        return this.complete_picture;
    }
    public String get_status() {
        return this.status;
    }
    public Boolean check_status() {
        String pass = "OK";
        if (status.equals(pass)){
            return true;
        } else{
            return false;
        }
    }
}
