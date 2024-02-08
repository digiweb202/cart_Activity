package com.mart.cart_activity.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.mart.cart_Activity.R;
import com.mart.cart_activity.Api.ApiClient;
import com.mart.cart_activity.Api.ApiService;
import com.mart.cart_activity.ApiResponse.UserUpdateResponse;
import com.mart.cart_activity.Entities.UserSignupEntities;
import com.mart.cart_activity.Model.UserViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {


    private EditText full_name;
    private EditText username;
    private EditText nickname;
    private EditText email;
    private EditText number;
    private EditText password;
    private EditText address;
    private Button submit;
    private UserViewModel userViewModel;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        full_name = findViewById(R.id.etFullName);
        username = findViewById(R.id.etUsername);
        nickname = findViewById(R.id.etNickName);
        email = findViewById(R.id.etEmail);
        number = findViewById(R.id.etNumber);
        password = findViewById(R.id.etPassword);
        address = findViewById(R.id.etAddress);
        submit = findViewById(R.id.btn_submit);

         userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        int userId = 1;

        userViewModel.getUserById(userId).observe(this, new Observer<UserSignupEntities>() {
            @Override
            public void onChanged(UserSignupEntities user) {
                if (user != null) {
                    // Populate the UI fields with the user data
                    full_name.setText(user.getFullname());
                    username.setText(user.getUsername());
                    nickname.setText(user.getNikname());

                    email.setText(user.getEmail());
                    address.setText(user.getAddress());
                    number.setText(user.getNumber());
                    password.setText(user.getPassword());

                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Update user details and navigate to another activity
                updateUserDetails();

            }
        });
    }


    private void updateUserDetails() {
        String updatedUsername = username.getText().toString().trim();
        String updatedEmail = email.getText().toString().trim();
        String updatedPassword = password.getText().toString().trim();
        String updatedFullname = full_name.getText().toString().trim();
        String updatedNickname = nickname.getText().toString().trim();
        String updatedNumber = number.getText().toString().trim();
        String updatedAddress = address.getText().toString().trim();

        // Validate all fields before updating
        if (updatedUsername.isEmpty()) {
            username.setError("Username is required");
            return;
        }

        if (updatedEmail.isEmpty()) {
            email.setError("Email is required");
            return;
        }

        if (updatedPassword.isEmpty()) {
            password.setError("Password is required");
            return;
        }

        if (updatedFullname.isEmpty()) {
            full_name.setError("Full Name is required");
            return;
        }

        if (updatedNickname.isEmpty()) {
            nickname.setError("Nickname is required");
            return;
        }

        if (updatedNumber.isEmpty()) {
            number.setError("Number is required");
            return;
        }

        if (updatedAddress.isEmpty()) {
            address.setError("Address is required");
            return;
        }

        int userId = 1; // You need to replace this with the actual user ID

        // Retrieve the existing user from the ViewModel
        userViewModel.getUserById(userId).observe(this, new Observer<UserSignupEntities>() {
            @Override
            public void onChanged(UserSignupEntities existingUser) {
                if (existingUser != null) {
                    // Update the existing user with the new details
                    existingUser.setUsername(updatedUsername);
                    existingUser.setEmail(updatedEmail);
                    existingUser.setPassword(updatedPassword);
                    existingUser.setFullname(updatedFullname);
                    existingUser.setNikname(updatedNickname);
                    existingUser.setNumber(updatedNumber);
                    existingUser.setAddress(updatedAddress);

                    // Save the updated user details
                    userViewModel.updateUser(existingUser);

                    // Navigate to another activity
                    Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
//    user update data api retrofit code

//    private void updateUserDetails() {
//        // ... (existing code)
//
//        int userId = 1; // You need to replace this with the actual user ID
//
//        // Use the updated Retrofit service to make the API call
//        Call<UserUpdateResponse> call = ApiClient.getClient().create(ApiService.class)
//                .updateUser(updatedEmail, updatedUsername, updatedPassword, updatedFullname, updatedNumber, updatedAddress, updatedNickname);
//
//        call.enqueue(new Callback<UserUpdateResponse>() {
//            @Override
//            public void onResponse(Call<UserUpdateResponse> call, Response<UserUpdateResponse> response) {
//                if (response.isSuccessful()) {
//                    // Handle successful response
//                    // response.body().getStatus() and response.body().getMessage() contain the status and message from the server
//
//                    // Navigate to another activity
//                    Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
//                    startActivity(intent);
//                } else {
//                    // Handle error
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UserUpdateResponse> call, Throwable t) {
//                // Handle failure
//            }
//        });
//    }
}