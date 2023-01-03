package com.doubleclick.spactrumscanner.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created By Eslam Ghazy on 1/3/2023
 */
public class MySharedPreferences {

    public static void saveId(Context context, String id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("ID", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("id", id).apply();
    }

    public static String getId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("ID", Context.MODE_PRIVATE);
        return sharedPreferences.getString("id", "");
    }

    public static void saveEmail(Context context, String email) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("EMAIL", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("email", email).apply();
    }

    public static String getEmail(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("EMAIL", Context.MODE_PRIVATE);
        return sharedPreferences.getString("email", "");
    }

    public static void savePassword(Context context, String password) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PASSWORD", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("password", password).apply();
    }

    public static String getPassword(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PASSWORD", Context.MODE_PRIVATE);
        return sharedPreferences.getString("password", "");
    }

    public static void saveToken(Context context, String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("TOKEN", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("token", token).apply();
    }

    public static String getToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("TOKEN", Context.MODE_PRIVATE);
        return sharedPreferences.getString("token", "");
    }



}
