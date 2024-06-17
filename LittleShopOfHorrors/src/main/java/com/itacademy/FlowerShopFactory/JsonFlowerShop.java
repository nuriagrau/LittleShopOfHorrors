package com.itacademy.FlowerShopFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.io.File;

import static com.itacademy.App.Application.jsonDirPath;

public class JsonFlowerShop extends LittleShopOfHorrors implements FlowerShopFactory {

    public JsonFlowerShop(String name) {
        super(name);
    }
    static ObjectMapper objectMapper = new ObjectMapper();
    //  private static JsonFlowerShop instance; Singleton not needed


    // Singleton for only one database. Not needed.
   /* public static JsonFlowerShop getInstance(String name) {
        if (instance == null) {
            instance = new JsonFlowerShop(name);
        }
        return instance;
    };*/


    @Override
    public Product createProduct(int flowerShopId, String name, double price, int stock) {
        return new JsonProduct(flowerShopId, name, price, stock);
    }

    @Override
    public Tree createTree(int flowerShopId, String name, double price, int stock, int heightCm) {
        return new JsonTree(flowerShopId, name,  price, stock, heightCm);
    }

    @Override
    public Flower createFlower(int flowerShopId, String name, double price, int stock, String colour) {
        return new JsonFlower(flowerShopId, name, price, stock, colour);
    }

    @Override
    public Decoration createDecoration(int flowerShopId, String name, double price, int stock, String material) {
        return new JsonDecoration(flowerShopId, name, price, stock, material);
    }

    @Override
    public Ticket createTicket(int flowerShopId) {
        return new JsonTicket(flowerShopId);
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
        try {
            objectMapper.writeValue(new File(jsonDirPath + "/" + ticket.getId() + ".json"), ticket);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                stockTrees += p.getStock();
            } else if (p instanceof Flower) {
                stockFlowers += p.getStock();
            } else if (p instanceof Decoration) {
                stockDecoration += p.getStock();
            }
        }
        return  "STOCK\n" +
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
            totalValue += (p.getPrice() * p.getStock());
        }
        return totalValue;
    }

    @Override
    public void showOldSales(String shopName) {
        for (Ticket ticket : tickets) {
            System.out.println("____________________________\n" +
                    shopName + "\n" + ticket.showHeader() +
                    ticket.showLines() +
                    "\n____________________________\n" );
        }
    }

    @Override
    public double calculateTotalSalesValue() {
        double totalSalesValue = 0d;
        for (Ticket ticket: tickets) {
            ticket.calculateTicketValue();
            totalSalesValue += ticket.getTicketValue();
        }
        return totalSalesValue;
    }

    public static void serializedJsonFlowerShop(JsonFlowerShop activeJsonFlowershop) throws JsonProcessingException {
        try {
            objectMapper.writeValue(new File(jsonDirPath + "/" + activeJsonFlowershop.getName() + ".json"), activeJsonFlowershop);
            System.out.println(activeJsonFlowershop.name + "has been serialized to a json file.");
        } catch (Exception e) {
            e.getMessage();
            System.out.println("Something went wrong when trying to serialize active flower shop.");
        }
    }




}
