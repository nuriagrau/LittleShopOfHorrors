package com.itacademy.Products;

import java.io.Serializable;

public class SqlProduct extends Product implements Serializable {

    public SqlProduct(String name, double price, int stock) {
        super(name, price, stock);
    }

}
