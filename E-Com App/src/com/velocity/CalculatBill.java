package com.velocity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class CalculatBill {

    public static void calculateBill() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter User ID to calculate bill: ");
        int userIdInput = scanner.nextInt();

        try {
            // Load JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");

            // Updated query using purchase_history table
            String query = "SELECT u.userid, u.username, p.product_name, ph.quantity, ph.price, " +
                           "(ph.quantity * ph.price) AS total_amount, ph.purchase_date " +
                           "FROM purchase_history ph " +
                           "JOIN users u ON ph.user_id = u.userid " +
                           "JOIN products p ON ph.product_id = p.product_id " +
                           "WHERE u.userid = ? " +
                           "ORDER BY ph.purchase_date";

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, userIdInput);
            rs = pstmt.executeQuery();

            double grandTotal = 0;

            System.out.println("\nUserID | Username | Product Name     | Quantity | Price   | Total Amount | Purchase Date");
            System.out.println("-------------------------------------------------------------------------------------------");

            while (rs.next()) {
                int userId = rs.getInt("userid");
                String username = rs.getString("username");
                String productName = rs.getString("product_name");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                double total = rs.getDouble("total_amount");
                java.sql.Date date = rs.getDate("purchase_date");

                grandTotal += total;

                System.out.printf("%-7d %-10s %-18s %-9d %-8.2f %-13.2f %s\n",
                        userId, username, productName, quantity, price, total, date.toString());
            }

            System.out.println("-------------------------------------------------------------------------------------------");
            System.out.println("Grand Total for User ID " + userIdInput + ": " + grandTotal);

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
}
