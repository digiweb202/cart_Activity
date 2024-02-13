package com.mart.cart_activity.Activity;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mart.cart_Activity.R;
import com.mart.cart_activity.Adapter.PopularAdapter;
import com.mart.cart_activity.Adapter.ProductTypeAdapter;
import com.mart.cart_activity.Adapter.SliderAdapter;
import com.mart.cart_activity.Adapter.SuggestionsAdapter;
import com.mart.cart_activity.Api.ApiResponse;
import com.mart.cart_activity.Api.ApiService;
import com.mart.cart_activity.ApiModel.ContentModel;
import com.mart.cart_activity.ApiModel.ProductTypeModel;
import com.mart.cart_activity.DatabaseApi.RetrofitClient;
import com.mart.cart_activity.Entities.UserEntities;
import com.mart.cart_activity.Entities.UserSignupEntities;
import com.mart.cart_activity.Model.UserViewModel;
import com.mart.cart_activity.Repository.UserSignupRepository;
import com.mart.cart_activity.domain.PopularDomain;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView popularRecyclerView;
    private RecyclerView digitalRecyclearView;
    private RecyclerView clothRecyclerView;
    private static final int PERMISSION_REQUEST_INTERNET = 1;
    private LinearLayout categoreisAllbtn;
    private ImageView notificationBtn;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    private TextView personname;
    private Button signoutbtn;
    UserViewModel userViewModel;
    private Button Buynow;
    private UserSignupRepository userSignupRepository;
    private ProductTypeAdapter adapter;

    private ViewPager2 viewPager;
    private SliderAdapter sliderAdapter;

    private Handler handler;
    private Runnable runnable;
    private EditText editTextSearch;
    EditText editTextTexts;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Set the content view using the layout resource ID


        LinearLayout cartBtn = findViewById(R.id.cartBtn);
        LinearLayout wishlist = findViewById(R.id.wishlistBtn);
        LinearLayout explorerBtn = findViewById(R.id.explorerBtn);
        LinearLayout profileBtn = findViewById(R.id.profilesBtn);
        categoreisAllbtn = findViewById(R.id.categoriesbtn);
        notificationBtn = findViewById(R.id.notificationbtn);
        personname = findViewById(R.id.personName);
//        Buynow = findViewById(R.id.buynow);
        editTextTexts = findViewById(R.id.editTextTexts);


        viewPager = findViewById(R.id.viewPager);


        // Set a key listener to detect when the user presses Enter on the keyboard

        editTextTexts.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event != null && (actionId == KeyEvent.KEYCODE_ENTER || event.getAction() == KeyEvent.ACTION_DOWN)) {
                    // Perform your search task here
                    performSearch();
                    return true;
                }else {
                    performSearch();
                    Toast.makeText(MainActivity.this,"Data View",Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
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
                        sliderAdapter = new SliderAdapter(contentList, MainActivity.this);
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

//        Intent intent = getIntent();
//        String data = intent.getStringExtra("keyName");
//        personname.setText(data);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        if(acct != null){
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            personname.setText(personName);
        }
        //SignOut Method


//        FloatingActionButton fab = findViewById(R.id.qrBtn);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                showQRCodePopup(view.getContext());
//            }
//        });
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });

        explorerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,infinity_product.class);
                startActivity(intent);
            }
        });
        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WishlistActivity.class);
                startActivity(intent);
            }
        });
        // Set OnClickListener for cartBtn
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action when the cartBtn is clicked
                Intent intent = new Intent(MainActivity.this,CartActivity.class);
                startActivity(intent);// You can define this method to handle the click action
            }
        });

        FloatingActionButton fab = findViewById(R.id.floatingbtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create and show the dialog box
                // Check for internet permission on devices with API level 23 and above
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                        // Permission not granted, request it
                        requestPermissions(new String[]{Manifest.permission.INTERNET}, PERMISSION_REQUEST_INTERNET);
                    } else {
                        // Permission already granted, proceed to show the dialog
                        showImageDialog();
                    }
                } else {
                    // For devices with API level below 23, internet permission is granted in the manifest
                    showImageDialog();
                }

            }
        });
        categoreisAllbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductCategoriesActivity.class);
                startActivity(intent);
            }
        });
        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NotificationActivity.class);
                startActivity(intent);

            }
        });
//        userViewModel = new ViewModelProvider(MainActivity.this).get(UserViewModel.class);
//
//        userViewModel.getAllPersons().observe(MainActivity.this, new Observer<List<UserEntities>>() {
//            @Override
//            public void onChanged(List<UserEntities> users) {
//                // Find the user with ID 1
//                UserEntities userWithId1 = null;
//                for (UserEntities user : users) {
//                    if (user.getId() == 1) {
//                        userWithId1 = user;
//                        break;
//                    }
//                }
//
//                // Display the information for the user with ID 1
//                if (userWithId1 != null) {
//                    StringBuilder data = new StringBuilder();
//                    String Name =  userWithId1.getName();
//                    String Password = userWithId1.getAge();
//                    personname.setText(Name);
//                    data.append("ID: ").append(userWithId1.getId()).append(", Name: ")
//                            .append(userWithId1.getName()).append(", Age: ")
//                            .append(userWithId1.getAge()).append("\n");
//
//                    Toast.makeText(MainActivity.this, data.toString(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        userSignupRepository = new UserSignupRepository(getApplication());

        // Example: Observe changes in user data for ID 1
        userSignupRepository.getUserById(1).observe(this, new Observer<UserSignupEntities>() {
            @Override
            public void onChanged(UserSignupEntities user) {
                if (user != null) {
                    // Handle user data here
                    String username = user.getUsername();
                    String email = user.getEmail();
                    personname.setText(username);
                    // ...
                }
            }
        });

// Define an array of "See all" button IDs and corresponding category TextView IDs
        int[] seeAllButtonIds = {R.id.textView7, R.id.textView7s, R.id.textView7snew};
        String[] categoryTextViewIds = {"A1", "digital", "clouth"}; // Use strings

// Set click listeners for the "See all" buttons using a loop
        for (int i = 0; i < seeAllButtonIds.length; i++) {
            TextView seeAllButton = findViewById(seeAllButtonIds[i]);
            final String category = categoryTextViewIds[i]; // Get category directly from the array

            seeAllButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Start the SingleProductCategories activity and pass the category information
                    startSingleProductCategories(category);
                }
            });
        }

        // Define an array of "See all" button IDs and corresponding category TextView IDs
        int[] seeAllButtonIdsimg = {R.id.imagecat1, R.id.imagecat4, R.id.imagecat2, R.id.imagecat3};
        String[] categoryTextViewIdsimg = {"digital", "beauty", "clouths", "tools"};

// Set click listeners for the "See all" buttons using a loop
        for (int i = 0; i < seeAllButtonIdsimg.length; i++) {
            ImageView seeAllButtonsimg = findViewById(seeAllButtonIdsimg[i]);
            final String categoryTextViewimg = (categoryTextViewIdsimg[i]);

            seeAllButtonsimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Get the category from the corresponding TextView


                    // Start the SingleProductCategories activity and pass the category information
                    startSingleProductCategories(categoryTextViewimg);
                }
            });
        }

//        Buynow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,CartActivity.class);
//                startActivity(intent);
//            }
//        });
        // Sample suggestions
        String[] suggestions = {"Android", "Android 1","android 3","alksdfjsdaf","Java", "Kotlin", "XML", "Studio"};

        // Adapter
        SuggestionsAdapter adapter = new SuggestionsAdapter(this,
                android.R.layout.simple_dropdown_item_1line, Arrays.asList(suggestions));

//        // AutoCompleteTextView
//        EditText autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
//        autoCompleteTextView.setAdapter(adapter);
        initRecyclerView();
    }

    private void startSingleProductCategories(String category) {
        // Start the SingleProductCategories activity and pass the category information
        Intent intent = new Intent(MainActivity.this, ProductTypeActivity.class);
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
        // Set up the LinearLayoutManager for PopularView
        LinearLayoutManager popularLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        popularRecyclerView.setLayoutManager(popularLayoutManager);

        // Set up the LinearLayoutManager for Digitalproduct
        LinearLayoutManager digitalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        digitalRecyclearView.setLayoutManager(digitalLayoutManager);

        LinearLayoutManager clothLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        clothRecyclerView.setLayoutManager(clothLayoutManager);



        PopularAdapter adapter = new PopularAdapter(items);
        popularRecyclerView.setAdapter(adapter);
        digitalRecyclearView.setAdapter(adapter);
        clothRecyclerView.setAdapter(adapter);


        fetchDataFromServer("A1");
        fetchDataFromServerDigital("digital");
        fetchDataFromServerCloth("clouth");
    }


private void showImageDialog() {
    // Call this method when internet permission is granted
    String imageUrl = "https://media.istockphoto.com/id/828088276/vector/qr-code-illustration.jpg?s=612x612&w=0&k=20&c=FnA7agr57XpFi081ZT5sEmxhLytMBlK4vzdQxt8A70M=";
    String title = "QR Code Image Dialog";
    String message = "This dialog displays a QR code image.";
    showDialog(MainActivity.this, imageUrl, title, message);
}
    private void showDialog(Context context, String imageUrl, String title, String message) {
        // Inflate the custom layout
        View dialogView = LayoutInflater.from(context).inflate(R.layout.custom_dialog_layout, null);

        // Create the AlertDialog with the custom layout
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogView);

        // Set title and message
        builder.setTitle(title);
        builder.setMessage(message);

        // Load the image into the ImageView using Picasso
        ImageView imageView = dialogView.findViewById(R.id.dialogImageView);
        Picasso.get().load(imageUrl).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareContent();
            }
        });
        // Create the dialog and set properties
        final AlertDialog dialog = builder.create();
        dialog.setCancelable(true); // Set to true to allow dismissing by clicking outside the dialog

        // Set the dismiss listener if needed
        dialog.setOnDismissListener(dialogInterface -> {
            // Code to be executed when the dialog is dismissed
        });

        // Show the dialog
        dialog.show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_INTERNET) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Internet permission granted, proceed to show the dialog
                showImageDialog();
            } else {
                // Permission denied, display a message or take appropriate action
                Toast.makeText(this, "Internet permission denied. Cannot load the image.", Toast.LENGTH_SHORT).show();
            }
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
                            adapter = new ProductTypeAdapter(productTypeModelList, MainActivity.this);
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

    private void fetchDataFromServerDigital(String productType) {
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
                            adapter = new ProductTypeAdapter(productTypeModelList, MainActivity.this);
                            digitalRecyclearView.setAdapter(adapter);
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
    private void fetchDataFromServerCloth(String productType) {
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
                            adapter = new ProductTypeAdapter(productTypeModelList, MainActivity.this);
                            clothRecyclerView.setAdapter(adapter);
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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove callbacks to prevent memory leaks
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
    }
    private void performSearch() {
        String searchQuery = editTextTexts.getText().toString().trim();

        // Check if the search query is not empty
        if (!searchQuery.isEmpty()) {
            // Show a toast with the entered text
            Toast.makeText(MainActivity.this, "Search query: " + searchQuery, Toast.LENGTH_SHORT).show();


            // Pass the search query to SearchResultActivity
            // You can use Intent to pass data between activities
            Intent intent = new Intent(MainActivity.this, SearchResultActivity.class);
            intent.putExtra("searchQuery", searchQuery);
            startActivity(intent);
            // TODO: Add logic for searching or navigating to search results
        } else {
            // If the search query is empty, you can show a different message or handle it as needed
            Toast.makeText(MainActivity.this, "Please enter a search query", Toast.LENGTH_SHORT).show();
        }
    }
}
