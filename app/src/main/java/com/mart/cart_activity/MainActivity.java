package com.mart.cart_activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mart.cart_Activity.R;
import com.mart.cart_activity.Adapter.PopularAdapter;
import com.mart.cart_activity.domain.PopularDomain;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView popularRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Set the content view using the layout resource ID
        initRecyclerView();
    }

    private void initRecyclerView() {
        ArrayList<PopularDomain> items = new ArrayList<>();
        items.add(new PopularDomain("title", "item_1", 3, 4, 5, "Test"));
        items.add(new PopularDomain("title", "item_2", 3, 4, 5, "Test"));
        items.add(new PopularDomain("title", "item_3", 3, 4, 5, "Test"));
        items.add(new PopularDomain("title", "item_4", 3, 4, 5, "Test"));
        items.add(new PopularDomain("title", "item_2", 3, 4, 5, "Test"));



        // Find the RecyclerView by ID
        popularRecyclerView = findViewById(R.id.PopularView);

        // Set up the LinearLayoutManager and adapter
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        popularRecyclerView.setLayoutManager(layoutManager);

        PopularAdapter adapter = new PopularAdapter(items);
        popularRecyclerView.setAdapter(adapter);
    }
}
