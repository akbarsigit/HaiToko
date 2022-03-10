package com.haitoko.customer;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.haitoko.share.entity.Customer;

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
	
}
