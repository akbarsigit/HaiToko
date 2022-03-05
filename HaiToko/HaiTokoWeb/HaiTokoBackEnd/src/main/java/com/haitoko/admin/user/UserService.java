package com.haitoko.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haitoko.share.entity.Role;
import com.haitoko.share.entity.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;
	
	public List<User> listAll(){
		return (List<User>) userRepo.findAll();
	}
	
	public List<Role> listRoles(){
		return (List<Role>) roleRepo.findAll();
	}

	public void save(User myUser) {
		userRepo.save(myUser);
	}
	
}
