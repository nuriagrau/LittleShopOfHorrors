package com.itacademy.FlowerShopFactory;

import com.itacademy.Products.Decorations.Decoration;
import com.itacademy.Products.Flowers.Flower;
import com.itacademy.Products.Product;
import com.itacademy.Products.Trees.Tree;
import com.itacademy.Tickets.Ticket;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class LittleShopOfHorrors implements Serializable {

    protected String name;

    protected static ArrayList<Product> stock;
    protected ArrayList<Ticket> tickets;

    protected double stockValue; // Maybe not needed as parameter

    public LittleShopOfHorrors(String name) {
        this.name = name;
        this.stock = new ArrayList<Product>();
        this.tickets = new ArrayList<Ticket>();
        this.stockValue = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Product> getStock() {
        return this.stock;
    }

    public void setStock(ArrayList<Product> stock) {
        this.stock = stock;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public double getStockValue() {
        return stockValue;
    }

    public void setStockValue(double stockValue) {
        this.stockValue = stockValue;
    }



    public abstract Product createProduct(String name, double price, int stock);

    public abstract Tree createTree(String name, double price, int stock, int heightCm);

    public abstract Flower createFlower(String name, double price, int stock, String colour);

    public abstract Decoration createDecoration(String name, double price, int stock, String material);

    public abstract Ticket createTicket();



    // methods that has to replicate in all concrete factories //
    public abstract void addProduct(Product product);

    public abstract void removeProduct(int productIndex);

    public abstract void addTicket(Ticket ticket);

    public abstract void removeTicket(Ticket ticket);


    public Product getProduct(int productIndex) {
        return this.stock.get(productIndex);
    }

    public int getProductIndexById(int productId) {
        int jsonProductIndex = -1;
        for (int i = 0; i < this.getStock().size(); i++) {
            if (this.getStock().get(i).getProductId() == productId) {
                jsonProductIndex = i;
            }
        }
        return jsonProductIndex;
    }

    public int getProductIndex(String name) {
        int jsonProductIndex = -1;
        for (int i = 0; i < this.getStock().size(); i++) {
            if (this.getStock().get(i).getName().equalsIgnoreCase(name)) {
                jsonProductIndex = i;
            }
        }
        return jsonProductIndex;
    }

    public String showProductsByType(int productType) {
        String productsList = "";
        switch (productType) {
            case 1:
                for(Product p: stock) {
                    if (p instanceof Tree) {
                        p = (Tree) p;
                        productsList += p.toString() + "\n";
                    }
                }
                break;
            case 2:
                for(Product p: stock) {
                    if (p instanceof Flower) {
                        p = (Flower) p;
                        productsList += p.toString() + "\n";
                    }
                }
                break;
            case 3:
                for(Product p: stock) {
                    if (p instanceof Decoration) {
                        p = (Decoration) p;
                        productsList += p.toString() + "\n";
                    }
                }
                break;
        }
        return productsList;
    }


    // The following methods can be out of the class, maybe interfaces to add functionalities?
    public abstract String printStockWithQuantities();

    public abstract double calculateTotalValue();

    public  abstract void showOldSales(String shopName);

    public abstract double calculateTotalSalesValue();

    @Override
    public String toString() {
        return "FlowerShop ["+ name + ", Total Value= " + stockValue + " € ]";
    }
}
