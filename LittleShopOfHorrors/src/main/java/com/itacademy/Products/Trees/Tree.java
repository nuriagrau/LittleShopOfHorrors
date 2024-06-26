package com.itacademy.Products.Trees;

import com.itacademy.Products.Product;

public class Tree extends Product {

    private int heightCm;

    public Tree(int flowerShopId, String name, double price, int stock, int heightCm) {
        super(flowerShopId, name, price, stock);
        this.heightCm = heightCm;
    }

    public int getHeightCm() {
        return heightCm;
    }

    public void setHeightCm(int heightCm) {
        this.heightCm = heightCm;
    }

    @Override
    public String showTicketDescription() {
        return super.getProductId() + " " + super.getName() + " " + heightCm + " cm";
    }

    @Override
    public String toString() {
        return "Tree[Id=" + productId + ", name=" + name + ", height(cm)= " + heightCm + ", price=" + price + " €, stock=" + stock;
    }
}

