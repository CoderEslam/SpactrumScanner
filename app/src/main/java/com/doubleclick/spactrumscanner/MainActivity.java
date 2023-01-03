package com.doubleclick.spactrumscanner;


import static com.doubleclick.spactrumscanner.utils.Constant.BASE_URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.doubleclick.spactrumscanner.api.API;
import com.doubleclick.spactrumscanner.model.Login;
import com.doubleclick.spactrumscanner.model.UserData;
import com.doubleclick.spactrumscanner.utils.MySharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EditText email, password;
    private Button signIn;
    private API apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(API.class);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signIn = findViewById(R.id.sign_in);
        if (!MySharedPreferences.getId(MainActivity.this).isEmpty() && !MySharedPreferences.getEmail(MainActivity.this).isEmpty() && !MySharedPreferences.getPassword(MainActivity.this).isEmpty()) {
            startActivity(new Intent(MainActivity.this, ScannerActivity.class));
        }
        signIn.setOnClickListener(view -> {
            SignIn();
        });

    }

    private void SignIn() {
        if (isEmptyField()) {
            apiInterface.LoginUser(new Login(email.getText().toString(), password.getText().toString())).enqueue(new Callback<UserData>() {
                @Override
                public void onResponse(@NonNull Call<UserData> call, @NonNull Response<UserData> response) {
                    assert response.body() != null;
                    Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                    MySharedPreferences.saveId(MainActivity.this, response.body().getUser().getId());
                    MySharedPreferences.saveEmail(MainActivity.this, response.body().getUser().getEmail());
                    MySharedPreferences.saveToken(MainActivity.this, response.body().getToken());
                    MySharedPreferences.savePassword(MainActivity.this, password.getText().toString());
                    startActivity(new Intent(MainActivity.this, ScannerActivity.class));
                }

                @Override
                public void onFailure(@NonNull Call<UserData> call, @NonNull Throwable t) {
                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private boolean isEmptyField() {
        return !email.getText().toString().isEmpty() && !password.getText().toString().isEmpty();
    }

}