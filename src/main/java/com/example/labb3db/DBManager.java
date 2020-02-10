package com.example.labb3db;

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

public class DBManager {

	private JSONArray json;

	public void setStr(String str) throws IOException {
		this.json = readJsonFromUrl(str);
	}

	public JSONArray getJson() {
		return json;
	}

	public void setDBAddName(String str) {
		this.json = dbAdd(str);
	}

	public void setDBAddProfession(String str) {
		this.json = dbAdd(str);
	}

	public JSONArray getDBAdd() {
		return json;
	}
	public void setDBDelete(String str) {
		this.json = dbDelete(str);
	}

	public JSONArray getDBDelete() {
		return json;
	}

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
	
	private JSONArray dbDelete(String str) {
		// TODO Auto-generated method stub
		return null;
	}

	private JSONArray dbAdd(String str) {
		// TODO Auto-generated method stub
		return null;
	}

	public JSONArray readJsonFromUrl(String searchtext) throws IOException, JSONException {
		String API_KEY = "02cef8f4068d207f198396e97a22f72b";
		String text = searchtext.replaceAll(" ", "+");

		URL url = new URL("https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY + "&query=" + text);

		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		try {
			con.setDoOutput(true);
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}

		InputStream is = url.openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);

			JSONArray arr = json.getJSONArray("results");

			/**
			 * här tar jag min jsonArray och hämtar tex "original_title" Jag kunde inte
			 * jobba vidare med detta tyvärr, se rapport.
			 */
//			 System.out.println(jsonArray.getJSONArray("original_title"));

			return arr;
		} finally {
			is.close();
		}
	}

	private String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
}
