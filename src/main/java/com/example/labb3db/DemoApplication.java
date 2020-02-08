package com.example.labb3db;

import java.sql.Connection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
public class DemoApplication {
	@RequestMapping("/db")
    @ResponseBody
    String connect() {
        try {
            Connection conn = DBManager.getConnection();
            return "Success!";
        } catch (Exception e) {return e.getMessage();}
    }

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

}
