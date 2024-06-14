package com.itacademy.Products;

public class Product implements IProduct {

    protected String name;

    protected double price;

    protected int stock;

    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String toString() {
        return "Product[id= " + productId + ", name= " + name + ", price=" + price + " €, stock=" + stock;
    }

    @Override
    public String showTicketDescription() {
        return null;
    }
}
