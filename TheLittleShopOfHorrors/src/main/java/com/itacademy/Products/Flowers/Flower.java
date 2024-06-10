package com.itacademy.Products.Flowers;
import com.itacademy.Products.Product;

public class Flower extends Product {

    private String colour;

    public Flower(int productId, String name, double price, int stock, String colour) {
        super(productId, name, price, stock);
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

}
