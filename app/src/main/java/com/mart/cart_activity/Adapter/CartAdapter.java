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
    private Context context;
    private CartAdapterListener listener;

    public CartAdapter(ArrayList<PopularDomain> items, Context context, CartAdapterListener listener) {
        this.items = items;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleTxt.setText(items.get(position).getTitle());
        holder.feeTxt.setText("$" + items.get(position).getPrice());
        holder.scoreTxt.setText("$" + items.get(position).getPrice());


//        item way show that value will be showing declare
        int drawableResource = holder.itemView.getResources().getIdentifier(items.get(position).getPicUrl(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(context)
                .load(drawableResource)
                .transform(new GranularRoundedCorners(30, 30, 0, 0))
                .into(holder.pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDetailsToast(items.get(position));
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("object", items.get(position));
                context.startActivity(intent);
            }
        });

        holder.plusCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantity = Integer.parseInt(holder.numberItemTxt.getText().toString());
                currentQuantity++;
                holder.numberItemTxt.setText(String.valueOf(currentQuantity));
                updateItemQuantity(position, currentQuantity);
//                updateItem(position);

                int quantity = Integer.parseInt(holder.numberItemTxt.getText().toString());
                if (quantity > 0) {

                    double totalPrice = items.get(position).getPrice() * quantity;
                    holder.scoreTxt.setText("$" + totalPrice);
                    holder.scoreTxt.setVisibility(View.VISIBLE);
                } else {

                    holder.scoreTxt.setVisibility(View.GONE);
                }

            }
        });

        holder.minusCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantity = Integer.parseInt(holder.numberItemTxt.getText().toString());
                if (currentQuantity > 0) {
                    currentQuantity--;
                    holder.numberItemTxt.setText(String.valueOf(currentQuantity));
                    updateItemQuantity(position, currentQuantity);
//                    updateItem(position);
                    int quantity = Integer.parseInt(holder.numberItemTxt.getText().toString());
                    if (quantity > 0) {

                        double totalPrice = items.get(position).getPrice() * quantity;
                        holder.scoreTxt.setText("$" + totalPrice);
                        holder.scoreTxt.setVisibility(View.VISIBLE);
                    } else {

                        holder.scoreTxt.setVisibility(View.GONE);
                    }

                }
            }
        });
    }
    // Add this method to your CartAdapter
    public void updateItem(int position) {
        notifyItemChanged(position);
    }
    private void updateItemQuantity(int position, int quantity) {
        if (listener != null) {
            listener.onQuantityChanged(position, quantity);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTxt;
        private final TextView feeTxt;
        private final TextView scoreTxt;
        private final ImageView pic;
        private final TextView plusCartBtn;
        private final TextView minusCartBtn;
        private final TextView numberItemTxt;

        public ViewHolder(View itemView) {
            super(itemView);
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
        Toast.makeText(context, "Details: " + item.getTitle() + ", Price: $" + item.getPrice(), Toast.LENGTH_SHORT).show();
    }

    // Interface to communicate quantity changes to the CartActivity
    public interface CartAdapterListener {
        void onQuantityChanged(int position, int quantity);
    }
}
