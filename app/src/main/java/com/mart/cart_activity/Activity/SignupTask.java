package com.mart.cart_activity.Activity;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SignupTask extends AsyncTask<Void, Void, String> {
    private String username;
    private String email;
    private String password;
    private SignupActivity activity;

    public SignupTask(String username, String email, String password, SignupActivity activity) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.activity = activity;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            // Construct the URL with parameters
            String urlString = "https://test.njoymart.in/apis/api_signup.php" +
                    "?username=" + username +
                    "&email=" + email +
                    "&password=" + password;

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

//    @Override
//    protected void onPostExecute(String result) {
//        Log.d("SignupTask", "Response: " + result);
//
//        // Parse the result and handle accordingly
//        // You can use JSON parsing libraries like Gson to parse the JSON response
//    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("SignupTask", "Response: " + result);

        // Parse the result and handle accordingly
        // You can use JSON parsing libraries like Gson to parse the JSON response

        // Pass the result to the activity
        activity.onSignupComplete(result);
    }
}
