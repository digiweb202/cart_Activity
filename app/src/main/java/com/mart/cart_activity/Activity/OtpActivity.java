package com.mart.cart_activity.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.toolbox.HttpResponse;
import com.mart.cart_Activity.R;
import com.mart.cart_activity.Api.ApiClient;
import com.mart.cart_activity.Api.ApiService;
import com.mart.cart_activity.OtpEditText;
import com.mart.cart_activity.Activity.SmsVerification;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import okhttp3.Callback;
import okhttp3.Response;
import retrofit2.Call;
public class OtpActivity extends AppCompatActivity {
    private Button btn_submit;
    private OtpEditText otpedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        btn_submit = findViewById(R.id.Btn_click);
        otpedit = findViewById(R.id.et_otp);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OtpActivity.this,"OTPActivity",Toast.LENGTH_SHORT).show();
                String phoneNumber = getIntent().getStringExtra("phoneNumber");
                Log.d("ReceivedPhoneNumber", "Phone Number: " + phoneNumber);

                String mobileNumber = phoneNumber; // Replace with your actual mobile number
                String otpCode = otpedit.getText().toString(); // Replace with your actual OTP
                Log.e("OTPCode",otpCode);

                  new SmsVerification(OtpActivity.this, mobileNumber, otpCode).execute();

            }
        });



    }

    // Move this method outside the onClick method

}
