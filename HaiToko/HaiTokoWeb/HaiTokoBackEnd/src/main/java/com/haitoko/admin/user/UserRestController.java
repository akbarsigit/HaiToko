package com.haitoko.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserRestController {
	
	@Autowired
	private UserService service; 
	
	@PostMapping("/users/checkMail")
	public String isDuplicatedEmail(@Param("id") Integer id,  @Param("email") String email) {
		return service.isEmailUnique(id, email) ? "ok" : "duplicated";
		
	}
	
	
}
