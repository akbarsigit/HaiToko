package com.haitoko.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.haitoko.share.entity.Customer;
import com.haitoko.share.entity.User;

@Controller
public class CustomerController {
	@Autowired private CustomerService service;
	
	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		
		model.addAttribute("pageTitle", "Customer Registration");
		model.addAttribute("customer", new Customer());
		
		return "register_form";
	}
	
	@PostMapping("/create_customer")
	public String createCustomer(Customer customer, Model model) {
		service.registerCustomer(customer);
		
		model.addAttribute("pageTitle", "Registration Succeeded!");
		
		return "/register_success";
	}
	
}
