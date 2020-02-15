package com.example.labb3db;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.db.DBManager;
import com.example.db.SQLTransporter;
import com.example.entity.User;

@RequestMapping("/DB")
@RestController
public class DB_RESTService {
	SQLTransporter sqlHandler = new SQLTransporter();
	ProcessorOLD processorOLD = new ProcessorOLD();
	DBManager dbmanager = new DBManager();
	ArrayList<User> output;

	@GetMapping("/all")
	public ArrayList<User> getUserByidnumber2() throws SQLException, URISyntaxException {
		output = new ArrayList<User>();
		output = dbmanager.selectQuery("SELECT * FROM ppl;");
		return output;
	}

	@GetMapping("/user/name/{name}")
	ArrayList<User> DBDeletes(@PathVariable("name") String name) throws URISyntaxException, SQLException {
		output = new ArrayList<User>();
		output = dbmanager.selectQuery("SELECT * FROM ppl WHERE Name='" + name + "'");
		return output;
	}

	@PutMapping("user/add/{name}/{profession}")
	public String addUser(@PathVariable("name") String name, @PathVariable("profession") String profession)
			throws URISyntaxException, SQLException {
		return dbmanager.addUser(name, profession) ? "Success!!" : "Error!";
	}

	@GetMapping("/user/id/{id}")
	ArrayList<User> getUserByid(@PathVariable("id") int id) throws SQLException, URISyntaxException {
		output = new ArrayList<User>();
		output = dbmanager.selectQuery("SELECT * FROM ppl WHERE id='" + id + "'");
		return output;
	}

	@GetMapping("/users/i/{id}")
//	@Produces (MediaType.APPLICATION_JSON)
	List<User> getUserByidnumberorg(@PathVariable("id") int id) throws SQLException, URISyntaxException {
		if (processorOLD.getUsers(id)) {
			return ProcessorOLD.getUserList();
		}
		return null;
	}

	@GetMapping("/users/idd/{id}")
//	@Produces (MediaType.APPLICATION_JSON)
	JSONObject getUserByidnumber2(@PathVariable("id") int id) throws SQLException, URISyntaxException {
		if (processorOLD.getUsers(id)) {
			return ProcessorOLD.getUserList3();
		}
		return null;
	}
}