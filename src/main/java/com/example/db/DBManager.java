package com.example.db;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONArray;

import com.example.entity.User;

public class DBManager {
	
	private static ArrayList<User> out;

	public void setStr(String str) throws SQLException, URISyntaxException {
		out = selectQuery(str);
	}
	public ArrayList<User> getStr() {
		return out;
	}

	private JSONArray json;
//
//	public void setStr(String str) throws IOException {
//		this.json = readJsonFromUrl(str);
//	}

	public JSONArray getJson() {
		return json;
	}

	public JSONArray getDBAdd() {
		return json;
	}

	public JSONArray getDBDelete() {
		return json;
	}

//	public static Connection getConnection() throws URISyntaxException, SQLException {
//		String dbUrl = System.getenv("JDBC_DATABASE_URL");
//		return DriverManager.getConnection(dbUrl);
//	}

	public static void updateQuery(Connection c, String s) throws SQLException {
		Statement stmt = c.createStatement();
		stmt.executeUpdate(s);
	}

	public static ArrayList<User> selectQuery(String query) throws SQLException, URISyntaxException {
		out = new ArrayList<User>();
		Connection con = MySqlConnection.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			User usr = new User();
			usr.setId(rs.getInt("id"));
			usr.setName(rs.getString("name"));
			usr.setProfession(rs.getString("profession"));
			out.add(usr);
		}
		return out;
	}
	public static boolean addUser(String name, String profession) throws URISyntaxException, SQLException {

			Connection connection = MySqlConnection.getConnection();
			Statement statement = connection.createStatement();
			String query = "INSERT INTO ppl(name, profession) VALUES('" + name + "', '" + profession + "');";

			if (statement.executeUpdate(query) != 0) {
				return true;
			}
		return false;
	}

}
