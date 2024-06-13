package com.itacademy.Products;

import java.io.Serializable;

public class JsonProduct extends Product implements Serializable {

    public JsonProduct(String name, double price, int stock) {
        super(name, price, stock);
    }

    @Override
    public String showTicketDescription() {
        return productId + " " + name + " " + price + " €" ;
    }


}
