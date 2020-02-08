package com.example.labb3db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SpringBootApplication
public class DemoApplication {
	@RequestMapping("/db")
	@ResponseBody
	String connect() {
		try {
			Connection conn = DBManager.getConnection();
			DBManager.updateQuery(conn,
					"CREATE TABLE IF NOT EXISTS ppl (id SERIAL, name varchar(45) NOT NULL, profession varchar(45) NOT NULL, PRIMARY KEY (id) )");
			DBManager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Dennis','Student');");
			DBManager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Dennis','Trainee');");
			DBManager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Dennis','Systemutvecklare');");
			DBManager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Bengt','IT-Aarkitekt');");
			DBManager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Calle','Systemutvecklare');");
			DBManager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Mona','Testare');");
			DBManager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Frida','Krav-analytiker');");
			DBManager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Solveig','Teamledare');");
			DBManager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Mikael','Enhetschef');");
			DBManager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Christina','General direktör');");
			DBManager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Jan','Överste direktör');");
			DBManager.updateQuery(conn,
					"INSERT INTO ppl(name, profession) VALUES('Liselotte','Systemutvecklare / handledare');");
			DBManager.updateQuery(conn, "INSERT INTO ppl(name, profession) VALUES('Kent','Linux tekniker');");

			String output = DBManager.selectQuery(conn, "SELECT * FROM ppl");
			return output;

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
