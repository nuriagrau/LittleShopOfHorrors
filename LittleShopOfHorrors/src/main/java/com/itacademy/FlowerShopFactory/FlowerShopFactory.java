package com.itacademy.FlowerShopFactory;

import com.itacademy.Products.Decorations.Decoration;
import com.itacademy.Products.Flowers.Flower;
import com.itacademy.Products.Product;
import com.itacademy.Products.Trees.Tree;
import com.itacademy.Tickets.Ticket;

import java.sql.SQLException;

public interface FlowerShopFactory {


    Product createProduct(int flowerShopId, String name, double price, int stock);

    Tree createTree(int flowerShopId, String name, double price, int stock,  int heightCm);

    Flower createFlower(int flowerShopId, String name, double price, int stock, String colour);

    Decoration createDecoration(int flowerShopId, String name, double price, int stock, String material);

    Ticket createTicket(int flowerShopId);

    public abstract void removeProduct(int productIndex);

}
