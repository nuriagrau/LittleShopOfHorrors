package com.itacademy.Persistance.Sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {

    private static Connection con = null;

    static
    {
        //String url = "jdbc:mysql://localhost:3306/thelittleshopofhorrorsdb","root", "admin2";
       // String user = "root"; MySQL Workbench version 6.3.
       // String pass = "admin2";
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver'");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/littleshopofhorrorsdb","root", "admin2");
        }
        catch (SQLException e) {
            e.getMessage();
        }
    }

    public static Connection getConnection()
    {
        return con;
    }
}
