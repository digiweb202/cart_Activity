package com.mart.cart_activity.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mart.cart_activity.Adapter.CartAdapter;
import com.mart.cart_activity.Helper.ManagmentCart;
import com.mart.cart_Activity.R;

public class CartActivity extends AppCompatActivity {
    private ImageView Backbtn;
    private ImageView ProfileEdit;
    private  ImageView Method_Payment;
    private ManagmentCart managementCart;
    private TextView textView22;
    private  ImageView openDialogButton;
    private TextView textview22;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ProfileEdit = findViewById(R.id.editProfile);
        Method_Payment = findViewById(R.id.payment_method);
        Backbtn = findViewById(R.id.mycartBtn);
        textView22 = findViewById(R.id.textView22);


        // Assuming you have a button to open the payment method dialog
        openDialogButton = findViewById(R.id.payment_method);

        openDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show the payment method dialog
                showPaymentMethodDialog();
                Log.e("CartActivity_Payment_Method","Button is working//:");
            }
        });
        Method_Payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ProfileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this,EditProfileActivity.class);
                startActivity(intent);
            }
        });
        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
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

    private void showPaymentMethodDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.payment_method_dialog, null);
        builder.setView(dialogView);

        RadioGroup radioGroup = dialogView.findViewById(R.id.paymentMethodRadioGroup);
        Button confirmButton = dialogView.findViewById(R.id.confirmButton);

        final AlertDialog dialog = builder.create();

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

                if (selectedRadioButtonId != -1) {
                    RadioButton selectedRadioButton = dialogView.findViewById(selectedRadioButtonId);

                    // Get the text from the selected radio button and set it to textView22
                    String selectedPaymentMethod = selectedRadioButton.getText().toString();
                    textView22.setText(selectedPaymentMethod);

                    // Dismiss the dialog or perform any other actions
                    dialog.dismiss();
                } else {
                    // Handle the case where no radio button is selected
                    // You may show an error message or take appropriate action
                }
            }
        });

        dialog.show();
    }
    @Override
    public void onBackPressed() {
        // Customize the behavior when the back button is pressed
        // For example, you can navigate to another activity, show a dialog, etc.

        // Add your custom code here
        super.onBackPressed(); // If you want to perform the default back button behavior, remove this line
    }
}
