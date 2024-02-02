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
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.*;

public class MyApiClient extends AsyncTask<Void, Void, String> {
    // Constructor to receive the context
    private Context context;
    private String phoneNumber;

    private String response;
    // Constructor to receive the context and phone number
    public MyApiClient(Context context, String phoneNumber) {
        this.context = context;
        this.phoneNumber = phoneNumber;
    }
    @Override
    protected String doInBackground(Void... voids) {
        try {
            String template = "Your verification code for njoymart.com is [otp length=\"4\" retry=\"3\" validity=\"10\"]\n" +
                    "Powered by\n" +
                    "Njoy Mart \n" +
                    "An Initiative by NW ECOMMERCE PRIVATE LIMITED";

            // Construct the URL with the API endpoint and use phoneNumber
            URL url = new URL("https://www.smsalert.co.in/api/mverify.json?apikey=63fcb0bd68ec3&sender=NWECOM&mobileno=" + phoneNumber + "&template=" + template);

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
        return response;
    }
    @Override
    protected void onPostExecute(String result) {
        // Handle the result here
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
                    Intent intent = new Intent(context, OtpActivity.class);
                    intent.putExtra("phoneNumber", phoneNumber);
                    // Pass any necessary data to OtpActivity using intent.putExtra if needed
                    context.startActivity(intent);
                    // Handle the case where the status is 'success'
                    // Extract additional information from the JSON response
                    JSONObject descriptionObject = jsonResponse.getJSONObject("description");
                    String desc = descriptionObject.getString("desc");

                    // Handle the batch details
                    JSONArray batchDetailsArray = descriptionObject.getJSONArray("batch_dtl");
//                    if (batchDetailsArray.length() > 0) {
//                        JSONObject batchDetail = batchDetailsArray.getJSONObject(0);
//                        String mobileNumber = batchDetail.getString("mobileno");
//                        String messageId = batchDetail.getString("msgid");
//                        String statusDetail = batchDetail.getString("status");
//
//                        // Handle the batch details as needed
//                        Log.d("MyApiClient", "Mobile Number: " + mobileNumber);
//                        Log.d("MyApiClient", "Message ID: " + messageId);
//                        Log.d("MyApiClient", "Status: " + statusDetail);
//                    }
                } else if ("error".equals(status)) {
                    // Handle the case where the status is 'error'
                    Log.e("MyApiClient", "Request failed. Description: " + description);
                } else {
                    // Handle other cases
                    Log.d("MyApiClient", "Request successful. Description: " + description);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                // Handle JSON parsing error
                Log.e("MyApiClient", "JSON parsing error");
            }
        } else {
            // Handle the case where the request failed
            Log.e("MyApiClient", "Request failed");
        }
    }

}
