package com.mart.cart_activity.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.mart.cart_Activity.R;
import com.mart.cart_activity.Entities.UserEntities;
import com.mart.cart_activity.Model.UserViewModel;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private ImageView EditProfileBtn;
    private ImageView Backbtn;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    private TextView UserName;
    UserViewModel userViewModel;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        EditProfileBtn = findViewById(R.id.profileEdit);
        Backbtn = findViewById(R.id.backBtn);
        UserName = findViewById(R.id.username);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        if(acct != null){
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            UserName.setText(personName);
        }


        userViewModel = new ViewModelProvider(ProfileActivity.this).get(UserViewModel.class);

        userViewModel.getAllPersons().observe(ProfileActivity.this, new Observer<List<UserEntities>>() {
            @Override
            public void onChanged(List<UserEntities> users) {
                // Find the user with ID 1
                UserEntities userWithId1 = null;
                for (UserEntities user : users) {
                    if (user.getId() == 1) {
                        userWithId1 = user;
                        break;
                    }
                }

                // Display the information for the user with ID 1
                if (userWithId1 != null) {
                    StringBuilder data = new StringBuilder();
                    String Name =  userWithId1.getName();
                    String Password = userWithId1.getAge();
                    UserName.setText(Name);
//                    data.append("ID: ").append(userWithId1.getId()).append(", Name: ")
//                            .append(userWithId1.getName()).append(", Age: ")
//                            .append(userWithId1.getAge()).append("\n");
//
//                    Toast.makeText(ProfileActivity.this, data.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        EditProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,EditProfileActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        // Customize the behavior when the back button is pressed
        // For example, you can navigate to another activity, show a dialog, etc.

        // Add your custom code here
        super.onBackPressed(); // If you want to perform the default back button behavior, remove this line
    }
}