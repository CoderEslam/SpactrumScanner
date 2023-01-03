package com.doubleclick.spactrumscanner;


import static com.doubleclick.spactrumscanner.utils.Constant.BASE_URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.doubleclick.spactrumscanner.Repo.Repository;
import com.doubleclick.spactrumscanner.ViewModel.ScannerViewModel;
import com.doubleclick.spactrumscanner.api.API;
import com.doubleclick.spactrumscanner.model.Login;
import com.doubleclick.spactrumscanner.model.UserData;
import com.doubleclick.spactrumscanner.utils.MySharedPreferences;
import com.doubleclick.spactrumscanner.utils.ScannerViewModelFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EditText email, password;
    private Button signIn;
    private ProgressBar progressBar;
    private ScannerViewModel scannerViewModel;
    private ScannerViewModelFactory scannerViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signIn = findViewById(R.id.sign_in);
        progressBar = findViewById(R.id.progressBar);
        scannerViewModelFactory = new ScannerViewModelFactory(new Repository());
        scannerViewModel = new ViewModelProvider(this, scannerViewModelFactory).get(ScannerViewModel.class);
        if (!MySharedPreferences.getId(MainActivity.this).isEmpty() && !MySharedPreferences.getEmail(MainActivity.this).isEmpty() && !MySharedPreferences.getPassword(MainActivity.this).isEmpty()) {
            startActivity(new Intent(MainActivity.this, ScannerActivity.class));
            finish();
        }
        signIn.setOnClickListener(view -> {
            SignIn();
        });

    }

    private void SignIn() {
        if (isEmptyField()) {
            progressBar.setVisibility(View.VISIBLE);
            scannerViewModel.loginData(new Login(email.getText().toString(), password.getText().toString()));
            scannerViewModel.getMyResponseLogin().observe(this, new Observer<Call<UserData>>() {
                @Override
                public void onChanged(Call<UserData> userDataCall) {
                    userDataCall.enqueue(new Callback<UserData>() {
                        @Override
                        public void onResponse(@NonNull Call<UserData> call, @NonNull Response<UserData> response) {
                            assert response.body() != null;
                            Log.e(TAG, "onResponse: " + response.body().toString());
                            MySharedPreferences.saveId(MainActivity.this, response.body().getUser().getId());
                            MySharedPreferences.saveEmail(MainActivity.this, response.body().getUser().getEmail());
                            MySharedPreferences.saveToken(MainActivity.this, response.body().getToken());
                            MySharedPreferences.savePassword(MainActivity.this, password.getText().toString());
                            progressBar.setVisibility(View.GONE);
                            startActivity(new Intent(MainActivity.this, ScannerActivity.class));
                            finish();
                        }

                        @Override
                        public void onFailure(@NonNull Call<UserData> call, @NonNull Throwable t) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
/*
            RetrofitInstance.INSTANCE.getApi().LoginUser(new Login(email.getText().toString(), password.getText().toString())).enqueue(new Callback<UserData>() {
                @Override
                public void onResponse(@NonNull Call<UserData> call, @NonNull Response<UserData> response) {

                }

                @Override
                public void onFailure(@NonNull Call<UserData> call, @NonNull Throwable t) {

                }
            });
*/
        }
    }

    private boolean isEmptyField() {
        return !email.getText().toString().isEmpty() && !password.getText().toString().isEmpty();
    }

}