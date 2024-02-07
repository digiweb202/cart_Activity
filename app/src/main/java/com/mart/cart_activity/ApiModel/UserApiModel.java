package com.mart.cart_activity.ApiModel;

import com.google.gson.annotations.SerializedName;

public class UserApiModel {
    @SerializedName("id")
    private String id;

    @SerializedName("username")
    private String username;

    @SerializedName("email")
    private String email;
}
