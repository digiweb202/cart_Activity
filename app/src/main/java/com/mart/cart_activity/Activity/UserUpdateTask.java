package com.mart.cart_activity.Activity;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class UserUpdateTask extends AsyncTask<Void, Void, String> {

    private String username;
    private String email;
    private String password;
    private String fullName;
    private String nickname;
    private String number;
    private String address;
    private String pincode;
    private EditProfileActivity activity;

    public UserUpdateTask(String username, String email, String password, String fullName,
                          String nickname, String number, String address, String pincode,
                          EditProfileActivity activity) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.nickname = nickname;
        this.number = number;
        this.address = address;
        this.pincode = pincode;
        this.activity = activity;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            // Construct the URL
            String urlString = "https://test.njoymart.in/apis/api_userprofile_update.php"+ "?username="+username+"&email="+email+"&password="+password
                    +"&fullname="+fullName+"&nickname="+nickname+"&number="+number+"&address="+address+"&pincode="+pincode;
            URL url = new URL(urlString);

            // Open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();


            // Set request method
            connection.setRequestMethod("GET");
            // Get response code
            int responseCode = connection.getResponseCode();

            // Read response body
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    return response.toString();
                }
            } else {
                return "Error: " + responseCode;
            }
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("UserUpdateTask", "Response: " + result);

        // Call the method to handle the update result in your activity
        activity.onUserUpdateComplete(result);
    }
}
