package com.apibucket.loginapi.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apibucket.loginapi.LoginApiV2Application;
import com.apibucket.loginapi.model.Roles;
import com.apibucket.loginapi.model.Users;
import com.apibucket.loginapi.repository.UsersJpaRepository;

@Service
public class UsersService {

	@Autowired
	private LoginApiV2Application loginApiV2Application;
	@Autowired
	private UsersJpaRepository usersJpaRepository;
	public void create(String name,String emailId,String mobileNo,String pwd,Set<Roles> role)
	{
		System.out.println("Create");
		Users entity=new Users();
		entity.setName(name);
		entity.setPassword(loginApiV2Application.getBCryptPasswordEncoder().encode(pwd));
		entity.setEmailId(emailId);
		entity.setMobileNo(mobileNo);
//		Roles roles=new Roles();
//		roles.setRole(role);
		entity.setRoles(role);
		usersJpaRepository.save(entity);
	}
	
//	@IsUser
//	@PreAuthorize("hasRole('ADMIN')")
	public Optional<Users> findByName(String userName)
	{
		return usersJpaRepository.findByName(userName);
		
	}
	
	
}
