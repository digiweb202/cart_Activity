package com.mart.cart_activity.Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

import org.w3c.dom.Text;

import pl.droidsonroids.gif.GifImageButton;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity implements CartAdapter.CartAdapterListener {

    private ImageView Backbtn;
    private ImageView ProfileEdit;
    private ImageView Method_Payment;
    private ManagmentCart managementCart;
    private TextView textView22;
    private ImageView openDialogButton;
    private TextView Payment;
    private TextView textview22;
    private TextView TotalTaxTotal;

    private TextView DeliveryTax;

    private TextView TotalTax;
    private TextView TotalAmount;
    private double tax;
    private CartAdapter cartAdapter;
    private TextView TextViewbutton;

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
        TotalAmount = findViewById(R.id.totalTxt);
//        TextViewbutton = findViewById(R.id.mycart);

        openDialogButton = findViewById(R.id.payment_method);

//        TextViewbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                managementCart.removeItem(2);
//            }
//        });
        openDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPaymentMethodDialog();
            }
        });

        Payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPaymentMethodDialog();
                Toast.makeText(CartActivity.this, "Payment Option", Toast.LENGTH_SHORT).show();
            }
        });

        Method_Payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Method_Payment click
            }
        });

        ProfileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });

        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button orderButton = findViewById(R.id.button2);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                managementCart.removeItem(2);
                managementCart.removeAllItems();
                showGifDialog();
            }
        });

        setVariables();
        initList();
        setupRecyclerView();
        calculatorCart();
    }

    private void setVariables() {
        managementCart = new ManagmentCart(this);
    }

    private void initList() {
        // You can handle the initialization logic here if needed
    }


    private void setupRecyclerView() {
        RecyclerView cartView = findViewById(R.id.cartView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        cartView.setLayoutManager(layoutManager);

        cartAdapter = new CartAdapter(managementCart.getListCart(), this, managementCart, this);

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

    private void showGifDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_layout_order);
        GifImageButton gifImageView = new GifImageButton(this);
        gifImageView.setImageResource(R.drawable.check);
        dialog.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        }, 3000);
    }

    private void calculatorCart() {
        double percentTax = 0.02;
        double delivery = 10;

        double itemTotal = Math.round(managementCart.getTotalFee() * 100) / 100;
        tax = Math.round(itemTotal * percentTax * 100) / 100;
        double total = Math.round((itemTotal + tax + delivery) * 100) / 100;

        TotalTaxTotal.setText("$" + itemTotal);
        DeliveryTax.setText("$" + delivery);
        TotalTax.setText("$" + tax);
        TotalAmount.setText("$" + total);
    }

    @Override
    public void onQuantityChanged(int position, int quantity) {
        // Update the quantity in the ManagmentCart
        managementCart.updateQuantity(position, quantity);

        // Recalculate totals
        calculatorCart();
    }

    @Override
    public void onBackPressed() {
        // Customize the behavior when the back button is pressed
        // For example, you can navigate to another activity, show a dialog, etc.
        // Add your custom code here
        super.onBackPressed();
        // If you want to perform the default back button behavior, remove this line
    }

}
