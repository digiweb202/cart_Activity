package com.mart.cart_activity.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.mart.cart_Activity.R;

public class ForgetPassActivity extends AppCompatActivity {
    private Button btn_submit;
    private LinearLayout SignupTextBtn;
    private LinearLayout SigninTextBtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        btn_submit = findViewById(R.id.btn_submit);
        SignupTextBtn = findViewById(R.id.fpsignuptxtBtn);
        SigninTextBtn = findViewById(R.id.fpsignintxtBtn);

        SigninTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgetPassActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        SignupTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgetPassActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });



        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ForgetPassActivity.this,MainActivity.class);
                startActivity(intent);


            }
        });

    }
}