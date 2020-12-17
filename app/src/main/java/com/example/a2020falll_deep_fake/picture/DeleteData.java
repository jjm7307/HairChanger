package com.example.a2020falll_deep_fake.picture;

import com.google.gson.annotations.SerializedName;

public class DeleteData {
    @SerializedName("uid")
    String uid;

    @SerializedName("rid")
    int rid;

    public DeleteData(String uid, int rid) {
        this.uid = uid;
        this.rid = rid;
    }

}
