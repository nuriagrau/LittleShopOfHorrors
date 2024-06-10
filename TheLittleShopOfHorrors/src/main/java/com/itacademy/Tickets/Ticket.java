package com.itacademy.Tickets;

import com.itacademy.Products.Product;

import java.sql.Timestamp;
import java.util.Map;

public class Ticket implements ITicket{

    private int id;

    static int nextId;

    private Timestamp timestamp;

    private Map<Product, Integer> ticketLines;

    private double ticketValue;

    public Ticket(Timestamp timestamp, double ticketValue) {
        this.id = nextId;
        nextId++;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.ticketValue = ticketValue;
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

    @Override
    public String toString() {
        return id + " " + timestamp + "\n"
                + ticketLines +
                "TOTAL " + getTicketValue() + " €";
    }

    @Override
    public double calculateTicketValue() {
        return 0;
    }
}
