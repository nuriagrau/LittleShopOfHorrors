package com.itacademy.Products;

import java.io.Serializable;

public class JsonProduct extends Product implements Serializable {

    public JsonProduct(String name, double price, int stock) {
        super(name, price, stock);
    }

    public static int getNextId() {
        return nextId;
    }


    @Override
    public String showTicketDescription() {
        return productId + " " + name + " " + price + " €" ;
    }


}
