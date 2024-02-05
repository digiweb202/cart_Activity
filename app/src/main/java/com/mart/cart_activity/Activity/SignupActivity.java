package com.mart.cart_activity.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

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
                // Get input values
                String username = Etd_username.getText().toString().trim();
                String email = Etd_email.getText().toString().trim();
                String password = Etd_pass.getText().toString().trim();
                String confirmPassword = Etd_confirm_pass.getText().toString().trim();

                // Validate username not empty
                if (username.isEmpty()) {
                    Etd_username.setError("Username cannot be empty");
                    return;
                }

                // Validate email format
                if (!isValidEmail(email)) {
                    Etd_email.setError("Enter a valid email address");
                    return;
                }

                // Validate password and confirm password match
                if (!isValidPassword(password, confirmPassword)) {
                    Etd_confirm_pass.setError("Passwords do not match");
                    return;
                }

                // Check if the user already exists in the database
                LiveData<UserSignupEntities> existingUserLiveData = userSignupRepository.getUserById(1);
                existingUserLiveData.observe(SignupActivity.this, new Observer<UserSignupEntities>() {
                    @Override
                    public void onChanged(UserSignupEntities existingUser) {
                        if (existingUser != null) {
                            // User exists, update the information
                            existingUser.setUsername(username);
                            existingUser.setEmail(email);
                            existingUser.setPassword(password);
                            userSignupRepository.updateUser(existingUser);
                        } else {
                            // User does not exist, create a new user and insert into the database
                            UserSignupEntities newUser = new UserSignupEntities(username, email, password);
                            userSignupRepository.insert(newUser);
                        }

                        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });

// Email validation method


    }
    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    // Password validation method
    private boolean isValidPassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}