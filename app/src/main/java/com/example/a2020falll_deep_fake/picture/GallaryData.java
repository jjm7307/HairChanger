package com.example.a2020falll_deep_fake.picture;

import com.google.gson.annotations.SerializedName;

public class GallaryData {
    @SerializedName("uid")
    private String user_id;

    public String get_name() {
        return user_id;
    }
    public GallaryData(String userId) {
        this.user_id = userId;
    }

}
