package com.itacademy.Products;

import java.io.Serializable;

public class JsonProduct extends Product implements Serializable {

    private int productId;
    static int nextId = 1;
    public JsonProduct(String name, double price, int stock) {
        super(name, price, stock);
        this.productId = nextId;
        nextId++;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public static int getNextId() {
        return nextId;
    }


    @Override
    public String showTicketDescription() {
        return productId + " " + name + " " + price + " €" ;
    }


}
