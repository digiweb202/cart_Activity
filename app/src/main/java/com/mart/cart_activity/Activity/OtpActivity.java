package com.mart.cart_activity.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mart.cart_Activity.R;
import com.mart.cart_activity.OtpEditText;
import com.mart.cart_activity.Activity.SmsVerification;

public class OtpActivity extends AppCompatActivity {
    private Button btn_submit;
    private OtpEditText otpedit;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        btn_submit = findViewById(R.id.btn_submit);
        otpedit = findViewById(R.id.et_otp);


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "8780719280";  // replace with the actual phone number
                String verificationCode = "5515";    // replace with the actual verification code

                SmsVerification smsVerification = new SmsVerification(OtpActivity.this, phoneNumber, verificationCode);
                smsVerification.execute();
                Toast.makeText(OtpActivity.this, "Click event Perform", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(OtpActivity.this, LoginActivity.class);
                //startActivity(intent);
            }
        });
    }
}