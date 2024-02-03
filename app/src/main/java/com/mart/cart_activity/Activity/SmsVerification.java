package com.mart.cart_activity.Activity;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class SmsVerification extends AsyncTask<Void, Void, String> {

    private String mobileNumber;
    private String otpCode;

    private Context context; // Added Context instance variable

    // Modified constructor to accept Context
    public SmsVerification(Context context, String mobileNumber, String otpCode) {
        this.context = context;
        this.mobileNumber = mobileNumber;
        this.otpCode = otpCode;
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            // Replace with your actual URL
            String apiUrl = "https://www.smsalert.co.in/api/mverify.json?apikey=63fcb0bd68ec3&mobileno=" + mobileNumber + "&code=" + otpCode;
            URL url = new URL(apiUrl);
            Log.e("URL","URL"+ url);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");

            int statusCode = urlConnection.getResponseCode();

            if (statusCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                   Log.e("responsesActivitySms",response.toString());
                    handleApiResponse(response.toString());
                return response.toString();
            } else {
                return "Unexpected HTTP status: " + statusCode + " " + urlConnection.getResponseMessage();
            }
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        // Handle the API response in the UI thread
        Log.d("SmsVerification", "Response: " + result);
        // You can perform any further processing here
    }
    private void handleApiResponse(String response) {
        try {
            JSONObject jsonResponse = new JSONObject(response);
            String status = jsonResponse.optString("status", "");
            JSONObject description = jsonResponse.optJSONObject("description");

            if ("success".equals(status) && description != null) {
                String desc = description.optString("desc", "");

                if ("Code Matched successfully.".equals(desc)) {
                    // Start LoginActivity using the stored Context
                    context.startActivity(new Intent(context, LoginActivity.class));
                    return; // Exit the method if the conditions are met
                }
            }

            // Handle other cases if needed
            Log.e("SmsVerification", "Verification failed or conditions not met: " + response);

        } catch (JSONException e) {
            // Handle JSON parsing error
            Log.e("SmsVerification", "Error parsing JSON: " + e.getMessage());
        }
    }

}
