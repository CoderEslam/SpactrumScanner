package com.doubleclick.spactrumscanner.Repo

import com.doubleclick.spactrumscanner.RetrofitInstance
import com.doubleclick.spactrumscanner.model.Login
import com.doubleclick.spactrumscanner.model.ResponseData
import com.doubleclick.spactrumscanner.model.SendData
import com.doubleclick.spactrumscanner.model.UserData
import retrofit2.Call
import retrofit2.Response

/**
 * Created By Eslam Ghazy on 1/3/2023
 */
class Repository {

    suspend fun postData(token: String, sendData: SendData): Call<ResponseData> {
        return RetrofitInstance.api.sendData(token, sendData);
    }

    suspend fun loginData(login: Login): Call<UserData> {
        return RetrofitInstance.api.LoginUser(login);
    }

}