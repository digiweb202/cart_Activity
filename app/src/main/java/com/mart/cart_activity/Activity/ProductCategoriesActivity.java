package com.mart.cart_activity.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
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
        GridLayout cardGrid = findViewById(R.id.cardGrid);

        for (int i = 0; i < cardGrid.getChildCount(); i++) {
            View cardView = cardGrid.getChildAt(i);

            if (cardView instanceof MaterialCardView) {
                final MaterialCardView materialCardView = (MaterialCardView) cardView;

                materialCardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Extract category information from the clicked card
                        TextView categoryTextView = materialCardView.findViewById(R.id.cards);
                        String category = categoryTextView.getText().toString();

                        // Start the CategoriesActivity and pass the category information
                        Intent intent = new Intent(ProductCategoriesActivity.this, SingleProductCategories.class);
                        intent.putExtra("category", category);
                        startActivity(intent);
                    }
                });
            }
        }
    }
}