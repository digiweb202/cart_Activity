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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.mart.cart_Activity.R;
import com.mart.cart_activity.Activity.DetailActivity;
import com.mart.cart_activity.domain.PopularDomain;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private final ArrayList<PopularDomain> items;
    private Context context = null;

    public CartAdapter(ArrayList<PopularDomain> items, Context context) {
        this.items = items;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the view using LayoutInflater
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Bind data to the views inside the ViewHolder
        holder.titleTxt.setText(items.get(position).getTitle());
        holder.feeTxt.setText("$" + items.get(position).getPrice());
//        holder.scoreTxt.setText(String.valueOf(items.get(position).getPrice()));
//        holder.scoreTxt.setText("$"+ items.get(position).getPrice());
        int drawableResource = holder.itemView.getResources().getIdentifier(items.get(position).getPicUrl(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(context)
                .load(drawableResource)
                .transform(new GranularRoundedCorners(30, 30, 0, 0))
                .into(holder.pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle item click
                showDetailsToast(items.get(position));
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("object", items.get(position));

                context.startActivity(intent);
            }
        });
        // Set click listeners for plus and minus buttons
        holder.plusCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle plus button click
                int currentQuantity = Integer.parseInt(holder.numberItemTxt.getText().toString());
                currentQuantity++;
                holder.numberItemTxt.setText(String.valueOf(currentQuantity));

                // Update the item quantity in the PopularDomain object if needed
                // For example, you can use items.get(position).setQuantity(currentQuantity);
                // Update the scoreTxt based on the current quantity
                double totalPrice = currentQuantity * items.get(position).getPrice();
                holder.scoreTxt.setText(String.valueOf(totalPrice));

                // Notify the adapter that the data has changed
                notifyDataSetChanged();
            }
        });

        holder.minusCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle minus button click
                int currentQuantity = Integer.parseInt(holder.numberItemTxt.getText().toString());

                // Ensure quantity doesn't go below 0
                if (currentQuantity > 0) {
                    currentQuantity--;
                    holder.numberItemTxt.setText(String.valueOf(currentQuantity));

                    // Update the item quantity in the PopularDomain object if needed
                    // For example, you can use items.get(position).setQuantity(currentQuantity);
                    // Update the scoreTxt based on the current quantity
                    double totalPrice = currentQuantity * items.get(position).getPrice();
                    holder.scoreTxt.setText(String.valueOf(totalPrice));

                    // Notify the adapter that the data has changed
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Declare views inside the ViewHolder
        private final TextView titleTxt;
        private final TextView feeTxt;
        private final TextView scoreTxt;
        private final ImageView pic;
        private final TextView plusCartBtn;
        private final TextView minusCartBtn;
        private final TextView numberItemTxt;

        public ViewHolder(View itemView) {
            super(itemView);

            // Initialize views
            titleTxt = itemView.findViewById(R.id.titleTxt);
            feeTxt = itemView.findViewById(R.id.feeEachItem);
            scoreTxt = itemView.findViewById(R.id.totalEachItem);
            pic = itemView.findViewById(R.id.pic);
            plusCartBtn = itemView.findViewById(R.id.plusCartBtn);
            minusCartBtn = itemView.findViewById(R.id.minusCartBtn);
            numberItemTxt = itemView.findViewById(R.id.numberItemTxt);
        }
    }

    private void showDetailsToast(PopularDomain item) {
        // Display a toast with item details
        Toast.makeText(context, "Details: " + item.getTitle() + ", Price: $" + item.getPrice(), Toast.LENGTH_SHORT).show();
    }
}
