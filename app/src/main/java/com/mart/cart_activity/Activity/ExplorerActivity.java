package com.mart.cart_activity.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.mart.cart_Activity.R;

public class ExplorerActivity extends AppCompatActivity {
    private ImageView explorerbackbtn;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorer);

        explorerbackbtn = findViewById(R.id.explorerBackbtn);

        explorerbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExplorerActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        // Customize the behavior when the back button is pressed
        // For example, you can navigate to another activity, show a dialog, etc.

        // Add your custom code here
        super.onBackPressed();
        Log.e("BackButton","Back Button press");
        // If you want to perform the default back button behavior, remove this line
    }
}