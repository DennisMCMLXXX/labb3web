package com.example.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.entity.User;

public class DBManager {

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
		Connection con = MySqlConnection.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
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
	
}
