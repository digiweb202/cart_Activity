package com.mart.cart_activity.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mart.cart_Activity.R;
import com.mart.cart_activity.Api.ApiService;
import com.mart.cart_activity.ApiModel.UserModel;
import com.mart.cart_activity.DatabaseApi.ApiClient;
import com.mart.cart_activity.DatabaseApi.RetrofitClient;
import com.mart.cart_activity.Entities.UserSignupEntities;
import com.mart.cart_activity.Repository.UserSignupRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    private Button signup_btn;
    private TextView forgetTextBtn;

    private EditText Etd_email;
    private EditText Etd_username;
    private EditText Etd_pass;
    private EditText Etd_confirm_pass;
    private UserSignupRepository userSignupRepository;
    private ApiService apiService;
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
        // Initialize Retrofit interface
        ApiService apiInterface = RetrofitClient.getClient().create(ApiService.class);

        apiService = ApiClient.getApiClient().create(ApiService.class);



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
                        SignupTask signupTask = new SignupTask(Etd_username.getText().toString(), Etd_email.getText().toString(), Etd_pass.getText().toString(), SignupActivity.this);
                        signupTask.execute();

                    }
                });

//                Signup Retrofit Api code
//                // Make API call using Retrofit
//                Call<String> call = apiInterface.signup(username, Name, Email, Pass);
//                call.enqueue(new Callback<String>() {
//                    @Override
//                    public void onResponse(Call<String> call, Response<String> response) {
//                        if (response.isSuccessful()) {
//                            // Handle successful response
//                            String result = response.body();
//                            Toast.makeText(MainActivity.this, "Response: " + result, Toast.LENGTH_SHORT).show();
//                            Log.d("API Response", result);
//                        } else {
//                            // Handle error response
//                            Toast.makeText(MainActivity.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
//                            Log.e("API Error", "Error: " + response.message());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<String> call, Throwable t) {
//                        // Handle failure
//                        Toast.makeText(MainActivity.this, "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                        Log.e("API Failure", "Error: " + t.getMessage());
//                    }
//                });

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

    private void signupUser(String username, String email, String password) {
        Call<UserModel> call = apiService.signup(username, email, password);

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {
                    UserModel user = response.body();
                    if (user != null) {
                        // Handle successful signup
                        Log.d("Signup", "User ID: " + user.getId());
                    }
                } else {
                    // Handle unsuccessful signup
                    Log.e("Signup", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                // Handle failure
                Log.e("Signup", "Failed: " + t.getMessage());
            }
        });
    }

    public void onSignupComplete(String result) {
        if (result != null && result.startsWith("Error:")) {
            // Show Toast if there's an error
            Toast.makeText(SignupActivity.this, result, Toast.LENGTH_SHORT).show();
        } else {
            // Proceed with startActivity(intent) if status code is 200
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}