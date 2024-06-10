package com.itacademy.FlowerShopFactory;

import com.itacademy.Products.Product;
import com.itacademy.Tickets.Ticket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class LSOH {


    private String name;

    private Map<Product, Integer> stock;

    private ArrayList<Ticket> tickets;

    private double stockValue; // Maybe not needed as parameter

    public LSOH(String name) {
        this.name = name;
        this.stock = new HashMap<Product, Integer>();
        this.tickets = new ArrayList<Ticket>();
        this.stockValue = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Product, Integer> getStock() {
        return stock;
    }

    public void setStock(Map<Product, Integer> stock) {
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


    // methods that has to replicate in all concrete factories //
    public abstract void addProduct(Product product, int quantity);

    public abstract void removeProduct(Product product);

    public abstract void addTicket(Ticket ticket);

    public abstract void removeTicket(Ticket ticket);


    // The following methods can be out of the class, maybe interfaces to add functionalities?
    public abstract String printStockWithQuantities();

    public abstract double calculateTotalValue();

    public  abstract void showOldSales();

    public abstract double calculateTotalSalesValue();
}
