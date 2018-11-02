package com.apibucket.loginapi.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springfox.documentation.annotations.ApiIgnore;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({ "id", "creationDate", "rod" })
@ApiModel(value="user",description="Information about users")
public class Users extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(notes="user name")
	private String name;
	@Email
	@ApiModelProperty(notes="user emailId")
	private String emailId;
	@Column(length = 15)
	@ApiModelProperty(notes="mobile number")
	private String mobileNo;
	@ApiModelProperty(notes="password")
	private String password;
	
	@ApiModelProperty(hidden=true)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Roles> roles;

	public Users(Users users) {
		this.emailId = users.getEmailId();
		this.mobileNo = users.getMobileNo();
		this.name = users.getName();
		this.password = users.getPassword();
		this.roles = users.getRoles();
	}

	public void addRoles(Roles role) {
		if (roles == null) {
			roles = new HashSet<>();
		}
		role.users = this;
		roles.add(role);
	}
}
