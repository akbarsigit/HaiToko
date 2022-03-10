package com.haitoko.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.haitoko.security.CustomerUserDetails;
import com.haitoko.share.entity.Customer;
import com.haitoko.share.entity.User;

@Controller
public class CustomerController {
	@Autowired private CustomerService customerService;
	
	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		
		model.addAttribute("pageTitle", "Customer Registration");
		model.addAttribute("customer", new Customer());
		
		return "register_form";
	}
	
	@PostMapping("/create_customer")
	public String createCustomer(Customer customer, Model model) {
		customerService.registerCustomer(customer);
		
		model.addAttribute("pageTitle", "Registration Succeeded!");
		
		return "/register_success";
	}
	
	@GetMapping("/account_details")
	public String viewAccountDetails(@AuthenticationPrincipal CustomerUserDetails loggedUser, Model model) {
		
		String email = loggedUser.getUsername();
		
		Customer customer = customerService.getByEmail(email);
		
		model.addAttribute("pageTitle", "Account Details");
		model.addAttribute("customer", customer);
		
		return "/account_form";
	}
	
	@PostMapping("/update_account_details")
	public String updateAccountDetails(Model model, Customer customer, RedirectAttributes ra, @AuthenticationPrincipal CustomerUserDetails loggedUser) {
		customerService.update(customer);
		ra.addFlashAttribute("message", "Your account details have been updated.");
		
		loggedUser.setName(customer.getName());
		
		return "redirect:/account_details";
	}

	
	
	
}
