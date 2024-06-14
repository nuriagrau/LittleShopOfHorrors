package com.itacademy.LSOH2;

import com.itacademy.Products.Product;
import com.itacademy.Tickets.Ticket;

import java.util.*;

// Not clear if needed. Keeping it just in case. Transported to FlowerShopFactory package as LSOH2 as abstract class


public abstract class LSOH2 {

    private String name;

    private Map<Product, Integer> stock;

    private ArrayList<Ticket> tickets;

    private double stockValue; // Maybe not needed as parameter

    public LSOH2(String name) {
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
    public abstract void addProduct();

    public abstract void removeProduct();

    public abstract void addTicket();

    public abstract void removeTicket();


    // The following methods can be out of the class, maybe interfaces to add functionalities?
    public abstract void printStockWithQuantities();

    public abstract void calculateTotalValue();

    public abstract void showOldSales();

    public abstract void calculateTotalSalesValue();


}
