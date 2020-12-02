package com.example.a2020falll_deep_fake.login;

import com.google.gson.annotations.SerializedName;

public class JoinData {
    @SerializedName("name")
    private String user_name;

    @SerializedName("id")
    private String user_id;

    @SerializedName("password")
    private String user_password;

    public String get_name() {
        return user_name;
    }
    public JoinData(String userName, String userId, String userPwd) {
        this.user_name = userName;
        this.user_id = userId;
        this.user_password = userPwd;
    }
}