package com.haitoko.admin.customer;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.haitoko.admin.customer.CustomerCsvExport;
import com.haitoko.share.entity.Customer;
import com.haitoko.share.entity.User;
import com.haitoko.share.exception.CustomerNotFoundException;

@Controller
public class CustomerController {

	@Autowired private CustomerService service;
	
	@GetMapping("/customers")
	public String listCustomer(Model model) {
		List<Customer> listCustomers = service.listAll();
		model.addAttribute("listCustomers", listCustomers);
		return "customers/customers";
	}
	
	@GetMapping("/customers/{id}/status/{status}")
	public String updateCustomerEnabledStatus(@PathVariable("id") Integer id,
			@PathVariable("status") boolean status, RedirectAttributes redirectAttributes) {
		service.updateCustomerEnabledStatus(id, status);
		String message = "The Customer Id "+ id +" status has been updated";
		redirectAttributes.addFlashAttribute("message", message);
		
		return "redirect:/customers";
	}	
	
	@GetMapping("/customers/detail/{id}")
	public String viewCustomer(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
		try {
			Customer customer = service.get(id);
			model.addAttribute("customer", customer);
			
			return "customers/customer_detail_modal";
		} catch (CustomerNotFoundException ex) {
			ra.addFlashAttribute("message", ex.getMessage());
			return "redirect:/customers";			
		}
	}
	
	@GetMapping("/customers/edit/{id}")
	public String editCustomer(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
		try {
			Customer customer = service.get(id);
			
			model.addAttribute("customer", customer);
			model.addAttribute("pageTitle", String.format("Edit Customer"));
			
			return "customers/customer_form";
			
		} catch (CustomerNotFoundException ex) {
			ra.addFlashAttribute("message", ex.getMessage());
			return "redirect:/customers";
		}
	}
	
	@PostMapping("/customers/save")
	public String saveCustomer(Customer customer, Model model, RedirectAttributes ra) {
		service.save(customer);
		ra.addFlashAttribute("message", "The customer has been updated successfully.");
		return "redirect:/customers";
	}

	@GetMapping("/customers/delete/{id}")
	public String deleteCustomer(@PathVariable Integer id, RedirectAttributes ra) {
		try {
			service.delete(id);			
			ra.addFlashAttribute("message", "The customer ID " + id + " has been deleted successfully.");
			
		} catch (CustomerNotFoundException ex) {
			ra.addFlashAttribute("message", ex.getMessage());
		}
		
		return "redirect:/customers";
	}
	
	@GetMapping("/customers/csv")
	public void exportCSV(HttpServletResponse response) throws IOException {
		List<Customer> listCustomers = service.listAll();
		CustomerCsvExport exporCustomer = new CustomerCsvExport();
		exporCustomer.exportCustomer(listCustomers, response);
	}
	
}
