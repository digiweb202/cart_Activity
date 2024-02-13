package com.mart.cart_activity.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.card.MaterialCardView;
import com.mart.cart_Activity.R;
import com.mart.cart_activity.Adapter.SliderAdapter;
import com.mart.cart_activity.Api.ApiService;
import com.mart.cart_activity.ApiModel.ContentModel;
import com.mart.cart_activity.ApiResponse.CategoriesResponse;
import com.mart.cart_activity.DatabaseApi.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductCategoriesActivity extends AppCompatActivity {
    private ImageView notificationBtn;
    private TextView personview;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    private ViewPager2 viewPager;
    private SliderAdapter sliderAdapter;

    private Handler handler;
    private Runnable runnable;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_categories);
        notificationBtn = findViewById(R.id.notificationbtn);
        personview = findViewById(R.id.personNames);



        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        if(acct != null){
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            personview.setText(personName);
        }
        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductCategoriesActivity.this,NotificationActivity.class);
                startActivity(intent);
            }
        });
        GridLayout cardGrid = findViewById(R.id.cardGrid);
//        API function calling

//        makeApiRequest();
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
                        Intent intent = new Intent(ProductCategoriesActivity.this, ProductTypeActivity.class);
                        intent.putExtra("category", category);
                        startActivity(intent);
                    }
                });
            }
        }
        viewPager = findViewById(R.id.viewPager);

        // Get the Retrofit client
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        // Create a call to the API endpoint
        Call<List<ContentModel>> call = apiService.getContent();

        // Enqueue the call to make an asynchronous request
        call.enqueue(new Callback<List<ContentModel>>() {
            @Override
            public void onResponse(Call<List<ContentModel>> call, Response<List<ContentModel>> response) {
                if (response.isSuccessful()) {
                    List<ContentModel> contentList = response.body();
                    if (contentList != null) {
                        sliderAdapter = new SliderAdapter(contentList, ProductCategoriesActivity.this);
                        viewPager.setAdapter(sliderAdapter);

                        // Set up automatic sliding
                        handler = new Handler(Looper.getMainLooper());
                        runnable = () -> {
                            int currentItem = viewPager.getCurrentItem();
                            int itemCount = sliderAdapter.getItemCount();

                            // Loop back to the first item when reaching the end
                            viewPager.setCurrentItem((currentItem + 1) % itemCount, true);
                        };

                        // Schedule automatic sliding with a delay and interval
                        handler.postDelayed(runnable, 3000); // Delay in milliseconds
                        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                            @Override
                            public void onPageSelected(int position) {
                                handler.removeCallbacks(runnable);
                                handler.postDelayed(runnable, 3000); // Interval in milliseconds
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ContentModel>> call, Throwable t) {
                // Handle failure
            }
        });
    }
    // Function to make the API request
    //Categories Details fetch api code
    private void makeApiRequest() {
        // Create an instance of the ApiService using the RetrofitClient
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        // Make the API call
        Call<List<CategoriesResponse>> call = apiService.getCategories();
        call.enqueue(new Callback<List<CategoriesResponse>>() {
            @Override
            public void onResponse(Call<List<CategoriesResponse>> call, Response<List<CategoriesResponse>> response) {
                if (response.isSuccessful()) {
                    List<CategoriesResponse> categories = response.body();
                    // Handle the retrieved data as needed
                } else {
                    // Handle unsuccessful response
                }
            }

            @Override
            public void onFailure(Call<List<CategoriesResponse>> call, Throwable t) {
                // Handle failure
            }
        });
    }

}