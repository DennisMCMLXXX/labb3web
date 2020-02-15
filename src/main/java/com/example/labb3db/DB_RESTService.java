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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.db.DBManager;
import com.example.db.SQLTransporter;
import com.example.entity.User;

@RequestMapping("/DB")
@RestController
public class DB_RESTService {
	SQLTransporter sqlHandler = new SQLTransporter();
	@GetMapping("/3")
	public ArrayList<User> getUserByidnumber2(Model model) throws SQLException, URISyntaxException {
		ArrayList<User> output = new ArrayList<User>();
		Connection conn = DBManager.getConnection();
		output = DBManager.selectQuery(conn, "SELECT * FROM ppl;");
		return output;
	}
//	@GET
//	@Path("/users")
//	@RequestMapping(
//			value = "/users",
//			method = RequestMethod.GET,
//			produces = { "application/json" } )
//	@ResponseBody
////	public List<User> getUsers() throws SQLException, URISyntaxException {
////		String dBName = "labb2";
//////		Processor.createDatabase(dBName);
////		if (sqlHandler.getUsers(dBName)) {
////			return Processor.getUserList();
////		}
////		return null;
//	String DBDeletes(Model model) throws URISyntaxException, SQLException {
//		ArrayList<User> output = new ArrayList<User>();
//		Connection conn = DBManager.getConnection();
//		output = DBManager.selectQuery(conn, "SELECT * FROM ppl");
//		model.addAttribute("users", output);
//
//		return "db";
//	
//}
	@RequestMapping("/DBDeletes/{name}")
	ArrayList<User> DBDeletes(@PathVariable("name") String name) throws URISyntaxException, SQLException {
		ArrayList<User> output = new ArrayList<User>();
		Connection conn = DBManager.getConnection();
		output = DBManager.selectQuery(conn, "SELECT * FROM ppl WHERE Name='" + name + "'");

		return output;
	}

	@GetMapping("/user/{id}")
	@Produces (MediaType.APPLICATION_JSON)
	ArrayList<User> getUserByid(Model model, @PathVariable("id") int id) throws SQLException, URISyntaxException {
		ArrayList<User> output = new ArrayList<User>();
		Connection conn = DBManager.getConnection();
		output = DBManager.selectQuery(conn, "SELECT * FROM ppl WHERE id='" + id + "'");
		return output;
	}
	
//	@GET
//	@Path("/users/{idnumber}")
//	@Produces(MediaType.APPLICATION_JSON)
	@GetMapping("/users/id/{id}")
	User getUserByidnumber(Model model, @PathVariable("id") int id) throws SQLException, URISyntaxException {
		if (sqlHandler.getUsers(id)) {
			return Processor.getUserList2();
		}
		return null;
//		ArrayList<User> output = new ArrayList<User>();
//		Connection conn = DBManager.getConnection();
//		output = DBManager.selectQuery(conn, "SELECT * FROM ppl WHERE id='" + idnumber + "'");
//		model.addAttribute("users", output);
//		return "db_plain";
	}
	@GetMapping("/users/idd/{id}")
	JSONObject getUserByidnumber2(Model model, @PathVariable("id") int id) throws SQLException, URISyntaxException {
		if (sqlHandler.getUsers(id)) {
			return Processor.getUserList3();
		}
		return null;
//		ArrayList<User> output = new ArrayList<User>();
//		Connection conn = DBManager.getConnection();
//		output = DBManager.selectQuery(conn, "SELECT * FROM ppl WHERE id='" + idnumber + "'");
//		model.addAttribute("users", output);
//		return "db_plain";
	}
	/**
	 * kunde inte använda mig av denna tjänst för jag fick felmeddelande att denna
	 * och id kolliderade. Vet ej varför.
	 */
//	@GET
//	@Path("/users/{name}")
//	@Produces(MediaType.APPLICATION_XML)
//	public List<User> getUserByName(@PathParam("name") String name) throws SQLException {
//		String dBName = "labb2";
//		if (sqlHandler.getUsers(dBName, name)) {
//			return UserDBManager.getUserList();
//		}
//		return null;
//	}
}