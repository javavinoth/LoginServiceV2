package com.apibucket.loginapi.security;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtUserTokens {
	private String name;
	private String role;
	private Date creationDate;
	private Date expirationDate;
}
