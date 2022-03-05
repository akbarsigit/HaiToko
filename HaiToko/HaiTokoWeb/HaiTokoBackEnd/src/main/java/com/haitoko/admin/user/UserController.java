package com.haitoko.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.haitoko.share.entity.Role;
import com.haitoko.share.entity.User;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/users")
	public String listAll(Model model) {
		List<User> myUsersList = service.listAll();
		model.addAttribute("myUsersList", myUsersList);
		return "users";
	}
	
	@GetMapping("/users/create")
	public String createUser(Model model) {
		List<Role> myRolesList = service.listRoles();
		
		User myUser = new User();
		myUser.setStatus(true);
		model.addAttribute("user", myUser);
		model.addAttribute("rolesList", myRolesList);
		
		return "form_user";
	}
	
	
	@PostMapping("/users/save")
	public String saveUser(User myUser, RedirectAttributes redirectAttributes) {
		System.out.println(myUser);
		service.save(myUser);
		
		redirectAttributes.addFlashAttribute("message", "The user created successfully!");
		
		return "redirect:/users";
	}
	
}
