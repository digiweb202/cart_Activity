package com.mart.cart_activity.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mart.cart_activity.Entities.ProductListEntities;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    long insertProduct(ProductListEntities product);

    @Query("SELECT * FROM products")
    List<ProductListEntities> getAllProducts();
}
