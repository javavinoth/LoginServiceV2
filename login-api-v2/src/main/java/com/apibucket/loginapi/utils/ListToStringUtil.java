package com.apibucket.loginapi.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import com.apibucket.loginapi.model.Roles;

public class ListToStringUtil {

	public static void main(String[] args) {
		Collection<Roles>role=new ArrayList<>();
		Roles r1=new Roles();
		Roles r2=new Roles();
		Roles r3=new Roles();
		r1.setRole("ADMIN");
		r2.setRole("USER");
		r3.setRole("VISITOR");
		role.add(r1);
		role.add(r3);
		role.add(r2);
		//System.out.println(ListToStringUtil.listToString(role));

	}
	public static String collectionToString(Collection<GrantedAuthority> role)
	{

		String roleStr="";
	if(!role.isEmpty())	
	{
		for(GrantedAuthority r:role)
		{
			roleStr=roleStr+r.getAuthority()+",";
		}
	}
	else
		return null;
	if(roleStr.endsWith(","))
	  {
		System.out.println(",");
		roleStr=roleStr.substring(0,  roleStr.length()-1);
		return roleStr;
	  }
	else
		return roleStr;
	
	}
	public static String setToString(Set<Roles> role)
	{
		String roleStr="";
	if(!role.isEmpty())	
	{
		for(Roles r:role)
		{
			roleStr=roleStr+r.getRole()+",";
		}
	}
	else
		return null;
	if(roleStr.endsWith(","))
	  {
		System.out.println(",");
		roleStr=roleStr.substring(0,  roleStr.length()-1);
		return roleStr;
	  }
	else
		return roleStr;
	}
	public static Set<Roles> stringToList(String role)
	{
		Set<Roles> roleList=new HashSet<>();
		String[]strArray=role.split(",");
		for(String s:strArray)
		{
			Roles roles=new Roles();
			roles.setRole(s);
			roleList.add(roles);
		}
		return roleList;
	}
	public static Set<Roles> collectionToSet(Collection<? extends GrantedAuthority> authorities)
	{
		Set<Roles> roleList=new HashSet<>();
		for(GrantedAuthority s:authorities)
		{
			Roles roles=new Roles();
			roles.setRole(s.getAuthority());
			roleList.add(roles);
		}
		return roleList;
	}
	
}
