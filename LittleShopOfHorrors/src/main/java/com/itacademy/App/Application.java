package com.itacademy.App;

import com.itacademy.FlowerShopFactory.JsonFlowerShop;
import com.itacademy.FlowerShopFactory.LittleShopOfHorrors;
import com.itacademy.FlowerShopFactory.SqlFlowerShop;
import com.itacademy.Products.Decorations.Decoration;
import com.itacademy.Products.Decorations.SqlDecoration;
import com.itacademy.Products.Flowers.Flower;
import com.itacademy.Products.Flowers.SqlFlower;
import com.itacademy.Products.Material;
import com.itacademy.Products.Product;
import com.itacademy.Products.ProductType;
import com.itacademy.Products.Trees.SqlTree;
import com.itacademy.Products.Trees.Tree;
import com.itacademy.Tickets.JsonTicket;
import com.itacademy.Tickets.SqlTicket;
import com.itacademy.Tickets.Ticket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import static com.itacademy.FlowerShopFactory.SqlFlowerShop.*;

public class Application {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<String> jsonFlowerShops = new ArrayList<String>();

    {
        // serialize and deserialize flowershops or load existent json flower shops
    }

    //static FlowerShopFactory activeFlowerShop = null;

    public static LittleShopOfHorrors activeLittleShopOfHorrors = null;
    public static String jsonDirPath;

    public static void startShow() throws Exception {
        int level, option, productType, jsonFlowerShopId = -1, sqlFlowerShopId = -1;
        int flowerShop = -1, flowerShopIndex = -1, productId = 0, quantity;
        int jsonFlowerShop = -1;
        String enterMessage = null, flowerShopName = null;
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
                    do {
                        jsonFlowerShop = inputInt(""" 
                                Welcome to Little Shop of Horrors! Choose an option: 
                                0. Exit
                                1. Create new flower shop
                                2. Enter to an existent flower shop NOT FUCTIONAL YET!
                                """);
                        switch (jsonFlowerShop) {
                            case 0:
                                enterMessage = "AudreyII says Aloha!!!";

                                break;
                            case 1:
                                flowerShopName = inputString("Enter the name for the new flower Shop: ");
                                activeLittleShopOfHorrors = new JsonFlowerShop(flowerShopName);
                                jsonFlowerShopId = activeLittleShopOfHorrors.getId();
                                jsonFlowerShops.add(flowerShopName);
                                jsonDirPath = obtainJsonDirPath(flowerShopName);
                                createShopDir(jsonDirPath);
                                enterMessage = "Creating the new " + flowerShopName + " flower shop...";
                                break;
                            case 2:
                            /* flowerShopName = inputString("There are the following flower Shops created for this level: \n"
                                    + showExistentFlowerShops(jsonFlowerShops) +
                                    "Enter the name of the one you want ot work with: ");
                            jsonDirPath = obtainJsonDirPath(flowerShopName);

                        enterMessage = "Entering to " + flowerShopName + " flower shop...";*/
                                break;
                        }
                    }  while(jsonFlowerShop !=0 && (jsonFlowerShop < 0 || jsonFlowerShop > 2));
                    break;
                case 2:
                    // Singleton to database
                    // Enter a blank FlowerShop with name BlankFlowerShop or DefaultFlowerShop
                    // 0 to exit (flowershopId Start with 100 if flowershopId == 0 exit else...
                    try {
                        sqlFlowerShopId = inputInt(SqlFlowerShop.showExistentFlowershops() + "Enter the Flowershop Id");
                        activeLittleShopOfHorrors = loadSqlFlowerShop(sqlFlowerShopId);
                        activeLittleShopOfHorrors.setStock(loadSqlProducts(sqlFlowerShopId));
                        activeLittleShopOfHorrors.setTickets(loadSqlTickets(sqlFlowerShopId));
                        for (int i = 0; i < activeLittleShopOfHorrors.getTickets().size() - 1; i++) {
                            SqlTicket sqlTicket = (SqlTicket) activeLittleShopOfHorrors.getTickets().get(i);
                            for (Map.Entry<Integer, Integer> entry : sqlTicket.getSqlTicketLines().entrySet()) {
                                int productIndex = activeLittleShopOfHorrors.getProductIndexBySqlId(entry.getKey());
                                Product productToAdd = activeLittleShopOfHorrors.getStock().get(productIndex);
                                activeLittleShopOfHorrors.getTickets().get(i).addTicketLine(productToAdd, entry.getValue());
                            }
                        }
                        System.out.println("Entering to " + activeLittleShopOfHorrors.getName() + " flower shop...");
                    } catch (NullPointerException e) {
                        System.out.println("Something went wrong when loading the database...");
                    }
                    break;
                case 3:
                    break;
            }
            System.out.println(enterMessage);
            if (level != 0 && jsonFlowerShop != 0) {
                do {
                    String name = "", colour, material;
                    double price;
                    int heightCm, stock, productIndex, saveFlowerShop, flowerShopId;
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
                            do {
                                saveFlowerShop = inputInt("""
                                    0. Exit
                                    1. Save active flowershop.
                                    2. Leave without saving active flowershop.
                                    """);
                                switch (saveFlowerShop) {
                                    case 0:
                                        break;
                                    case 1:
                                        jsonDirPath = createShopDir(obtainJsonDirPath(flowerShopName));
                                        JsonFlowerShop.serializedJsonFlowerShop((JsonFlowerShop) activeLittleShopOfHorrors);
                                        break;
                                    case 2:
                                        System.out.println("Active flower shop will be missed....");
                                        break;
                                }
                            } while(saveFlowerShop !=0 && (saveFlowerShop < 0 || saveFlowerShop > 2));
                            System.out.println("You are leaving " + flowerShopName + "...");
                            break;
                        case 1:
                            flowerShopId = activeLittleShopOfHorrors.getId();
                            do {
                                productType = 0;
                                productType = inputInt("""
                                    Enter the product type to add:
                                    0. Exit
                                    1. Tree
                                    2. Flower
                                    3. Decoration
                                    """);

                                switch (productType) {
                                    case 0:
                                        break;
                                    case 1:
                                        name = inputString("Enter the tree name: ");
                                        heightCm = inputInt("Enter its height in cm: ");
                                        price = inputDouble("Enter its price: ");
                                        stock = inputInt("Enter its stock: ");
                                        Tree newTree = activeLittleShopOfHorrors.createTree(flowerShopId, name, price, stock, heightCm);
                                        activeLittleShopOfHorrors.addProduct(newTree);
                                        break;
                                    case 2:
                                        name = inputString("Enter the flower name: ");
                                        colour = inputString("Enter its colour: ");
                                        price = inputDouble("Enter its price: ");
                                        stock = inputInt("Enter its stock: ");
                                        Flower newFlower = activeLittleShopOfHorrors.createFlower(flowerShopId, name, price, stock, colour);
                                        activeLittleShopOfHorrors.addProduct(newFlower);
                                        break;
                                    case 3:
                                        name = inputString("Enter decoration name: ");
                                        do {
                                            material = inputString("Enter its material (Wood or Plastic): ");
                                            found = Material.findByValue(material);
                                        } while (!found);
                                        price = inputDouble("Enter its price: ");
                                        stock = inputInt("Enter its stock: ");
                                        Decoration newDecoration = activeLittleShopOfHorrors.createDecoration(flowerShopId, name, price, stock, material);
                                        activeLittleShopOfHorrors.addProduct(newDecoration);
                                        break;
                                }
                                activeLittleShopOfHorrors.setStockValue(activeLittleShopOfHorrors.calculateTotalValue());
                            } while(productType !=0 && (productType < 0 || productType > 3));
                            break;
                        case 2:
                            int sqlId = -1;
                            String typeValue = null;
                            do {
                                productType = 0;
                                productType = inputInt("""
                                    Enter the product type to remove:
                                    0. Exit
                                    1. Tree
                                    2. Flower
                                    3. Decoration
                                    """);
                                switch (productType) {
                                    case 0:
                                        break;
                                    case 1:
                                        typeValue = ProductType.valueOfValue("Tree");
                                        break;
                                    case 2:
                                        typeValue = ProductType.valueOfValue("Flower");
                                        break;
                                    case 3:
                                        typeValue = ProductType.valueOfValue("Decoration");
                                        break;
                                }
                            }  while(productType != 0 && (productType < 0 || productType > 3));
                            name = inputString(activeLittleShopOfHorrors.showProductsByType(productType) + "\nEnter the name of the" + typeValue + " to remove: ");
                            productIndex = activeLittleShopOfHorrors.getProductIndex(name);
                            if (productIndex != -1) {
                                activeLittleShopOfHorrors.removeProduct(productIndex);
                            } else {
                                System.out.println("This product does not exist.");
                            }
                            activeLittleShopOfHorrors.setStockValue(activeLittleShopOfHorrors.calculateTotalValue());
                            break;
                        case 3: // Print All Existent Products // Make specific method with stocks?
                            System.out.println("LittleShopOfHorrors" + activeLittleShopOfHorrors.getName() + "\n" +
                                    "\n_____________________________________________________________\n" +
                                    "                                 " + Instant.now()  +
                                    "\n_____________________________________________________________\n");
                            System.out.println("___________________________ TREES ___________________________\n" +
                                    activeLittleShopOfHorrors.showProductsByType(1));
                            System.out.println("__________________________ FLOWERS __________________________\n" +
                                    activeLittleShopOfHorrors.showProductsByType(2));
                            System.out.println("_________________________ DECORATION _________________________\n" +
                                    activeLittleShopOfHorrors.showProductsByType(3));
                            break;
                        case 4: // Print Stock quantities
                            System.out.println(activeLittleShopOfHorrors.printStockWithQuantities());
                            break;
                        case 5: // Print Stock Value
                            activeLittleShopOfHorrors.setStockValue(activeLittleShopOfHorrors.calculateTotalValue());
                            System.out.println(activeLittleShopOfHorrors.toString());
                            break;
                        case 6: // Create Purchase Ticket
                            int ticketProductType;
                            String ticketHeader = "";
                            int activeFlowershopId = -1;
                            if (activeLittleShopOfHorrors instanceof JsonFlowerShop) {
                                activeFlowershopId = activeLittleShopOfHorrors.getId();
                            } else if (activeLittleShopOfHorrors instanceof SqlFlowerShop) {
                                activeFlowershopId = ((SqlFlowerShop) activeLittleShopOfHorrors).getSqlFlowerShopId();
                            }
                            Ticket newTicket = activeLittleShopOfHorrors.createTicket(activeFlowershopId);
                                do {
                                    ticketProductType = inputInt("""
                                            Enter the product type you want to buy or exit:
                                            0. Exit
                                            1. Tree
                                            2. Flower
                                            3. Decoration
                                            """);
                                    if (ticketProductType != 0) {
                                        switch (ticketProductType) {
                                            case 1:
                                                productId = inputInt(activeLittleShopOfHorrors.showProductsByType(1) + "\nEnter the tree id: ");
                                                break;
                                            case 2:
                                                productId = inputInt(activeLittleShopOfHorrors.showProductsByType(2) + "\nEnter the flower id: ");
                                                break;
                                            case 3:
                                                productId = inputInt(activeLittleShopOfHorrors.showProductsByType(3) + "\nEnter the decoration id: ");
                                                break;
                                        }
                                        productIndex = activeLittleShopOfHorrors.getProductIndexById(productId);
                                        if (productIndex != -1) {
                                            Product productToAdd = activeLittleShopOfHorrors.getProduct(productIndex);
                                            quantity = inputInt("Enter quantity: ");
                                            stock = activeLittleShopOfHorrors.getStock().get(productIndex).getStock();
                                            if (quantity <= stock) {
                                                if (activeLittleShopOfHorrors instanceof SqlFlowerShop) {
                                                    ((SqlFlowerShop) activeLittleShopOfHorrors).addSqlTicketLine(((SqlFlowerShop) activeLittleShopOfHorrors).getSqlFlowerShopId(), ((SqlTicket) newTicket).getTicketSqlId(), productToAdd, quantity);
                                                    ((SqlFlowerShop) activeLittleShopOfHorrors).updateStockAfterTicketLine(productToAdd, quantity);
                                                }
                                                newTicket.addTicketLine(productToAdd, quantity);
                                                activeLittleShopOfHorrors.getStock().get(productIndex).setStock(stock - quantity);
                                                System.out.println("Product added to the ticket");
                                            } else {
                                                System.out.println("The product stock is " + stock + " enter an equal or lower quantity.");
                                            }
                                        } else {
                                            System.out.println("There is no product with id " + productId);
                                        }
                                    }
                                } while(ticketProductType !=0);
                                activeLittleShopOfHorrors.addTicket(newTicket);
                                ticketValue = newTicket.calculateTicketValue();
                                newTicket.setTicketValue(ticketValue);
                                if (activeLittleShopOfHorrors instanceof SqlFlowerShop) {
                                    ((SqlFlowerShop) activeLittleShopOfHorrors).updateTicketValue(newTicket, ticketValue);
                                }
                                // do abstract method showTicket() in ticket, override in json & sql?
                                if (activeLittleShopOfHorrors instanceof JsonFlowerShop) {
                                    ticketHeader = newTicket.showHeader();
                                } else if (activeLittleShopOfHorrors instanceof SqlFlowerShop) {
                                    ticketHeader = ((SqlTicket) newTicket).showHeader();
                                }
                                if (ticketValue != 0) {
                                    System.out.println("__________________________________\n" +
                                            "LittleShopOfHorrors     " + activeLittleShopOfHorrors.getName() + "\n"
                                            + ticketHeader +
                                            newTicket.showLines() +
                                            "\n__________________________________\n");

                                    activeLittleShopOfHorrors.setStockValue(activeLittleShopOfHorrors.calculateTotalValue());
                                    activeLittleShopOfHorrors.calculateTotalSalesValue();
                                }
                            break;
                        case 7: // Show old purchases list
                            activeLittleShopOfHorrors.showOldSales(activeLittleShopOfHorrors.getName());
                            break;
                        case 8: // Show total sales value
                            totalSalesValue = activeLittleShopOfHorrors.calculateTotalSalesValue();
                            System.out.println("\n______________________________________\n" +
                                    "LittleShopOfHorrors      " + activeLittleShopOfHorrors.getName() +
                                    "\n______________________________________\n"
                                             + Instant.now() + "\n" +
                                    "\nTOTAL SALES VALUE =     " + totalSalesValue +
                                    "\n______________________________________\n" );
                            break;
                    }
                } while (option != 0 && (option > 0 || option < 8));
            }
        } while ((level != 0) && ( level < 0 || level > 3));
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

    public static int getSqlId(int productIndex) {
        int sqlId = -1;
        if (activeLittleShopOfHorrors.getProduct(productIndex) instanceof Tree) {
            sqlId = ((SqlTree) activeLittleShopOfHorrors.getProduct(productIndex)).getSqlId();
        } else if (activeLittleShopOfHorrors.getProduct(productIndex) instanceof Flower) {
            sqlId = ((SqlFlower) activeLittleShopOfHorrors.getProduct(productIndex)).getSqlId();
        } else if (activeLittleShopOfHorrors.getProduct(productIndex) instanceof  Decoration){
            sqlId = ((SqlDecoration) activeLittleShopOfHorrors.getProduct(productIndex)).getSqlId();
        }
        return sqlId;
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

    public static String readToStringTXT(String filePath) throws Exception {
        File file = new File(filePath);
        String string;
        String txtFileToString = "";
        BufferedReader bReader = new BufferedReader(new FileReader(file));
        while ((string = bReader.readLine()) != null) {
            txtFileToString += string + "\n";
        }
        bReader.close();
        return txtFileToString;
    }

}
