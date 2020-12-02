package com.example.a2020falll_deep_fake.login;

import com.google.gson.annotations.SerializedName;

public class JoinData {
    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private String id;

    @SerializedName("password")
    private String password;

    public JoinData(String userName, String userId, String userPwd) {
        this.name = userName;
        this.id = userId;
        this.password = userPwd;
    }
}