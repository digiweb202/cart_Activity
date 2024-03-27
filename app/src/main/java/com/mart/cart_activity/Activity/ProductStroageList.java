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
        // Check if the product ID and seller SKU already exist in the list
        boolean alreadyExists = false;
        for (List<String> productPair : productList) {
            if (productPair.get(0).equals(productID) && productPair.get(1).equals(sellerSKU)) {
                alreadyExists = true;
                break;
            }
        }

        // If the product ID and seller SKU do not exist in the list, add them
        if (!alreadyExists) {
            List<String> productPair = new ArrayList<>();
            productPair.add(productID);
            productPair.add(sellerSKU);
            productList.add(productPair);
        }
    }

    public List<List<String>> getProductList() {
        return productList;
    }
}
