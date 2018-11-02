package com.apibucket.loginapi.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.apibucket.loginapi.model.Users;

public class CustomUserDetails extends Users implements UserDetails {

    public CustomUserDetails(final Users users) {
        super(users);
    }
  /*  public CustomUserDetails(String userName, long id, String token, List<GrantedAuthority> grantedAuthorities) {

        super. = userName;
        this.id = id;
        this.token= token;
        this.authorities = grantedAuthorities;
    }*/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
    	System.out.println("super.getPassword() : "+super.getPassword());
        return super.getPassword();
    }

    @Override
    public String getUsername() {
    	System.out.println("super.getName() :"+super.getName());
        return super.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
