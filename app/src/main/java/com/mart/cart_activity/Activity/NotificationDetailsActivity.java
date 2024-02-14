package com.mart.cart_activity.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.mart.cart_Activity.R;

public class NotificationDetailsActivity extends AppCompatActivity {
    private TextView message;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_details);
        message = findViewById(R.id.thankYouMessage);
        title = findViewById(R.id.title);
        title.setText("Title"+"\n"+"Welcome to Njoymart");
        message.setText("Discover a world of Njoygroup. Njoymart is designed to Briefly Describe the Purpose or Mission. Whether you're Target Audience, Njoymart is here to Main Benefits or Solutions.\n" +
                "\n" +
                "Links:\n" +
                "\n" +
                "Sign Up Now - Join our community and unlock exclusive features.\n" +
                "Explore Features - Learn about the powerful tools and functionalities.\n" +
                "Get Started - Dive into the world of Njoymart.\n");
    }
}