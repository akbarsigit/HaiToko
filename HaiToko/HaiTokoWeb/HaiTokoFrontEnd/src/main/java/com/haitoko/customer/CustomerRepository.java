package com.haitoko.customer;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.haitoko.share.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

	@Query("SELECT c FROM Customer c WHERE c.email = ?1")
	public Customer findByEmail(String email);
	
	@Query("UPDATE Customer c SET c.status = true WHERE c.id = ?1")
	@Modifying
	public void status(Integer id);	
}

