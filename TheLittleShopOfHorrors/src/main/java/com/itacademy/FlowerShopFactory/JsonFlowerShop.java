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
    public Product createProduct(String name, double price, int stock) {
        return new JsonProduct(name, price, stock);
    }

    @Override
    public Tree createTree(String name, double price, int stock, int heightCm) {
        return new JsonTree(name,  price, stock, heightCm);
    }

    @Override
    public Flower createFlower(String name, double price, int stock, String colour) {
        return new JsonFlower(name, price, stock, colour);
    }

    @Override
    public Decoration createDecoration(String name, double price, int stock, String material) {
        return new JsonDecoration(name, price, stock, material);
    }

    @Override
    public Ticket createTicket() {
        return new JsonTicket();
    }

    @Override
    public void addProduct(Product product) {
        super.getStock().add(product);
    }

    @Override
    public void removeProduct(int productIndex) {
        super.getStock().remove(productIndex);
    }

    @Override
    public void addTicket(Ticket ticket) {
        super.getTickets().add(ticket);
    }

    @Override
    public void removeTicket(Ticket ticket) {
        super.getTickets().remove(super.getTickets().indexOf(ticket));
    }


    // If the active database is charged from persistent data, if not pass these methods to specific shop
    @Override
    public String printStockWithQuantities() {
        int stockTrees = 0, stockFlowers = 0, stockDecoration = 0;
        for (Product p : super.getStock()) {
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
        for (Product p : stock){
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
