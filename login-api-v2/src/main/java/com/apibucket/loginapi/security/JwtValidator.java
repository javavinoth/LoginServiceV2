package com.apibucket.loginapi.security;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {

	private String secret = "apibucket";

	public JwtUserTokens validate(String token) {

		JwtUserTokens users = null;
		
			Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

			users = new JwtUserTokens();

			users.setName(body.getSubject());
//			users.setDetail((String) body.get("detail"));
			users.setRole((String) body.get("role"));
//			users.setJwtId(body.getId());
		

		return users;
	}
}
