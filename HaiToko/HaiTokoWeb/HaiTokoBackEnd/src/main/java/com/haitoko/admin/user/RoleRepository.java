package com.haitoko.admin.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.haitoko.share.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>{

}
