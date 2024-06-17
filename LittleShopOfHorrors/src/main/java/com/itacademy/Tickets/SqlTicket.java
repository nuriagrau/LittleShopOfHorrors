package com.itacademy.Tickets;

import com.itacademy.Products.Decorations.SqlDecoration;
import com.itacademy.Products.Flowers.SqlFlower;
import com.itacademy.Products.Product;
import com.itacademy.Products.Trees.SqlTree;

import java.sql.Timestamp;
import java.util.Map;

public class SqlTicket extends Ticket{

    private int ticketSqlId;

    public SqlTicket(int flowerShopId, int ticketSqlId) {
        super(flowerShopId);
        this.ticketSqlId = ticketSqlId;
    }

    public int getTicketSqlId() {
        return ticketSqlId;
    }

    public void setTicketSqlId(int ticketSqlId) {
        this.ticketSqlId = ticketSqlId;
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
