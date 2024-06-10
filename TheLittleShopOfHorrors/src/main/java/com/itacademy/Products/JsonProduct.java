package com.itacademy.Products;

public class JsonProduct extends Product{

    public JsonProduct(int productId, String name, double price, int stock) {
        super(productId, name, price, stock);
    }

    @Override
    public String showTicketDescription() {
        return productId + " " + name + " " + price + " €" ;
    }

}
