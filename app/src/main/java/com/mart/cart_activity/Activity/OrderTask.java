package com.mart.cart_activity.Activity;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class OrderTask extends AsyncTask<Void, Void, String> {
    private String userid;
    private String email;
    private String product_id;
    private String select_sku;
    private String quantity;
    private String total_amount;
    private CartActivity Activity;

    public OrderTask(String userid, String email, String product_id, String select_sku, String quantity, String total_amount,  CartActivity Activity) {
        this.userid = userid;
        this.email = email;
        this.product_id = product_id;
        this.select_sku = select_sku;
        this.quantity = quantity;
        this.total_amount = total_amount;
        this.Activity = Activity;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            // Construct the URL with your server endpoint
            String urlString = "https://test.njoymart.in/apis/api_order.php";
            URL url = new URL(urlString);

            // Open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);

            // Build the request parameters
            String urlParameters = "userid=" + userid +
                    "&email=" + email +
                    "&product_id=" + product_id +
                    "&select_sku=" + select_sku +
                    "&quantity=" + quantity +
                    "&total_amount=" + total_amount;

            // Write parameters to the connection's output stream
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = urlParameters.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

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
        Log.d("OrderTask", "Response: " + result);

        // Parse the result using Gson or handle it as needed
        // You can use JSON parsing libraries like Gson to parse the JSON response

        // Pass the result to the callback
        Activity.onOrderComplete(result);
    }

    // Define a callback interface for handling the order completion
    public interface OrderCallback {
        void onOrderComplete(String result);
    }
}
