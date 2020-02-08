package com.example.labb3db;

import java.net.URISyntaxException;
import java.sql.*;

public class DBManager {

	public static Connection getConnection() throws URISyntaxException, SQLException {
		String dbUrl = System.getenv("JDBC_DATABASE_URL");
		return DriverManager.getConnection(dbUrl);
	}

	public static void updateQuery(Connection c, String s) throws SQLException {
		Statement stmt = c.createStatement();
		stmt.executeUpdate(s);
	}

	public static String selectQuery(Connection c, String s) throws SQLException {
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(s);
		String out = "";
		while (rs.next()) {
			out += rs.getString("id");
			out += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"; // 2*tab
			out += rs.getString("name");
			out += "<br>"; // radbyte
			out += rs.getString("profession ");
			out += "<br>"; // radbyte
		}
		return out;
	}

}
