package com.itacademy.App;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.itacademy.FlowerShopFactory.JsonFlowerShop;
import com.itacademy.FlowerShopFactory.LSOH;
import com.itacademy.Products.Decorations.Decoration;
import com.itacademy.Products.Flowers.Flower;
import com.itacademy.Products.Material;
import com.itacademy.Products.Product;
import com.itacademy.Products.Trees.Tree;
import com.itacademy.Tickets.JsonTicket;
import com.itacademy.Tickets.Ticket;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Application {

    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<String> jsonFlowerShops = new ArrayList<String>();

    {
        // serialize and deserialize flowershops or load existent json flower shops
    }


    //static FlowerShopFactory activeFlowerShop = null;
    public static LSOH activeLSOH = null;
    public static String jsonDirPath;

    public static void startShow() throws JsonProcessingException {
        int level, option, productType;
        int flowerShop, flowerShopIndex = -1, productId = 0, quantity;
        String message, enterMessage = null, flowerShopName = null;
        double ticketValue,totalSalesValue;
        do {
            level = inputInt("""
                    \n
                    Select exercise level:
                    0. Exit
                    1. Level 1 (Json)
                    2. Level 2 (Sql)
                    3. Level 3 (Mongo DB)
                    """);

            switch (level) {
                case 0:
                    enterMessage = "You are leaving Little Shop of Horrors App...";
                    break;
                case 1:
                    flowerShop = inputInt(""" 
                            Welcome to Little Shop of Horrors! Choose an option: 
                            1. Create new flower shop
                            2. Enter to an existent flower shop NOT FUCTIONAL YET!
                            """);
                    if (flowerShop == 1) {
                        flowerShopName = inputString("Enter the name for the new flower Shop: ");
                        activeLSOH = new JsonFlowerShop(flowerShopName);
                        // Create dir for flowershop
                        // add flowershopName to flowershops arraylist
                        jsonFlowerShops.add(flowerShopName);
                        jsonDirPath = obtainJsonDirPath(flowerShopName);
                        // create a methos for the following
                        createShopDir(jsonDirPath);
                        enterMessage = "Creating the new " + flowerShopName + " flower shop...";
                    } else {
                           /* flowerShopName = inputString("There are the following flower Shops created for this level: \n"
                                    + showExistentFlowerShops(jsonFlowerShops) +
                                    "Enter the name of the one you want ot work with: ");
                            jsonDirPath = obtainJsonDirPath(flowerShopName);

                        enterMessage = "Entering to " + flowerShopName + " flower shop...";*/
                    }
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
            System.out.println(enterMessage);
        } while ((level != 0) && (level > 3));
        if (level != 0) {
            do {
                String name = "", colour, material;
                double price;
                int heightCm, stock, productIndex, saveFlowerShop;
                boolean found;

                option = inputInt("""
                        \n
                        Select an action:
                        0. Exit
                        1. Add Products
                        2. Remove Products
                        3. Print All Existent Products
                        4. Print Stock quantities
                        5. Print Stock Value
                        6. Create Purchase Ticket
                        7. Show old purchases list
                        8. Show total sales value                 
                        """);

                switch (option) {
                    case 0:
                        saveFlowerShop = inputInt("""
                                1. Save active flowershop.
                                2. Leave without saving active flowershop.
                                """);
                        switch(saveFlowerShop) {
                            case 1:
                                jsonDirPath = createShopDir(obtainJsonDirPath(flowerShopName));
                                JsonFlowerShop.serializedJsonFlowerShop((JsonFlowerShop) activeLSOH);
                                break;
                            case 2:
                                System.out.println("Active flower shop will be missed....");
                                break;
                        }
                        message = "You are leaving " + flowerShopName + "...";
                        break;
                    case 1:
                        productType = inputInt("""
                                Enter the product type to add:
                                1. Tree
                                2. Flower
                                3. Decoration
                                """);

                        switch (productType) {
                            case 1:
                                name = inputString("Enter the tree name: ");
                                heightCm = inputInt("Enter its height in cm: ");
                                price = inputDouble("Enter its price: ");
                                stock = inputInt("Enter its stock: ");
                                Tree newTree = activeLSOH.createTree(name, price, stock, heightCm);
                                activeLSOH.addProduct(newTree);
                                break;
                            case 2:
                                name = inputString("Enter the flower name: ");
                                colour = inputString("Enter its colour: ");
                                price = inputDouble("Enter its price: ");
                                stock = inputInt("Enter its stock: ");
                                Flower newFlower = activeLSOH.createFlower(name, price, stock, colour);
                                activeLSOH.addProduct(newFlower);
                                break;
                            case 3:
                                name = inputString("Enter decoration name: ");
                                do {
                                    material = inputString("Enter its material (Wood or Plastic): ");
                                    found = Material.findByValue(material);
                                } while (!found);
                                price = inputDouble("Enter its price: ");
                                stock = inputInt("Enter its stock: ");
                                Decoration newDecoration = activeLSOH.createDecoration(name, price, stock, material);
                                activeLSOH.addProduct(newDecoration);
                                break;
                        }
                        activeLSOH.setStockValue(activeLSOH.calculateTotalValue());
                        break;
                    case 2:
                        productType = inputInt("""
                                Enter the product type to remove:
                                1. Tree
                                2. Flower
                                3. Decoration
                                """);
                        switch (productType) {
                            case 1:
                                name = inputString(activeLSOH.showProductsByType(productType) + "\nEnter the name of the tree to remove: ");
                                break;
                            case 2:
                                name = inputString(activeLSOH.showProductsByType(productType) + "\nEnter the name of the flower to remove: ");
                                break;
                            case 3:
                                name = inputString(activeLSOH.showProductsByType(productType) + "\nEnter the name of the decoration to remove: ");
                                break;
                        }
                        productIndex = activeLSOH.getProductIndex(name);
                        activeLSOH.removeProduct(productIndex);
                        activeLSOH.setStockValue(activeLSOH.calculateTotalValue());
                        break;
                    case 3: // Print All Existent Products // Make specific method with stocks?
                        System.out.println("___________________________ TREES ___________________________\n" +
                                activeLSOH.showProductsByType(1));
                        System.out.println("__________________________ FLOWERS __________________________\n" +
                                activeLSOH.showProductsByType(2));
                        System.out.println("_________________________ DECORATION _________________________\n" +
                                activeLSOH.showProductsByType(3));
                        break;
                    case 4: // Print Stock quantities
                        System.out.println(activeLSOH.printStockWithQuantities());
                        break;
                    case 5: // Print Stock Value
                        activeLSOH.setStockValue(activeLSOH.calculateTotalValue());
                        System.out.println(activeLSOH.toString());
                        break;
                    case 6: // Create Purchase Ticket
                        Ticket newTicket = activeLSOH.createTicket();

                        do {
                            productType = inputInt("""
                                    Enter the product type you want to buy or exit:
                                    0. Exit
                                    1. Tree
                                    2. Flower
                                    3. Decoration
                                    """);
                            switch (productType) {
                                case 0:
                                    break;
                                case 1:
                                    productId = inputInt(activeLSOH.showProductsByType(1) + "\nEnter the tree id: ");
                                    break;
                                case 2:
                                    productId = inputInt(activeLSOH.showProductsByType(2) + "\nEnter the flower id: ");
                                    break;
                                case 3:
                                    productId = inputInt(activeLSOH.showProductsByType(3) + "\nEnter the decoration id: ");
                                    break;
                            }
                            if (productType != 0) {
                                productIndex = activeLSOH.getProductIndexById(productId);
                                if (productIndex != -1) {
                                    Product productToAdd = activeLSOH.getProduct(productIndex);
                                    do {
                                        quantity = inputInt("Enter quantity: ");
                                        stock = activeLSOH.getStock().get(productIndex).getStock();
                                        if (quantity <= stock) {
                                            newTicket.addTicketLine(productToAdd, quantity);
                                            activeLSOH.getStock().get(productIndex).setStock(stock - quantity);
                                            System.out.println("Product added to the ticket");
                                        } else {
                                            System.out.println("The product stock is " + stock + " enter an equal or lower quantity.");
                                        }
                                    } while (quantity > stock);
                                } else if (productIndex == -1) {
                                    System.out.println("There is no product with id " + productId);
                                }
                            }
                        } while (productType != 0 && productType <= 3);
                        activeLSOH.addTicket(newTicket);
                        ticketValue = newTicket.calculateTicketValue();
                        newTicket.setTicketValue(ticketValue);
                        System.out.println("_______________________________\n" +
                                activeLSOH.getName() + "     " + newTicket.showHeader() +
                                newTicket.showLines() +
                                "\n_______________________________\n");
                        activeLSOH.setStockValue(activeLSOH.calculateTotalValue());
                        activeLSOH.calculateTotalSalesValue(); // extract print
                        break;
                    case 7: // Show old purchases list
                        activeLSOH.getTickets().toString();
                        activeLSOH.showOldSales(activeLSOH.getName());
                        break;
                    case 8: // Show total sales value
                        totalSalesValue = activeLSOH.calculateTotalSalesValue();
                        System.out.println(activeLSOH.toString() + "\n" +
                                "___________________________________________________________________\n" +
                                "\nTOTAL SALES VALUE =" + totalSalesValue);
                }

                //activeLSOH.setStockValue(activeLSOH.calculateTotalValue()); Maybe here as it is nearly always a t each case?

            } while (option != 0);
        }
    }


    public static String obtainJsonDirPath(String flowerShopName) {
        return JsonTicket.JsonSerializable.filepath.toString() + flowerShopName.replace(" ", "");
    }

    public static String createShopDir(String jsonDirPath) {
        File directory = new File(jsonDirPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        return jsonDirPath;
    }

    public static int getJsonFlowerShopIndex(String name) {
        int jsonFlowerShopIndex = -1;
        for (int i = 0; i < jsonFlowerShops.size(); i++) {
            if (jsonFlowerShops.get(i).equalsIgnoreCase(name)) {
                jsonFlowerShopIndex = i;
            }
        }
        return jsonFlowerShopIndex;
    }


    public static String showExistentFlowerShops(ArrayList<String> flowerShopsArray) {
        String existentFlowerShopsList = "";
        for (String name : flowerShopsArray) {
            existentFlowerShopsList += name + "\n";
        }
        return existentFlowerShopsList;
    }

    public static int inputInt(String request) {
        int input = 0;
        boolean isValid = false;
        while (!isValid) {
            System.out.println(request);
            try {
                input = scanner.nextInt();
                isValid = true;
            } catch (InputMismatchException e) {
                System.err.println("Format Error. Please enter a valid integer.");
            } finally {
                scanner.nextLine();
            }
        }
        return input;
    }

    public static double inputDouble(String request) {
        double input = 0d;
        boolean isValid = false;
        while (!isValid) {
            System.out.println(request);
            try {
                input = scanner.nextDouble();
                isValid = true;
            } catch (InputMismatchException e) {
                System.err.println("Format Error. Please enter a valid double.");
            } finally {
                scanner.nextLine();
            }
        }
        return input;
    }

    public static String inputString(String missatge) {
        Boolean isValid = false;
        String userInput = "";
        do {
            try {
                System.out.println(missatge);
                userInput = scanner.nextLine();
                isValid = true;
            } catch (Exception e) {
                System.out.println("Format Error. Introduce a String.");
            }
        } while (!isValid || userInput.length() == 0);
        return userInput;

    }

    public static boolean inputBoolean(String request) {
        boolean input = false;
        boolean isValid = false;
        while (!isValid) {
            System.out.println(request);
            try {
                input = scanner.nextBoolean();
                isValid = true;
            } catch (InputMismatchException e) {
                System.err.println("Format Error. Please enter true or false.");
            } finally {
                scanner.nextLine();
            }
        }
        return input;
    }

}
