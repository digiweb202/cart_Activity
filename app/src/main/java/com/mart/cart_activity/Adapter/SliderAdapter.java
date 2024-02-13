package com.mart.cart_activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mart.cart_Activity.R;
import com.mart.cart_activity.ApiModel.ContentModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private List<ContentModel> contentList;
    private Context context;

    public SliderAdapter(List<ContentModel> contentList, Context context) {
        this.contentList = contentList;
        this.context = context;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slid_item, parent, false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        ContentModel content = contentList.get(position);
        Picasso.get().load(content.getImageURL()).into(holder.imageView);
        holder.textViewContent.setText(content.getContentText());
    }

    @Override
    public int getItemCount() {
        return contentList.size();
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewContent;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViews);
            textViewContent = itemView.findViewById(R.id.testdata);
        }
    }
}
