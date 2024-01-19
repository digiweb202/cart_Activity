package com.mart.cart_activity.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mart.cart_Activity.R;
import com.mart.cart_activity.Helper.ManagmentCart;
import com.mart.cart_activity.domain.PopularDomain;

public class DetailActivity extends AppCompatActivity {

    private PopularDomain object;
    private int numberOrder =1 ;
    private ManagmentCart managmentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getBundles();
        setupUI();
        managmentCart= new ManagmentCart(this);

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
                    object.setNumberInChart(numberOrder);
                    managmentCart.insertFood(object);
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
