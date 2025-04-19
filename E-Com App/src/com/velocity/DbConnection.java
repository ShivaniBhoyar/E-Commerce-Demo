package com.velocity;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	public static Connection DBConnection() {
		Connection con = null;
		try {
			// step- 1 loading a Driver Class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Step -2 Established Connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");
		} catch (Exception e) {
			System.out.println(e);
		}

		return con;
	}
}
