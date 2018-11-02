/*package com.apibucket.loginapi.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

public class LogFilter extends AbstractAuthenticationProcessingFilter {

	protected LogFilter() {
		super("/**");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		System.out.println("Filter req.getRequestURI()-->" + req.getRequestURI());
		Enumeration<String> headerNames = req.getHeaderNames();
		if(headerNames.hasMoreElements())
		{
			System.out.println("********** Headers is Present ***********");
			while (headerNames.hasMoreElements()) {
				String string = (String) headerNames.nextElement();
				if (string.contains("authorization")) {
					System.out.println("LoginService--> " + string);
					System.out.println("LoginService--> " + req.getHeader(string));
				}
			}
		}
		else
		{
			System.out.println("********** Headers is not Present ***********");
		}
		while (headerNames.hasMoreElements()) {
			String string = (String) headerNames.nextElement();
			if (string.contains("authorization")) {
				System.out.println("LoginService--> " + string);
				System.out.println("LoginService--> " + req.getHeader(string));
			}
		}
		return null;
	}

}
*/