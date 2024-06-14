package com.itacademy.Products.Decorations;

import com.itacademy.Products.Product;

public class Decoration extends Product {


    private String material;

    public Decoration(String name, double price, int stock, String material) {
        super(name, price, stock);
        this.material = material;
    }

    @Override
    public String showTicketDescription() {
        return super.getProductId() + " " + super.getName() + " " + material;
    }

    @Override
    public String toString() {
        return "Decoration[Id=" + productId + ", name=" + name + ", material " + material + ", price=" + price + " €, stock=" + stock;
    }
}
