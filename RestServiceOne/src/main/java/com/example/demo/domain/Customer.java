package com.example.demo.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.codec.digest.DigestUtils;

import com.example.demo.domain.validation.groups.ModifyAccountGroup;
import com.example.demo.domain.validation.groups.ModifyCustomerGroup;

/**
 * this class serves as a Domain of the Customer Entity
 * 
 * @author ibaou
 *
 */
public class Customer {

	@NotNull
	@Size(min = 2, max = 255)
	private String firstname;
    
	@NotNull
	@Size(min = 2, max = 255)	
	private String lastname;
	
	@NotNull(groups = { ModifyAccountGroup.class, ModifyCustomerGroup.class })
	private Long id;
	
	public String producePseudonym() {
		return DigestUtils.md5Hex(firstname + lastname);
	}	
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
