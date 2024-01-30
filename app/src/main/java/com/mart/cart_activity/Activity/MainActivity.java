package com.mart.cart_activity.Activity;

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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mart.cart_Activity.R;
import com.mart.cart_activity.Adapter.PopularAdapter;
import com.mart.cart_activity.domain.PopularDomain;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import android.widget.Toast;
import androidx.annotation.NonNull;
public class MainActivity extends AppCompatActivity {
    private RecyclerView popularRecyclerView;
    private RecyclerView digitalRecyclearView;
    private RecyclerView clothRecyclerView;
    private static final int PERMISSION_REQUEST_INTERNET = 1;
    private LinearLayout categoreisAllbtn;
    private ImageView notificationBtn;
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
                Intent intent = new Intent(MainActivity.this,ExplorerActivity.class);
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

        initRecyclerView();
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

}
