package com.velocity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Purchase_Item {


	    public static void purchaseItem() {
	        Scanner sc = new Scanner(System.in);
	        System.out.print("UserID>> ");
	        int userID = sc.nextInt();

	        double totalBill = 0;

	        String fetchCart = "SELECT C.PRODUCT_ID, C.QUANTITY, P.PURCHASE_PRICE " +
	                           "FROM CARTS C JOIN PRODUCTS P ON C.PRODUCT_ID = P.PRODUCT_ID " +
	                           "WHERE C.USERID = ?";
	        String insertPurchase = "INSERT INTO PURCHASE_HISTORY (USER_ID, PRODUCT_ID, QUANTITY, PRICE) " +
	                                "VALUES (?, ?, ?, ?)";
	        String deleteCart = "DELETE FROM CARTS WHERE USERID = ?";

	        try (Connection con = DbConnection.DBConnection();
	             PreparedStatement cartStmt = con.prepareStatement(fetchCart);
	             PreparedStatement insertStmt = con.prepareStatement(insertPurchase);
	             PreparedStatement deleteStmt = con.prepareStatement(deleteCart)) {

	            cartStmt.setInt(1, userID);
	            ResultSet rs = cartStmt.executeQuery();

	            while (rs.next()) {
	                int productId = rs.getInt("PRODUCT_ID");
	                int quantity = rs.getInt("QUANTITY");
	                double price = rs.getDouble("PURCHASE_PRICE");

	                double itemTotal = price * quantity;
	                totalBill += itemTotal;

	                // Insert into purchase history
	                insertStmt.setInt(1, userID);
	                insertStmt.setInt(2, productId);
	                insertStmt.setInt(3, quantity);
	                insertStmt.setDouble(4, price);
	                insertStmt.addBatch();
	            }

	            insertStmt.executeBatch(); // Save all purchase records
	            deleteStmt.setInt(1, userID);
	            deleteStmt.executeUpdate(); // Clear the cart

	            System.out.println("Total Bill Amount>> " + totalBill);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}