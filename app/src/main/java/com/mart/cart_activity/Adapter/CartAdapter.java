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
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.mart.cart_Activity.R;
import com.mart.cart_activity.Activity.DetailActivity;
import com.mart.cart_activity.Helper.ManagmentCart;
import com.mart.cart_activity.domain.PopularDomain;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private final ArrayList<PopularDomain> items;
    private Context context;
    ManagmentCart managmentCart;
    private CartAdapterListener listener;

    public CartAdapter(ArrayList<PopularDomain> items, Context context, ManagmentCart managementCart, CartAdapterListener listener) {
        this.items = items;
        this.context = context;
        this.listener = listener;
        this.managmentCart = managementCart;
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
//        holder.scoreTxt.setText("$" + items.get(position).getPrice());
        PopularDomain currentItem = items.get(position);
        currentItem.setNumberInChart(currentItem.getNumberInChart());
        holder.numberItemTxt.setText(String.valueOf(currentItem.getNumberInChart()));
        double totalPrice = items.get(position).getPrice() * Integer.parseInt(String.valueOf(currentItem.getNumberInChart()));
        holder.scoreTxt.setText("$" + totalPrice);


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
                int currentQuantity = Integer.parseInt(String.valueOf(currentItem.getNumberInChart()));
                currentQuantity++;

                updateItemQuantity(position, currentQuantity);
//                updateItem(position);

                int quantity = Integer.parseInt(holder.numberItemTxt.getText().toString());
                if (quantity > 0) {

                    double totalPrice = items.get(position).getPrice() * Integer.parseInt(String.valueOf(currentQuantity));
                    holder.scoreTxt.setText("$" + totalPrice);
                    holder.scoreTxt.setVisibility(View.VISIBLE);
                    holder.numberItemTxt.setText(String.valueOf(currentQuantity));
                    currentItem.setNumberInChart(Integer.parseInt(String.valueOf(currentQuantity)));
                } else {

                    double totalPrice = items.get(position).getPrice() * currentQuantity;
                    holder.scoreTxt.setText("$" + totalPrice);
                    holder.scoreTxt.setVisibility(View.VISIBLE);
                }
//                    holder.numberItemTxt.setText(String.valueOf(currentItem.getNumberInChart()));

            }
        });

        holder.minusCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantity = Integer.parseInt(holder.numberItemTxt.getText().toString());

                if (currentQuantity > 1) {
                    currentQuantity--;

                    // Update the UI with the new quantity
                    holder.numberItemTxt.setText(String.valueOf(currentQuantity));

                    // Update the data (assuming this method is defined elsewhere)
                    updateItemQuantity(position, currentQuantity);

                    // Update the total price and visibility if the quantity is still greater than 0
                    double totalPrice = items.get(position).getPrice() * currentQuantity;
                    holder.scoreTxt.setText("$" + totalPrice);
                    holder.scoreTxt.setVisibility(View.VISIBLE);

                    // Update the data with the new quantity
                    currentItem.setNumberInChart(currentQuantity);
                } else {
                    // If the quantity is 1, you can choose to handle it differently,
                    // such as showing/hiding views, updating data, etc.
                    // For now, let's just update the UI with the new quantity.
                    holder.numberItemTxt.setText("1");

                    // If you want to update the data for quantity 1, use the following line:
                    // updateItemQuantity(position, 1);
                    double totalPrice = items.get(position).getPrice() * currentQuantity;
                    holder.scoreTxt.setText("$" + totalPrice);
                    holder.scoreTxt.setVisibility(View.VISIBLE);

                }
            }
        });
        // Set long-click listener for removing items
        // Set long-click listener for removing items
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Call the method in ManagmentCart to remove the item
                // Call the method in ManagmentCart to remove the item
//                managmentCart.removeItem(1);

                // Notify the adapter about the removal
                notifyItemRemoved(position);

                showRemoveConfirmationDialog(position);
                notifyDataSetChanged();


                return true;
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
    public void updateDataset(ArrayList<PopularDomain> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }
    private void showRemoveConfirmationDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Are you sure you want to remove this item from your Cart?")
                .setPositiveButton("OK", (dialog, which) -> {
                    items.get(position).setNumberInChart(0);
                    // Call the method in ManagmentCart to remove the item
                    managmentCart.removeItem(position);
//                    PopularDomain popularDomain = null;
//                    popularDomain.setNumberInChart(0);
                    // Remove the item from the items list
                    items.remove(position);
                    // Notify the adapter about the removal
                    notifyItemRemoved(position);
                    notifyDataSetChanged();
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    // Dismiss the dialog if the user cancels
                    dialog.dismiss();
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
