package com.example.db;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.labb3db.User;

@Path("/UserService")

public class UserService {
	SQLTransporter sqlHandler = new SQLTransporter();

	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() throws SQLException, URISyntaxException {
		String dBName = "labb2";
//		Processor.createDatabase(dBName);
		if (sqlHandler.getUsers(dBName)) {
			return Processor.getUserList();
		}
		return null;
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