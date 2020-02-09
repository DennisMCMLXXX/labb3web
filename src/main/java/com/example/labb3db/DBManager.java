package com.example.labb3db;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

public class DBManager {
	
	private JSONArray json;
	
	public static Connection getConnection() throws URISyntaxException, SQLException {
		String dbUrl = System.getenv("JDBC_DATABASE_URL");
		return DriverManager.getConnection(dbUrl);
	}

	public static void updateQuery(Connection c, String s) throws SQLException {
		Statement stmt = c.createStatement();
		stmt.executeUpdate(s);
	}

//	public static String selectQuery(Connection c, String s) throws SQLException {
//		Statement stmt = c.createStatement();
//		ResultSet rs = stmt.executeQuery(s);
//		String out = "";
//		while (rs.next()) {
//			out += rs.getString("id");
//			out += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"; // 2*tab
//			out += rs.getString("name");
//			out += "<br>"; // radbyte
//			out += rs.getString("profession ");
//			out += "<br>"; // radbyte
//		}
//		return out;
//	}

	public static ArrayList<User> selectQuery(Connection c, String s) throws SQLException {
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(s);
		ArrayList<User> out = new ArrayList<User>();
		while (rs.next()) {
			User usr = new User();
			usr.setId(rs.getInt("id"));
			usr.setName(rs.getString("name"));
			usr.setProfession(rs.getString("profession"));
			out.add(usr);
		}
		return out;
	}
	public void setSearchStr(String searchStr) throws IOException {
		this.json = readJsonFromUrl(searchStr);
	}

	public JSONArray getSearchStr() {
		return json;
	}
	
	public JSONArray readJsonFromUrl(String searchtext) throws IOException, JSONException {
		return json;
	}
}
