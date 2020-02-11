package com.example.db;

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

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.labb3db.DBManager;
import com.example.labb3db.User;

@Path("/DB")
@Controller
@SpringBootApplication
//@RequestMapping("/DB")
public class UserService {
	SQLTransporter sqlHandler = new SQLTransporter();

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
	String DBDeletes(Model model, @PathVariable("name") String name) throws URISyntaxException, SQLException {
		ArrayList<User> output = new ArrayList<User>();
		Connection conn = DBManager.getConnection();
		output = DBManager.selectQuery(conn, "SELECT * FROM ppl WHERE Name='" + name + "'");
		model.addAttribute("users", output);

		return "db_plain";
	}

	@GET
	@Path("/users/{idnumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUserByidnumber(@PathParam("idnumber") int idnumber) throws SQLException, URISyntaxException {
		String dBName = "labb2";
		if (sqlHandler.getUsers(dBName, idnumber)) {
			return Processor.getUserList();
		}
		return null;
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