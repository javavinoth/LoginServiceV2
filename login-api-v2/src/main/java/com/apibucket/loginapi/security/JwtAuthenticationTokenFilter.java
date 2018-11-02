package com.apibucket.loginapi.security;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.apibucket.loginapi.model.JwtAuthenticationToken;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    public JwtAuthenticationTokenFilter() {
    	super("/**");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {/*

        String header = httpServletRequest.getHeader("Authorisation");


        if (header == null || !header.startsWith("Token ")) {
            throw new RuntimeException("JWT Token is missing");
        }

        String authenticationToken = header.substring(6);

        JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);
        return getAuthenticationManager().authenticate(token);
    */
    	System.out.println("Security req.getRequestURI()-->" + req.getRequestURI());
    	Enumeration<String> headerNames = req.getHeaderNames();
    	if(headerNames.hasMoreElements())
		{
			System.out.println("********** Headers is Present ***********");
//			String str="";
			while (headerNames.hasMoreElements()) {
//				System.out.println("******Inside while *******");
				String string = (String) headerNames.nextElement();
				System.out.println(string);
				if (string.contains("authorization")) {
					System.out.println("LoginService--> " + string);
					System.out.println("LoginService--> " + req.getHeader(string));
//					str=req.getHeader(string);
				}
			}
		}
		else
		{
			System.out.println("********** Headers is not Present ***********");
		}
		
		String str="";
		while (headerNames.hasMoreElements()) {
			String string = (String) headerNames.nextElement();
			if (string.contains("authorization")) {
				System.out.println("LoginService--> " + string);
				System.out.println("LoginService--> " + req.getHeader(string));
				str=req.getHeader(string);
			}
		}
		JwtAuthenticationToken token = new JwtAuthenticationToken(str);
		return getAuthenticationManager().authenticate(token);
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
