package com.haitoko.admin.user;

import org.springframework.data.repository.CrudRepository;

import com.haitoko.share.entity.User;

public interface UserRepository extends CrudRepository<User, Integer>{

}
