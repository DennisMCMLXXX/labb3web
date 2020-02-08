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
	@RequestMapping("/filewrite")
    @ResponseBody
    String filewrite() {
        int nbr = 0;
        try {
            File file = new File("counter.txt");
            if (!file.exists()) {
                file.createNewFile();}
            else {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String oldNbr = br.readLine();
                nbr = Integer.parseInt(oldNbr);
                br.close();}
            nbr++;
            String newNbr = "" + nbr;
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(newNbr);
            bw.close();
        } catch (IOException e) {return e.getMessage();}
        return "Denna sida har laddats " + nbr + " gånger.";
    }

}
