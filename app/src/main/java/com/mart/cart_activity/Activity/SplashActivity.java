package com.mart.cart_activity.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.mart.cart_Activity.R;
import com.mart.cart_activity.Entities.UserSignupEntities;
import com.mart.cart_activity.Repository.UserSignupRepository;

public class SplashActivity extends AppCompatActivity {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    private UserSignupRepository userSignupRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // on below line we are
                // creating a new intent




                userSignupRepository = new UserSignupRepository(getApplication());

                // Example: Observe changes in user data for ID 1
                userSignupRepository.getUserById(1).observe(SplashActivity.this, new Observer<UserSignupEntities>() {
                    @Override
                    public void onChanged(UserSignupEntities user) {
                        if (user != null) {
                            // Handle user data here
                            String username = user.getUsername();
                            Toast.makeText(SplashActivity.this, username, Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(SplashActivity.this,MainActivity.class);
                            startActivity(i);
                            // ...
                        }else {
                            Intent i = new Intent(SplashActivity.this, LoginActivity.class);

                        
                            startActivity(i);
                        }
                    }
                });

                // on the below line we are finishing
                // our current activity.
                finish();
            }
        }, 2000);
    }
}