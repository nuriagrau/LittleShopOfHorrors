package com.itacademy.FlowerShopFactory;

import com.itacademy.Persistance.Sql.DatabaseConnection;
import com.itacademy.Products.Decorations.Decoration;
import com.itacademy.Products.Flowers.Flower;
import com.itacademy.Products.Product;
import com.itacademy.Products.Trees.Tree;
import com.itacademy.Tickets.Ticket;


import java.sql.*;

public class SqlFlowerShop extends LittleShopOfHorrors implements FlowerShopFactory {

    public SqlFlowerShop(String name) {
        super(name);
    }

    static Connection con = DatabaseConnection.getConnection();


    public static void generalQuery(String sqlQuery) throws SQLException {
        Statement myStatement = con.createStatement();
        String sql = sqlQuery;
        ResultSet myResultSet = myStatement.executeQuery(sql);
        //System.out.println("List Product");
        //System.out.println("---------------------------");
        while (myResultSet.next()) {
            //String name = myResultSet.getString("name");
            //String price = myResultSet.getDouble("price");
            //String stock = myResultSet.getInt("stock");
            //System.out.println(name + " - " + price + " " + stock);
        }

        myResultSet.close();
    }



    @Override
    public Product createProduct(String name, double price, int stock) {
        return null;
    }

    @Override
    public Tree createTree(String name, double price, int stock, int heightCm) {
        return null;
    }

    @Override
    public Flower createFlower(String name, double price, int stock, String colour) {
        return null;
    }

    @Override
    public Decoration createDecoration(String name, double price, int stock, String material) {
        return null;
    }

    @Override
    public Ticket createTicket() {
        return null;
    }

    @Override
    public void addProduct(Product product) {

    }

    @Override
    public void removeProduct(int productIndex) {

    }

    @Override
    public void addTicket(Ticket ticket) {

    }

    @Override
    public void removeTicket(Ticket ticket) {

    }

    @Override
    public String printStockWithQuantities() {
        return null;
    }

    @Override
    public double calculateTotalValue() {
        return 0;
    }

    @Override
    public void showOldSales(String shopName) {

    }

    @Override
    public double calculateTotalSalesValue() {
        return 0;
    }
}
