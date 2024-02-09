package com.mart.cart_activity.ApiResponse;

import com.google.gson.annotations.SerializedName;

public class CategoriesResponse {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("link")
    private String link;

    @SerializedName("image")
    private String image;

    // Create getters for the fields

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public String getImage() {
        return image;
    }
}
