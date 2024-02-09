package com.mart.cart_activity.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mart.cart_Activity.R;
import com.mart.cart_activity.CartManagment.ManagmentCartList;
import com.mart.cart_activity.Dao.ProductDao;
import com.mart.cart_activity.Database.AppDatabase;
import com.mart.cart_activity.Entities.ProductListEntities;
import com.mart.cart_activity.Helper.ManagmentCart;
import com.mart.cart_activity.domain.PopularDomain;

public class DetailActivity extends AppCompatActivity {

    private PopularDomain object;
    private int numberOrder =1 ;
    private ManagmentCart managmentCart;
    private Button gotocart;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getBundles();
        setupUI();
        managmentCart= new ManagmentCart(this);
        ImageView shareImageView = findViewById(R.id.imageView8);
        gotocart = findViewById(R.id.go_to_cart);


        gotocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managmentCart.displayCartItems();
                Intent intent = new Intent(DetailActivity.this,CartActivity.class);
                startActivity(intent);
            }
        });
        // Set OnClickListener for shareImageView
        shareImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action when the shareImageView is clicked
                shareContent(); // You can define this method to handle the click action
            }
        });


    }

    private void getBundles() {
        object = (PopularDomain) getIntent().getSerializableExtra("object");
        if (object != null) {
            // Proceed with setting up UI
            setupUI();
        } else {
            // Handle the case where the object is null
            // For example, show an error message or finish the activity
            finish();
        }


    }


    // Method to share content
    private void shareContent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject"); // Optional subject
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Your shared content goes here."); // Content to be shared

        // Create a chooser to show available sharing apps
        Intent chooserIntent = Intent.createChooser(shareIntent, "Share via");

        // Check if there are apps that can handle the intent
        if (shareIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooserIntent);
        }
    }
    private void setupUI() {
        if (object != null) {
            ImageView itemPic = findViewById(R.id.viewitem);
            TextView titleTxt = findViewById(R.id.titleTxt);
            TextView priceTxt = findViewById(R.id.priceTxt);
            TextView descriptionTxt = findViewById(R.id.descriptionTxt);
            TextView reviewTxt = findViewById(R.id.reviewTxt);
            TextView ratingTxt = findViewById(R.id.ratingTxt);
            Button btnAdd = findViewById(R.id.buyBtn);
            ImageView backbtn = findViewById(R.id.backbtn);

            int drawableResourceId = getResources().getIdentifier(object.getPicUrl(), "drawable", getPackageName());
            Glide.with(this).load(drawableResourceId).into(itemPic);

            titleTxt.setText(object.getTitle());
            priceTxt.setText(String.format("$%s", object.getPrice()));
            descriptionTxt.setText(object.getDescription());
            reviewTxt.setText(String.valueOf(object.getReview()));
            ratingTxt.setText(String.valueOf(object.getScore()));

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle button click
                    // You may want to add your logic here
                    // For example, updating the object or performing some action

                    try {
//                        object.setNumberInChart(numberOrder);
//                        object.setNumberInChart(object.getNumberInChart() + 1 > object.getNumberInChart() ? object.getNumberInChart() + 1 : object.getNumberInChart());
                            object.setNumberInChart(1);
                        managmentCart.insertFood(object);
                        // Assuming you have an instance of AppDatabase
                        AppDatabase appDatabase = AppDatabase.getInstance(getApplicationContext());

// Get the ProductDao from AppDatabase
                        ProductDao productDao = appDatabase.productDao();

// Now, you can use productDao in ManagmentCartList
                        ManagmentCartList managmentCartList = new ManagmentCartList(productDao);

// Rest of your code
                        ProductListEntities product = new ProductListEntities(object.getPicUrl(), object.getTitle(), object.getPrice());

                        long productId = managmentCartList.insertProduct(product);

                        if (productId != -1) {
                            // Product inserted successfully
                            Toast.makeText(DetailActivity.this, "Product added to cart", Toast.LENGTH_SHORT).show();
                        } else {
                            // Error inserting product
                            Toast.makeText(DetailActivity.this, "Error adding product to cart", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(DetailActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }



                }
            });

            backbtn.setOnClickListener(v -> finish());
        } else {
            // Handle the case when the object is null
            // Log or show an error message
            Toast.makeText(this, "Object is null", Toast.LENGTH_SHORT).show();
            finish(); // You might want to finish the activity if the object is null
        }
    }

}
