package com.mart.cart_activity.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import com.mart.cart_Activity.R;
import com.mart.cart_activity.Adapter.ExplorerPopularAdapter;
import com.mart.cart_activity.Adapter.PopularAdapter;
import com.mart.cart_activity.domain.PopularDomain;

import java.util.ArrayList;

public class WishlistActivity extends AppCompatActivity {
    private RecyclerView popularRecyclerView;
    private ImageView Backbtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        initRecyclerView();
        Backbtn = findViewById(R.id.wishlistBackBtn);
        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WishlistActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initRecyclerView() {
        ArrayList<PopularDomain> items = new ArrayList<>();
        items.add(new PopularDomain("T-Shirt", "item_1", 3, 4, 25, "Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience."));
        items.add(new PopularDomain("watch", "item_2", 3, 4, 50, "Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience."));
        items.add(new PopularDomain("Mobile", "item_3", 3, 4, 100, "Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience."));
        items.add(new PopularDomain("TV", "item_4", 3, 4, 200, "Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience."));
        items.add(new PopularDomain("Watch", "item_2", 3, 4, 300, "Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience."));
        items.add(new PopularDomain("Watch", "item_2", 3, 4, 300, "Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience."));
        items.add(new PopularDomain("Watch", "item_2", 3, 4, 300, "Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience."));

        items.add(new PopularDomain("Watch", "item_2", 3, 4, 300, "Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience."));
        items.add(new PopularDomain("Watch", "item_2", 3, 4, 300, "Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience."));


        // Find the RecyclerView by ID
        popularRecyclerView = findViewById(R.id.PopularView);


        // Set up the GridLayoutManager and adapter
        int spanCount = 2; // You can change this based on the number of columns you want
        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
        popularRecyclerView.setLayoutManager(layoutManager);


//        // Add spacing between items
//        int spacing = convertDpToPixel(24); // Set your desired spacing in dp
//        // Set your desired spacing in dp
//        popularRecyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, true));


        ExplorerPopularAdapter adapter = new ExplorerPopularAdapter(items);
        popularRecyclerView.setAdapter(adapter);
    }
    // Utility method to convert dp to pixels
    private int convertDpToPixel(int dp) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        return (int) (dp * metrics.density);
    }

    @Override
    public void onBackPressed() {
        // Customize the behavior when the back button is pressed
        // For example, you can navigate to another activity, show a dialog, etc.

        // Add your custom code here
        super.onBackPressed(); // If you want to perform the default back button behavior, remove this line
    }
}