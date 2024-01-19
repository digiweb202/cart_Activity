package com.mart.cart_activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
 // Make sure to import your R class
import com.mart.cart_Activity.R;
import com.mart.cart_activity.domain.PopularDomain;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.Viewholder> {
    private ArrayList<PopularDomain> items;
    private Context context;

    public PopularAdapter(ArrayList<PopularDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the view using LayoutInflater
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_pup_list, parent, false);
        context = parent.getContext();
        return new Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        // Bind data to the views inside the ViewHolder
        holder.titleText.setText(items.get(position).getTitle());
        holder.feeTxt.setText("$" + items.get(position).getPrice());
        holder.scoreTxt.setText(String.valueOf(items.get(position).getScore()));

        int drawableResource = holder.itemView.getResources().getIdentifier(items.get(position).getPicUrl(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(context)
                .load(drawableResource)
                .transform(new GranularRoundedCorners(30, 30, 0, 0))
                .into(holder.pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle item click
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        // Declare views inside the ViewHolder
        private final TextView titleText;
        private final TextView feeTxt;
        private final TextView scoreTxt;
        private final ImageView pic;

        public Viewholder(View itemView) {
            super(itemView);

            // Initialize views
            titleText = itemView.findViewById(R.id.titleText);
            feeTxt = itemView.findViewById(R.id.feeTxt);
            scoreTxt = itemView.findViewById(R.id.scoreTxt);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
