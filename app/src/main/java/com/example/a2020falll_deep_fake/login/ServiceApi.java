package com.example.a2020falll_deep_fake.login;

import com.example.a2020falll_deep_fake.login.JoinData;
import com.example.a2020falll_deep_fake.login.JoinResponse;
import com.example.a2020falll_deep_fake.login.LoginData;
import com.example.a2020falll_deep_fake.login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {
    @POST("/login/login/")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/login/register/")
    Call<JoinResponse> userJoin(@Body JoinData data);
}