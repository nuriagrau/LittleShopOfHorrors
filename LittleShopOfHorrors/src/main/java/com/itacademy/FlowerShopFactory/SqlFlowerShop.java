package com.itacademy.FlowerShopFactory;

import com.itacademy.Persistance.Sql.DatabaseConnection;
import com.itacademy.Products.Decorations.Decoration;
import com.itacademy.Products.Decorations.SqlDecoration;
import com.itacademy.Products.Flowers.Flower;
import com.itacademy.Products.Flowers.SqlFlower;
import com.itacademy.Products.Product;
import com.itacademy.Products.SqlProduct;
import com.itacademy.Products.Trees.SqlTree;
import com.itacademy.Products.Trees.Tree;
import com.itacademy.Tickets.SqlTicket;
import com.itacademy.Tickets.Ticket;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SqlFlowerShop extends LittleShopOfHorrors implements FlowerShopFactory {

    static Connection con = DatabaseConnection.getConnection();

    private int sqlFlowerShopId;

    public SqlFlowerShop(String name) {
        super(name);
    }

    public int getSqlFlowerShopId() {
        return sqlFlowerShopId;
    }

    public void setSqlFlowerShopId(int sqlFlowerShopId) {
        this.sqlFlowerShopId = sqlFlowerShopId;
    }


    public static String showExistentFlowershops() {
        String existentFlowerShops = "", name;
        int id;
        String selectFlowerShop = "SELECT * FROM FlowerShop";
        SqlFlowerShop newSqlFlowerShop = null;
        try {
            Statement myStatement = con.createStatement();
            ResultSet myResultSet = myStatement.executeQuery(selectFlowerShop);
            while (myResultSet.next()) {
                name = myResultSet.getString("name");
                id = myResultSet.getInt("flowerShopId");
                existentFlowerShops += id + ".- " + name + "\n";
            }
            myResultSet.close();
            myStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return existentFlowerShops;
        }
    }

    public static SqlFlowerShop loadSqlFlowerShop(int sqlFlowerShopId) {
        String flowerShopName;
        double stockValue;
        String selectFlowerShop = "SELECT * FROM FlowerShop";
        SqlFlowerShop newSqlFlowerShop = null;
        try {
            Statement myStatement = con.createStatement();
            ResultSet myResultSet = myStatement.executeQuery(selectFlowerShop);
            while (myResultSet.next()) {
                flowerShopName = myResultSet.getString("name");
                stockValue = myResultSet.getDouble("stockValue");
                newSqlFlowerShop = new SqlFlowerShop(flowerShopName);
                newSqlFlowerShop.setStockValue(stockValue);
            }
            myResultSet.close();
            myStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return newSqlFlowerShop;
        }
    }

    public static ArrayList<Product> loadSqlProducts(int sqlFlowerShopId) {
        int sqlProductId;
        String name, colour, material;
        double price;
        int stock, heightCm;
        String productType;
        ArrayList<Product> newSqlStock = new ArrayList<>();
        String selectStock = "SELECT * FROM Product WHERE flowerShopId = " + sqlFlowerShopId;
        String selectSubproduct = "SELECT * FROM ";
        try {
            Statement myStatement = con.createStatement();
            ResultSet myResultSet = myStatement.executeQuery(selectStock);
            while (myResultSet.next()) {
                Product newProduct = null;
                sqlProductId = myResultSet.getInt("productId");
                sqlFlowerShopId = myResultSet.getInt("flowerShopId");
                name = myResultSet.getString("name");
                price = myResultSet.getDouble("price");
                stock = myResultSet.getInt("stock");
                productType = myResultSet.getString("productType");
                selectSubproduct += productType + " WHERE productId = " + sqlProductId + ";";
                myResultSet = myStatement.executeQuery(selectSubproduct);
                while (myResultSet.next()) {
                    if (productType.equalsIgnoreCase("Tree")) {
                        heightCm = myResultSet.getInt("heightCm");
                        newProduct = new SqlTree(sqlFlowerShopId, name, price, stock,heightCm, sqlProductId);
                    } else if (productType.equalsIgnoreCase("Flower")) {
                        colour = myResultSet.getString("colour");
                        newProduct = new SqlFlower(sqlFlowerShopId, name, price, stock,colour, sqlProductId);
                    } else if (productType.equalsIgnoreCase("Decoration")) {
                        material = myResultSet.getString("material");
                        newProduct = new SqlDecoration(sqlFlowerShopId, name, price, stock, material, sqlProductId);
                    }
                    newSqlStock.add(newProduct);
                }
            }
            myResultSet.close();
            myStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return newSqlStock;
        }
    }

    public static ArrayList<Ticket> loadSqlTickets(int sqlFlowerShopId) {
        int sqlTicketId;
        Timestamp timestamp;
        String sqlFlowerShopName;
        double ticketValue, lineValue;
        int quantity, productId;
        ArrayList<Ticket> newTickets = new ArrayList<>();
        Map<Integer, Integer> ticketLines = new HashMap<>();
        String selectTicket = "SELECT * FROM Ticket WHERE flowerShopId = " + sqlFlowerShopId;
        String selectTicketLines = "SELECT ProductID, quantity, lineValue FROM TicketLine JOIN Product ON Ticket.productId = Product.productId WHERE ticketId =  ";
        try {
            Statement myStatement = con.createStatement();
            ResultSet myResultSet = myStatement.executeQuery(selectTicket);
            while (myResultSet.next()) {
                Ticket newTicket = null;
                sqlTicketId = myResultSet.getInt("ticketId");
                sqlFlowerShopId = myResultSet.getInt("flowerShopId");
                timestamp = myResultSet.getTimestamp("timestamp");
                ticketValue = myResultSet.getDouble("ticketValue");
                newTicket = new SqlTicket(sqlTicketId, sqlTicketId);
                newTicket.setTimestamp(timestamp);
                newTicket.setTicketValue(ticketValue);

                selectTicketLines += selectTicketLines + sqlTicketId + ";";
                myResultSet = myStatement.executeQuery(selectTicketLines);
                while (myResultSet.next()) {
                        productId = myResultSet.getInt("productId");
                        quantity = myResultSet.getInt("quantity");
                        lineValue = myResultSet.getDouble("lineValue");
                        ticketLines.put(productId, quantity);
                    }
                    ;
            }
            myResultSet.close();
            myStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return newTickets;
        }
    }

    public static Map<Integer, Integer> loadSqlTicketLines(int sqlTicketId) {
        double lineValue;
        int quantity, productId;
        Map<Integer, Integer> newTicketLines = new HashMap<>();
        String selectTicketLines = "SELECT ProductId, quantity, lineValue FROM TicketLine JOIN Product ON Ticket.productId = Product.productId WHERE ticketId =  ";
        try {
            Statement myStatement = con.createStatement();
            selectTicketLines += selectTicketLines + sqlTicketId + ";";
            ResultSet myResultSet = myStatement.executeQuery(selectTicketLines);
            while (myResultSet.next()) {
                productId = myResultSet.getInt("productId");
                quantity = myResultSet.getInt("quantity");
                lineValue = myResultSet.getDouble("lineValue");
                newTicketLines.put(productId, quantity);
            }
            myResultSet.close();
            myStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return newTicketLines;
        }
    }

    @Override
    public Product createProduct(int flowerShopId, String name, double price, int stock) {
        return new SqlProduct(flowerShopId, name, price, stock);
    }

    @Override
    public Tree createTree(int flowerShopId, String name, double price, int stock, int heightCm) {
        Statement myStatement = null;
        String addTreeQuery = "";
        int sqlId =  -1;
        try {
            myStatement = con.createStatement();
            String addProductQuery = "INSERT INTO Product (name, price, stock, type) VALUES (" + name +", " + price + ", " +  stock +  ", " +  "TREE" +");";
            ResultSet myResultSet = myStatement.executeQuery(addProductQuery);
            while (myResultSet.next()) {
                sqlId = myResultSet.getInt("productId");
            }
            addTreeQuery = "INSERT INTO Tree (productId, heightCm) VALUES ((SELECT productId FROM Product WHERE name = "+ name + "), " + heightCm + ");";
            myResultSet = myStatement.executeQuery(addTreeQuery);
            System.out.println("Tree " + name  + " inserted successfully.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new SqlTree(flowerShopId,name, price, stock, heightCm, sqlId);
    }

    @Override
    public Flower createFlower(int flowerShopId, String name, double price, int stock, String colour) {
        Statement myStatement = null;
        String addFlowerQuery = "";
        int sqlId =  -1;
        try {
            myStatement = con.createStatement();
            String addProductQuery = "INSERT INTO Product (name, price, stock, type) VALUES (" + name +", " + price + ", " +  stock +  ", " +  "FLOWER" +");";
            ResultSet myResultSet = myStatement.executeQuery(addProductQuery);
            while (myResultSet.next()) {
                sqlId = myResultSet.getInt("productId");
            }
            addFlowerQuery = "INSERT INTO Flower (productId, colour) VALUES ((SELECT productId FROM Product WHERE name = "+ name + "), " + colour + ");";
            myResultSet = myStatement.executeQuery(addFlowerQuery);
            System.out.println("Flower " + name  + " inserted successfully.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new SqlFlower(flowerShopId, name, price, stock, colour, sqlId);
    }

    @Override
    public Decoration createDecoration(int flowerShopId, String name, double price, int stock, String material) {
        Statement myStatement = null;
        String addDecorationQuery = "";
        int sqlId =  -1;
        try {
            myStatement = con.createStatement();
            String addProductQuery = "INSERT INTO Product (name, price, stock, type) VALUES (" + name +", " + price + ", " +  stock +  ", " +  "DECORATION" +");";
            ResultSet myResultSet = myStatement.executeQuery(addProductQuery);
            while (myResultSet.next()) {
                sqlId = myResultSet.getInt("productId");
            }
            addDecorationQuery = "INSERT INTO Decoration (productId, material) VALUES ((SELECT productId FROM Product WHERE name = "+ name + "), " + material + ");";
            myResultSet = myStatement.executeQuery(addDecorationQuery);
            System.out.println("Decoration " + name  + " inserted successfully.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new SqlDecoration(flowerShopId, name, price, stock, material, sqlId);
    }


    @Override
    public void addProduct(Product product) {
        super.getStock().add(product);
    }


    @Override
    public void removeProduct(int productIndex) {
        int sqlId = -1;
        if (super.getStock().get(productIndex) instanceof SqlTree) {
            sqlId = ((SqlTree) super.getProduct(productIndex)).getSqlId();
        } else if (super.getStock().get(productIndex) instanceof SqlFlower) {
            sqlId = ((SqlFlower) super.getProduct(productIndex)).getSqlId();
        } else if (super.getStock().get(productIndex) instanceof SqlDecoration) {
            sqlId = ((SqlDecoration) super.getProduct(productIndex)).getSqlId();
        }
        Statement myStatement = null;
        String removeProductQuery = "DELETE FROM Product WHERE productId = " + sqlId;
        try {
            myStatement = con.createStatement();
            ResultSet myResultSet = myStatement.executeQuery(removeProductQuery);
            System.out.println("Product deleted successfully.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        super.getStock().remove(productIndex);
    }


    @Override
    public Ticket createTicket(int flowerShopId) {
        Statement myStatement = null;
        int ticketSqlId =  -1;
        try {
            myStatement = con.createStatement();
            String addTicketQuery = "INSERT INTO Ticket (totalValue) VALUES\n" +
                    "(0.0);";
            ResultSet myResultSet = myStatement.executeQuery(addTicketQuery);
            while (myResultSet.next()) {
                ticketSqlId = myResultSet.getInt("ticketId");
            }
            System.out.println("Ticket created successfully.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new SqlTicket(flowerShopId, ticketSqlId);
    }

    @Override
    public void addTicket(Ticket ticket) {
        super.getTickets().add(ticket);
    }

    public static void updateTicketValue(Ticket ticket, Double ticketValue) {
        Statement myStatement = null;
        String updateTicketValueQuery = "UPDATE Ticket SET ticketValue = " + ticket.getTicketValue() + "WHERE ticketId = " + ((SqlTicket) ticket).getTicketSqlId() +";\n";
        try {
            myStatement = con.createStatement();
            ResultSet myResultSet = myStatement.executeQuery(updateTicketValueQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void removeTicket(Ticket ticket) {
        int ticketSqlId = ((SqlTicket)super.getTickets().get(super.getTickets().indexOf(ticket))).getTicketSqlId();
        String removeTicketQuery = "DELETE FROM Ticket WHERE ticket.id = " + ticketSqlId;
        super.getTickets().remove(super.getTickets().indexOf(ticket));
    }

    @Override
    public String printStockWithQuantities() {
        int stockTrees = 0, stockFlowers = 0, stockDecoration = 0;
        String selectStockQuery = "SELECT SUM(stock) FROM Product GROUP BY productType";
        try {
            Statement myStatement = con.createStatement();
            String sql = "SELECT * FROM product";
            ResultSet myResultSet = myStatement.executeQuery(sql);
            while (myResultSet.next()) {
                stockTrees = myResultSet.getInt("TREE");
                stockFlowers = myResultSet.getInt("FLOWER");
                stockDecoration = myResultSet.getInt("DECORATION");
            }
            myResultSet.close();
            myStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
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
        String selectTotalValueQuery = "SELECT SUM(stock * price) AS totalValue FROM Product";
        try {
            Statement myStatement = con.createStatement();
            ResultSet myResultSet = myStatement.executeQuery(selectTotalValueQuery);
            while (myResultSet.next()) {
                totalValue = myResultSet.getDouble("totalValue");
            }
            myResultSet.close();
            myStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalValue;
    }

    @Override
    public void showOldSales(String shopName) {
        ArrayList<Ticket> oldSalesTickets;
        String name, lines = "", header = "";
        Timestamp timestamp;
        int ticketId, quantity;
        double lineValue, ticketValue;
        String selectOldSalesHeaderQuery = "SELECT ticketId, timestamp, ticketValue " +
                "FROM Ticket";
        String selectOldSalesLinesQuery = "SELECT ticketId, name, quantity, lineValue" +
                "FROM Ticket JOIN TicketLine ON Ticket.ticketId=TicketLine.ticketId JOIN Product ON TicketLine.ProductId = Product.ProductId GROUP BY ticketId;";
        try {
            Statement myStatement = con.createStatement();
            ResultSet myHeaderResultSet = myStatement.executeQuery(selectOldSalesHeaderQuery);
            while (myHeaderResultSet.next()) {
                ticketId = myHeaderResultSet.getInt("ticketId");
                timestamp = myHeaderResultSet.getTimestamp("timestamp");
                ticketValue = myHeaderResultSet.getDouble("totalValue");
                header = shopName + "\n" + ticketId + "\n   " + timestamp + "\n";

                ResultSet myLinesResultSet = myStatement.executeQuery(selectOldSalesLinesQuery);
                while (myLinesResultSet.next()) {
                    ticketId = myLinesResultSet.getInt("ticketId");
                    timestamp = myLinesResultSet.getTimestamp("timestamp");
                    name = myLinesResultSet.getString("name");
                    quantity = myLinesResultSet.getInt("quantity");
                    lineValue = myLinesResultSet.getDouble("lineValue");
                    ticketValue = myLinesResultSet.getDouble("totalValue");
                    lines += name + "  " +  quantity + "pcs  " + lineValue + "\n";
                }
                myLinesResultSet.close();
                System.out.println("____________________________\n" +
                        shopName + header + lines +
                        "\nTOTAL            " + ticketValue +
                        "\n____________________________\n" );

            }
            myHeaderResultSet.close();
            myStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public double calculateTotalSalesValue() {
        double totalSalesValue = 0d;
        String selectTotalSalesValueQuery = "SELECT SUM(ticketValue) AS totalSalesValue FROM Ticket";
        try {
            Statement myStatement = con.createStatement();
            ResultSet myResultSet = myStatement.executeQuery(selectTotalSalesValueQuery);
            while (myResultSet.next()) {
                totalSalesValue = myResultSet.getDouble("totalSalesValue");
            }
            myResultSet.close();
            myStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalSalesValue;
    }
}
