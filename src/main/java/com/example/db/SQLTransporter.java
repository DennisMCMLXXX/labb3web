package com.example.db;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.entity.User;
import com.example.labb3db.Processor;

public class SQLTransporter {

	public boolean getUsers(final String dBName) throws SQLException, URISyntaxException {

		Statement statement = getSQLSetup(dBName);
		String sqlStatement = "SELECT * FROM ppl;";
		if (statement.execute(sqlStatement)) {
			return getUsersFromDB(statement);
		}

		return false;
	}

	public boolean getUsers(final String dBName, final int idnumber) throws SQLException, URISyntaxException {
		Statement statement = getSQLSetup(dBName);
		statement.executeUpdate("USE " + dBName + ";");
		String sqlStatement = "SELECT * FROM ppl WHERE id LIKE " + idnumber + ";";
		if (statement.execute(sqlStatement)) {
			return getUsersFromDB(statement);
		}

		return false;
	}



	private Statement getSQLSetup(String dBName) throws SQLException, URISyntaxException {
		Connection connection = MySqlConnection.getConnection();
		Statement statement = connection.createStatement();
		statement.executeUpdate("USE " + dBName + ";");
		return statement;
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
