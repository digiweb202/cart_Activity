package com.mart.cart_activity.Adapter;

import android.content.Context;
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
import com.mart.cart_activity.Activity.DetailActivity;
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

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                List<String> productPair = productList.get(position);

                if (productPair != null && productPair.size() == 2) {
                        holder.productIdTextView.setText("Product ID: " + productPair.get(0));
                        holder.sellerSkuTextView.setText("Seller SKU: " + productPair.get(1));

                        // Fetch data for the specific product using Retrofit
                        fetchDataForProduct(holder, productPair.get(0), productPair.get(1));
                }
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
        }

        @Override
        public int getItemCount() {
                return productList.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
                TextView productIdTextView;
                TextView sellerSkuTextView;
                ImageView itemimage;
                TextView titleName;
                TextView descriptiontxt;
                TextView pricetxt;

                public ViewHolder(@NonNull View itemView) {
                        super(itemView);
                        productIdTextView = itemView.findViewById(R.id.titleTxt);
                        sellerSkuTextView = itemView.findViewById(R.id.feeEachItem);
                        itemimage = itemView.findViewById(R.id.pic);
                        titleName = itemView.findViewById(R.id.titleTxt);
//                        descriptiontxt = itemView.findViewById(R.id.descriptiontxt);
                        pricetxt = itemView.findViewById(R.id.totalEachItem);
                }
        }
}
