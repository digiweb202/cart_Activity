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
import com.mart.cart_activity.ApiModel.ProductListImgModel;
import com.mart.cart_activity.ApiModel.ProductTypeModel;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class ProductTypeAdapter extends RecyclerView.Adapter<ProductTypeAdapter.ViewHolder> {

    private List<ProductTypeModel> productList;
    private Context context;

    public ProductTypeAdapter(List<ProductTypeModel> productList, Context context) {
        this.productList = productList;
        this.context = context;
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
        holder.bind(productTypeModel);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView productNameTextView;  // Change this to the actual views in your item layout
        ImageView imageView;
        private TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize your views here
            productNameTextView = itemView.findViewById(R.id.titleText);
            imageView  = itemView.findViewById(R.id.pic);// Change to your actual view ID
            price = itemView.findViewById(R.id.feeTxt);
            // Set OnClickListener on the item view
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

        public void bind(ProductTypeModel productTypeModel) {
            // Bind data to your views here
            productNameTextView.setText(productTypeModel.watches.getProduct_ID());
            // Check if watches_img list is not empty before accessing elements
            if (!productTypeModel.watches_img.isEmpty()) {
                ProductListImgModel firstWatchesImg = productTypeModel.watches_img.get(0);
                String mainImageURL = firstWatchesImg.getMain_Image_URL();

                // Load the image into the ImageView using Picasso
                Picasso.get().load(mainImageURL).into(imageView);
                price.setText(productTypeModel.watches.getYour_Price());
            } else {
                Toast.makeText(context, "Preview not avaliable", Toast.LENGTH_SHORT).show();
            }
        }
    }
}