package com.mart.cart_activity.Activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentSender;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.mart.cart_Activity.R;
import com.mart.cart_activity.Database.Databases;
import com.mart.cart_activity.Databaseinitializers.DatabaseInitializers;
import com.mart.cart_activity.Entities.UserEntities;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private Button login_btn;
    private TextView forgottxtbtn;

    private LinearLayout signupbtn;

    private TextInputEditText txtInput_user, txt_pass;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    private SignInClient oneTapClient;
    private BeginSignInRequest signUpRequest;
    private  LinearLayout googlebtn;

    private static final int REQ_ONE_TAP = 2;  // Can be any integer unique to the Activity.
    private boolean showOneTapUI = true;

    Databases myDatabase;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_btn = findViewById(R.id.btn_login);
        forgottxtbtn = findViewById(R.id.forgottxtBtn);
        signupbtn = findViewById(R.id.signuptxtBtn);
        txtInput_user = findViewById(R.id.txtInput_user);
        txt_pass = findViewById(R.id.txt_pass);
        googlebtn = findViewById(R.id.google_btn);


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);
        myDatabase = DatabaseInitializers.getInstance(getApplicationContext());



        oneTapClient = Identity.getSignInClient(this);
        signUpRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        // Your server's client ID, not your Android client ID.
                        .setServerClientId(getString(R.string.webClientId))
                        // Show all accounts on the device.
                        .setFilterByAuthorizedAccounts(false)
                        .build())
                .build();

        ActivityResultLauncher<IntentSenderRequest> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                try{
                    SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(o.getData());
                    String idToken = credential.getGoogleIdToken();
                    if (idToken != null){
                        String email = credential.getId();
                        String name = credential.getDisplayName();
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        intent.putExtra("keyEmail", email);
                        intent.putExtra("keyName",name);

                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"Email"+email,Toast.LENGTH_SHORT).show();

                    }
                } catch (ApiException e){
                    e.printStackTrace();
                }
            }
        });

        googlebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                signin();
//                IntentSenderRequest intentSenderRequest = new IntentSenderRequest.Builder(o.getPendingIntent()).build();


                oneTapClient.beginSignIn(signUpRequest)
                        .addOnSuccessListener(LoginActivity.this, new OnSuccessListener<BeginSignInResult>() {
                            @Override
                            public void onSuccess(BeginSignInResult result) {

                                    IntentSenderRequest intentSenderRequest= new IntentSenderRequest.Builder(result.getPendingIntent().getIntentSender()).build();
                                    activityResultLauncher.launch(intentSenderRequest);

                            }
                        })
                        .addOnFailureListener(LoginActivity.this, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // No Google Accounts found. Just continue presenting the signed-out UI.
                                Log.d(TAG, e.getLocalizedMessage());
                            }
                        });
            }
        });
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });
        forgottxtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ForgetPassActivity.class);
                startActivity(intent);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = txtInput_user.getText().toString();
                String password = txt_pass.getText().toString();
                UserEntities user1 = new UserEntities(username, password);

                // Perform database operation in a background thread
                new InsertPersonTask().execute(user1);
                Toast.makeText(LoginActivity.this,"Successfully Login",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(LoginActivity.this,ForgetPassActivity.class);
                startActivity(intent);
            }
        });
    }

    void signin(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            //application Try and Catches related work Exception related work adding here
            try {
                task.getResult(ApiException.class);
                nevigateToSecondActivity();
            } catch (ApiException e) {
                //application will be implimation related Toast work
                //application try Catche
                Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();

                throw new RuntimeException(e);
            }
        }
    }

    void nevigateToSecondActivity(){
        finish();
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
    }
    private void authenticateUser() {
        String email = txtInput_user.getText().toString().trim();
        String password = txt_pass.getText().toString().trim();

        // Replace with your API endpoint
        String apiUrl = "http://localhost/NJOYMART_V2.O/Mart-Website%20Frontend/Njoymart-Website-Frontend/nest/demo/apis/login.php?email=" + email + "&password=" + password;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, apiUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Check the status from the API response
                            int status = response.getInt("status");
                            if (status == 200) {
                                // Login successful, proceed to the next activity
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                // Login failed, show an error message
                                Toast.makeText(LoginActivity.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Log.e("API_ERROR", "Error occurred: " + error.getMessage());
                        Toast.makeText(LoginActivity.this, "Error occurred. Please try again later.", Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the RequestQueue
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }
    private class InsertPersonTask extends AsyncTask<UserEntities, Void, Void> {
        @Override
        protected Void doInBackground(UserEntities... users) {
            myDatabase.getPersonDAO().addPerson(users[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // Update UI or perform post-execution logic if needed
            txtInput_user.setText("");
            txt_pass.setText("");
        }
    }
}