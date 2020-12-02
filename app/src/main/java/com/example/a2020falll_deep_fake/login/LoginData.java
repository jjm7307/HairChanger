package com.example.a2020falll_deep_fake.login;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("id")
    String id;

    @SerializedName("password")
    String password;

    public LoginData(String get_id, String get_password) {
        this.id = get_id;
        this.password = get_password;
    }
}