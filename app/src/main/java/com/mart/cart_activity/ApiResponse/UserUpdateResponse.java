package com.mart.cart_activity.ApiResponse;

import com.google.gson.annotations.SerializedName;

public class UserUpdateResponse {
    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
