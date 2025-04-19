package com.velocity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CheckRegisteredUser {
	public static void getRegisteredUsers() {
		try {
			Connection con = DbConnection.DBConnection();
			//System.out.println("Connection Done!");
			// PreparedStatament
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Users");
			ResultSet rs = ps.executeQuery();
			//System.out.println("Query is executed");
			boolean found = false;
			 while (rs.next()) {
				 found = true;
				   // System.out.println("Inside loop");
			        System.out.println("User Id       >> " + rs.getInt("userid"));
			        System.out.println("First Name     >> " + rs.getString("first_name"));
			        System.out.println("Last Name      >> " + rs.getString("last_name"));
			        System.out.println("Username      >> " + rs.getString("username"));
			        System.out.println("Password    >> " + rs.getInt("password"));
			        System.out.println("City      >> " + rs.getString("city"));
			        System.out.println("Email      >> " + rs.getString("email"));
			        System.out.println("MobileNumber      >> " + rs.getString("mobile_number"));

			        System.out.println("-----------------------------------------");
			    }
			 if (!found) {
				    System.out.println("No Users found in the table.");
			 }
			con.close();
			ps.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		}

}