package com.haitoko.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		model.addAttribute("title", "Create User");
		
		return "form_user";
	}
	
	
	@PostMapping("/users/save")
	public String saveUser(User myUser, RedirectAttributes redirectAttributes) {
		System.out.println(myUser);
		service.save(myUser);
		redirectAttributes.addFlashAttribute("message", "The user has been successfully saved!");
		
		return "redirect:/users";
	}
	
	@GetMapping("/users/edit/{id}")
	public String editUser(@PathVariable(name="id") Integer id, RedirectAttributes redirectAttributes, Model model) {
		try {
			User user = service.get(id);
			List<Role> myRolesList = service.listRoles();
			model.addAttribute("rolesList", myRolesList);
			model.addAttribute("user", user);
			model.addAttribute("title", "Edit User");
			return "form_user";
		}catch(UserNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			return "redirect:/users";
		}
	}
	
	@GetMapping("/users/delete/{id}")
	public String deleteUser(@PathVariable(name="id") Integer id, RedirectAttributes redirectAttributes, Model model) {
		try {
			service.delete(id);
			redirectAttributes.addFlashAttribute("message", "User Deleted!");
		}catch(UserNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			
		}
		return "redirect:/users";
	}
	
}
