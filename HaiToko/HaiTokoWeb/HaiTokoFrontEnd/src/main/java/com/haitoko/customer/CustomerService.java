package com.haitoko.customer;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.haitoko.share.entity.Customer;
import com.haitoko.share.entity.User;

import net.bytebuddy.utility.RandomString;

@Service
public class CustomerService {

	@Autowired private CustomerRepository customerRepo;
	@Autowired PasswordEncoder passwordEncoder;
	
	public boolean isEmailUnique(String email) {
		Customer customer = customerRepo.findByEmail(email);
		return customer == null;
	}
	
	public void registerCustomer(Customer customer) {
		encodePassword(customer);
		customer.setStatus(true);
		customer.setCreatedTime(new Date());
		
		customerRepo.save(customer);
		
	}

	private void encodePassword(Customer customer) {
		String encodedPassword = passwordEncoder.encode(customer.getPassword());
		customer.setPassword(encodedPassword);
	}
	
	
	public Customer getByEmail(String email) {
		return customerRepo.findByEmail(email);
	}
	
	public void update(Customer customerForm) {
		Customer customerDB = customerRepo.findById(customerForm.getId()).get();
		if (!customerForm.getPassword().isEmpty()) {
			String encodedPassword = passwordEncoder.encode(customerForm.getPassword());
			customerForm.setPassword(encodedPassword);			
		} else {
			customerForm.setPassword(customerDB.getPassword());
		}		
		
		customerForm.setStatus(customerDB.isStatus());
		customerForm.setCreatedTime(customerDB.getCreatedTime());
		customerDB.setName(customerForm.getName());
		
		customerRepo.save(customerForm);
	}

	
}
