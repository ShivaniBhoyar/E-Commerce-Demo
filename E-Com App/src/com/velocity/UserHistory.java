package com.velocity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class UserHistory {

	public static void getUserCartHistory() {

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter User ID to view cart history: ");
		int userId = scanner.nextInt();

		try {
			Connection con = DbConnection.DBConnection();

			String query = "SELECT "
					+ "u.userid, u.first_name, u.last_name, u.username, u.city, u.email, u.mobile_number, "
					+ "p.product_name, p.purchase_price AS product_price, "
					+ "ph.quantity, (ph.quantity * p.purchase_price) AS total_price, ph.purchase_date "
					+ "FROM purchase_history ph " + "JOIN users u ON ph.user_id = u.userid "
					+ "JOIN products p ON ph.product_id = p.product_id " + "WHERE u.userid = ?";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();

			boolean found = false;

			while (rs.next()) {
				found = true;
				System.out.println("\n===== Purchase History =====");
				System.out.println("User ID       >> " + rs.getInt("userid"));
				System.out.println("Name          >> " + rs.getString("first_name") + " " + rs.getString("last_name"));
				System.out.println("Username      >> " + rs.getString("username"));
				System.out.println("City          >> " + rs.getString("city"));
				System.out.println("Email         >> " + rs.getString("email"));
				System.out.println("Mobile        >> " + rs.getString("mobile_number"));
				System.out.println("Product       >> " + rs.getString("product_name"));
				System.out.println("Unit Price    >> " + rs.getDouble("product_price"));
				System.out.println("Quantity      >> " + rs.getInt("quantity"));
				System.out.println("Total Price   >> " + rs.getDouble("total_price"));
				System.out.println("Purchase Date >> " + rs.getDate("purchase_date"));
				System.out.println("-----------------------------------------");
			}

			if (!found) {
				System.out.println("No cart history found for User ID: " + userId);
			}

			rs.close();
			ps.close();
			con.close();
			// scanner.close();

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

}