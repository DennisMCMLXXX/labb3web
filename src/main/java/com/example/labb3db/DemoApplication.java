package com.example.labb3db;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.db.Processor;
import com.example.db.SQLTransporter;

@Controller
@SpringBootApplication
public class DemoApplication {
	SQLTransporter sqlHandler = new SQLTransporter();

	@RequestMapping("/db")
//	@ResponseBody
	String db(Model model) {
		ArrayList<User> output = new ArrayList<User>();
		try {
			Connection conn = DBManager.getConnection();
//			DBManager.updateQuery(conn,
//					"CREATE TABLE IF NOT EXISTS ppl (id SERIAL PRIMARY KEY, name TEXT NOT NULL, profession TEXT NOT NULL)");
//			DBManager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Dennis', 'Student')");
//			DBManager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Dennis', 'Trainee')");
//			DBManager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Dennis', 'Systemutvecklare')");
//			DBManager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Bengt', 'IT-Aarkitekt');");
//			DBManager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Calle', 'Systemutvecklare');");
//			DBManager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Mona', 'Testare');");
//			DBManager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Frida', 'Krav-analytiker');");
//			DBManager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Solveig', 'Teamledare');");
//			DBManager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Mikael', 'Enhetschef');");
//			DBManager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Christina', 'General direktör');");
//			DBManager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Jan', 'Överste direktör');");
//			DBManager.updateQuery(conn,
//					"INSERT INTO ppl(name, profession) VALUES('Liselotte', 'Systemutvecklare / handledare');");
//			DBManager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Kent', 'Linux tekniker');");
//			String output = DBManager.selectQuery(conn, "SELECT * FROM ppl");
			output = DBManager.selectQuery(conn, "SELECT * FROM ppl");
//			output = DBManager.selectQuery(conn, "SELECT * FROM ppl");

//			DBManager.updateQuery(conn, "drop table ppl"); // ta bort alla rader
			model.addAttribute("users", output);
			return "db";

//			return output;

		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	@GetMapping("/test/{name}")
	public Response test(@PathParam("name") String name) throws SQLException, URISyntaxException {
		ArrayList<User> output = new ArrayList<User>();
		Connection conn = DBManager.getConnection();
//		output = DBManager.selectQuery(conn, "SELECT * FROM ppl WHERE Name='" + name + "'");
		output = DBManager.selectQuery(conn, "SELECT * FROM ppl");

	  
		ResponseBuilder builder = Response.ok(output);
	    
	    return builder.build();
	}
	@RequestMapping("/users")
//	@ResponseBody
//	public List<User> getUsers() throws SQLException, URISyntaxException {
//		String dBName = "labb2";
////		Processor.createDatabase(dBName);
//		if (sqlHandler.getUsers(dBName)) {
//			return Processor.getUserList();
//		}
//		return null;
	String DBDeletes(Model model) throws URISyntaxException, SQLException {
		ArrayList<User> output = new ArrayList<User>();
		Connection conn = DBManager.getConnection();
		output = DBManager.selectQuery(conn, "SELECT * FROM ppl");
		model.addAttribute("users", output);

		return "db";
	}

	@RequestMapping("/DBDeletes/{name}")
	String DBDeletes(Model model, @PathVariable("name") String name) throws URISyntaxException, SQLException {
		ArrayList<User> output = new ArrayList<User>();
		Connection conn = DBManager.getConnection();
		output = DBManager.selectQuery(conn, "SELECT * FROM ppl WHERE Name='" + name + "'");
		model.addAttribute("users", output);

		return "db_plain";
	}

	@GetMapping("/DBDelete")
	String DBDelete(Model model) throws URISyntaxException, SQLException {
		model.addAttribute("DBDelete", new DBManager());
		return "delete_form";
	}

	@PostMapping("/DBDelete")
	public String DBDeleteSubmit(@ModelAttribute DBManager search)
			throws IOException, URISyntaxException, SQLException {
		System.out.println(search.getDBDelete());
		return "db";
	}

	@GetMapping("/DBAdd")
	String alldb(Model model) throws URISyntaxException, SQLException {
		model.addAttribute("DBAdd", new DBManager());
		return "add_form";
	}

	@PostMapping("/DBAdd")
	public String DBAddSubmit(@ModelAttribute DBManager search) throws IOException {
		System.out.println(search.getJson());
		return "db";
	}

	@GetMapping("/DBSearch")
	public String DBSearch(Model model) {
		model.addAttribute("DBSearch", new DBManager());
		return "search_form";
	}

	@PostMapping("/DBSearch")
	public String DBSearchSubmit(@ModelAttribute DBManager search) throws IOException {
		System.out.println(search.getJson());
		return "db";
	}

	@RequestMapping("/")
	@ResponseBody
	ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}
}
