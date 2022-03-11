package com.haitoko.admin.customer;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.haitoko.share.entity.Customer;
import com.haitoko.share.entity.User;
import com.haitoko.share.exception.CustomerNotFoundException;

@Service
@Transactional
public class CustomerService {
	
	@Autowired private CustomerRepository customerRepo;
	@Autowired private PasswordEncoder passwordEncoder;
	
	
	public List<Customer> listAll(){
		return (List<Customer>) customerRepo.findAll();
	}
	
	
	public void updateCustomerEnabledStatus(Integer id, boolean status) {
		customerRepo.updateEnabledStatus(id, status);
	}
	
	public Customer get(Integer id) throws CustomerNotFoundException {
		try {
			return customerRepo.findById(id).get();
		} catch (NoSuchElementException ex) {
			throw new CustomerNotFoundException("Could not find any customers");
		}
	}
	
	public boolean isEmailUnique(Integer id, String email) {
		Customer existCustomer = customerRepo.findByEmail(email);

		if (existCustomer != null && existCustomer.getId() != id) {
			return false;
		}
		
		return true;
	}
	
	public void save(Customer customerForm) {
		Customer customerDB = customerRepo.findById(customerForm.getId()).get();
		if (!customerForm.getPassword().isEmpty()) {
			String encodedPassword = passwordEncoder.encode(customerForm.getPassword());
			customerForm.setPassword(encodedPassword);			
		} else {
			customerForm.setPassword(customerDB.getPassword());
		}		
		
		customerForm.setStatus(customerDB.isStatus());
		customerForm.setCreatedTime(customerDB.getCreatedTime());
		
		customerRepo.save(customerForm);
	}
	
	public void delete(Integer id) throws CustomerNotFoundException {
		Long count = customerRepo.countById(id);
		if (count == null || count == 0) {
			throw new CustomerNotFoundException("Could not find any customers with ID " + id);
		}
		
		customerRepo.deleteById(id);
	}
	
}
