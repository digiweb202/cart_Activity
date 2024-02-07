package com.mart.cart_activity.Api;
import com.mart.cart_activity.Activity.OtpActivity;
import com.mart.cart_activity.ApiResponse.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @FormUrlEncoded
    @POST("signup.php")
    Call<String> signup(
            @Field("username") String username,
            @Field("first_name") String firstName,
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("your_php_script_name.php") // Replace with the actual PHP script name
    Call<LoginResponse> loginUser(
            @Query("email") String email,
            @Query("password") String password
    );
}
