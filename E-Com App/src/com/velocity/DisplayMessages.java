package com.velocity;

import java.util.Scanner;

public class DisplayMessages {

    public static void getData1() {
        Scanner scanner = new Scanner(System.in); // Moved the scanner initialization outside of the loop

        do {
            System.out.println("******** Welcome to E-Commerce based application*******");
            System.out.println(" 1. User Operation");
            System.out.println(" 2. Admin Operation");
            System.out.println(" 3. Guest Operation");
            System.out.println(" 4. Exit");  // Added exit option to break the loop
            System.out.print("Enter your choice: ");
            int mainChoice = scanner.nextInt();

            switch (mainChoice) {

                case 1:
                    System.out.println("\n==== User Menu ====");
                    System.out.println("1. User Registration");
                    System.out.println("2. User Login");
                    System.out.println("3. View Product List (Sorted)");
                    System.out.println("4. Buy Product");
                    System.out.println("5. View Cart");
                    System.out.println("6. Purchase Items");
                    System.out.print("Enter your User Operation choice: ");
                    int subchoice1 = scanner.nextInt();
                    switch (subchoice1) {
                        case 1:
                            System.out.println("User Registration selected ");
                            UserRegistration.getUserData();
                            break;

                        case 2:
                            System.out.println("User Login selected");
                            Userlogin.getLoginData();
                            break;

                        case 3:
                            System.out.println("View Sorted Product List");
                            ViewProduct.getSortedProducts();
                            break;

                        case 4:
                            System.out.println("Buy Product");
                            Buy_Products.buyProduct();
                            break;

                        case 5:
                            System.out.println("View Cart");
                            View_Cart.view();
                            break;

                        case 6:
                            System.out.println("Purchase Items");
                            Purchase_Item.purchaseItem();
                            break;

                        default:
                            System.out.println("Invalid User Operation choice");
                    }
                    break;

                case 2:
                    System.out.println("Admin Operation Selected");
                    System.out.println("1. Add product item");
                    System.out.println("2. Calculate Bill");
                    System.out.println("3. Display amount to End User");
                    System.out.println("4. Check Quantity");
                    System.out.println("5. Check registered user ");
                    System.out.println("6. Check the particular user history ");
                    System.out.print("Enter your Admin Operation choice: ");
                    int subchoice2 = scanner.nextInt();
                    switch (subchoice2) {
                        case 1:
                            System.out.println("Add product item selected");
                            AddProduct.addProduct();
                            break;

                        case 2:
                            System.out.println("Calculate Bill selected");
                            CalculatBill.calculateBill();
                            break;

                        case 3:
                            System.out.println("Display amount to End User selected ");
                            DispalyAmountEndUser.displayAmount();
                            break;

                        case 4:
                            System.out.println("Check Quantity selected");
                            Admin.getData();
                            break;

                        case 5:
                            System.out.println("Check registered user selected");
                            CheckRegisteredUser.getRegisteredUsers();
                            break;

                        case 6:
                            System.out.println("Check the particular user history selected");
                            UserHistory.getUserCartHistory();
                            break;

                        default:
                            System.out.println("Invalid Admin Operation choice");
                    }
                    break;

                case 3:
                    System.out.println("1. View product item ");
                    System.out.println("2. Try to Purchase Product (Login Required)");
                    System.out.print("Enter your Guest Operation choice: ");
                    int subchoice3 = scanner.nextInt();
                    switch (subchoice3) {
                        case 1:
                            System.out.println("View product item selected ");
                            ViewProduct.getSortedProducts();
                            System.out.println("-----------------------------------------");
                            System.out.println("Guests are not allowed to purchase a product.");
                            break;

                        case 2:
                            System.out.println("-----------------------------------------");
                            System.out.println("Guests are not allowed to purchase products.");
                            System.out.println("Please register or login as a User first.");
                           
                            break;

                        default:
                            System.out.println("Invalid Guest Operation choice");
                    }
                    break;

                case 4:
                    System.out.println("Exiting the application. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid main menu choice. Please try again.");
            }
        } while (true);  // This loop will continue until the user selects option 4 to exit
    }
}
