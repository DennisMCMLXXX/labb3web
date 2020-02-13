package com.example.labb3db;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.db.DBManager;
import com.example.entity.User;

public class Processor {
	ArrayList<User> output;

	public ArrayList<User> getUsers() {

		return output;
	}

	private static List<User> userList = null;
	private static User user2 = null;

	public static void setUserList(List<User> users) {
		userList = new ArrayList<User>(users);
	}

	public static User getUserList2() {
		return user2;
	}

	public static List<User> getUserList() {
		return userList;
	}

	public boolean getUsers(final int idnumber) throws SQLException, URISyntaxException {
		Connection connection = DBManager.getConnection();
		Statement statement = connection.createStatement();
		String sqlStatement = "SELECT * FROM ppl WHERE id LIKE " + idnumber + ";";
		if (statement.execute(sqlStatement)) {
			return getUsersFromDB(statement);
		}

		return false;
	}

	private Statement getSQLSetup(String dBName) throws SQLException, URISyntaxException {
		Connection connection = DBManager.getConnection();
		Statement statement = connection.createStatement();
		statement.executeUpdate("USE " + dBName + ";");
		return statement;
	}

	private boolean getUsersFromDB2(Statement statement) throws SQLException {
		List<User> userList = new ArrayList<User>();
		ResultSet resultSet;
		resultSet = statement.getResultSet();

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String proffesion = resultSet.getString("profession");
			User user = new User(id, name, proffesion);
			userList.add(user);
		user = user2;
		}
		
		Processor.setUserList(userList);
		return true;
	}

	private boolean getUsersFromDB(Statement statement) throws SQLException {
		List<User> userList = new ArrayList<User>();
		ResultSet resultSet;
		resultSet = statement.getResultSet();

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String proffesion = resultSet.getString("profession");
			User user = new User(id, name, proffesion);
			userList.add(user);
		}

		Processor.setUserList(userList);
		return true;
	}
}
