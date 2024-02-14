package com.mart.cart_activity.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mart.cart_Activity.R;

import java.util.List;

public class ProductOrderListAdapter extends ArrayAdapter<List<String>> {

private final Context context;

public ProductOrderListAdapter(Context context, List<List<String>> productList) {
        super(context, 0, productList);
        this.context = context;
        }

@NonNull
@Override
public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
        convertView = LayoutInflater.from(context).inflate(R.layout.viewholder_pup_list, parent, false);
        }

        List<String> productPair = getItem(position);

        if (productPair != null && productPair.size() == 2) {
        TextView productIdTextView = convertView.findViewById(R.id.titleTxt);
        TextView sellerSkuTextView = convertView.findViewById(R.id.feeEachItem);

        productIdTextView.setText("Product ID: " + productPair.get(0));
        sellerSkuTextView.setText("Seller SKU: " + productPair.get(1));
        }

        return convertView;
        }
}