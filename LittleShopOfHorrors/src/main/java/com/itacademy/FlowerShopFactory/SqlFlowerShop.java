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


    public static String showExistentFlowershops() throws NullPointerException {
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
            e.getMessage();
        } finally {
            return existentFlowerShops;
        }
    }

    public static SqlFlowerShop loadSqlFlowerShop(int sqlFlowerShopId) throws NullPointerException {
        String flowerShopName;
        double stockValue;
        String selectFlowerShop = "SELECT * FROM FlowerShop WHERE flowerShopId =" + sqlFlowerShopId;
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
            e.getMessage();
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
        String selectStock = "SELECT * FROM Product WHERE flowerShopId = " + sqlFlowerShopId + ";";
        Product newProduct = null;
        try {
            Statement myStatement = con.createStatement();
            ResultSet myResultSet = myStatement.executeQuery(selectStock);
            while (myResultSet.next()) {
                sqlProductId = myResultSet.getInt("productId");
                sqlFlowerShopId = myResultSet.getInt("flowerShopId");
                name = myResultSet.getString("name");
                price = myResultSet.getDouble("price");
                stock = myResultSet.getInt("stock");
                productType = myResultSet.getString("productType");

                String selectSubproduct = "SELECT * FROM " + productType.toLowerCase() + " WHERE productId = " + sqlProductId + ";";
                Statement ourStatement = con.createStatement();
                ResultSet ourResultSet = ourStatement.executeQuery(selectSubproduct);

                while (ourResultSet.next()) {
                    if (productType.equalsIgnoreCase("Tree")) {
                        heightCm = ourResultSet.getInt("heightCm");
                        newProduct = new SqlTree(sqlFlowerShopId, name, price, stock, heightCm, sqlProductId);

                    } else if (productType.equalsIgnoreCase("Flower")) {
                        colour = ourResultSet.getString("colour");
                        newProduct = new SqlFlower(sqlFlowerShopId, name, price, stock, colour, sqlProductId);

                    } else if (productType.equalsIgnoreCase("Decoration")) {
                        material = ourResultSet.getString("material");
                        newProduct = new SqlDecoration(sqlFlowerShopId, name, price, stock, material, sqlProductId);

                    }
                    newSqlStock.add(newProduct);
                }
            }

        } catch (Exception e) {
            e.getMessage();
        } finally {

            System.out.println(newSqlStock.size());
            return newSqlStock;
        }

    }


    public static ArrayList<Ticket> loadSqlTickets(int sqlFlowerShopId) {
        int sqlTicketId;
        Timestamp timestamp;
        String sqlFlowerShopName;
        double ticketValue, lineValue;
        int quantity, sqlProductId;
        ArrayList<Ticket> newTickets = new ArrayList<>();
        SqlTicket newTicket = null;
        String selectTicket = "SELECT * FROM Ticket WHERE flowerShopId = " + sqlFlowerShopId + ";";
        try {
            Statement myStatement = con.createStatement();
            ResultSet myResultSet = myStatement.executeQuery(selectTicket);
            while (myResultSet.next()) {
                sqlTicketId = myResultSet.getInt("ticketId");
                //sqlFlowerShopId = myResultSet.getInt("flowerShopId");
                timestamp = myResultSet.getTimestamp("timestamp");
                ticketValue = myResultSet.getDouble("ticketValue");
                newTicket = new SqlTicket(sqlFlowerShopId, sqlTicketId);
                newTicket.setTimestamp(timestamp);
                newTicket.setTicketValue(ticketValue);
                String selectTicketLines = "SELECT TicketLine.ProductID, quantity, lineValue FROM TicketLine JOIN Product ON TicketLine.productId = Product.productId WHERE ticketId = ";
                selectTicketLines += sqlTicketId + ";"; //
                Map<Integer, Integer> newTicketLines = newTicket.getSqlTicketLines();
                Statement ourStatement = con.createStatement();
                ResultSet ourResultSet = ourStatement.executeQuery(selectTicketLines);
                while (ourResultSet.next()) {//
                    sqlProductId = ourResultSet.getInt("productId");
                    quantity = ourResultSet.getInt("quantity");
                    newTicket.addSqlTicketLine(sqlProductId, quantity);
                }
                //System.out.println(newTicketLines.toString());
                newTicket.setSqlTicketLines(newTicketLines);
                //System.out.println(newTicket.toString());
                newTickets.add(newTicket);
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            return newTickets;
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
            String addProductQuery = "INSERT INTO Product (name, price, stock, type) VALUES (" + name +", " + price + ", " +  stock +  ", " +  "'TREE'" +");";
            String askLastInsert = "SELECT last_insert_id();";
            int rows = myStatement.executeUpdate(addProductQuery);
            ResultSet myResultSet = myStatement.executeQuery(askLastInsert);
            while (myResultSet.next()) {
                sqlId = myResultSet.getInt("last_insert_id()");
            }
            addTreeQuery = "INSERT INTO Tree (productId, material) VALUES (" + sqlId + ", " + heightCm + ");";
            int decoRows = myStatement.executeUpdate(addTreeQuery);
            if (decoRows > 0) {
                System.out.println("Tree " + name  + " inserted successfully.");
            }
            myResultSet.close();
            myStatement.close();
        } catch (SQLException e) {
            e.getMessage();
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
            String addProductQuery = "INSERT INTO Product (name, price, stock, type) VALUES (" + name +", " + price + ", " +  stock +  ", " +  "'FLOWER'" +");";
            String askLastInsert = "SELECT last_insert_id();";
            int rows = myStatement.executeUpdate(addProductQuery);
            ResultSet myResultSet = myStatement.executeQuery(askLastInsert);
            while (myResultSet.next()) {
                sqlId = myResultSet.getInt("last_insert_id()");
            }
            addFlowerQuery = "INSERT INTO Flower (productId, colour) VALUES (" + sqlId + ", " + colour + ");";
            int decoRows = myStatement.executeUpdate(addFlowerQuery);
            if (decoRows > 0) {
                System.out.println("Flower " + name  + " inserted successfully.");
            }
            myResultSet.close();
            myStatement.close();
        } catch (SQLException e) {
            e.getMessage();
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
            String addProductQuery = "INSERT INTO Product (name, price, stock, type) VALUES (" + name +", " + price + ", " +  stock +  ", " +  "'DECORATION'" +");";
            String askLastInsert = "SELECT last_insert_id();";
            int rows = myStatement.executeUpdate(addProductQuery);
            ResultSet myResultSet = myStatement.executeQuery(askLastInsert);
            while (myResultSet.next()) {
                sqlId = myResultSet.getInt("last_insert_id()");
            }
            addDecorationQuery = "INSERT INTO Decoration (productId, material) VALUES (" + sqlId + ", " + material + ");";
            int decoRows = myStatement.executeUpdate(addDecorationQuery);
            if (decoRows > 0) {
                System.out.println("Decoration " + name  + " inserted successfully.");
            }
            myResultSet.close();
            myStatement.close();
        } catch (SQLException e) {
            e.getMessage();
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
            int rows = myStatement.executeUpdate(removeProductQuery);
            if (rows > 0) {
                System.out.println("Product deleted successfully.");
            }
            myStatement.close();
        } catch (SQLException e) {
            e.getMessage();
        }
        super.getStock().remove(productIndex);
    }


    @Override
    public Ticket createTicket(int flowerShopId) {
        Statement myStatement = null;
        int sqlTicketId =  -1;
        try {
            myStatement = con.createStatement();
            String addTicketQuery = "INSERT INTO Ticket (ticketValue) VALUES\n" +
                    "(0.0);";
            String askLastInsert = "SELECT last_insert_id();";
            int rows = myStatement.executeUpdate(addTicketQuery);
            ResultSet myResultSet = myStatement.executeQuery(askLastInsert);
            while (myResultSet.next()) {
                sqlTicketId = myResultSet.getInt("last_insert_id()");
            }
            System.out.println("Ticket created successfully.");
            myResultSet.close();
            myStatement.close();
        } catch (SQLException e) {
            e.getMessage();
        }
        return new SqlTicket(flowerShopId, sqlTicketId);
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
            int rows = myStatement.executeUpdate(updateTicketValueQuery);
            if (rows > 0) {
                System.out.println("Ticket value updated.");
            }
            myStatement.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
    @Override
    public void removeTicket(Ticket ticket) {
        int ticketSqlId = ((SqlTicket)super.getTickets().get(super.getTickets().indexOf(ticket))).getTicketSqlId();
        Statement myStatement = null;
        String removeTicketQuery = "DELETE FROM Ticket WHERE ticketId = " + ticketSqlId;
        try {
            myStatement = con.createStatement();
            int rows = myStatement.executeUpdate(removeTicketQuery);
            if (rows > 0) {
                System.out.println("Ticket removed correctly.");
            }
            myStatement.close();
        } catch (SQLException e) {
            e.getMessage();
        }
        super.getTickets().remove(super.getTickets().indexOf(ticket));
    }

    @Override
    public String printStockWithQuantities() {
        int stockTrees = 0, stockFlowers = 0, stockDecoration = 0;
        String selectTreeStockQuery = "SELECT SUM(stock) AS 'TREE' FROM Product WHERE productType = 'TREE'";
        String selectFlowerStockQuery = "SELECT SUM(stock) AS 'FLOWER' FROM Product WHERE productType = 'FLOWER'";
        String selectDecorationStockQuery = "SELECT SUM(stock) AS 'DECORATION' FROM Product WHERE productType = 'DECORATION'";
        try {
            Statement myStatement = con.createStatement();
            ResultSet myResultSet = myStatement.executeQuery(selectTreeStockQuery);
            while (myResultSet.next()) {
                stockTrees = myResultSet.getInt("TREE");
                stockFlowers = myResultSet.getInt("FLOWER");
                stockDecoration = myResultSet.getInt("DECORATION");
            }
            myResultSet.close();
            myStatement.close();
        } catch (Exception e) {
            e.getMessage();
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
        String selectTotalValueQuery = "SELECT SUM(stock * price) AS 'totalValue' FROM Product";
        try {
            Statement myStatement = con.createStatement();
            ResultSet myResultSet = myStatement.executeQuery(selectTotalValueQuery);
            while (myResultSet.next()) {
                totalValue = myResultSet.getDouble("totalValue");
            }
            myResultSet.close();
            myStatement.close();
        } catch (Exception e) {
            e.getMessage();
        }
        return totalValue;
    }

    @Override
    public void showOldSales(String shopName) {
        for (Ticket ticket : tickets) {
            SqlTicket sqlTicket = (SqlTicket) ticket;
            System.out.println("____________________________\n" +
                    shopName + "\n" + sqlTicket.showHeader() +
                    ticket.showLines() +
                    "\n____________________________\n" );
        }
    }

    @Override
    public double calculateTotalSalesValue() {
        double totalSalesValue = 0d;
        String selectTotalSalesValueQuery = "SELECT SUM(ticketValue) AS 'totalSalesValue' FROM Ticket";
        try {
            Statement myStatement = con.createStatement();
            ResultSet myResultSet = myStatement.executeQuery(selectTotalSalesValueQuery);
            while (myResultSet.next()) {
                totalSalesValue = myResultSet.getDouble("totalSalesValue");
            }
            myResultSet.close();
            myStatement.close();
        } catch (Exception e) {
            e.getMessage();
        }
        return totalSalesValue;
    }
}
