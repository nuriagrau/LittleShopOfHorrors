package com.itacademy.Tickets;

import com.itacademy.Products.Product;

public interface ITicket {
    abstract double calculateTicketValue();

    abstract void addTicketLine(Product product, int quantity);
}
