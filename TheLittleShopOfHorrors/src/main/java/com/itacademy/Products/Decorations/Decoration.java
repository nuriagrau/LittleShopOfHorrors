package com.itacademy.Products.Decorations;

import com.itacademy.Products.Product;

public class Decoration extends Product {


    private String material;

    public Decoration(int productId, String name, double price, int stock, String material) {
        super(productId, name, price, stock);
        this.material = material;
    }

    @Override
    public String showTicketDescription() {
        return super.getProductId() + " " + super.getName() + " " + material;
    }

}
