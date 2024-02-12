package com.mart.cart_activity.ApiModel;

import com.google.gson.annotations.SerializedName;

public class GetProductModel {

    @SerializedName("Seller_SKU")
    private String sellerSKU;

    @SerializedName("Product_ID")
    private String productID;

    @SerializedName("Item_Name")
    private String productName;

    @SerializedName("Product_Description")
    private String productDescription;

    @SerializedName("Your_Price")
    private String yourPrice;

    @SerializedName("Main_Image_URL")
    private String mainImageurl;
    // Add more fields as needed

    public void setSellerSKU(String sellerSKU) {
        this.sellerSKU = sellerSKU;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getYourPrice() {
        return yourPrice;
    }

    public void setYourPrice(String yourPrice) {
        this.yourPrice = yourPrice;
    }

    public String getMainImageurl() {
        return mainImageurl;
    }

    public void setMainImageurl(String mainImageurl) {
        this.mainImageurl = mainImageurl;
    }

    // Create getters for the fields
    public String getSellerSKU() {
        return sellerSKU;
    }

    public String getProductID() {
        return productID;
    }
    // Add this constant
    public static final int PAGE_SIZE = 10;
}
