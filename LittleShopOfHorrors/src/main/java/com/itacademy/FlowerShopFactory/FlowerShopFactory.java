package com.itacademy.FlowerShopFactory;

import com.itacademy.Products.Decorations.Decoration;
import com.itacademy.Products.Flowers.Flower;
import com.itacademy.Products.Product;
import com.itacademy.Products.Trees.Tree;
import com.itacademy.Tickets.Ticket;

public interface FlowerShopFactory {


    Product createProduct(String name, double price, int stock);

    Tree createTree(String name, double price, int stock, int heightCm);

    Flower createFlower(String name, double price, int stock, String colour);

    Decoration createDecoration(String name, double price, int stock, String material);

    Ticket createTicket();

    public abstract void removeProduct(int productIndex);

}
