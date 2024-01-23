package com.mart.cart_activity.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mart.cart_activity.Adapter.CartAdapter;
import com.mart.cart_activity.Helper.ManagmentCart;
import com.mart.cart_Activity.R;

public class CartActivity extends AppCompatActivity {

    private ManagmentCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        setVariables();
        initList();
    }

    private void setVariables() {
//        managementCart = new ManagmentCart(); // Initialize your ManagementCart class here
    }

    private void initList() {
//        if (managementCart.getListCart().isEmpty()) {
//            showEmptyCart();
//        } else {
//            showNonEmptyCart();
//            setupRecyclerView();
//        }
    }

    private void showEmptyCart() {
//        findViewById(R.id.emptyTxt).setVisibility(View.VISIBLE);
//        findViewById(R.id.scroll).setVisibility(View.GONE);
    }

    private void showNonEmptyCart() {
//        findViewById(R.id.emptyTxt).setVisibility(View.GONE);
//        findViewById(R.id.scroll).setVisibility(View.VISIBLE);
    }

    private void setupRecyclerView() {
        RecyclerView cartView = findViewById(R.id.cartView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        cartView.setLayoutManager(layoutManager);

        // Set up your RecyclerView adapter and pass the data from managementCart.getListCart()
        CartAdapter cartAdapter = new CartAdapter(managementCart.getListCart());
        cartView.setAdapter(cartAdapter);
    }
}
