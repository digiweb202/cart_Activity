package com.mart.cart_activity.Activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.mart.cart_Activity.R;
import com.mart.cart_activity.Adapter.PopularAdapter;
import com.mart.cart_activity.Adapter.ProductTypeAdapter;
import com.mart.cart_activity.Api.ApiResponse;
import com.mart.cart_activity.Api.ApiService;
import com.mart.cart_activity.ApiModel.ProductTypeModel;
import com.mart.cart_activity.DatabaseApi.RetrofitClient;
import com.mart.cart_activity.domain.PopularDomain;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ProductTypeActivity extends AppCompatActivity {
    private RecyclerView catrecycleview;
    private ProductTypeAdapter adapter;
    String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_type);
        catrecycleview= findViewById(R.id.PopularView);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("category")) {
            category = intent.getStringExtra("category");

            // Now you can use the category information as needed
            TextView categoryTextView = findViewById(R.id.textView6);
            categoryTextView.setText("Product Categories: " + category);

            Toast.makeText(this, "Category:"+category, Toast.LENGTH_SHORT).show();
        }
        // Set up the LinearLayoutManager for PopularView

        initRecyclerView();


    }
    private void initRecyclerView() {
        ArrayList<PopularDomain> items = new ArrayList<>();
        items.add(new PopularDomain("T-Shirt", "item_1", 3, 4, 25, "Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience."));
        items.add(new PopularDomain("watch", "item_2", 3, 4, 50, "Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience."));
        items.add(new PopularDomain("Mobile", "item_3", 3, 4, 100, "Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience."));
        items.add(new PopularDomain("TV", "item_4", 3, 4, 200, "Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience."));
        items.add(new PopularDomain("Watch", "item_2", 3, 4, 300, "Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience."));



        // Set up the GridLayoutManager for PopularView with 2 columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        catrecycleview.setLayoutManager(gridLayoutManager);

// Set up the adapter
        PopularAdapter adapter = new PopularAdapter(items);
        catrecycleview.setAdapter(adapter);

        fetchDataFromServer(category);

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
                            adapter = new ProductTypeAdapter(productTypeModelList, ProductTypeActivity.this);
                            catrecycleview.setAdapter(adapter);
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