package com.haitoko.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.haitoko.share.entity.Role;
import com.haitoko.share.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateUserOneRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User user1 = new User("akbarsigit@gmail.com", "akbar12", "Akbar Sigit");
		user1.addRole(roleAdmin);
		
		User saved = repo.save(user1);
		assertThat(saved.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateUserTwoRole() {
		User user2 = new User("user2@gmail.com", "user123", "User Lengkap");
		
		Role editRole = new Role(2);
		Role adminRole = new Role(1);
		
		user2.addRole(editRole);
		user2.addRole(adminRole);
		
		User saved2 = repo.save(user2);
		assertThat(saved2.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testListAllUser() {
		Iterable<User> listUsers = repo.findAll();
		
		listUsers.forEach(user -> System.out.println(user));
	}
	
	@Test
	public void testGetUserById() {
		User user1 = repo.findById(1).get();
		System.out.print(user1);
		assertThat(user1).isNotNull();
	}
	
	@Test
	public void tesUpdateUserDetails() {
		User user1 = repo.findById(1).get();
		user1.setStatus(true);
		user1.setEmail("updateemal@gmail.com");
		
		repo.save(user1);
	}
	
	@Test
	public void testUpdateUserRoles() {
		User user1 = repo.findById(2).get();
		Role roleEditor = new Role(2);
		Role roleCourier = new Role(3);
		
		user1.getRoles().remove(roleEditor); 
		user1.addRole(roleCourier);
	}
	
	@Test
	public void testDeleteUser() {
		Integer userid = 2;
		repo.deleteById(userid);
	}
	
	
	
	
}
