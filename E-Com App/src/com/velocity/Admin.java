package com.velocity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Admin {

	public static void productQant(int productId) {

		try {
			Connection con = DbConnection.DBConnection();

			PreparedStatement ps = con
					.prepareStatement("select product_name,product_quantity from products where product_id=?");
			ps.setInt(1, productId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String productName = rs.getString("product_name");
				int qty = rs.getInt("product_quantity");

				System.out.println("Product: " + productName);
				System.out.println("Available Quantity: " + qty);
			} else {
				System.out.println("Product not found with ID: " + productId);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void getData() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a product id ");
		int a = sc.nextInt();
		productQant(a);
	}

}
