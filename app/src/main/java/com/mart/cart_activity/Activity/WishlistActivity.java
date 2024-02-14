package com.mart.cart_activity.Activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.mart.cart_Activity.R;
import com.mart.cart_activity.Adapter.ExplorerPopularAdapter;
import com.mart.cart_activity.Adapter.PopularAdapter;
import com.mart.cart_activity.Adapter.ProductTypeAdapter;
import com.mart.cart_activity.Api.ApiResponse;
import com.mart.cart_activity.Api.ApiService;
import com.mart.cart_activity.ApiModel.ProductTypeModel;
import com.mart.cart_activity.DatabaseApi.RetrofitClient;
import com.mart.cart_activity.domain.PopularDomain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Response;

public class WishlistActivity extends AppCompatActivity {
    private RecyclerView popularRecyclerView;
    private ImageView Backbtn;
    private ProductTypeAdapter adapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
//        initRecyclerView();
// Example usage to get the wishlist items
        Set<String> wishlistItems = getWishlistFromSharedPreferences();

// Iterate through the set and do something with each wishlist item
        for (String wishlistItem : wishlistItems) {
            // Perform actions with each wishlist item (e.g., display in UI, log, etc.)
            Log.d("Wishlist::", wishlistItem);
        }

        Backbtn = findViewById(R.id.wishlistBackBtn);

        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WishlistActivity.this,MainActivity.class);
                startActivity(intent);

// Example usage to get the wishlist items
                Set<String> wishlistItems = getWishlistFromSharedPreferences();

// Iterate through the set and do something with each wishlist item
                for (String wishlistItem : wishlistItems) {
                    // Perform actions with each wishlist item (e.g., display in UI, log, etc.)
                    Log.d("Wishlist::", wishlistItem);
                }
            }
        });
        ArrayList<PopularDomain> items = new ArrayList<>();
        items.add(new PopularDomain("!!!", "", 3, 4, 25, "Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience."));




        popularRecyclerView = findViewById(R.id.PopularViews);
// Set up the GridLayoutManager for PopularView with 2 columns
        GridLayoutManager popularGridLayoutManager = new GridLayoutManager(this, 2);
        popularRecyclerView.setLayoutManager(popularGridLayoutManager);


        PopularAdapter adapter = new PopularAdapter(items);
        popularRecyclerView.setAdapter(adapter);

        fetchDataFromServer("A1");
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

    // Method to retrieve the list of product IDs and seller SKUs from SharedPreferences
    // Method to retrieve the list of wishlist items from SharedPreferences
    private Set<String> getWishlistFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("WishlistPrefs", MODE_PRIVATE);
        return sharedPreferences.getStringSet("WISHLIST_SET", new HashSet<>());
    }
    private void fetchDataFromServer(String productType) {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<ApiResponse> call = apiService.getCombinedData(productType);

        call.enqueue(new retrofit2.Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    if (apiResponse != null && apiResponse.isSuccess()) {
                        List<ProductTypeModel> productTypeModelList = apiResponse.getData();
                        if (productTypeModelList != null && !productTypeModelList.isEmpty()) {
                            adapter = new ProductTypeAdapter(productTypeModelList, WishlistActivity.this);
                            popularRecyclerView.setAdapter(adapter);
                        } else {
                            Log.e(TAG, "Empty or null data in the API response");
                        }
                    } else {
                        Log.e(TAG, "Unsuccessful API response");
                    }
                } else {
                    Log.e(TAG, "Server returned an error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e(TAG, "Error fetching data from server: " + t.getMessage());
            }
        });
    }

}