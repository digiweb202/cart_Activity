package com.mart.cart_activity.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mart.cart_Activity.R;
import com.mart.cart_activity.Entities.UserSignupEntities;
import com.mart.cart_activity.Repository.UserSignupRepository;

public class SignupActivity extends AppCompatActivity {
    private Button signup_btn;
    private TextView forgetTextBtn;

    private EditText Etd_email;
    private EditText Etd_username;
    private EditText Etd_pass;
    private EditText Etd_confirm_pass;
    private UserSignupRepository userSignupRepository;

    private LinearLayout signinTextBtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signup_btn = findViewById(R.id.btn_signup);
        forgetTextBtn = findViewById(R.id.forgettxtbtn);
        signinTextBtn = findViewById(R.id.signintxtBtn);
        Etd_email = findViewById(R.id.etd_email);
        Etd_username = findViewById(R.id.etd_username);
        Etd_pass = findViewById(R.id.etd_pass);
        Etd_confirm_pass = findViewById(R.id.etd_confirm_pass);

        userSignupRepository = new UserSignupRepository(getApplication());




        signinTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        forgetTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this,ForgetPassActivity.class);
                startActivity(intent);
            }
        });
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = Etd_username.getText().toString();
                String email = Etd_email.getText().toString();
                String password = Etd_pass.getText().toString();

//                UserSignupEntities newUser = new UserSignupEntities(username,email,password);
////                newUser.setUsername(username);
////                newUser.setEmail(email);
////                newUser.setPassword(password);
//
//                userSignupRepository.insert(newUser);

                // Create a UserSignupEntities object with the updated information
                UserSignupEntities updatedUser = new UserSignupEntities(1, username, email, password);

                // Update the user in the database
                userSignupRepository.updateUser(updatedUser);
                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}