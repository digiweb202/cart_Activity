package com.mart.cart_activity.ApiModel;

import com.google.gson.annotations.SerializedName;

public class ProductListImgModel {

    @SerializedName("Main_Image_URL")
    public String main_Image_URL;
    @SerializedName("Other_Image_URL1")
    public String other_Image_URL1;
    @SerializedName("Other_Image_URL2")
    public String other_Image_URL2;
    @SerializedName("Other_Image_URL3")
    public Object other_Image_URL3;
    @SerializedName("Other_Image_URL4")
    public Object other_Image_URL4;
    @SerializedName("Other_Image_URL5")
    public Object other_Image_URL5;


    @SerializedName("Other_Image_URL6")
    public Object other_Image_URL6;
    @SerializedName("Other_Image_URL7")
    public Object other_Image_URL7;
    @SerializedName("Other_Image_URL8")
    public Object other_Image_URL8;
    @SerializedName("Swatch_Image_URL")
    public Object swatch_Image_URL;
    public int id;
    @SerializedName("Product_ID")
    public String product_ID;
    @SerializedName("Seller_SKU")
    public String seller_SKU;

    public String getMain_Image_URL() {
        return main_Image_URL;
    }

    public void setMain_Image_URL(String main_Image_URL) {
        this.main_Image_URL = main_Image_URL;
    }

    public String getOther_Image_URL1() {
        return other_Image_URL1;
    }

    public void setOther_Image_URL1(String other_Image_URL1) {
        this.other_Image_URL1 = other_Image_URL1;
    }

    public String getOther_Image_URL2() {
        return other_Image_URL2;
    }

    public void setOther_Image_URL2(String other_Image_URL2) {
        this.other_Image_URL2 = other_Image_URL2;
    }

    public Object getOther_Image_URL3() {
        return other_Image_URL3;
    }

    public void setOther_Image_URL3(Object other_Image_URL3) {
        this.other_Image_URL3 = other_Image_URL3;
    }

    public Object getOther_Image_URL4() {
        return other_Image_URL4;
    }

    public void setOther_Image_URL4(Object other_Image_URL4) {
        this.other_Image_URL4 = other_Image_URL4;
    }

    public Object getOther_Image_URL5() {
        return other_Image_URL5;
    }

    public void setOther_Image_URL5(Object other_Image_URL5) {
        this.other_Image_URL5 = other_Image_URL5;
    }

    public Object getOther_Image_URL6() {
        return other_Image_URL6;
    }

    public void setOther_Image_URL6(Object other_Image_URL6) {
        this.other_Image_URL6 = other_Image_URL6;
    }

    public Object getOther_Image_URL7() {
        return other_Image_URL7;
    }

    public void setOther_Image_URL7(Object other_Image_URL7) {
        this.other_Image_URL7 = other_Image_URL7;
    }

    public Object getOther_Image_URL8() {
        return other_Image_URL8;
    }

    public void setOther_Image_URL8(Object other_Image_URL8) {
        this.other_Image_URL8 = other_Image_URL8;
    }

    public Object getSwatch_Image_URL() {
        return swatch_Image_URL;
    }

    public void setSwatch_Image_URL(Object swatch_Image_URL) {
        this.swatch_Image_URL = swatch_Image_URL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_ID() {
        return product_ID;
    }

    public void setProduct_ID(String product_ID) {
        this.product_ID = product_ID;
    }

    public String getSeller_SKU() {
        return seller_SKU;
    }

    public void setSeller_SKU(String seller_SKU) {
        this.seller_SKU = seller_SKU;
    }

}
