package com.mart.cart_activity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mart.cart_Activity.R;
import com.mart.cart_activity.Activity.DetailActivity;
import com.mart.cart_activity.ApiModel.GetProductModel;
import com.mart.cart_activity.ApiModel.ProductListImgModel;
import com.mart.cart_activity.ApiModel.ProductListmodel;
import com.mart.cart_activity.ApiModel.ProductTypeModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {
    private static List<ProductTypeModel> productList;

private List<ProductTypeModel> originalWatchesList; // Store the original unfiltered data
private static Context context;
private LoadMoreListener loadMoreListener;

public SearchResultAdapter(List<ProductTypeModel> watchesList, Context context, LoadMoreListener loadMoreListener) {
        this.productList = watchesList != null ? watchesList : new ArrayList<>();
        this.originalWatchesList = new ArrayList<>(this.productList);
        this.context = context;
        this.loadMoreListener = loadMoreListener;
        }

@NonNull
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_pup_list, parent, false);
        return new ViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductTypeModel productTypeModel = productList.get(position);
        ProductListmodel watch = productTypeModel.watches;
        List<ProductListImgModel> watchesImgList = productTypeModel.watches_img;

        // Bind watch data to the ViewHolder
        holder.itemNameTextView.setText(watch.item_Name);
        holder.priceTextView.setText("$" + watch.your_Price);

        // Load the main image using Picasso
        if (!watchesImgList.isEmpty()) {
        String mainImageUrl = watchesImgList.get(0).main_Image_URL;
        Picasso.get().load(mainImageUrl).into(holder.watchImageView);
        }

        // Check if we need to load more data
        if (position == productList.size() - 1 && loadMoreListener != null) {
        loadMoreListener.onLoadMore();
        }
        }

//@Override
//        public int getItemCount() {
//        return watchesList != null ? watchesList.size() : 0;
//        }


    public void onBindViewHolder(@NonNull ProductTypeAdapter.ViewHolder holder, int position) {
        ProductTypeModel productTypeModel = productList.get(position);
        holder.bind(productTypeModel);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
    ImageView watchImageView;
    TextView itemNameTextView, priceTextView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        watchImageView = itemView.findViewById(R.id.pic);
        itemNameTextView = itemView.findViewById(R.id.titleText);
        priceTextView = itemView.findViewById(R.id.feeTxt);
        // Add click listener to itemView
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the clicked product
                ProductTypeModel clickedProduct = productList.get(getAdapterPosition());

                // Show details in a toast
                String details = "Product ID: " + clickedProduct.watches.getProduct_ID() +
                        "\nPrice: " + clickedProduct.watches.getYour_Price();
                Toast.makeText(context, details, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);

                // Pass necessary data to the details_product activity using Intent extras
                intent.putExtra("PRODUCT_ID", clickedProduct.watches.getProduct_ID());
                intent.putExtra("SELLER_SKU", clickedProduct.watches.getSeller_SKU());

                // Start the details_product activity
                itemView.getContext().startActivity(intent);
                // Show Toast
            }
        });
    }
}

    // Add this method to filter the data and update the adapter
    public void filterList(List<ProductTypeModel> filteredList) {
        productList = filteredList;
        notifyDataSetChanged();
    }

// Interface to handle load more events
public interface LoadMoreListener {
    void onLoadMore();
}
}