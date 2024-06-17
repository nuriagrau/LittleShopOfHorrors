package com.itacademy.Products;

public class Product implements IProduct {


    protected int productId;
    static int nextId = 1;

    protected String name;

    protected double price;

    protected int stock;

    protected String productType;

    public Product(String name, double price, int stock) {
        this.productId = nextId;
        nextId++;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.productType = productType;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String toString() {
        return "Product[id= " + productId + ", name= " + name + ", price=" + price + " €, stock=" + stock;
    }

    @Override
    public String showTicketDescription() {
        return null;
    }
}
