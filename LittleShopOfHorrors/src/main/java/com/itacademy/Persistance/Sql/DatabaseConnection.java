package com.itacademy.Persistance.Sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {

    private static Connection con = null;

    static
    {
        String url = "jdbc:mysql://localhost:3306/thelittleshopofhorrorsdb";
        String user = "root";
        String pass = "admin2";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        }
        catch (ClassNotFoundException | SQLException e) {
            e.getMessage();
        }
    }
    public static Connection getConnection()
    {
        return con;
    }
}
