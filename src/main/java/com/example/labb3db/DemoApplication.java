package com.example.labb3db;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.json.JSONObject;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.db.DBManager;
import com.example.db.MySqlConnection;
import com.example.db.SQLTransporter;
import com.example.entity.User;

@Controller
@SpringBootApplication
public class DemoApplication {
	DB_RESTService db_RESTService = new DB_RESTService();
	
	DBManager dbmanager = new DBManager();
	ArrayList<User> output;

	@RequestMapping("/db")
//	@ResponseBody
	String db(Model model) {
		ArrayList<User> output = new ArrayList<User>();
		try {
			Connection conn = MySqlConnection.getConnection();
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
			output = dbmanager.selectQuery("SELECT * FROM ppl");
			model.addAttribute("users", output);
			return "db";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	@RequestMapping("/users")
	String DBDeletes(Model model) throws URISyntaxException, SQLException {
		output = db_RESTService.getAll();
		model.addAttribute("users", output);
		return "db";
	}

	@RequestMapping("/DBDeletes/{name}")
	String DBDeletes(Model model, @PathVariable("name") String name) throws URISyntaxException, SQLException {
		ArrayList<User> output = new ArrayList<User>();
		Connection conn = MySqlConnection.getConnection();
		output = dbmanager.selectQuery("SELECT * FROM ppl WHERE Name='" + name + "'");
		model.addAttribute("users", output);

		return "db_plain";
	}
	@GET
	@Path("/users/{idnumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUserByidnumber(@PathParam("idnumber") int idnumber) throws SQLException, URISyntaxException {
		String dBName = "labb2";
//		if (sqlHandler.getUsers(dBName, idnumber)) {
//			return Processor.getUserList();
//		}
		return null;
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
