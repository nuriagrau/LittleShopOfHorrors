package com.itacademy.Tickets;

import com.itacademy.Products.Decorations.SqlDecoration;
import com.itacademy.Products.Flowers.SqlFlower;
import com.itacademy.Products.Product;
import com.itacademy.Products.Trees.SqlTree;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class SqlTicket extends Ticket{

    private int sqlTicketId;

    private Map<Integer, Integer> sqlTicketLines;

    public SqlTicket(int sqlFlowerShopId, int qlTicketId) {
        super(sqlFlowerShopId);
        this.sqlTicketId = qlTicketId;
        this.sqlTicketLines = new HashMap<>();
    }

    public int getTicketSqlId() {
        return sqlTicketId;
    }

    public Map<Integer, Integer> getSqlTicketLines() {
        return sqlTicketLines;
    }

    public void setSqlTicketLines(Map<Integer, Integer> sqlTicketLines) {
        sqlTicketLines = sqlTicketLines;
    }

    public void addSqlTicketLine(Integer productId, Integer quantity) {
        this.sqlTicketLines.put(productId, quantity);

    }


    @Override
    public String toString() {
        return sqlTicketId + " " + super.getTimestamp() + "\n"
                + super.getTicketLines() + sqlTicketLines + "\n" +
                "TOTAL " + getTicketValue() + " €";
    }

    @Override
    public String showHeader() {
        return sqlTicketId + " " + super.getTimestamp() + "\n";
    }


}
