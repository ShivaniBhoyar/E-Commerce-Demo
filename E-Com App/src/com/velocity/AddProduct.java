package com.velocity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class AddProduct {

    public static void addProduct() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Product Name: ");
        String productName = scanner.nextLine();

        System.out.print("Enter Product Description: ");
        String productDescription = scanner.nextLine();

        System.out.print("Enter Product Quantity: ");
        int quantity = scanner.nextInt();

        System.out.print("Enter Purchase Price: ");
        double purchasePrice = scanner.nextDouble();

        try {
            // Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Connect to the database
            Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "system", "system");

            // Insert query (without product_id)
            String query = "INSERT INTO products (product_name, product_description, product_quantity, purchase_price) " +
                           "VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, productName);
            ps.setString(2, productDescription);
            ps.setInt(3, quantity);
            ps.setDouble(4, purchasePrice);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println(" Product added successfully!");
            } else {
                System.out.println("❌ Failed to add product.");
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
