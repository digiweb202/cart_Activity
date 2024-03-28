package com.mart.cart_activity.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;


import com.bumptech.glide.Glide;
import com.mart.cart_Activity.R;
import com.mart.cart_activity.Api.ApiService;
import com.mart.cart_activity.ApiModel.SingleProductModel;
import com.mart.cart_activity.CartManagment.ManagmentCartList;
import com.mart.cart_activity.Dao.ProductDao;
import com.mart.cart_activity.Database.AppDatabase;
import com.mart.cart_activity.DatabaseApi.RetrofitClient;
import com.mart.cart_activity.Entities.ProductListEntities;
import com.mart.cart_activity.Helper.ManagmentCart;
import com.mart.cart_activity.domain.PopularDomain;
import com.squareup.picasso.Picasso;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailActivity extends AppCompatActivity {

    private PopularDomain object;
    private int numberOrder =1 ;
    private ManagmentCart managmentCart;
    private Button gotocart;
    private ImageView itemimage;
    private TextView titleName;
    private TextView descriptiontxt;
    private TextView pricetxt;
    private ImageView wishlist;
    private Button addBtn;
    String productID;
    String sellerSKU;
    Button buyAdd;
    ImageView shareImageView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        itemimage = findViewById(R.id.viewitem);
        titleName = findViewById(R.id.titleTxt);
        descriptiontxt = findViewById(R.id.descriptionTxt);
        pricetxt = findViewById(R.id.priceTxt);
        wishlist = findViewById(R.id.imageView7);
        addBtn = findViewById(R.id.buyBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(DetailActivity.this,"Check//:",Toast.LENGTH_SHORT).show();
                ProductStroageList.getInstance().addProduct(productID,sellerSKU);

                List<List<String>> productList = ProductStroageList.getInstance().getProductList();
                StringBuilder toastMessage = new StringBuilder("Product List:\n");

                for (List<String> productPair : productList) {
                    // Assuming each product pair has two elements (product ID and seller SKU)
                    if (productPair.size() == 2) {
                        toastMessage.append("Product ID: ").append(productPair.get(0))
                                .append(", Seller SKU: ").append(productPair.get(1))
                                .append("\n");
                    }
                }

                Toast.makeText(DetailActivity.this, toastMessage.toString(), Toast.LENGTH_SHORT).show();



            }
        });
        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Store product ID and seller SKU in SharedPreferences
//                addToWishlist(productID, sellerSKU);
                Toast.makeText(DetailActivity.this,"Added Your WishList is product//:",Toast.LENGTH_SHORT).show();
            }
        });


        String productId = getIntent().getStringExtra("PRODUCT_ID");
        String seller_sku = getIntent().getStringExtra("SELLER_SKU");

        Retrofit retrofit = RetrofitClient.getClient();
//        getBundles();
//        setupUI();
        managmentCart= new ManagmentCart(this);
         shareImageView = findViewById(R.id.imageView8);
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
                String productId = getIntent().getStringExtra("PRODUCT_ID");
                String seller_sku = getIntent().getStringExtra("SELLER_SKU");
                // Perform action when the shareImageView is clicked
                shareContent(productId,seller_sku ); // You can define this method to handle the click action
            }
        });


        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
        // Create an instance of the ApiService interface
        ApiService apiService = retrofit.create(ApiService.class);

        // Get parameters from your API input method
        productID = productId;
        sellerSKU = seller_sku;
        Call<List<SingleProductModel>> call;
        Intent intent = getIntent();
        Uri data = intent.getData();
        if (data != null) {
            String productIds = data.getQueryParameter("id");
            String sellerSku = data.getQueryParameter("sku");
            // Now you have the product ID and seller SKU, do whatever you need with them
            call = apiService.getData(productIds, sellerSku);
            productID = productIds;
            sellerSKU = sellerSku;
        }else{
            call = apiService.getData(productID, sellerSKU);
        }

        // Make the Retrofit call
//        Call<List<SingleProductModel>> call = apiService.getData(productID, sellerSKU);
        call.enqueue(new Callback<List<SingleProductModel>>() {
            @Override
            public void onResponse(Call<List<SingleProductModel>> call, Response<List<SingleProductModel>> response) {
                if (response.isSuccessful()) {
                    List<SingleProductModel> data = response.body();
                    // Process the data as needed

                    String itemimagename = data.get(0).getMain_Image_URL();
                    Picasso.get().load(itemimagename).into(itemimage);
                    String itemName = data.get(0).getItem_Name();
                    titleName.setText(itemName);
                    titleName.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(DetailActivity.this, itemName, Toast.LENGTH_SHORT).show();
                        }
                    });
                    descriptiontxt.setText(data.get(0).getProduct_Description());
                    pricetxt.setText(data.get(0).getYour_Price());



                } else {
                    // Handle error
                    Toast.makeText(DetailActivity.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<SingleProductModel>> call, Throwable t) {
                // Handle failure
                Toast.makeText(DetailActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
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
    public void shareContent(String productid,String sellersku) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Njoymart"); // Optional subject
        shareIntent.putExtra(Intent.EXTRA_TEXT, "https://njoymart.in/product?id="+productid+"&sku="+sellersku+"\n"); // Content to be shared

        // Create a chooser to show available sharing apps
        Intent chooserIntent = Intent.createChooser(shareIntent, "link");

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
            buyAdd = findViewById(R.id.buyBtn);
            ImageView backbtn = findViewById(R.id.backbtn);

            int drawableResourceId = getResources().getIdentifier(object.getPicUrl(), "drawable", getPackageName());
            Glide.with(this).load(drawableResourceId).into(itemPic);

            titleTxt.setText(object.getTitle());
            priceTxt.setText(String.format("$%s", object.getPrice()));
            descriptionTxt.setText(object.getDescription());
            reviewTxt.setText(String.valueOf(object.getReview()));
            ratingTxt.setText(String.valueOf(object.getScore()));

            buyAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



//                    addToWishlist(productID, sellerSKU);
                    Toast.makeText(DetailActivity.this,"ProductID"+productID+"SellSKU"+sellerSKU,Toast.LENGTH_SHORT).show();
                    // Handle button click
                    // You may want to add your logic here
                    // For example, updating the object or performing some action

//                    try {
////                        object.setNumberInChart(numberOrder);
////                        object.setNumberInChart(object.getNumberInChart() + 1 > object.getNumberInChart() ? object.getNumberInChart() + 1 : object.getNumberInChart());
//                            object.setNumberInChart(1);
//                        managmentCart.insertFood(object);
//                        // Assuming you have an instance of AppDatabase
//                        AppDatabase appDatabase = AppDatabase.getInstance(getApplicationContext());
//
//// Get the ProductDao from AppDatabase
//                        ProductDao productDao = appDatabase.productDao();
//
//// Now, you can use productDao in ManagmentCartList
//                        ManagmentCartList managmentCartList = new ManagmentCartList(productDao);
//
//// Rest of your code
//                        ProductListEntities product = new ProductListEntities(object.getPicUrl(), object.getTitle(), object.getPrice());
//
//                        long productId = managmentCartList.insertProduct(product);
//
//                        if (productId != -1) {
//                            // Product inserted successfully
//                            Toast.makeText(DetailActivity.this, "Product added to cart", Toast.LENGTH_SHORT).show();
//                        } else {
//                            // Error inserting product
//                            Toast.makeText(DetailActivity.this, "Error adding product to cart", Toast.LENGTH_SHORT).show();
//                        }
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        Toast.makeText(DetailActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }



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
    // Method to save product ID and seller SKU in SharedPreferences
    // Method to add a product to the wishlist in SharedPreferences
    // Method to add a product to the wishlist in SharedPreferences
    private void addToWishlist(String productId, String sellerSku) {
        SharedPreferences sharedPreferences = getSharedPreferences("WishlistPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Retrieve the existing set of wishlist items
        Set<String> wishlistSet = sharedPreferences.getStringSet("WISHLIST_SET", new HashSet<>());

        // Check if the product is already in the wishlist
        String wishlistItem = "Product ID: " + productId + ", Seller SKU: " + sellerSku;
        if (!wishlistSet.contains(wishlistItem)) {
            // Product not in the wishlist, add it
            wishlistSet.add(wishlistItem);

            // Save the updated wishlist set
            editor.putStringSet("WISHLIST_SET", wishlistSet);

            // Apply the changes
            editor.apply();

            // Show a toast indicating that the product has been added to the wishlist
            Toast.makeText(this, "Added to Wishlist", Toast.LENGTH_SHORT).show();
        } else {
            // Show a toast indicating that the product is already in the wishlist
            Toast.makeText(this, "Product already in Wishlist", Toast.LENGTH_SHORT).show();
        }
    }


    // Method to retrieve the list of wishlist items from SharedPreferences
    private List<String> getWishlistFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("WishlistPrefs", MODE_PRIVATE);
        Set<String> wishlistSet = sharedPreferences.getStringSet("WISHLIST_SET", new HashSet<>());
        return new ArrayList<>(wishlistSet);
    }


}
