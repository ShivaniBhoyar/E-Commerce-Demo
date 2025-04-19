package com.velocity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class DispalyAmountEndUser {

    // Method to display purchase history amount to the end user
    public static void displayAmountToEndUser(int userId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Load JDBC driver and connect to DB
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");

            // SQL query to fetch purchase history and calculate total
            String query = "SELECT u.username, p.product_name, ph.quantity, ph.price, " +
                           "(ph.quantity * ph.price) AS total_amount, ph.purchase_date " +
                           "FROM purchase_history ph " +
                           "JOIN users u ON ph.user_id = u.userid " +
                           "JOIN products p ON ph.product_id = p.product_id " +
                           "WHERE u.userid = ? " +
                           "ORDER BY ph.purchase_date";

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();

            double totalAmount = 0;

            // Display header
            System.out.println("\nPurchase History for User ID: " + userId);
            System.out.println("Username | Product Name     | Quantity | Price   | Total Amount | Purchase Date");
            System.out.println("----------------------------------------------------------------------------------");

            while (rs.next()) {
                String username = rs.getString("username");
                String productName = rs.getString("product_name");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                double total = rs.getDouble("total_amount");
                java.sql.Date date = rs.getDate("purchase_date");

                totalAmount += total;

                System.out.printf("%-9s %-18s %-9d %-8.2f %-13.2f %s\n",
                        username, productName, quantity, price, total, date.toString());
            }

            // Display final total
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println("Total Amount Paid: ₹" + totalAmount);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Method to take user input and call the display method
    public static void displayAmount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------------------------------------------");
        System.out.print("Enter User ID to display purchase amount: ");
        int userId = sc.nextInt();

        displayAmountToEndUser(userId);
    }
}
