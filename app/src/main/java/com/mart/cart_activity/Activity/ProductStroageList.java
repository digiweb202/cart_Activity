package com.mart.cart_activity.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.content.Context;
import android.widget.Toast;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductStroageList {
    private static ProductStroageList instance;
    private List<List<String>> productList;

    private ProductStroageList() {
        productList = new ArrayList<>();
    }

    public static synchronized ProductStroageList getInstance() {
        if (instance == null) {
            instance = new ProductStroageList();
        }
        return instance;
    }

    public void addProduct(String productID, String sellerSKU) {
        List<String> productPair = new ArrayList<>();
        productPair.add(productID);
        productPair.add(sellerSKU);
        productList.add(productPair);
    }

    public List<List<String>> getProductList() {
        return productList;
    }
}