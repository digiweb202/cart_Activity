package com.mart.cart_activity.Adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class SuggestionsAdapter extends ArrayAdapter<String> {

    public SuggestionsAdapter(Context context, int resource, List<String> suggestions) {
        super(context, resource, suggestions);
    }
}