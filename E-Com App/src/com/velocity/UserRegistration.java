package com.velocity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class UserRegistration {
	static String query = "insert into users  (first_name, last_name, username, password, city, email, mobile_number) values(?, ?, ?, ?, ?, ?, ?)";
	public static int userId = 0;

	public static void getConnectionInsertData(String first_name, String last_name, String username, String password,
			String city, String email, String mobile_number) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");
			PreparedStatement ps = con.prepareStatement(query, new String[] { "userid" });
			ps.setString(1, first_name);
			ps.setString(2, last_name);
			ps.setString(3, username);
			ps.setString(4, password);
			ps.setString(5, city);
			ps.setString(6, email);
			ps.setString(7, mobile_number);
			int rows = ps.executeUpdate();
			if (rows > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					userId = rs.getInt(1);
					System.out.println("User registered successfully! UserID: " + userId);
				}
			} else {
				System.out.println("User registration failed.");
			}
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void getUserData() {
		System.out.println("Enter the user Information");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the  first name");
		String Name = scanner.next();
		System.out.println("Enter the  last name");
		String Last_Name = scanner.next();
		System.out.println("Enter the Username and its must be unique");
		String UserName = scanner.next();
		System.out.println("Enter the Passoword");
		String passoword = scanner.next();
		System.out.println("Enter the user city");
		String city = scanner.next();
		System.out.println("Enter the user mail id");
		String mail = scanner.next();
		System.out.println("Enter the user mobile number");
		String mobile_number = scanner.next();
		getConnectionInsertData(Name, Last_Name, UserName, passoword, city, mail, mobile_number);
	}

}
