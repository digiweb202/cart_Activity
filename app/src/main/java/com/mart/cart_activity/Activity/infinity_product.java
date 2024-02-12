package com.mart.cart_activity.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.mart.cart_Activity.R;
import com.mart.cart_activity.Adapter.ProductAdapter;
import com.mart.cart_activity.Api.ApiService;
import com.mart.cart_activity.ApiModel.GetProductModel;
import com.mart.cart_activity.DatabaseApi.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class infinity_product extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String BASE_URL = "https://test.njoymart.in/apis/";

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<GetProductModel> dataList;
    private int currentPage = 1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infinity_product);
        recyclerView = findViewById(R.id.recyclerView);
        dataList = new ArrayList<>();
        adapter = new ProductAdapter(infinity_product.this, dataList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Implement infinite scrolling
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading() && !isLastPage()) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= GetProductModel.PAGE_SIZE) {
                        loadMoreItems();
                    }
                }
            }
        });

        // Initial data loading
        fetchData();
    }

    private void fetchData() {
        RetrofitClient.getClient()
                .create(ApiService.class)
                .fetchData(GetProductModel.PAGE_SIZE, currentPage)
                .enqueue(new Callback<List<GetProductModel>>() {
                    @Override
                    public void onResponse(Call<List<GetProductModel>> call, Response<List<GetProductModel>> response) {
                        if (response.isSuccessful()) {
                            List<GetProductModel> newData = response.body();

                            if (newData != null) {
                                dataList.addAll(newData);
                                adapter.notifyDataSetChanged();
                                currentPage++;
                            }
                        } else {
                            Log.e(TAG, "Request failed with code: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<GetProductModel>> call, Throwable t) {
                        Log.e(TAG, "Network request failed: " + t.getMessage());
                    }
                });
    }


    private void loadMoreItems() {
        fetchData();
    }

    private boolean isLoading() {
        // Add your loading indicator logic here
        return false;
    }

    private boolean isLastPage() {
        // Add your last page logic here
        return false;
    }
}