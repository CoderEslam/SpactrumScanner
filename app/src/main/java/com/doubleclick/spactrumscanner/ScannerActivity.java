package com.doubleclick.spactrumscanner;

import static com.doubleclick.spactrumscanner.utils.Constant.BASE_URL;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.doubleclick.spactrumscanner.Repo.Repository;
import com.doubleclick.spactrumscanner.ViewModel.ScannerViewModel;
import com.doubleclick.spactrumscanner.model.Login;
import com.doubleclick.spactrumscanner.model.ResponseData;
import com.doubleclick.spactrumscanner.model.SendData;
import com.doubleclick.spactrumscanner.utils.MySharedPreferences;
import com.doubleclick.spactrumscanner.utils.ScannerViewModelFactory;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private Button send;
    private ProgressBar progressBar;
    private ScannerViewModel scannerViewModel;
    private ScannerViewModelFactory scannerViewModelFactory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        qr_code = findViewById(R.id.qr_code);
        note = findViewById(R.id.note);
        send = findViewById(R.id.send);
        progressBar = findViewById(R.id.progressBar);
        barcodeLauncher.launch(new ScanOptions());
        scannerViewModelFactory = new ScannerViewModelFactory(new Repository());
        scannerViewModel = new ViewModelProvider(this, scannerViewModelFactory).get(ScannerViewModel.class);

        send.setOnClickListener(view -> {
            if (!MySharedPreferences.getId(this).isEmpty() && qr_code.getText().toString().length() > 0) {
                progressBar.setVisibility(View.VISIBLE);
                scannerViewModel.pushData("Bearer " + MySharedPreferences.getToken(ScannerActivity.this), new SendData(qr_code.getText().toString(), MySharedPreferences.getId(ScannerActivity.this), note.getText().toString()));
                scannerViewModel.getMyResponseScanner().observe(this, new Observer<Call<ResponseData>>() {
                    @Override
                    public void onChanged(Call<ResponseData> responseDataCall) {
                        responseDataCall.enqueue(new Callback<ResponseData>() {
                            @Override
                            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                                assert response.body() != null;
                                Toast.makeText(ScannerActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                qr_code.setText("");
                                note.setText("");
                            }

                            @Override
                            public void onFailure(Call<ResponseData> call, Throwable t) {
                                Toast.makeText(ScannerActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.e(TAG, ": " + t.getMessage());
                                progressBar.setVisibility(View.GONE);
                                qr_code.setText("");
                                note.setText("");
                            }
                        });
                    }
                });
                /*RetrofitInstance.INSTANCE.getApi().sendData().enqueue(new Callback<ResponseData>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseData> call, @NonNull Response<ResponseData> response) {

                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseData> call, @NonNull Throwable t) {

                    }
                });*/
            }
        });
    }

    private void setText(String text) {
        qr_code.setText(text);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.scan) {
            barcodeLauncher.launch(new ScanOptions());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}