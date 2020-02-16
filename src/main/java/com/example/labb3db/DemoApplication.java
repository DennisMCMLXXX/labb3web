package com.example.labb3db;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.db.DBManager;
import com.example.db.MySqlConnection;
import com.example.entity.User;

@Controller
@SpringBootApplication
public class DemoApplication {
	DB_RESTService db_RESTService = new DB_RESTService();

	DBManager dbmanager = new DBManager();
	ArrayList<User> output;

	@RequestMapping("/")
	@ResponseBody
	ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}

	@RequestMapping("/db")
//	@ResponseBody
	String db(Model model) {
		ArrayList<User> output = new ArrayList<User>();
		try {
			Connection conn = MySqlConnection.getConnection();
			dbmanager.updateQuery(conn, "DROP TABLE ppl");
			dbmanager.updateQuery(conn,
					"CREATE TABLE IF NOT EXISTS ppl (id SERIAL PRIMARY KEY, name TEXT NOT NULL, profession TEXT NOT NULL)");
			dbmanager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Dennis', 'Student')");
			dbmanager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Dennis', 'Trainee')");
			dbmanager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Dennis', 'Systemutvecklare')");
			dbmanager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Bengt', 'IT-Aarkitekt');");
			dbmanager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Calle', 'Systemutvecklare');");
			dbmanager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Mona', 'Testare');");
			dbmanager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Frida', 'Krav-analytiker');");
			dbmanager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Solveig', 'Teamledare');");
			dbmanager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Mikael', 'Enhetschef');");
			dbmanager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Christina', 'General direktör');");
			dbmanager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Jan', 'Överste direktör');");
			dbmanager.updateQuery(conn,
					"INSERT INTO ppl(name, profession) VALUES('Liselotte', 'Systemutvecklare / handledare');");
			dbmanager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Kent', 'Linux tekniker');");
			output = dbmanager.selectQuery("SELECT * FROM ppl");
			model.addAttribute("users", output);
			return "db";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@RequestMapping("/all")
	public ArrayList<User> all(Model model) throws SQLException, URISyntaxException {
		return output = db_RESTService.getAll();
//		model.addAttribute("users", output);
//		return "db";
	}
	

	

//
//	@RequestMapping("/post")
//	public ArrayList<User> DBAddSubmit(@ModelAttribute DBManager search) throws IOException, SQLException, URISyntaxException {
////		System.out.println(search.getJson());
//		db_restservice.getAll();
//		return db_restservice.getAll();
//	}

	@GetMapping("/show")
	public String DBShow(Model model, @ModelAttribute DBManager search) {
		model.addAttribute("WebManager", new WebManager());
//		System.out.println(search.);
		return "search_form";
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


	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}
}
