package com.itacademy.Products;

import java.io.Serializable;

public class SqlProduct extends Product implements Serializable {

    private int sqlFlowerShopId;

    public SqlProduct(int flowerShopId, String name, double price, int stock) {
        super(flowerShopId, name, price, stock);
        this.sqlFlowerShopId = sqlFlowerShopId;
    }

}
