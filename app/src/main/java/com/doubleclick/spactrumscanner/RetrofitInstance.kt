package com.doubleclick.spactrumscanner

import com.doubleclick.spactrumscanner.api.API
import com.doubleclick.spactrumscanner.utils.Constant.BASE_URL
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created By Eslam Ghazy on 1/3/2023
 */
object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }

    val api: API by lazy {
        retrofit.create(API::class.java)
    }

}