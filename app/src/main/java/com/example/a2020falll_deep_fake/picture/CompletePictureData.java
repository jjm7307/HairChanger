package com.example.a2020falll_deep_fake.picture;

import com.google.gson.annotations.SerializedName;

public class CompletePictureData {
    @SerializedName("uid")
    String uid;

    @SerializedName("rid")
    int rid;

    public CompletePictureData(String uid, int rid) {
        this.uid = uid;
        this.rid = rid;
    }
}
