package com.mart.cart_activity.ApiResponse;

import com.google.gson.annotations.SerializedName;

public class LoginApiResponse {
    @SerializedName("user")
    private User user;

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    public User getUser() {
        return user;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public static class User {
        @SerializedName("id")
        private int id;

        @SerializedName("username")
        private String username;

        @SerializedName("email")
        private String email;

        public int getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }
    }
}
