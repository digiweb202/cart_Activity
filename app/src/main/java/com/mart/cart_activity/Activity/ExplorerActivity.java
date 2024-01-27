package com.mart.cart_activity.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mart.cart_Activity.R;
import com.mart.cart_activity.Adapter.PopularAdapter;
import com.mart.cart_activity.domain.PopularDomain;

import java.util.ArrayList;


public class ExplorerActivity extends AppCompatActivity {
    private ImageView explorerbackbtn;

    private RecyclerView popularRecyclerView;
    private RecyclerView digitalRecyclearView;
    private RecyclerView clothRecyclerView;
    private RecyclerView Toolsproducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorer);

        explorerbackbtn = findViewById(R.id.explorerBackbtn);

        explorerbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExplorerActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Define an array of "See all" button IDs and corresponding category TextView IDs
        int[] seeAllButtonIds = {R.id.propular_seeall, R.id.digital_seeall, R.id.cloth_seeall, R.id.tools_seeall};
        int[] categoryTextViewIds = {R.id.propular_product, R.id.textView6s, R.id.textView6snew, R.id.textviewproductcard};

        // Set click listeners for the "See all" buttons using a loop
        for (int i = 0; i < seeAllButtonIds.length; i++) {
            TextView seeAllButton = findViewById(seeAllButtonIds[i]);
            final TextView categoryTextView = findViewById(categoryTextViewIds[i]);

            seeAllButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Get the category from the corresponding TextView
                    String category = categoryTextView.getText().toString();

                    // Start the SingleProductCategories activity and pass the category information
                    startSingleProductCategories(category);
                }
            });
        }
        initRecyclerView(); // Move this line inside the onCreate method
    }

    private void startSingleProductCategories(String category) {
        // Start the SingleProductCategories activity and pass the category information
        Intent intent = new Intent(ExplorerActivity.this, SingleProductCategories.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
    private void initRecyclerView() {
        ArrayList<PopularDomain> items = new ArrayList<>();
        items.add(new PopularDomain("T-Shirt", "item_1", 3, 4, 25, "Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience."));
        items.add(new PopularDomain("watch", "item_2", 3, 4, 50, "Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience."));
        items.add(new PopularDomain("Mobile", "item_3", 3, 4, 100, "Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience."));
        items.add(new PopularDomain("TV", "item_4", 3, 4, 200, "Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience."));
        items.add(new PopularDomain("Watch", "item_2", 3, 4, 300, "Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience."));



        popularRecyclerView = findViewById(R.id.PopularView);
        digitalRecyclearView = findViewById(R.id.Digitalproduct);
        clothRecyclerView = findViewById(R.id.Clothsproduct);
        Toolsproducts = findViewById(R.id.Toolsproducts);

        // Set up the LinearLayoutManager for PopularView
        LinearLayoutManager popularLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        popularRecyclerView.setLayoutManager(popularLayoutManager);

        // Set up the LinearLayoutManager for Digitalproduct
        LinearLayoutManager digitalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        digitalRecyclearView.setLayoutManager(digitalLayoutManager);

        LinearLayoutManager clothLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        clothRecyclerView.setLayoutManager(clothLayoutManager);

        LinearLayoutManager toolsproducts = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        Toolsproducts.setLayoutManager(toolsproducts);


        PopularAdapter adapter = new PopularAdapter(items);
        popularRecyclerView.setAdapter(adapter);
        digitalRecyclearView.setAdapter(adapter);
        clothRecyclerView.setAdapter(adapter);
        Toolsproducts.setAdapter(adapter);

    }


    @Override
    public void onBackPressed() {
        // Customize the behavior when the back button is pressed
        // For example, you can navigate to another activity, show a dialog, etc.

        // Add your custom code here
        super.onBackPressed();
        Log.e("BackButton","Back Button press");
        // If you want to perform the default back button behavior, remove this line
    }
}