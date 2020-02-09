package com.example.labb3db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SpringBootApplication
public class DemoApplication {
	@RequestMapping("/db")
	@ResponseBody
	String db(Model model) {
		ArrayList<User> output = new ArrayList<User>();
		try {
			Connection conn = DBManager.getConnection();
			DBManager.updateQuery(conn,
					"CREATE TABLE IF NOT EXISTS ppl (id INTEGER PRIMARY KEY, name TEXT, profession TEXT)");
			DBManager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES(DEFAULT, 'Dennis', 'Student')");
//			DBManager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Dennis', 'Trainee')");
//			DBManager.updateQuery(conn,
//					"INSERT INTO ppl(name, profession) VALUES('Dennis', 'Systemutvecklare')");
//			DBManager.updateQuery(conn, "INSERT INTO ppl(id, name, profession) VALUES('1', 'Bengt', 'IT-Aarkitekt');");
//			DBManager.updateQuery(conn, "INSERT INTO ppl(id, name, profession) VALUES('1', 'Calle', 'Systemutvecklare');");
//			DBManager.updateQuery(conn, "INSERT INTO ppl(id, name, profession) VALUES('1', 'Mona', 'Testare');");
//			DBManager.updateQuery(conn, "INSERT INTO ppl(id, name, profession) VALUES('1', 'Frida', 'Krav-analytiker');");
//			DBManager.updateQuery(conn, "INSERT INTO ppl(id, name, profession) VALUES('1', 'Solveig', 'Teamledare');");
//			DBManager.updateQuery(conn, "INSERT INTO ppl(id, name, profession) VALUES('1', 'Mikael', 'Enhetschef');");
//			DBManager.updateQuery(conn, "INSERT INTO ppl(id, name, profession) VALUES('1', 'Christina', 'General direktör');");
//			DBManager.updateQuery(conn, "INSERT INTO ppl(id, name, profession) VALUES('1', 'Jan', 'Överste direktör');");
//			DBManager.updateQuery(conn,
//					"INSERT INTO ppl(id, name, profession) VALUES('Liselotte', 'Systemutvecklare / handledare');");
//			DBManager.updateQuery(conn, "INSERT INTO ppl(id, name, profession) VALUES('1', 'Kent', 'Linux tekniker');");
//			String output = DBManager.selectQuery(conn, "SELECT * FROM ppl");

			output = DBManager.selectQuery(conn, "SELECT * FROM ppl");
			DBManager.updateQuery(conn, "DELETE FROM ppl"); // ta bort alla rader
			model.addAttribute("user", output);
			return "db";

//			return output;

		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@RequestMapping("/")
	@ResponseBody
	ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		System.out.println("Skriver till konsol...");
		return modelAndView;
	}

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}
}
