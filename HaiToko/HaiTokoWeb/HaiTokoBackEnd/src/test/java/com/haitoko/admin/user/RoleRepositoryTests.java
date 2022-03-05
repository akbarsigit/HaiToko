package com.haitoko.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.haitoko.share.entity.Role;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {
	@Autowired
	private RoleRepository repo;
	
	@Test
	public void testCreateFirstRole() {
		Role roleAdmin = new Role("admin", "Manage System");
		Role savedRole = repo.save(roleAdmin);
		assertThat(savedRole.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateRestRoles(){
		Role roleEditor = new Role("editor", "manage price, customer, shipping, orders, product, categories, and menus");
		Role roleCourier = new Role("courier" , "manage order status, view orders, and product");
		
		repo.saveAll(List.of(roleEditor, roleCourier));
	} 
	
}
