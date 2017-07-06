package com.example.demo.domain;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * this class serves as a Domain of the Customer Entity
 * 
 * @author ibaou
 *
 */
public class Customer {

	private String firstname;
	private String lastname;
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
