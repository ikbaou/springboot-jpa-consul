package com.example.demo.domain;

import java.io.Serializable;

public class EventMessage implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String name;
	
	public EventMessage(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "EventMessage [name=" + name + "]";
	}		

}
