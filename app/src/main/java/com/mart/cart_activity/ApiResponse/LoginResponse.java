package com.mart.cart_activity.ApiResponse;

import com.google.gson.annotations.SerializedName;
import com.mart.cart_activity.ApiModel.UserApiModel;

public class LoginResponse {

    @SerializedName("user")
    private UserApiModel user;

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    // Getter methods for user, status, and message

    // Getter method for the message field
    public String getMessage() {
        return message;
    }

    // Getter methods for user and status
    public UserApiModel getUser() {
        return user;
    }

    public String getStatus() {
        return status;
    }
}
