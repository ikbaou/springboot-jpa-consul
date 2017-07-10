package com.example.demo.domain;

public class Message {
	
	public enum Level {
		SUCCESS,
		ERROR,
		INFO,
		WARN
	}	
	
	private Level type;
	private String key;
	private Object args[];
	
	public Message(String key) {
		this(key, new Object[]{});
	}
	
	public Message(String key, Object... args) {
		this(key, Level.SUCCESS, args);
	}
	
	public Message(String key, Level type, Object... args) {
		super();
		this.type = type;
		this.key = key;
		this.args = args;
	}
	
	public Level getType() {
		return type;
	}
	
	public void setType(Level type) {
		this.type = type;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public Object[] getArgs() {
		return args;
	}
	
	public void setArgs(Object[] args) {
		this.args = args;
	}
}
