package com.mart.cart_activity.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.mart.cart_activity.Adapter.CartAdapter;
import com.mart.cart_activity.Adapter.ProductOrderListAdapter;
import com.mart.cart_activity.Api.ApiService;
import com.mart.cart_activity.ApiModel.SingleProductModel;
import com.mart.cart_activity.DatabaseApi.RetrofitClient;
import com.mart.cart_activity.Entities.UserSignupEntities;
import com.mart.cart_activity.Helper.ManagmentCart;
import com.mart.cart_Activity.R;
import com.mart.cart_activity.Model.UserViewModel;
import com.mart.cart_activity.Repository.UserSignupRepository;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;
import org.w3c.dom.Text;

import pl.droidsonroids.gif.GifImageButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CartActivity extends AppCompatActivity implements CartAdapter.CartAdapterListener, PaymentResultListener {
    UserViewModel userViewModel;
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
    private Button orderButton;
    private Button payment;
    double amTotal;
    TextView address;
    RecyclerView listcycle;
    String email;
    double totalitemamount;
    GoogleSignInOptions gso;

    GoogleSignInClient gsc;
    TextView subtotal;
    private ProductOrderListAdapter adapter;


    private UserSignupRepository userSignupRepository;
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
        orderButton = findViewById(R.id.button2);
        payment = findViewById(R.id.pay);
        address = findViewById(R.id.textView20);
        listcycle = findViewById(R.id.cartViews);
        subtotal = findViewById(R.id.textView13);


        // Define a LiveData object to hold the total amount data


// Inside your onCreate method or any other appropriate place
// Initialize the totalAmountLiveData object

        // Assuming you have a reference to the CartActivity instance named cartActivityInstance
        LiveData<Double> totalAmountLiveData = ProductOrderListAdapter.observeTotalAmount();

        totalAmountLiveData.observe(CartActivity.this, new Observer<Double>() {
            @Override
            public void onChanged(Double totalAmount) {
                // Handle the updated total amount here
                // For example, update UI elements
                TotalAmount.setText("$" + totalAmount);

                // Display the total amount in a toast message
                Toast.makeText(CartActivity.this, "Total Amount: $" + String.format("%.2f", totalAmount), Toast.LENGTH_SHORT).show();
            }
        });

// Update the value of totalAmountLiveData whenever the total amount changes
// For example, if you calculate the total amount elsewhere in your code
// you can update the value of totalAmountLiveData like this:
// totalAmountLiveData.setValue(newTotalAmount);

// Observe changes to totalAmountLiveData inside the onClick listener of the subtotal button
        subtotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inside the onClick listener, observe totalAmountLiveData


            }
        });





// Assuming you already have productList
        List<List<String>> productList = ProductStroageList.getInstance().getProductList();

// Create and set up the adapter
        ProductOrderListAdapter productAdapter = new ProductOrderListAdapter(this, productList);

// Find the RecyclerView in your layout
        RecyclerView listcycle = findViewById(R.id.cartViews);

// Set the layout manager for the RecyclerView (e.g., LinearLayoutManager)
        listcycle.setLayoutManager(new LinearLayoutManager(this));

// Set the adapter for the RecyclerView
        listcycle.setAdapter(productAdapter);


        // Assuming you have a ListView with the id "productListView" in your layout
//        ListView productListView = findViewById(R.id.cartView);
//        productListView.setAdapter(productAdapter);

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


        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                managementCart.removeItem(2);
//                managementCart.removeAllItems();
//                AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
//                LayoutInflater inflater = getLayoutInflater();
//                View dialogView = inflater.inflate(R.layout.payment_method_dialog, null);
//                builder.setView(dialogView);
//                RadioGroup radioGroup = dialogView.findViewById(R.id.paymentMethodRadioGroup);
//                Button confirmButton = dialogView.findViewById(R.id.confirmButton);
//                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
//
//                RadioButton selectedRadioButton = dialogView.findViewById(selectedRadioButtonId);
//                String selectedPaymentMethod = selectedRadioButton.getText().toString();
//                textView22.setText(selectedPaymentMethod);
//
//                // Check if the selected payment method is "Online"
//                if (selectedPaymentMethod.equals("Online")) {
//                    // Call makePayment() function only when "Online" option is selected
//                    makepayment();
//                }
                try {
                   makepayment();
                } catch (Exception e) {
                    // Handle the exception here
                    Log.e("Error","Makepayment()");

                }


//                showGifDialog();

//// Example usage to get the wishlist items
//                Set<String> wishlistItems = getWishlistFromSharedPreferences();
//
//// Iterate through the set and do something with each wishlist item
//                for (String wishlistItem : wishlistItems) {
//                    // Perform actions with each wishlist item (e.g., display in UI, log, etc.)
//                    Log.d("Wishlist::", wishlistItem);
//                    Toast.makeText(CartActivity.this,"DATA:"+wishlistItem,Toast.LENGTH_SHORT).show();
//                }
////                OrderTask orderTask = new OrderTask("2", email, "fsldke3s", "skldfjied", "23", "348743", CartActivity.this);
////                orderTask.execute();
//
//                List<List<String>> productList = ProductStroageList.getInstance().getProductList();
//                StringBuilder toastMessage = new StringBuilder("Product List:\n");
//
//                for (List<String> productPair : productList) {
//                    // Assuming each product pair has two elements (product ID and seller SKU)
//                    if (productPair.size() == 2) {
//                        toastMessage.append("Product ID: ").append(productPair.get(0))
//                                .append(", Seller SKU: ").append(productPair.get(1))
//                                .append("\n");
//
//                        // Pass product ID and seller SKU as strings to the OrderTask
//                        OrderTask orderTask = new OrderTask("2", email, productPair.get(0), productPair.get(1), "23", "348743", CartActivity.this);
//                        orderTask.execute();
//                    }
//                }
//
//// Display the product list using Toast
//                Toast.makeText(CartActivity.this, toastMessage.toString(), Toast.LENGTH_SHORT).show();
                GlobalCartData globalCartData = GlobalCartData.getInstance();
                List<ProductInfo> productInfoList = globalCartData.getProductInfoList();

                StringBuilder toastMessage = new StringBuilder("Product Data:\n");

                for (ProductInfo productInfo : productInfoList) {
                    toastMessage.append("Product ID: ").append(productInfo.getProductId())
                            .append("\nSeller SKU: ").append(productInfo.getSellerSku())
                            .append("\nQuantity: ").append(productInfo.getQuantity())
                            .append("\nPrice: ").append(productInfo.getPrice())
                            .append("\n\n");
//                    TotalTaxTotal.setText(String.valueOf(productInfo.getPrice()));
//                    totalitemamount += productInfo.getPrice();
                    OrderTask orderTask = new OrderTask("2", email, productInfo.getProductId(), productInfo.getSellerSku(),
                            String.valueOf(productInfo.getQuantity()), String.valueOf(productInfo.getPrice()), CartActivity.this);
                    orderTask.execute();
                }

                Toast.makeText(getApplicationContext(), toastMessage.toString(), Toast.LENGTH_LONG).show();

            }
        });
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        int userId = 1;

        userViewModel.getUserById(userId).observe(this, new Observer<UserSignupEntities>() {
            @Override
            public void onChanged(UserSignupEntities user) {
                if (user != null) {
                    // Populate the UI fields with the user data
                    address.setText(user.getAddress());
                    email = user.getEmail();
//                    txtaddress.setText(user.getAddress());
//                    txtemail.setText(user.getEmail());
//                    txtnumber.setText(user.getNumber());
//                    UserName.setText(user.getUsername());
//                    String userNickname = user.getNikname();
//                    txtnikname.setText(userNickname != null ? "@" + userNickname : "@");

                }
            }
        });

        setVariables();
        initList();
        setupRecyclerView();
//        calculatorCart();
//        calculateAndDisplayCart();
//        onCartUpdated(0);
    }

    private void setVariables() {
        managementCart = new ManagmentCart(this);
    }

    private void initList() {
        // You can handle the initialization logic here if needed
    }


    private void setupRecyclerView() {
        RecyclerView cartViews = findViewById(R.id.cartViews);

        // Assuming productList is your list of products
        List<List<String>> productList = ProductStroageList.getInstance().getProductList();

        // Create and set up the adapter
        ProductOrderListAdapter productAdapter = new ProductOrderListAdapter(this, productList);

        // Assuming you have a ListView with the id "productListView" in your layout
//        ListView productListView = findViewById(R.id.cartView);
//        productListView.setAdapter(productAdapter);

//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        cartView.setLayoutManager(layoutManager);
//
//        cartAdapter = new CartAdapter(managementCart.getListCart(), this, managementCart, this);
//
//        cartView.setAdapter(cartAdapter);
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

    private void calculateAndDisplayCart() {
        final double[] totalAmount = {0};  // Using an array to make it effectively final

        GlobalCartData globalCartData = GlobalCartData.getInstance();
        List<ProductInfo> productInfoList = globalCartData.getProductInfoList();

        StringBuilder toastMessage = new StringBuilder("Product Data:\n");

        // Make a single Retrofit call for all products
        Retrofit retrofit = RetrofitClient.getClient();
        ApiService apiService = retrofit.create(ApiService.class);


        for (ProductInfo productInfo : productInfoList) {
            toastMessage.append("Product ID: ").append(productInfo.getProductId())
                    .append("\nSeller SKU: ").append(productInfo.getSellerSku())
                    .append("\nQuantity: ").append(productInfo.getQuantity())
                    .append("\nPrice: ").append(productInfo.getPrice())
                    .append("\n\n");

            // Make the Retrofit call
            Call<List<SingleProductModel>> call = apiService.getData(productInfo.getProductId(), productInfo.getSellerSku());
            call.enqueue(new Callback<List<SingleProductModel>>() {
                @Override
                public void onResponse(Call<List<SingleProductModel>> call, Response<List<SingleProductModel>> response) {
                    if (response.isSuccessful()) {
                        List<SingleProductModel> data = response.body();
                        // Process the data as needed
                        if (!data.isEmpty()) {
                            totalAmount[0] += Double.parseDouble(data.get(0).getYour_Price());
                            // Perform any additional processing with yourPrice if necessary
                        }
                    } else {
                        // Handle error
                        Toast.makeText(CartActivity.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<SingleProductModel>> call, Throwable t) {

                }
            });
        }

        double percentTax = 0.02;
        double delivery = 10;

        // Calculate tax and total
        double tax = Math.round(totalitemamount * percentTax * 100) / 100;
        double total = Math.round((totalitemamount + tax + delivery) * 100) / 100;

        // Update UI elements
        TotalTaxTotal.setText("$" + totalitemamount);
        DeliveryTax.setText("$" + delivery);
        TotalTax.setText("$" + tax);
        TotalAmount.setText("$" + total);

        amTotal = total;
        // Remove unnecessary comments and notify calls
    }



    @Override
    public void onQuantityChanged(int position, int quantity) {
        // Update the quantity in the ManagmentCart
        managementCart.updateQuantity(position, quantity);

        // Recalculate totals
//        calculatorCart();
    }

    @Override
    public void onBackPressed() {
        // Customize the behavior when the back button is pressed
        // For example, you can navigate to another activity, show a dialog, etc.
        // Add your custom code here
        super.onBackPressed();
        // If you want to perform the default back button behavior, remove this line
    }

    private void makepayment()
    {

        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_live_dntcr3OmvhF2vG");

        checkout.setImage(R.drawable.logo);
        final Activity activity = this;

        try {
            JSONObject options = new JSONObject();

            options.put("name", "Njoymart");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", "30000");//300 X 100
            options.put("prefill.email", "test@gmail.com");
            options.put("prefill.contact","8780719280");
            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }


    public void onPaymentSuccess(String s)
    {
        Toast.makeText(CartActivity.this,"Successful payment ID:" +s,Toast.LENGTH_SHORT).show();
//        paytext.setText("Successful payment ID :"+s);
    }


    public void onPaymentError(int i, String s) {
        Toast.makeText(CartActivity.this,"Failed and cause is:"+s,Toast.LENGTH_SHORT).show();

//        paytext.setText("Failed and cause is :"+s);
    }

    private Set<String> getWishlistFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("WishlistPrefs", MODE_PRIVATE);
        return sharedPreferences.getStringSet("WISHLIST_SET", new HashSet<>());
    }
    public void onOrderComplete(String result) {
        if (result != null && result.startsWith("Error:")) {
            // Show Toast if there's an error
            Toast.makeText(CartActivity.this, result, Toast.LENGTH_SHORT).show();

        } else {
            // Proceed with startActivity(intent) if status code is 200
            Intent intent = new Intent(CartActivity.this, CartActivity.class);
            startActivity(intent);
        }
    }

    public void onCartUpdated(int totalAmountData) {
        // Update your TextView in CartActivity
        TextView totalAmountTextView = findViewById(R.id.totalFeeTxt);
        totalAmountTextView.setText(String.valueOf(totalAmountData));

    }




}
