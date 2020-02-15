package com.example.labb3db;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.db.DBManager;
import com.example.entity.User;

@RequestMapping("/dbdb")
@RestController
public class Test {
	Processor processor = new Processor();
	
	@GetMapping("/3")
	public ArrayList<User> getUserByidnumber2(Model model) throws SQLException, URISyntaxException {
		ArrayList<User> output = new ArrayList<User>();
		Connection conn = DBManager.getConnection();
		output = DBManager.selectQuery(conn, "SELECT * FROM ppl;");
		return output;
	}
}
