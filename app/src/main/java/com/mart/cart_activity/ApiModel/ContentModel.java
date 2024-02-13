package com.mart.cart_activity.ApiModel;

import com.google.gson.annotations.SerializedName;

public class ContentModel {
    @SerializedName("id")
    private int id;

    @SerializedName("imageURL")
    private String imageURL;

    @SerializedName("contentText")
    private String contentText;

    public int getId() {
        return id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getContentText() {
        return contentText;
    }
}

