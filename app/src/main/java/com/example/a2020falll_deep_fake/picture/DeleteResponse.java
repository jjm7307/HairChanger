package com.example.a2020falll_deep_fake.picture;

import com.google.gson.annotations.SerializedName;

public class DeleteResponse {
    @SerializedName("status")
    private String status;


    public String get_checklist() { return this.status; }
    public Boolean check_status() {
        String pass = "OK";
        if (status.equals(pass)){
            return true;
        } else{
            return false;
        }
    }
}
