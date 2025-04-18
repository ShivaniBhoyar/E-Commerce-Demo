package com.velocity;

import java.util.Scanner;

public class DisplayMessages {
    
    public static void getData() {
        
        System.out.println("******** Welcome to E-Commerce based application*******");
        System.out.println(" 1. User Operation");
        System.out.println(" 2. Admin Operation");
        System.out.println(" 3. Guest Operation");
        System.out.print("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
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
                // call registration method
                break;

            case 2:
                System.out.println("User Login selected");
                // call login method
                break;

            case 3:
                System.out.println("View Sorted Product List");
                // call sort + display product method
                break;

            case 4:
                System.out.println("Buy Product");
                // add product to cart
                break;

            case 5:
                System.out.println("View Cart");
                // display cart contents
                break;

            case 6:
                System.out.println("Purchase Items");
                // finalize order
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
            System.out.println("5. Check registered user");
            System.out.println("6. Check the particular user history");
            System.out.println("11. Check registered user");  // New option
            System.out.println("12. Check the particular user history"); // New option
            System.out.print("Enter your Admin Operation choice: ");
            int subchoice2 = scanner.nextInt();
            switch (subchoice2) {
            case 1:
                System.out.println("Add product item selected");
                // call Add product item method
                break;

            case 2:
                System.out.println("Calculate Bill selected");
                // call Calculate Bill method
                break;

            case 3:
                System.out.println("Display amount to End User selected ");
                // call Display amount to End User method
                break;

            case 4:
                System.out.println("Check Quantity selected");
                // call Check Quantity method
                break;

            case 5:
                System.out.println("Check registered user selected");
                // Check registered user method call
                break;

            case 6:
                System.out.println("Check the particular user history selected");
                // call Check the particular user history method
                break;

            case 11:  // New option logic
                System.out.println("Check registered user selected");
                // call Check registered user method
                break;

            case 12:  // New option logic
                System.out.println("Check the particular user history selected");
                // call Check the particular user history method
                break;

            default:
                System.out.println("Invalid Admin Operation choice");
                
            }
            break;
        case 3:
            System.out.println("Guest Operation Selected");
            System.out.println("1. View product item");
            System.out.println("2. Not purchase item");
            System.out.println("13. View product item");  // New option
            System.out.println("14. Not purchase item");  // New option
            System.out.print("Enter your Guest Operation choice: ");
            int subchoice3 = scanner.nextInt();
            switch (subchoice3) {
            case 1:
                System.out.println("View product item selected ");
                // call View product item method
                break;

            case 2:
                System.out.println("Not purchase item selected");
                // call Not purchase item method
                break;

            case 13:  // New option logic
                System.out.println("View product item selected ");
                // call View product item method
                break;

            case 14:  // New option logic
                System.out.println("Not purchase item selected");
                // call Not purchase item method
                break;

            default:
                System.out.println("Invalid Guest Operation choice");
            }
            break;
        default:
            System.out.println("Invalid Main Operation choice");
        }

    }
}
