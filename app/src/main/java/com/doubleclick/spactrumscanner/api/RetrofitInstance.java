package com.doubleclick.spactrumscanner.api;

import static com.doubleclick.spactrumscanner.utils.Constant.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created By Eslam Ghazy on 1/3/2023
 */
public class RetrofitInstance {
    Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
