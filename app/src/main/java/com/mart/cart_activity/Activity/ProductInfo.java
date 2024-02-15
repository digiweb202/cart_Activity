package com.mart.cart_activity.Activity;

public class ProductInfo {
    private String productId;
    private String sellerSku;
    private int quantity;

    private double price;

    public ProductInfo(String productId, String sellerSku, int quantity, double price) {
        this.productId = productId;
        this.sellerSku = sellerSku;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSellerSku() {
        return sellerSku;
    }

    public void setSellerSku(String sellerSku) {
        this.sellerSku = sellerSku;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
