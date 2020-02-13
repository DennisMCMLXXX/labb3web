package com.example.labb3db;

import java.net.URISyntaxException;
import java.sql.SQLException;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;

@RequestMapping("/dbdb")
@RestController
public class Test {
	Processor processor = new Processor();
	
	@GetMapping("/3")
//	@Produces(MediaType.APPLICATION_JSON)
	public User getUserByidnumber2() throws SQLException, URISyntaxException {
		if (processor.getUsers0()) {
//			return Processor.getUserList();
			return Processor.getUserList2();
		}
//		JSONObject obj = new JSONObject(); 
//		
//		String name = "name";
//		String profession = "profession";
//		obj.put(name, "Dennis");
//		obj.put(profession, "profession");
		
		return null;
	}
}
