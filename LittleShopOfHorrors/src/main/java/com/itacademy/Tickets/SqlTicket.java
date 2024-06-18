package com.itacademy.Tickets;

import com.itacademy.Products.Decorations.SqlDecoration;
import com.itacademy.Products.Flowers.SqlFlower;
import com.itacademy.Products.Product;
import com.itacademy.Products.Trees.SqlTree;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class SqlTicket extends Ticket{

    private int ticketSqlId;

    private Map<Integer, Integer> sqlTicketLines;

    public SqlTicket(int flowerShopId, int ticketSqlId) {
        super(flowerShopId);
        this.ticketSqlId = ticketSqlId;
        this.sqlTicketLines = new HashMap<>();
    }

    public int getTicketSqlId() {
        return ticketSqlId;
    }

    public Map<Integer, Integer> getSqlTicketLines() {
        return sqlTicketLines;
    }

    public void setSqlTicketLines(Map<Integer, Integer> sqlTicketLines) {
        this.sqlTicketLines = sqlTicketLines;
    }

    @Override
    public void addTicketLine(Product product, int quantity) {
        super.getTicketLines().put(product, quantity);
        super.setTicketValue(calculateTicketValue());
        int productSqlId = -1;
        double lineValue = product.getPrice() * quantity;
        if (product instanceof SqlTree) {
            productSqlId = ((SqlTree) product).getSqlId();
        } else if (product instanceof SqlFlower) {
            productSqlId = ((SqlFlower) product).getSqlId();
        } else if (product instanceof SqlDecoration) {
            productSqlId = ((SqlDecoration) product).getSqlId();
        }
        String addTicketLinesQuery = "INSERT INTO TicketLine (ticketId, productId, quantity, lineValue) VALUES\n" +
                "(" + this.ticketSqlId + ", (SELECT productId FROM Product WHERE name = " + product.getName() + "), " + quantity + ", " + lineValue + ");\n" +
                "UPDATE Product SET stock = " + (product.getStock() - quantity) + " WHERE productId = " + productSqlId + ";";

    }
}
