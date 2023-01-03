package com.doubleclick.spactrumscanner;

import static com.doubleclick.spactrumscanner.utils.Constant.BASE_URL;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.doubleclick.spactrumscanner.api.API;
import com.doubleclick.spactrumscanner.model.ResponseData;
import com.doubleclick.spactrumscanner.model.SendData;
import com.doubleclick.spactrumscanner.utils.MySharedPreferences;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScannerActivity extends AppCompatActivity {

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if (result.getContents() == null) {
                    Toast.makeText(ScannerActivity.this, "Cancelled", Toast.LENGTH_LONG).show();
                } else {
                    setText(result.getContents());
                }
            });

    private static final String TAG = "ScannerActivity";

    private TextView qr_code;
    private EditText note;
    private API apiInterface;
    private Button send;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        qr_code = findViewById(R.id.qr_code);
        note = findViewById(R.id.note);
        send = findViewById(R.id.send);
        barcodeLauncher.launch(new ScanOptions());
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        apiInterface = retrofit.create(API.class);

        send.setOnClickListener(view -> {
            apiInterface.sendData("Bearer " + MySharedPreferences.getToken(ScannerActivity.this), new SendData(qr_code.getText().toString(), MySharedPreferences.getId(ScannerActivity.this), note.getText().toString())).enqueue(new Callback<ResponseData>() {
                @Override
                public void onResponse(@NonNull Call<ResponseData> call, @NonNull Response<ResponseData> response) {
                    assert response.body() != null;
                    Toast.makeText(ScannerActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(@NonNull Call<ResponseData> call, @NonNull Throwable t) {
                    Toast.makeText(ScannerActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e(TAG, ": " + t.getMessage());

                }
            });
        });

    }

    private void setText(String text) {
        qr_code.setText(text);
    }

}