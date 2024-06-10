package com.itacademy.Tickets;

import java.sql.Timestamp;

public class JsonTicket extends Ticket {

    public JsonTicket(Timestamp timestamp, double ticketValue) {
        super(timestamp, ticketValue);
    }

    @Override
    public double calculateTicketValue() {
        return 0;
    }
}
