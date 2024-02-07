package com.mart.cart_activity.CartManagment;

import com.mart.cart_activity.Dao.ProductDao;
import com.mart.cart_activity.Entities.ProductListEntities;

import java.util.List;

public class ManagmentCartList {
    private ProductDao productDao;

    public ManagmentCartList(ProductDao productDao) {
        this.productDao = productDao;
    }
    public long insertProduct(ProductListEntities product) {
        return productDao.insertProduct(product);
    }

    public List<ProductListEntities> getAllProducts() {
        return productDao.getAllProducts();
    }
}
