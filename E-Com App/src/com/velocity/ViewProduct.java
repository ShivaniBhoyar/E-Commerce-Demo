package com.velocity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewProduct {
	public static void getSortedProducts() {
	try {
		Connection con = DbConnection.DBConnection();
		System.out.println("Connection Done!");
		// PreparedStatament
		PreparedStatement ps = con.prepareStatement("SELECT * FROM products order by product_name");
		ResultSet rs = ps.executeQuery();
		System.out.println("Query is executed");
		boolean found = false;
		 while (rs.next()) {
			 found = true;
			    System.out.println("Inside loop");
		        System.out.println("Product Id       >> " + rs.getInt("product_id"));
		        System.out.println("Product Name     >> " + rs.getString("product_name"));
		        System.out.println("Description      >> " + rs.getString("product_description"));
		        System.out.println("Available Qty    >> " + rs.getInt("product_quantity"));
		        System.out.println("Purchase Price   >> " + rs.getDouble("purchase_price"));
		        System.out.println("-----------------------------------------");
		    }
		 if (!found) {
			    System.out.println("No products found in the table.");
		 }
		con.close();
		ps.close();
		rs.close();
	} catch (Exception e) {
		System.out.println(e);
	}
	}
}
