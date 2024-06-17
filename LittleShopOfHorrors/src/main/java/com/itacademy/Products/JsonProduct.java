package com.itacademy.Products;

import java.io.Serializable;

public class JsonProduct extends Product implements Serializable {

    public JsonProduct(int flowerShopId, String name, double price, int stock) {
        super(flowerShopId, name, price, stock);
    }

    public static int getNextId() {
        return nextId;
    }


    @Override
    public String showTicketDescription() {
        return productId + " " + name + " " + price + " €" ;
    }


}
