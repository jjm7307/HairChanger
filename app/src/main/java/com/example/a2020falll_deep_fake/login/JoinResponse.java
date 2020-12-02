package com.example.a2020falll_deep_fake.login;

import com.google.gson.annotations.SerializedName;

public class JoinResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}