package com.apibucket.loginapi.security;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.apibucket.loginapi.model.Users;
import com.apibucket.loginapi.utils.ListToStringUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {

	/*public String generate(JwtUserTokens users) {

		Claims claims = Jwts.claims().setSubject(users.getName());

		claims.put("role", users.getRole());
		claims.put("detail", users.getDetail());
		claims.setExpiration(new Date());
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, "apibucket").compact();
	}*/
	public String generate(Users users) {
		
		long ttlMillis=86400000;
		long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);
	    long expMillis = nowMillis + ttlMillis;
        Date exp = new Date(expMillis);
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        System.out.println(sdf.format(exp));
		Claims claims=Jwts.claims().setSubject(users.getName());
		String rolestr = ListToStringUtil.setToString(users.getRoles());
		claims.put("role", rolestr);
		claims.put("creationDate", LocalDateTime.now());
		claims.put("expirationDate", exp);
		
		
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, "apibucket").
				setExpiration(exp).
				compact();
	}
}
