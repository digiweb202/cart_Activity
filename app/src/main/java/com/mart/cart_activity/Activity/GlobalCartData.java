package com.mart.cart_activity.Activity;

import java.util.ArrayList;
import java.util.List;

public class GlobalCartData {
    private static GlobalCartData instance;
    private List<ProductInfo> productInfoList;

    private GlobalCartData() {
        productInfoList = new ArrayList<>();
    }

    public static GlobalCartData getInstance() {
        if (instance == null) {
            instance = new GlobalCartData();
        }
        return instance;
    }

    public List<ProductInfo> getProductInfoList() {
        return productInfoList;
    }
}
