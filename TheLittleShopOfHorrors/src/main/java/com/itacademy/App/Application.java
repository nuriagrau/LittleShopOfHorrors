package com.itacademy.App;

import com.itacademy.FlowerShopFactory.FlowerShopFactory;
import com.itacademy.FlowerShopFactory.JsonFlowerShop;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {

    private static Scanner scanner = new Scanner(System.in);

    private static ArrayList<JsonFlowerShop> jsonFlowerShops = new ArrayList<JsonFlowerShop>();
    {
        // serialize and deserialize flowershops or load existent json flower shops
    }


    public static void startShow() {
        FlowerShopFactory activeFlowerShop = null;
        int level, option, productType;
        int flowerShop, flowerShopIndex = -1;
        String message, enterMessage = null, flowerShopName = null;
        do {
            level = inputInt("""
                    Select exercise level:
                    0. Exit
                    1. Level 1 (Json)
                    2. Level 2 (Sql)
                    3. Level 3 (Mongo DB)
                    """);

            switch(level) {
                case 0:
                    message = "You are leaving Little Shop of Horrors App...";
                    break;
                case 1:
                    flowerShop = inputInt(""" 
                            Welcome to Little Shop of Horrors! Choose an option: 
                            1. Create new flower shop
                            2. Enter to an existent flower shop
                            """);
                    if (flowerShop == 1) {
                        flowerShopName = inputString("Enter the name for the new flower Shop: ");
                        activeFlowerShop = new JsonFlowerShop(flowerShopName);
                        enterMessage = "Creating the new " + flowerShopName + "flower shop...";
                    } else {
                        do {
                            flowerShopName = inputString("There are the following flower Shops created for this level: \n"
                                    + showExistentFlowerShops(jsonFlowerShops) +
                                    "Enter the name of the one you want ot work with: ");
                            flowerShopIndex = getJsonFlowerShopIndex(flowerShopName);
                        } while (flowerShopIndex != -1);
                        activeFlowerShop = jsonFlowerShops.get(flowerShopIndex);
                        enterMessage = "Entering to " + flowerShopName + " flower shop...";
                    }
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
            System.out.println(enterMessage);
            do {
                String productName, colour, material;
                double price;
                int heightCm, quantity;

                option = inputInt("""
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
                        message = "You are leaving " + flowerShopName + "...";
                        break;
                    case 1:
                        productType = inputInt("""
                                Enter the product type to add:
                                1. Tree
                                2. Flower
                                3. Decoration
                                """);

                        switch(productType) {
                            case 1:
                                productName = inputString("Enter flower name: ");
                                price = inputDouble("Enter the price: ");
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                        }
                        break;
                    case 2:
                        productType = inputInt("""
                                Enter the product type to remove:
                                1. Tree
                                2. Flower
                                3. Decoration
                                """);
                        switch(productType) {
                            case 1:

                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                        }
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                }

            } while (option != 0);
        } while (level != 0);
    }





    public static int getJsonFlowerShopIndex(String name) {
        int jsonFlowerShopIndex = -1;
        for (int i = 0; i < jsonFlowerShops.size(); i++) {
            if (jsonFlowerShops.get(i).getName().equalsIgnoreCase(name)) {
                jsonFlowerShopIndex = i;
            }
        }
        return jsonFlowerShopIndex;
    }

    public static String showExistentFlowerShops(ArrayList<JsonFlowerShop> flowerShopsArray) {
        String existentFlowerShopsList = "";
        for (JsonFlowerShop jsfs : flowerShopsArray ) {
            existentFlowerShopsList += jsfs.getName() + "\n";
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
