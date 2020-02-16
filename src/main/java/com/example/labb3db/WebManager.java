package com.example.labb3db;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.db.DBManager;
import com.example.entity.User;

public class WebManager {

	DBManager dbmanager = new DBManager();
	DB_RESTService db_restservice = new DB_RESTService();
	
	public ArrayList<User> getStr() throws SQLException, URISyntaxException {
		
		return db_restservice.getAll();
	}


}
