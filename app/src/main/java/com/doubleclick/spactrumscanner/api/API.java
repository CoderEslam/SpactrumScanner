package com.doubleclick.spactrumscanner.api;

import com.doubleclick.spactrumscanner.model.Login;
import com.doubleclick.spactrumscanner.model.ResponseData;
import com.doubleclick.spactrumscanner.model.SendData;
import com.doubleclick.spactrumscanner.model.UserData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created By Eslam Ghazy on 1/3/2023
 */
public interface API {

    @Headers("Content-Type: application/json")
    @POST("login")
    Call<UserData> LoginUser(@Body Login login);

    @Headers("Content-Type: application/json")
    @POST("device")
    Call<ResponseData> sendData(@Header("Authorization") String token, @Body SendData sendData);

}
