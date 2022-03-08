package com.haitoko.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.haitoko.admin.security.HaiTokoUserDetails;
import com.haitoko.share.entity.User;

@Controller
public class AccountController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/account")
	public String viewDetails(@AuthenticationPrincipal HaiTokoUserDetails loggedUser, Model model) {
		String email = loggedUser.getUsername();
		User myuser = service.getByEmail(email);
		
		model.addAttribute("user", myuser);
		return "form_account";
	}
	
	@PostMapping("/account/update")
	public String saveDetails(User myUser, RedirectAttributes redirectAttributes, @AuthenticationPrincipal HaiTokoUserDetails logUser) {
		service.updateAcc(myUser);
		redirectAttributes.addFlashAttribute("message", "Your account have been updated!");
		
		logUser.setName(myUser.getName());
		
		return "redirect:/account";
	}
	
}
