package com.example.db;

import java.util.ArrayList;
import java.util.List;

import com.example.labb3db.User;

public class Processor {

	private static List<User> userList = null;

//	public static void createDatabase(String dBName) {
//		CreateDB createDB = new CreateDB();
//		createDB.createDataBase(dBName);
//	}

	public static void setUserList(List<User> users) {
		userList = new ArrayList<User>(users);
	}

	public static List<User> getUserList() {
		return userList;
	}
}
