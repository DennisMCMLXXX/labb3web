package com.example.labb3db;

import java.net.URISyntaxException;
import java.sql.*;

public class DBManager {
	public static Connection getConnection() throws URISyntaxException, SQLException {
		String dbUrl = System.getenv("JDBC_DATABASE_URL");
		return DriverManager.getConnection(dbUrl);
	}
}
