package com.mart.cart_activity.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mart.cart_Activity.R;
import com.mart.cart_activity.Api.ApiClient;
import com.mart.cart_activity.Api.ApiService;
import com.mart.cart_activity.ApiModel.UpdateUserModel;
import com.mart.cart_activity.ApiResponse.UserUpdateResponse;
import com.mart.cart_activity.DatabaseApi.RetrofitClient;
import com.mart.cart_activity.Entities.UserSignupEntities;
import com.mart.cart_activity.Model.UserViewModel;

import org.json.JSONException;
import org.json.JSONObject;

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
    private EditText pincode;
    private Button submit;
    private UserViewModel userViewModel;

    private ApiService apiService;
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
        pincode = findViewById(R.id.etPincode);
        submit = findViewById(R.id.btn_submit);
        apiService = RetrofitClient.getClient().create(ApiService.class);

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
                    pincode.setText(user.getPincode());
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
                updateUserProfile();
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
        String updatePincode = pincode.getText().toString().trim();

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
        if (updatePincode.isEmpty()) {
            address.setError("Pincode is required");
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
                    existingUser.setPincode(updatePincode);

                    // Save the updated user details
                    userViewModel.updateUser(existingUser);
                    UserUpdateTask userUpdateTask = new UserUpdateTask(updatedUsername,updatedEmail,updatedPassword,updatedFullname,updatedNickname,updatedNumber ,updatedAddress,updatePincode,EditProfileActivity.this);
                    userUpdateTask.execute();
                    // Navigate to another activity
//                    Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
//                    startActivity(intent);
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


    private void updateUserProfile() {
        // Retrieve updated values from UI
        String updatedUsername = username.getText().toString().trim();
        String updatedEmail = email.getText().toString().trim();
        String updatedPassword = password.getText().toString().trim();
        String updatedFullname = full_name.getText().toString().trim();
        String updatedNickname = nickname.getText().toString().trim();
        String updatedNumber = number.getText().toString().trim();
        String updatedAddress = address.getText().toString().trim();
        String updatedPincode = pincode.getText().toString().trim();

        // Get the user ID of the user you want to update (replace with the actual user ID)
        int userId = 1;

        Call<UpdateUserModel> call = apiService.updateUserProfile(
                userId,  // Pass the user ID to identify the user to be updated
                updatedUsername,
                updatedEmail,
                updatedPassword,
                updatedFullname,
                updatedNickname,
                updatedNumber,
                updatedAddress,
                updatedPincode
        );

        call.enqueue(new Callback<UpdateUserModel>() {
            @Override
            public void onResponse(Call<UpdateUserModel> call, Response<UpdateUserModel> response) {
                if (response.isSuccessful()) {
                    UpdateUserModel user = response.body();
                    // Handle successful response
                    Log.d("API_RESPONSE", "User ID: " + user.getId());
                    Toast.makeText(EditProfileActivity.this, "User updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle error response
                    Log.e("API_RESPONSE", "Error: " + response.message());
                    Toast.makeText(EditProfileActivity.this, "Failed to update user", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateUserModel> call, Throwable t) {
                // Handle failure
                Log.e("API_RESPONSE", "Error: " + t.getMessage());
                Toast.makeText(EditProfileActivity.this, "Failed to update user", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onUserUpdateComplete(String result) {
        try {
            JSONObject jsonResult = new JSONObject(result);

            // Check the status code
            int statusCode = jsonResult.getInt("status");
            String message = jsonResult.getString("message");

            if (statusCode == 200) {
                // Proceed with starting the LoginActivity
                Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish(); // Optional: Close the current activity if needed
            } else {
                // Show a Toast with the error message
                Toast.makeText(EditProfileActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            // Handle JSON parsing error, if any
            Toast.makeText(EditProfileActivity.this, "Error parsing JSON", Toast.LENGTH_SHORT).show();
        }
    }


}