package com.example.a2020falll_deep_fake.login;

import com.example.a2020falll_deep_fake.login.JoinData;
import com.example.a2020falll_deep_fake.login.JoinResponse;
import com.example.a2020falll_deep_fake.login.LoginData;
import com.example.a2020falll_deep_fake.login.LoginResponse;
import com.example.a2020falll_deep_fake.picture.CompletePictureData;
import com.example.a2020falll_deep_fake.picture.CompletePictureResponse;
import com.example.a2020falll_deep_fake.picture.DeleteData;
import com.example.a2020falll_deep_fake.picture.DeleteResponse;
import com.example.a2020falll_deep_fake.picture.GallaryData;
import com.example.a2020falll_deep_fake.picture.GallaryResponse;
import com.example.a2020falll_deep_fake.picture.PictureResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ServiceApi {
    @POST("/login/login/")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/login/register/")
    Call<JoinResponse> userJoin(@Body JoinData data);

    @Multipart
    @POST("/request/request/")
    Call<PictureResponse> uploadPicture(
            //@Path("template")userId: Int,
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file1,
            @Part MultipartBody.Part file2,
            @Part MultipartBody.Part file3,
            @Part("id") String user_id,
            @Part("hair") String user_hair
    );

    @POST("/request/update/")
    Call<GallaryResponse> updateResponse(@Body GallaryData user_id);

    @POST("/request/download/")
    Call<CompletePictureResponse> requestPicture(@Body CompletePictureData request);

    @POST("/request/cancel/")
    Call<DeleteResponse> requestPicture(@Body DeleteData delete_data);
}