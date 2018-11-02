package com.apibucket.loginapi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apibucket.loginapi.security.JwtUserTokens;
import com.apibucket.loginapi.security.JwtValidator;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping("/user/tkn")
public class TokenController {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JwtValidator jwtValidator;
/*
	@PostMapping("/val/{token}")
	public ResponseEntity<JwtUserTokens> validateToken(@PathVariable("token")String token) {
		LOG.info("******* TokenController /val/{token} *********");
		JwtUserTokens validate = jwtValidator.validate(token);
		if (validate != null) {
			return new ResponseEntity<JwtUserTokens>(validate, HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}*/
	
	@PostMapping("/tknval")
	public void signIn(HttpServletRequest req, HttpServletResponse res) {
		LOG.info("*********** tknval *************");
		String tkn = req.getHeader("tkn");
		try {
			jwtValidator.validate(tkn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.setHeader("tkn", "Invalid-Token");
		}
	}

	@PostMapping("/val/{token}")
	public ResponseEntity<JwtUserTokens> validateToken(@PathVariable("token") String token) {
		JwtUserTokens validate = null;
		try {
			validate = jwtValidator.validate(token);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		if (validate != null) {
			return new ResponseEntity<JwtUserTokens>(validate, HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

}
