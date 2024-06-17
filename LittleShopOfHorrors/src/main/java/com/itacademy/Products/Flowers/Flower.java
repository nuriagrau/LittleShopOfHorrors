package com.itacademy.Products.Flowers;

import com.itacademy.Products.Product;

public class Flower extends Product {


    private String colour;

    public Flower(int flowerShopId, String name, double price, int stock, String colour) {
        super(flowerShopId, name, price, stock);
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public String showTicketDescription() {
        return super.getProductId() + " " + super.getName() + " " + colour;
    }

    @Override
    public String toString() {
        return "Flower[Id=" + productId + ", name=" + name + ", colour= " + colour + ", price=" + price + " €, stock=" + stock;
    }
}
