package com.mart.cart_activity.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mart.cart_Activity.R;
import com.mart.cart_activity.ApiModel.GetProductModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    private List<GetProductModel> dataList;
    private LayoutInflater inflater;

    public ProductAdapter(Context context, List<GetProductModel> dataList) {
        this.inflater = LayoutInflater.from(context);
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.explorer_viewholder_pup_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetProductModel data = dataList.get(position);
        // Bind data to ViewHolder views
        holder.txtProductName.setText(data.getProductName());
        holder.txtPrice.setText(data.getYourPrice());

        Picasso.get().load(data.getMainImageurl()).into(holder.imgProduct);

        // Bind more fields as needed
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtProductName;
        TextView txtPrice;
        ImageView imgProduct;  // Change the type to ImageView

        @SuppressLint("WrongViewCast")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtProductName = itemView.findViewById(R.id.titleText);
            txtPrice = itemView.findViewById(R.id.feeTxt);
            imgProduct = itemView.findViewById(R.id.pic);  // Change the ID and type
        }
    }
}
