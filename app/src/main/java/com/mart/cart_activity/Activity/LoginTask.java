package com.mart.cart_activity.Activity;



import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LoginTask extends AsyncTask<Void, Void, String> {

    private String username;
    private String password;
    private LoginActivity activity;
    public LoginTask(String username, String password, LoginActivity activity) {
        this.username = username;
        this.password = password;
        this.activity = activity;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            // Construct the URL with parameters
            String urlString = "http://localhost/NJOYMART_V2.O/Mart-Website%20Frontend/Njoymart-Website-Frontend/nest/demo/apis/api_login.php" +
                    "?username=" + username +
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
//        Log.d("LoginTask", "Response: " + result);
//
//        // Parse the result and handle accordingly
//        // You can use JSON parsing libraries like Gson to parse the JSON response
//    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("LoginTask", "Response: " + result);

        // Call the method to handle the login result in your activity
        activity.onLoginComplete(result);
    }


}