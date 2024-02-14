package com.mart.cart_activity.ApiModel;

import com.google.gson.annotations.SerializedName;

public class LoginModel {
    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;
}
