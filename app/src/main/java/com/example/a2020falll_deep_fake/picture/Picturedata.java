package com.example.a2020falll_deep_fake.picture;

import com.google.gson.annotations.SerializedName;

public class Picturedata {
    @SerializedName("id")
    String userid;

    @SerializedName("hair")
    String haircode;

    public Picturedata(String id, String code) {
        this.userid = id;
        this.haircode = code;
    }
}
