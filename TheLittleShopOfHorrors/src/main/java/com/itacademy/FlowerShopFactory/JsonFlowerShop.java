package com.itacademy.FlowerShopFactory;


import com.itacademy.Products.Decorations.Decoration;
import com.itacademy.Products.Decorations.JsonDecoration;
import com.itacademy.Products.Flowers.Flower;
import com.itacademy.Products.Flowers.JsonFlower;
import com.itacademy.Products.JsonProduct;
import com.itacademy.Products.Product;
import com.itacademy.Products.Trees.JsonTree;
import com.itacademy.Products.Trees.Tree;
import com.itacademy.Tickets.JsonTicket;
import com.itacademy.Tickets.Ticket;

import java.sql.Timestamp;


public class JsonFlowerShop extends LSOH implements FlowerShopFactory {


    //  private static JsonFlowerShop instance; Singleton not needed
    public JsonFlowerShop(String name) {
        super(name);
    }


    // Singleton for only one database. Not needed.
   /* public static JsonFlowerShop getInstance(String name) {
        if (instance == null) {
            instance = new JsonFlowerShop(name);
        }
        return instance;
    };*/

    @Override
    public Product createProduct(int productId, String name, double price, int stock) {
        return new JsonProduct(productId, name, price, stock);
    }

    @Override
    public Tree createTree(int productId, String name, double price, int stock, int heightCm) {
        return new JsonTree(productId, name,  price, stock, heightCm);
    }

    @Override
    public Flower createFlower(int productId, String name, double price, int stock, String colour) {
        return new JsonFlower(productId, name, price, stock, colour);
    }

    @Override
    public Decoration createDecoration(int productId, String name, double price, int stock, String material) {
        return new JsonDecoration(productId, name, price, stock, material);
    }

    @Override
    public Ticket createTicket(Timestamp timestamp, double ticketValue) {
        return new JsonTicket(timestamp, ticketValue);
    }

    @Override
    public void addProduct(Product product, int quantity) {
        super.getStock().put(product, quantity);

    }

    @Override
    public void removeProduct(Product product) {
        super.getStock().remove(product);
    }

    @Override
    public void addTicket(Ticket ticket) {
        super.getTickets().add(ticket);
    }

    @Override
    public void removeTicket(Ticket ticket) {
        super.getTickets().remove(super.getTickets().indexOf(ticket));
    }

    @Override
    public String printStockWithQuantities() {
        int stockTrees = 0, stockFlowers = 0, stockDecoration = 0;
        for (Product p : super.getStock().keySet()) {
            if (p instanceof Tree) {
                stockTrees++;
            } else if (p instanceof Flower) {
                stockFlowers++;
            } else if (p instanceof Decoration) {
                stockDecoration++;
            }
        }
        return  "<b>STOCK:</b>\n" +
                "TREES: \n" +
                "     " + stockTrees + "\n" +
                "FLOWERS: \n" +
                "     " + stockFlowers + "\n" +
                "DECORATION: \n" +
                "     " + stockDecoration + "\n";
    }

    @Override
    public double calculateTotalValue() {
        double totalValue = 0d;
        for (Product p : super.getStock().keySet()){
            totalValue += p.getPrice();
        }
        return totalValue;
    }

    @Override
    public void showOldSales() {
        System.out.println(super.getTickets().toString());
    }

    @Override
    public double calculateTotalSalesValue() {
        double totalSalesValue = 0d;
        for (Ticket ticket: super.getTickets()) {
            totalSalesValue += ticket.getTicketValue();
        }
        return totalSalesValue;
    }
}
