package com.mart.cart_activity.Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mart.cart_activity.Adapter.CartAdapter;
import com.mart.cart_activity.Helper.ManagmentCart;
import com.mart.cart_Activity.R;

import pl.droidsonroids.gif.GifImageButton;

public class CartActivity extends AppCompatActivity {
    private ImageView Backbtn;
    private ImageView ProfileEdit;
    private  ImageView Method_Payment;
    private ManagmentCart managementCart;
    private TextView textView22;
    private  ImageView openDialogButton;
    private TextView Payment;
    private TextView textview22;
    private TextView TotalTaxTotal;

    private TextView DeliveryTax;

    private TextView TotalTax;
    private TextView TotalAmount;
    double tax;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ProfileEdit = findViewById(R.id.editProfile);
        Method_Payment = findViewById(R.id.payment_method);
        Backbtn = findViewById(R.id.mycartBtn);
        textView22 = findViewById(R.id.payment);
        Payment = findViewById(R.id.payment);
        TotalTaxTotal = findViewById(R.id.totalFeeTxt);
        DeliveryTax = findViewById(R.id.deliveryTxt);
        TotalTax = findViewById(R.id.taxTxt);
        TotalAmount= findViewById(R.id.totalTxt);



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
        Payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPaymentMethodDialog();
                Toast.makeText(CartActivity.this,"Payment Option",Toast.LENGTH_SHORT).show();
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
        Button orderButton = findViewById(R.id.button2);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGifDialog();
            }
        });
        setVariables();
        initList();
        setupRecyclerView();
        calculatorCart();
    }
    private void setVariables() {
        managementCart = new ManagmentCart(this); // Assuming ManagmentCart constructor takes a Context parameter
    }

    @SuppressLint("ResourceType")
    private void showGifDialog() {
        // Create a custom dialog
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_layout_order);
        GifImageButton gifImageView = new GifImageButton(this);
        gifImageView.setImageResource(R.drawable.check);
        // Set up the ImageView in the dialog
//        ImageView gifImageView = dialog.findViewById(R.id.gif);
        // You can load your GIF into the ImageView using a library like Glide or directly from resources

        // Set up any other views or functionality as needed

        // Show the dialog
        dialog.show();

        // Set up a Handler to dismiss the dialog after 2 seconds
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        }, 3000); // 2000
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
//        CartAdapter cartAdapter = new CartAdapter(managementCart.getListCart());
        CartAdapter cartAdapter = new CartAdapter(managementCart.getListCart(), this);

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

    private void calculatorCart() {
        double percentTax = 0.02;
        double delivery = 10;

        // Calculate the item total based on the updated quantities
        double itemTotal = Math.round(managementCart.getTotalFee() * 100) / 100;

        // Calculate the new tax based on the updated item total
        tax = Math.round(itemTotal * percentTax * 100) / 100;

        // Calculate the new total amount
        double total = Math.round((itemTotal + tax + delivery) * 100) / 100;

        // Update the TextViews with the new values
        TotalTaxTotal.setText("$" + itemTotal);
        DeliveryTax.setText("$" + delivery);
        TotalTax.setText("$" + tax);
        TotalAmount.setText("$" + total);
    }

    @Override
    public void onBackPressed() {
        // Customize the behavior when the back button is pressed
        // For example, you can navigate to another activity, show a dialog, etc.

        // Add your custom code here
        super.onBackPressed(); // If you want to perform the default back button behavior, remove this line
    }
}
