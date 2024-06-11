package com.itacademy.Products;

public class JsonProduct extends Product{

    public JsonProduct(String name, double price, int stock) {
        super(name, price, stock);
    }

    @Override
    public String showTicketDescription() {
        return productId + " " + name + " " + price + " €" ;
    }

}
