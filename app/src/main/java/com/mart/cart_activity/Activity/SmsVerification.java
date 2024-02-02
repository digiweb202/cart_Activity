package com.mart.cart_activity.Activity;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SmsVerification extends AsyncTask<Void, Void, String> {
    private Context context;
    private String phoneNumber;
    private String verificationCode;  // New field for verification code

    private String response;

    // Constructor to receive the context, phone number, and verification code
    public SmsVerification(Context context, String phoneNumber, String verificationCode) {
        this.context = context;
        this.phoneNumber = phoneNumber;
        this.verificationCode = verificationCode;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            // Construct the URL with the API endpoint and use phoneNumber and the provided verification code
            URL url = new URL("http://www.smsalert.co.in/api/mverify.json?apikey=63fcb0bd68ec3&mobileno=8780719280&code=5515");
                Log.e("APIAPI",url.toString());
            // Open a connection to the URL
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try {
                // Read the response
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }

                bufferedReader.close();
                return stringBuilder.toString();
            } finally {
                // Close the connection
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public String getResponse() {
        Log.e("APIresponse",response.toString());

        return response;
    }
    @Override
    protected void onPostExecute(String result) {
        // Handle the result here
        Log.e("result",result.toString());
        if (result != null) {
            // Parse the JSON response
            try {
                JSONObject jsonResponse = new JSONObject(result);
                String status = jsonResponse.getString("status");
                String description = jsonResponse.getString("description");

                // Display a Toast with the description
                Toast.makeText(context, description, Toast.LENGTH_SHORT).show();

                // You can also use 'status' for further handling based on the API response
                if ("success".equals(status)) {
                    response = "success";

                    // Check if 'desc' property exists
                    if (jsonResponse.has("description")) {
                        String desc = jsonResponse.getJSONObject("description").getString("desc");
                        Log.d("SmsVerification", "Description: " + desc);
                    }

                    Intent intent = new Intent(context, OtpActivity.class);
                    intent.putExtra("phoneNumber", phoneNumber);
                    context.startActivity(intent);
                } else if ("error".equals(status)) {
                    // Handle the case where the status is 'error'
                    Log.e("SmsVerification", "Request failed. Description: " + description);
                } else {
                    // Handle other cases
                    Log.d("SmsVerification", "Request successful. Description: " + description);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                // Handle JSON parsing error
                Log.e("SmsVerification", "JSON parsing error");
            }
        } else {
            // Handle the case where the request failed
            Log.e("SmsVerification", "Request failed");
        }
    }


}
