package com.mart.cart_activity.Api;

import com.google.gson.annotations.SerializedName;
import com.mart.cart_activity.ApiModel.ProductTypeModel;

import java.util.List;

public class ApiResponse {
    @SerializedName("success")
    private boolean success;

    @SerializedName("data")
    private List<ProductTypeModel> data;

    public boolean isSuccess() {
        return success;
    }

    public List<ProductTypeModel> getData() {
        return data;
    }
}