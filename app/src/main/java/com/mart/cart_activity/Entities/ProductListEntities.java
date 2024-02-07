package com.mart.cart_activity.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class ProductListEntities {
    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo(name = "image")
    public String image;
    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "price")
    public double price;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public ProductListEntities(String image, String title, double price) {
        this.image = image;
        this.title = title;
        this.price = price;
    }
}
