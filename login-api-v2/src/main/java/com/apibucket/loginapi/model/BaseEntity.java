package com.apibucket.loginapi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(unique = true, nullable = false, updatable = false)
	@Setter(AccessLevel.NONE)
	@ApiModelProperty(hidden = true)
	private long id;

	@Column(name = "creation_date", updatable = false, nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Setter(AccessLevel.NONE)
	@ApiModelProperty(hidden = true)
	private Date creationDate;

	@Column(name = "Last_Modified_Date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	@Setter(AccessLevel.NONE)
	@ApiModelProperty(hidden = true)
	private Date rod;

}
