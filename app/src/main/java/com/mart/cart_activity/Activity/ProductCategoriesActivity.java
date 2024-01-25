package com.mart.cart_activity.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.mart.cart_Activity.R;

public class ProductCategoriesActivity extends AppCompatActivity {
    private ImageView notificationBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_categories);
        notificationBtn = findViewById(R.id.notificationbtn);
        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductCategoriesActivity.this,NotificationActivity.class);
                startActivity(intent);
            }
        });
    }
}