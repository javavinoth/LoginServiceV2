package com.apibucket.loginapi.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apibucket.loginapi.model.Roles;
import com.apibucket.loginapi.model.Users;
import com.apibucket.loginapi.proxy.EmailServiceProxy;
import com.apibucket.loginapi.security.JwtGenerator;
import com.apibucket.loginapi.security.JwtUserTokens;
import com.apibucket.loginapi.security.JwtValidator;
import com.apibucket.loginapi.service.UsersService;
import com.apibucket.loginapi.utils.ListToStringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@Api(value="/user",consumes="application/json",produces="application/json",description="User Management")
public class UserController {
	

	@Autowired
	private HttpServletRequest httpServletRequest;
	
	@Autowired
	private HttpServletResponse httpServletResponse;
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UsersService usersService;

	@Autowired
	private JwtGenerator jwtGenerator;

	@Autowired
	private JwtValidator jwtValidator;
	
	@Autowired
	private EmailServiceProxy emailServiceProxy;

	@ApiOperation(value="To Login the Registered Users")
	@PostMapping("/signin")
	public void signIn() {
		LOG.info("**********/user/signin ********** ");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LOG.info("/signin");
		if (auth.isAuthenticated()) {

			System.out.println("****** auth.isAuthenticated() *******");
			Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
			String role = ListToStringUtil.collectionToString((Collection<GrantedAuthority>) authorities);
			Set<Roles> roles = ListToStringUtil.collectionToSet(authorities);
			Users tokens = new Users();
			// tokens.setDetail(auth.getDetails().toString());
			tokens.setName(auth.getName());
			System.out.println("role: " + role);
			tokens.setRoles(roles);
			httpServletResponse.setHeader("token", jwtGenerator.generate(tokens));
		}
	}
	@ApiOperation(value="To Create the User account")
	@PostMapping("/signup")
	public void signup(@RequestBody Users users) {
		LOG.info("*********** Inside *************");
		LOG.info("Name: "+users.getName());
		LOG.info("PWD :"+users.getPassword());
		if(!isEmpty(users.getEmailId())&&!isEmpty(users.getMobileNo())&&!isEmpty(users.getName())&&!isEmpty(users.getPassword()))
		{
		usersService.create(users.getName(), users.getEmailId(), users.getMobileNo(), users.getPassword(),
				users.getRoles());
		Roles ro=new Roles();
		ro.setRole("USER");
		Set<Roles> roles = new HashSet<>();
		roles.add(ro);
		users.setRoles(roles);
		String token="Bearer "+jwtGenerator.generate(users);
		emailServiceProxy.verifyAccount(token, users.getEmailId());
		return;
//		emailServiceProxy.retriveEmail(token);
		}
		else
		{
			LOG.info("Mandatory parameter is missing");
			return;
		}
	}

/*	@RequestMapping(value = "/userLogin", method = RequestMethod.GET)
	public String showMyLoginPage(ModelMap model) {
		
		return "plain-login";
		
	}*/
	
		
	private boolean isEmpty(String str)
	{
		if(str==null)
			return true;
		else
			return false;
	}
}
