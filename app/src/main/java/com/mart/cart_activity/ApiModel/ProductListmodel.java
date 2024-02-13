package com.mart.cart_activity.ApiModel;

import com.google.gson.annotations.SerializedName;

public class ProductListmodel {
    @SerializedName("Product_Type")
    public String product_Type;
    @SerializedName("Seller_SKU")
    public String seller_SKU;

    @SerializedName("Brand_Name")
    public String brand_Name;
    @SerializedName("Update_Delete")
    public String update_Delete;
    @SerializedName("Item_Name")
    public String item_Name;
    @SerializedName("Manufacturer")
    public String manufacturer;
    @SerializedName("Model_Number")
    public String model_Number;
    @SerializedName("Manufacturer_Part_Number")
    public String manufacturer_Part_Number;
    @SerializedName("Product_ID")
    public String product_ID;
    @SerializedName("Product_ID_Type")
    public String product_ID_Type;
    @SerializedName("Recommended_Browse_Nodes")
    public String recommended_Browse_Nodes;
    @SerializedName("Product_Description")
    public String product_Description;
    @SerializedName("Your_Price")
    public String your_Price;
    @SerializedName("Quantity")
    public int quantity;
    @SerializedName("Age_Range_Description")
    public String age_Range_Description;
    @SerializedName("Target_Gender")
    public String target_Gender;
    @SerializedName("Model_Name")
    public String model_Name;
    public int id;

    public String getProduct_Type() {
        return product_Type;
    }

    public void setProduct_Type(String product_Type) {
        this.product_Type = product_Type;
    }

    public String getSeller_SKU() {
        return seller_SKU;
    }

    public void setSeller_SKU(String seller_SKU) {
        this.seller_SKU = seller_SKU;
    }

    public String getBrand_Name() {
        return brand_Name;
    }

    public void setBrand_Name(String brand_Name) {
        this.brand_Name = brand_Name;
    }

    public String getUpdate_Delete() {
        return update_Delete;
    }

    public void setUpdate_Delete(String update_Delete) {
        this.update_Delete = update_Delete;
    }

    public String getItem_Name() {
        return item_Name;
    }

    public void setItem_Name(String item_Name) {
        this.item_Name = item_Name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel_Number() {
        return model_Number;
    }

    public void setModel_Number(String model_Number) {
        this.model_Number = model_Number;
    }

    public String getManufacturer_Part_Number() {
        return manufacturer_Part_Number;
    }

    public void setManufacturer_Part_Number(String manufacturer_Part_Number) {
        this.manufacturer_Part_Number = manufacturer_Part_Number;
    }

    public String getProduct_ID() {
        return product_ID;
    }

    public void setProduct_ID(String product_ID) {
        this.product_ID = product_ID;
    }

    public String getProduct_ID_Type() {
        return product_ID_Type;
    }

    public void setProduct_ID_Type(String product_ID_Type) {
        this.product_ID_Type = product_ID_Type;
    }

    public String getRecommended_Browse_Nodes() {
        return recommended_Browse_Nodes;
    }

    public void setRecommended_Browse_Nodes(String recommended_Browse_Nodes) {
        this.recommended_Browse_Nodes = recommended_Browse_Nodes;
    }

    public String getProduct_Description() {
        return product_Description;
    }

    public void setProduct_Description(String product_Description) {
        this.product_Description = product_Description;
    }

    public String getYour_Price() {
        return your_Price;
    }

    public void setYour_Price(String your_Price) {
        this.your_Price = your_Price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAge_Range_Description() {
        return age_Range_Description;
    }

    public void setAge_Range_Description(String age_Range_Description) {
        this.age_Range_Description = age_Range_Description;
    }

    public String getTarget_Gender() {
        return target_Gender;
    }

    public void setTarget_Gender(String target_Gender) {
        this.target_Gender = target_Gender;
    }

    public String getModel_Name() {
        return model_Name;
    }

    public void setModel_Name(String model_Name) {
        this.model_Name = model_Name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
