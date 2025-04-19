package com.velocity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Userlogin {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "system";
	private static final String PASS = "system";

	public static int getLoginData() {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter username: ");
		String usernameInput = sc.nextLine();

		System.out.print("Enter password: ");
		String passwordInput = sc.nextLine();

		int userId = -1; //
		try {
			//
			Class.forName(DRIVER);
			Connection con = DriverManager.getConnection(URL, USER, PASS);

			String query = "SELECT userid FROM users WHERE username = ? AND password = ?"; // ✅ CHANGE HERE
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, usernameInput);
			pstmt.setString(2, passwordInput);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				// Set userId from the result set
				userId = rs.getInt("userid"); // ADD THIS LINE
				System.out.println("Login successful! Welcome, " + usernameInput);
			} else {
				System.out.println("Invalid username or password. Try again.");
			}

			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userId;
	}
}
