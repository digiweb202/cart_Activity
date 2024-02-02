package com.mart.cart_activity.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.mart.cart_Activity.R;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class NumberActivity extends AppCompatActivity {
    private Button btn_submit;
    private TextInputEditText numberEditText;
    private static final int INTERNET_PERMISSION_REQUEST_CODE = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        btn_submit = findViewById(R.id.btn_submitss);
        numberEditText = findViewById(R.id.numberEditText);

        btn_submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                String phoneNumber = numberEditText.getText().toString();
//                new ApiCallTask().execute();
//                if (!phoneNumber.isEmpty()) {
//                    // Check for INTERNET permission
//                    if (hasInternetPermission()) {
//                        // Call AsyncTask to send the phone number
//                        new ApiCallTask().execute();  // <-- Fix here
//                        Log.e("Task", "1");
//                    } else {
//                        // Request INTERNET permission dynamically
//                        requestInternetPermission();
//                    }
//                } else {
//                    // Handle empty phone number
//                    Toast.makeText(NumberActivity.this, "Please enter a phone number", Toast.LENGTH_SHORT).show();
//                }
                String phoneNumber = numberEditText.getText().toString();

                // Pass the context and phone number to MyApiClient
                new MyApiClient(NumberActivity.this, phoneNumber).execute();

                Toast.makeText(NumberActivity.this,"CLick this button ",Toast.LENGTH_SHORT).show();
            }

        });
    }

    private boolean hasInternetPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
                == PackageManager.PERMISSION_GRANTED;
    }

    private void requestInternetPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.INTERNET},
                INTERNET_PERMISSION_REQUEST_CODE);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == INTERNET_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with AsyncTask
                String phoneNumber = numberEditText.getText().toString();
                if (!phoneNumber.isEmpty()) {
//                    new SendNumberTask().execute(phoneNumber);
                } else {
                    // Handle empty phone number
                    Toast.makeText(NumberActivity.this, "Please enter a phone number", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Permission denied, show a message or take appropriate action
                Toast.makeText(NumberActivity.this, "Permission denied. Cannot proceed.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class ApiCallTask extends AsyncTask<String, Void, String> {

        private static final String TAG = "ApiCallTask";

        @Override
        protected String doInBackground(String... params) {
            if (params.length == 0) {
                return null;
            }
            String phoneNumber = params[0];
            return performApiCall(phoneNumber);
        }

        private String performApiCall(String phoneNumber) {
            try {
                String apiKey = "63fcb0bd68ec3";
                String sender = "NWECOM";
                String template = "Your verification code for njoymart.com is [otp length=\"4\" retry=\"3\" validity=\"10\"]\n" +
                        "Powered by\n" +
                        "Njoy Mart \n" +
                        "An Initiative by NW ECOMMERCE PRIVATE LIMITED";

                // Construct the URL

                String apiUrl = "http://www.smsalert.co.in/api/mverify.json";
                String url = apiUrl + "?apikey=" + apiKey +
                        "&sender=" + sender +
                        "&mobileno=" + phoneNumber +
                        "&template=" + template;

                // Create OkHttpClient
                OkHttpClient client = new OkHttpClient();

                // Build the request
                Request request = new Request.Builder()
                        .url(url)
                        .get()
                        .build();

                // Execute the request
                Response response = client.newCall(request).execute();

                // Check if the request was successful
                if (response.isSuccessful()) {
                    // Successfully made the API call
                    return response.body().string();
                } else {
                    // Handle error
                    Log.e(TAG, "Error making API call. Response code: " + response.code());
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null) {
                // Handle the API response here
                Log.d(TAG, "API Response: " + result);
            } else {
                // Handle the case where the API call failed
                Log.e(TAG, "API Call failed");
            }
        }
    }

}
