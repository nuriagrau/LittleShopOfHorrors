package com.itacademy.Tickets;

import com.itacademy.Products.Product;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class Ticket implements ITicket {

    private int id;

    static int nextId = 1000;

    private Timestamp timestamp;

    private Map<Product, Integer> ticketLines;

    private double ticketValue;

    private int flowerShopId;

    public Ticket(int flowerShopId) {
        this.id = nextId;
        nextId++;
        this.flowerShopId = flowerShopId;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        ticketLines = new HashMap<>();
        this.ticketValue = 0d;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Map<Product, Integer> getTicketLines() {
        return ticketLines;
    }

    public void setTicketLines(Map<Product, Integer> ticketLines) {
        this.ticketLines = ticketLines;
    }

    public double getTicketValue() {
        return ticketValue;
    }

    public void setTicketValue(double ticketValue) {
        this.ticketValue = ticketValue;
    }

    public int getFlowerShopId() {
        return flowerShopId;
    }

    public void setFlowerShopId(int flowerShopId) {
        this.flowerShopId = flowerShopId;
    }

    @Override
    public String toString() {
        return id + " " + timestamp + "\n"
                + ticketLines + "\n" +
                "TOTAL " + getTicketValue() + " €";
    }

    @Override
    public double calculateTicketValue() {
        double lineValue = 0d, totalValue = 0d;
        for (Map.Entry<Product, Integer> e : ticketLines.entrySet()) {
            lineValue = e.getKey().getPrice() * e.getValue();
            totalValue += lineValue;
        }
        this.setTicketValue(totalValue);
        return totalValue;
    }

    @Override
    public void addTicketLine(Product product, int quantity) {
        ticketLines.put(product, quantity);
        setTicketValue(calculateTicketValue());
    }

    public String showLines() {
        String lines = "";
        double lineValue = 0d, totalValue = 0d;
        for (Map.Entry<Product, Integer> e : ticketLines.entrySet()) {
            lineValue = e.getKey().getPrice() * e.getValue();
            lines +=  e.getKey().getName() + "     " +  e.getValue() + " pcs     " + lineValue + "\n";
            totalValue += lineValue;
        }
        lines += "\nTOTAL            " + totalValue;
        return lines;
    }

    public String showHeader() {
        return id + "     " + timestamp + "\n";
    }
}
