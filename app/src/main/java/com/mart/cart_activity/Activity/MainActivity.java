package com.mart.cart_activity.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView popularRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Set the content view using the layout resource ID

        LinearLayout cartBtn = findViewById(R.id.cartBtn);
        LinearLayout wishlist = findViewById(R.id.wishlistBtn);
        LinearLayout explorerBtn = findViewById(R.id.explorerBtn);
        LinearLayout profileBtn = findViewById(R.id.profilesBtn);

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

        initRecyclerView();
    }

    private void initRecyclerView() {
        ArrayList<PopularDomain> items = new ArrayList<>();
        items.add(new PopularDomain("T-Shirt", "item_1", 3, 4, 25, "Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience."));
        items.add(new PopularDomain("watch", "item_2", 3, 4, 50, "Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience.Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience."));
        items.add(new PopularDomain("Mobile", "item_3", 3, 4, 100, "Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience."));
        items.add(new PopularDomain("TV", "item_4", 3, 4, 200, "Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience."));
        items.add(new PopularDomain("Watch", "item_2", 3, 4, 300, "Certainly! However, I'll need more information about the product you'd like a short description for. Please provide some details about the product, its features, and its intended audience."));



        // Find the RecyclerView by ID
        popularRecyclerView = findViewById(R.id.PopularView);

        // Set up the LinearLayoutManager and adapter
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        popularRecyclerView.setLayoutManager(layoutManager);

        PopularAdapter adapter = new PopularAdapter(items);
        popularRecyclerView.setAdapter(adapter);
    }


//    private void showQRCodePopup(Context context) {
//        // Inflate the custom layout for the QR code popup
//        View dialogView = LayoutInflater.from(context).inflate(R.layout.qr_code_popup, null);
//        ImageView qrCodeImageView = dialogView.findViewById(R.id.qrCodeImageView);
//
//        // Set the QR code image to qrCodeImageView (you need to generate your QR code image here)
//        Bitmap qrCodeBitmap = generateQRCode(); // Implement your QR code generation logic
//        qrCodeImageView.setImageBitmap(qrCodeBitmap);
//
//        // Build the alert dialog
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setView(dialogView)
//                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//
//        // Show the alert dialog
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }
//
//    // Implement your QR code generation logic here
//    private Bitmap generateQRCode() {
//        // Example: This is a placeholder. Replace it with your actual QR code generation logic.
//        // For example, you can use a library like ZXing to generate QR codes.
//        return ((BitmapDrawable) getResources().getDrawable(R.drawable.qrcode)).getBitmap();
//    }
}
