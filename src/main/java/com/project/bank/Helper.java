package com.project.bank;

import java.sql.Connection;
import java.sql.DriverManager;

public class Helper {
	public static Connection getConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "liku");
		
	}
}
