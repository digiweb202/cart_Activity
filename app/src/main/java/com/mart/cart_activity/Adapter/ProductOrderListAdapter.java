package com.mart.cart_activity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mart.cart_Activity.R;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mart.cart_Activity.R;
import com.mart.cart_activity.Activity.CartActivity;
import com.mart.cart_activity.Activity.DetailActivity;
import com.mart.cart_activity.Activity.GlobalCartData;
import com.mart.cart_activity.Activity.ProductInfo;
import com.mart.cart_activity.Api.ApiService;
import com.mart.cart_activity.ApiModel.SingleProductModel;
import com.mart.cart_activity.DatabaseApi.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductOrderListAdapter extends RecyclerView.Adapter<ProductOrderListAdapter.ViewHolder> {

        private final Context context;
        private final List<List<String>> productList;
        public int totalAmountData;
        String price;
        String quantitys;
      // Make sure to initialize this
        // Create an instance of the ApiService interface

        Retrofit retrofit = RetrofitClient.getClient();
        ApiService apiService = retrofit.create(ApiService.class);
        public ProductOrderListAdapter(Context context, List<List<String>> productList) {
                this.context = context;
                this.productList = productList;
//                this.apiService = apiService;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(context).inflate(R.layout.viewholder_cart, parent, false);
                return new ViewHolder(view);
        }
//        private void updateItemQuantity(int position, int quantity) {
//                if (listener != null) {
//                        listener.onQuantityChanged(position, quantity);
//                }
//        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                List<String> productPair = productList.get(position);
                int quantity;
                if (productPair != null && productPair.size() == 2) {
                        String productId = productPair.get(0);
                        String sellerSku = productPair.get(1);

                        holder.productIdTextView.setText("Product ID: " + productId);
                        fetchDataForProduct(holder, productId, sellerSku);

                        // Use GlobalCartData to manage product information
                        int parsedQuantity = Integer.parseInt(quantitys != null ? quantitys : "1");
                        int parsedPrice = Integer.parseInt(price != null ? price : "0");

                        List<ProductInfo> productInfoList = GlobalCartData.getInstance().getProductInfoList();
                        boolean productExists = false;

                        for (ProductInfo productInfo : productInfoList) {
                                if (productInfo.getProductId().equals(productId) && productInfo.getSellerSku().equals(sellerSku)) {
                                        // Product already exists in the list, update the quantity
                                        productInfo.setQuantity(productInfo.getQuantity() + parsedQuantity);
                                        productExists = true;
                                        break;
                                }
                        }

                        if (!productExists) {
                                // Product doesn't exist in the list, add a new ProductInfo object
                                ProductInfo newProductInfo = new ProductInfo(productId, sellerSku, parsedQuantity, parsedPrice);
                                productInfoList.add(newProductInfo);
                        }
                }
                int pricedata;
                holder.plusCartBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                try {
                                int quantity = Integer.parseInt(holder.numberItemTxt.getText().toString());
                                updateItemQuantity(holder, quantity + 1);

                                // Parse price as double and calculate the total
                                double totalPrice = Double.parseDouble(price) * quantity;

                                // Format the total price as a String
                                String totalPriceString = String.format("%.2f", totalPrice);

                                // Set the formatted total price to the pricetxt TextView
//                                holder.pricetxt.setText(totalPriceString);

                                totalAmountData += Double.parseDouble(totalPriceString);

                                if (cartUpdateListener != null) {
                                        cartUpdateListener.onCartUpdated(totalAmountData);
                                }
//                                notifyDataSetChanged();

                                price = String.valueOf(totalAmountData);
                                quantitys = String.valueOf(quantity);
                                } catch (NumberFormatException e) {
                                        // Handle the case where the text is not a valid integer
                                        // You might want to set a default value or show an error message
                                        e.printStackTrace(); // Log the exception for debugging purposes
                                }
                        }
                });

                holder.minusCartBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                try {
                                int quantity = Integer.parseInt(holder.numberItemTxt.getText().toString());

                                // Ensure the quantity remains a minimum of 1
                                if (quantity > 1) {
                                        updateItemQuantity(holder, quantity - 1);

                                        // Parse price as double and calculate the total
                                        double totalPrice = Double.parseDouble(price) * quantity;

                                        // Format the total price as a String
                                        String totalPriceString = String.format("%.2f", totalPrice);

                                        // Set the formatted total price to the pricetxt TextView
//                                        holder.pricetxt.setText(totalPriceString);
//                                        notifyDataSetChanged();

                                        totalAmountData += Integer.parseInt(totalPriceString);
                                        if (cartUpdateListener != null) {
                                                cartUpdateListener.onCartUpdated(totalAmountData);
                                        }
                                }
                                price = String.valueOf(totalAmountData);
                                quantitys = String.valueOf(quantity);
                                } catch (NumberFormatException e) {
                                        // Handle the case where the text is not a valid integer
                                        // You might want to set a default value or show an error message
                                        e.printStackTrace(); // Log the exception for debugging purposes
                                }
                        }
                });
//                Intent intent = new Intent(context, CartActivity.class);
//                intent.putExtra("totalAmountData", totalAmountData);
//                context.startActivity(intent);
//                notifyDataSetChanged();

        }

        private void fetchDataForProduct(ViewHolder holder, String productID, String sellerSKU) {
                Call<List<SingleProductModel>> call = apiService.getData(productID, sellerSKU);
                call.enqueue(new Callback<List<SingleProductModel>>() {
                        @Override
                        public void onResponse(Call<List<SingleProductModel>> call, Response<List<SingleProductModel>> response) {
                                if (response.isSuccessful()) {
                                        List<SingleProductModel> data = response.body();
                                        if (data != null && !data.isEmpty()) {
                                                // Process the data and update the views in the ViewHolder
                                                updateViews(holder, data.get(0));
                                        }
                                }
                        }

                        @Override
                        public void onFailure(Call<List<SingleProductModel>> call, Throwable t) {
                                // Handle failure if needed
                        }
                });
        }

        private void updateViews(ViewHolder holder, SingleProductModel productModel) {
                // Update your views with the fetched data
                String itemimagename = productModel.getMain_Image_URL();
                Picasso.get().load(itemimagename).into(holder.itemimage);
                String itemName = productModel.getItem_Name();
                holder.titleName.setText(itemName);
                holder.titleName.setOnClickListener(v -> {
                        Toast.makeText(context, itemName, Toast.LENGTH_SHORT).show();
                });
//                holder.descriptiontxt.setText(productModel.getProduct_Description());
                holder.pricetxt.setText(productModel.getYour_Price());
                holder.sellerSkuTextView.setText(productModel.getYour_Price());
                price = productModel.getYour_Price();
        }


        public static class ViewHolder extends RecyclerView.ViewHolder {
                TextView productIdTextView;
                TextView sellerSkuTextView;
                ImageView itemimage;
                TextView titleName;
                TextView descriptiontxt;
                TextView pricetxt;
                TextView plusCartBtn;
                TextView minusCartBtn;

                TextView numberItemTxt;

                public ViewHolder(@NonNull View itemView) {
                        super(itemView);
                        productIdTextView = itemView.findViewById(R.id.titleTxt);
                        sellerSkuTextView = itemView.findViewById(R.id.feeEachItem);
                        itemimage = itemView.findViewById(R.id.pic);
                        titleName = itemView.findViewById(R.id.titleTxt);
//                        descriptiontxt = itemView.findViewById(R.id.descriptiontxt);
                        pricetxt = itemView.findViewById(R.id.totalEachItem);
                        plusCartBtn = itemView.findViewById(R.id.plusCartBtn);
                        minusCartBtn = itemView.findViewById(R.id.minusCartBtn);
                        numberItemTxt = itemView.findViewById(R.id.numberItemTxt);
                }
        }
        private void updateItemQuantity(ViewHolder holder, int quantity) {
                holder.numberItemTxt.setText(String.valueOf(quantity));

                // Update the global cart data
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                        ProductInfo productInfo = GlobalCartData.getInstance().getProductInfoList().get(adapterPosition);
                        productInfo.setQuantity(quantity);
                }
        }
        @Override
        public int getItemCount() {
                return productList.size();
        }
        public interface CartUpdateListener {
                void onCartUpdated(int totalAmountData);
        }
        private CartUpdateListener cartUpdateListener;

        public void setCartUpdateListener(CartUpdateListener listener) {
                this.cartUpdateListener = listener;
        }



}
