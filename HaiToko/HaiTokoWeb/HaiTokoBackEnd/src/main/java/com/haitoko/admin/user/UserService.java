package com.haitoko.admin.user;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.haitoko.share.entity.Role;
import com.haitoko.share.entity.User;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User getByEmail(String email) {
		return userRepo.getUserByEmail(email);
	}
	
	public List<User> listAll(){
		return (List<User>) userRepo.findAll();
	}
	
	public List<Role> listRoles(){
		return (List<Role>) roleRepo.findAll();
	}

	public void save(User myUser) {
		boolean isEdit = (myUser.getId() != null);
		
		if(isEdit) {
			User existUser = userRepo.findById(myUser.getId()).get();
			
			if(myUser.getPassword().isEmpty()) {
				myUser.setPassword(existUser.getPassword());
			}else {
				encodePass(myUser);
			}
			
		}else {
			encodePass(myUser);
		}
		
		userRepo.save(myUser);
	}
	
	public User updateAcc(User userForm) {
		User userDB = userRepo.findById(userForm.getId()).get();
		
		if(!userForm.getPassword().isEmpty()) {
			userDB.setPassword(userForm.getPassword());
			encodePass(userDB);
		}
		
		userDB.setName(userForm.getName());
		
		return userRepo.save(userDB);
	}
	
	
	private void encodePass(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);	
	}
	
	public boolean isEmailUnique(Integer id, String email) {
		User userEmail = userRepo.getUserByEmail(email);
		
		if(userEmail == null) return true;
		
		boolean isNewUser = (id == null);
		if(isNewUser) {
			if(userEmail != null) return false;
		}else {
			if(userEmail.getId() != id) {
				return false;
			}
		}
		
		return true;
	}
	
	public User get(Integer id) throws UserNotFoundException{
		try {
		return userRepo.findById(id).get();
		}catch(NoSuchElementException ex) {
			throw new UserNotFoundException("Cant find any user");
		}
	}
		
	public void delete(Integer id) throws UserNotFoundException {
		Long countById = userRepo.countById(id);
		if(countById == null || countById == 0) {
			throw new UserNotFoundException("Cant find any user");
		}
		
		userRepo.deleteById(id);
	}
	
	public void updateUserStatus(Integer id, boolean status) {
		userRepo.updateStatus(id, status);
	}
	
	
}
